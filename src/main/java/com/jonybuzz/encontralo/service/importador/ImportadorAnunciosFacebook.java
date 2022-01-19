package com.jonybuzz.encontralo.service.importador;

import com.google.common.base.Enums;
import com.jonybuzz.encontralo.client.facebook.FacebookClient;
import com.jonybuzz.encontralo.client.facebook.dto.PostFbDto;
import com.jonybuzz.encontralo.client.witai.WitAiClient;
import com.jonybuzz.encontralo.client.witai.dto.EntitiesWitDto;
import com.jonybuzz.encontralo.client.witai.dto.TraitsWitDto;
import com.jonybuzz.encontralo.model.*;
import com.jonybuzz.encontralo.model.facebook.AccesoPaginasFacebook;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import com.jonybuzz.encontralo.repository.ColorRepository;
import com.jonybuzz.encontralo.repository.EspecieRepository;
import com.jonybuzz.encontralo.repository.FranjaEtariaRepository;
import com.jonybuzz.encontralo.repository.LocalidadRepository;
import com.jonybuzz.encontralo.repository.PelajeRepository;
import com.jonybuzz.encontralo.repository.RazaRepository;
import com.jonybuzz.encontralo.repository.TamanioRepository;
import com.jonybuzz.encontralo.repository.facebook.AccesoPaginasFacebookRepository;
import com.jonybuzz.encontralo.security.AesEncryptionUtil;
import com.jonybuzz.encontralo.service.helper.FuzzyHelper;
import com.jonybuzz.encontralo.service.mappers.AnuncioMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImportadorAnunciosFacebook implements ImportadorAnuncios {

    @Autowired
    private FacebookClient facebookClient;
    @Autowired
    private AccesoPaginasFacebookRepository accesoPaginasFacebookRepository;
    @Value("${app.importadorAnuncios.facebook.token.aespassword}")
    private String passwordEncriptacion;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private WitAiClient witAiClient;
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private EspecieRepository especieRepository;
    @Autowired
    private TamanioRepository tamanioRepository;
    @Autowired
    private FranjaEtariaRepository franjaEtariaRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private PelajeRepository pelajeRepository;
    @Autowired
    private LocalidadRepository localidadRepository;

    @Override
    public OrigenAnuncio getOrigen() {
        return OrigenAnuncio.FACEBOOK;
    }

    @Override
    public void importar(LocalDateTime desde, LocalDateTime hasta) {
        log.info("Importacion de Facebook comenzada.");

        var paginas = accesoPaginasFacebookRepository.findAll();
        for (AccesoPaginasFacebook pagina : paginas) {
            try {
                var accessToken = AesEncryptionUtil.desencriptar(pagina.getAccessToken(), passwordEncriptacion);
                var paginaFeed = facebookClient.getFeed(pagina.getIdPagina(), accessToken).block();
                Assert.notNull(paginaFeed, "Respuesta vacía.");
                Assert.notNull(paginaFeed.getData(), "Campo 'data' vacio.");
                paginaFeed.getData().stream()
                        .map(this::facebookPostToAnuncio)
                        .filter(Objects::nonNull)
                        .forEach(anuncio -> anuncioRepository.save(anuncio));
            } catch (GeneralSecurityException ex) {
                log.error("Error desencriptando password para Facebook token.", ex);
            } catch (IllegalArgumentException ex) {
                log.error("Error en la respuesta de Facebook.", ex);
            }
        }

        log.info("Importacion de Facebook finalizada.");
    }

    private Anuncio facebookPostToAnuncio(PostFbDto post) {

        String textoPost;
        String[] urlsImagenes;

        String postType = post.getAttachments().getData().get(0).getType();
        if (Objects.equals(postType, "album")) {
            //cuando se comparte una publicación de más de una foto
            textoPost = post.getMessage(); //descripcion del repost
            //TODO obtener la publicacion compartida para ver el mensaje original. El repost no suele tener mensaje.
            urlsImagenes = post.getAttachments().getData().get(0).getSubattachments().getData().stream()
                    .filter(attach -> Objects.equals(attach.getType(), "photo"))
                    .map(attach -> attach.getMedia().getImage().getSrc())
                    .limit(4)
                    .toArray(String[]::new);
        } else if (Objects.equals(postType, "photo")) {
            //publicacion original o compartida de una sola foto
            textoPost = post.getAttachments().getData().get(0).getDescription();
            urlsImagenes = new String[]{post.getAttachments().getData().get(0).getMedia().getImage().getSrc()};
        } else {
            // publicaciones sin fotos o con video se ignoran por el momento
            return null;
        }

        var interpretacion = witAiClient.getMessage(textoPost, post.getCreatedTime()).block();
        assert interpretacion != null;
        var entidades = interpretacion.getEntities();
        assert entidades != null;

        var anuncio = new Anuncio();
        anuncio.setTipo(identificarTipoAnuncio(interpretacion.getTraits()));
        anuncio.setNombreMascota(getFirstValue(entidades.getNombre()));
        anuncio.setNombreMascotaNormalizado(AnuncioMapper.normalizarNombre(getFirstValue(entidades
                .getNombre())));
        var raza = identificarRaza(getFirstValue(entidades.getRaza()));
        anuncio.setRaza(raza);
        anuncio.setEspecieId(identificarEspecie(raza, getFirstValue(entidades.getEspecie())));
        anuncio.setSexo(getFirstEnum(Sexo.class, entidades.getSexo()));
        anuncio.setTamanioId(identificarTamanio(getFirstValue(entidades.getTamanio())));
        anuncio.setFranjaEtariaId(identificarFranjaEtaria(getFirstValue(entidades.getEdad())));
        anuncio.setColores(identificarColores(getValues(entidades.getColor())));
        anuncio.setTieneCollar(identificarTieneCollar(getFirstValue(entidades.getTieneCollar())));
        anuncio.setPelajeId(identificarPelaje(getFirstValue(entidades.getPelaje())));
        anuncio.setFotos(descargarFotos(urlsImagenes));
        anuncio.setLocalidad(identificarLocalidad(getFirstValue(entidades.getLocalidad())));
        anuncio.setComentario(interpretacion.getText());
        anuncio.setTelefonoContacto(getFirstValue(entidades.getPhoneNumber()));
        anuncio.setFechaCreacion(post.getCreatedTime().withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime());

        anuncio.setOrigen(this.getOrigen());
        anuncio.setLinkExterno(post.getPermalinkUrl());

        return anuncio;
    }

    private String getFirstValue(List<EntitiesWitDto.EntityWitDto> entities) {
        if (entities == null) {
            return null;
        } else {
            if (entities.get(0) == null) {
                return null;
            } else {
                return entities.get(0).getValue();
            }
        }
    }

    private Set<String> getValues(List<EntitiesWitDto.EntityWitDto> entities) {
        if (entities == null) {
            return Collections.emptySet();
        } else {
            return entities.stream()
                    .map(EntitiesWitDto.EntityWitDto::getValue)
                    .collect(Collectors.toSet());
        }
    }

    private <T extends Enum<T>> T getFirstEnum(Class<T> clazz, List<EntitiesWitDto.EntityWitDto> entity) {
        if (entity == null) {
            return null;
        } else {
            return getEnum(clazz, entity.get(0).getValue());
        }
    }

    private <T extends Enum<T>> T getEnum(Class<T> clazz, String value) {
        if (value == null) {
            return null;
        } else {
            return Enums.getIfPresent(clazz, value.toUpperCase()).orNull();
        }
    }

    private TipoAnuncio identificarTipoAnuncio(TraitsWitDto traits) {
        return traits.getTipo().stream()
                .max(Comparator.comparing(TraitsWitDto.TraitWitDto::getConfidence))
                .map(TraitsWitDto.TraitWitDto::getValue)
                .map(name -> getEnum(TipoAnuncio.class, name))
                .orElse(null);
    }

    private Raza identificarRaza(String razaInterpretada) {
        return FuzzyHelper.buscarPorAproximacion(razaInterpretada, razaRepository.findAll(), Raza::getNombre)
                .orElse(null);
    }

    private Integer identificarEspecie(Raza raza, String especieInterpretada) {
        if (raza != null) {
            return raza.getEspecieId();
        } else {
            return FuzzyHelper.buscarPorAproximacion(especieInterpretada, especieRepository.findAll(), Especie::getNombre)
                    .map(Especie::getId)
                    .orElse(null);
        }
    }

    private Integer identificarTamanio(String tamanioInterpretado) {
        return FuzzyHelper.buscarPorAproximacion(tamanioInterpretado, tamanioRepository.findAll(), Tamanio::getNombre)
                .map(Tamanio::getId)
                .orElse(null);
    }

    private Integer identificarFranjaEtaria(String franjaEtariaInterpretada) {
        var franjas = franjaEtariaRepository.findAll();
        return FuzzyHelper.buscarPorAproximacion(franjaEtariaInterpretada, franjas, FranjaEtaria::getNombre)
                .map(FranjaEtaria::getId)
                .orElse(null);
    }

    private Set<Color> identificarColores(Set<String> coloresInterpretados) {
        return coloresInterpretados.stream()
                .map(col -> FuzzyHelper.buscarPorAproximacion(col, colorRepository.findAll(), Color::getNombre).orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private Boolean identificarTieneCollar(String valorInterpretado) {
        if (valorInterpretado == null) {
            return null;
        } else {
            return valorInterpretado.equalsIgnoreCase("CON_COLLAR");
        }
    }

    private Integer identificarPelaje(String pelajeInterpretado) {
        return FuzzyHelper.buscarPorAproximacion(pelajeInterpretado, pelajeRepository.findAll(), Pelaje::getNombre)
                .map(Pelaje::getId)
                .orElse(null);
    }

    private Set<Imagen> descargarFotos(String[] urls) {
        Set<Imagen> imagenes = new HashSet<>();
        for (int i = 0; i < urls.length; i++) {
            try {
                var url = new URL(urls[i]);
                try (BufferedInputStream in = new BufferedInputStream(url.openStream());
                     ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    byte[] dataBuffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                        outputStream.write(dataBuffer, 0, bytesRead);
                    }
                    var imagen = new Imagen();
                    imagen.setDatos(outputStream.toByteArray());
                    imagen.setPosicion(i);
                    imagenes.add(imagen);
                } catch (IOException e) {
                    log.warn("Error descargando imagen de Facebook.", e);
                }
            } catch (MalformedURLException e) {
                log.warn("URL de imagen de Facebook malformada.", e);
            }
        }
        return imagenes;
    }

    private Localidad identificarLocalidad(String localidadInterpretada) {
        return FuzzyHelper.buscarPorAproximacion(localidadInterpretada, localidadRepository.findAll(), Localidad::getNombre)
                .orElse(null);
    }

}
