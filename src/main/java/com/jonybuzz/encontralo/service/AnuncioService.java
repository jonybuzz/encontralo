package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.dto.AnuncioDto;
import com.jonybuzz.encontralo.dto.AnuncioResumidoDto;
import com.jonybuzz.encontralo.dto.ImagenDownloadDto;
import com.jonybuzz.encontralo.dto.ImagenUploadDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.model.FiltroAnuncios;
import com.jonybuzz.encontralo.model.Imagen;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import com.jonybuzz.encontralo.repository.ColorRepository;
import com.jonybuzz.encontralo.repository.EspecieRepository;
import com.jonybuzz.encontralo.repository.FranjaEtariaRepository;
import com.jonybuzz.encontralo.repository.LocalidadRepository;
import com.jonybuzz.encontralo.repository.PelajeRepository;
import com.jonybuzz.encontralo.repository.RazaRepository;
import com.jonybuzz.encontralo.repository.TamanioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class AnuncioService {

    public static final int PAGE_SIZE = 15;
    public static final String PATH_DESCARGA_IMAGENES = "imagenes";
    private static final int IMAGE_SIZE_MAX = 4;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private EspecieRepository especieRepository;
    @Autowired
    private TamanioRepository tamanioRepository;
    @Autowired
    private FranjaEtariaRepository franjaEtariaRepository;
    @Autowired
    private PelajeRepository pelajeRepository;
    @Autowired
    private LocalidadRepository localidadRepository;

    @Transactional
    public Long crearAnuncio(NuevoAnuncioDto dto) {
        Anuncio anuncio = new Anuncio();
        validarFotos(dto);
        validarNuevoAnuncio(dto);
        anuncio.setTipo(dto.getTipo());
        anuncio.setNombreMascota(StringUtils.normalizeSpace(dto.getNombreMascota()));
        anuncio.setNombreMascotaNormalizado(this.normalizarNombre(dto.getNombreMascota()));
        anuncio.setEspecieId(dto.getEspecieId());
        anuncio.setRaza(dto.getRazaId() == null ? null : razaRepository.getOne(dto.getRazaId()));
        anuncio.setSexo(dto.getSexo());
        anuncio.setTamanioId(dto.getTamanioId());
        anuncio.setFranjaEtariaId(dto.getFranjaEtariaId());
        anuncio.setColores(new HashSet<>(colorRepository.findAllById(dto.getColoresIds())));
        anuncio.setTieneCollar(dto.getTieneCollar());
        anuncio.setPelajeId(dto.getPelajeId());
        anuncio.setFotos(dto.getFotos().stream()
                .map(ImagenUploadDto::toImagen).collect(Collectors.toSet()));
        anuncio.setLocalidad(localidadRepository.getOne(dto.getLocalidadId()));
        anuncio.setComentario(StringUtils.normalizeSpace(dto.getComentario()));
        anuncio.setTelefonoContacto(dto.getTelefonoContacto());
        anuncio.setFechaCreacion(LocalDateTime.now());
        return anuncioRepository.saveAndFlush(anuncio).getId();
    }

    @Transactional(readOnly = true)
    public Page<AnuncioResumidoDto> buscarAnunciosResumidos(FiltroAnuncios filtro) {
        var anuncioBuscado = new Anuncio();
        anuncioBuscado.setTipo(filtro.getTipoAnuncio());
        anuncioBuscado.setEspecieId(filtro.getEspecieId());
        Page<Anuncio> pagina = anuncioRepository.findAll(
                Example.of(anuncioBuscado),
                PageRequest.of(filtro.getPagina() - 1, PAGE_SIZE));
        List<AnuncioResumidoDto> dtos = pagina.stream()
                .map(this::anuncioToResumenDto)
                .collect(Collectors.toList());
        return new PageImpl<>(dtos, pagina.getPageable(), pagina.getTotalElements());
    }

    @Transactional(readOnly = true)
    public AnuncioDto obtenerAnuncio(Long anuncioId) {
        return anuncioToDto(anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new NoSuchElementException("El anuncio #" + anuncioId + " no existe.")));
    }

    private void validarNuevoAnuncio(NuevoAnuncioDto dto) throws AnuncioIncompletoException {
        List<String> camposFaltantes = new ArrayList<>();
        if (dto.getTipo() == null) {
            camposFaltantes.add("tipo");
        }
        if (dto.getEspecieId() == null) {
            camposFaltantes.add("especie");
        }
        if (dto.getLocalidadId() == null) {
            camposFaltantes.add("localidad");
        }
        if (dto.getTelefonoContacto() == null) {
            camposFaltantes.add("telefono");
        }
        if (camposFaltantes.size() == 1) {
            throw new AnuncioIncompletoException("Faltan completar el campo " + camposFaltantes.get(0));
        }
        if (camposFaltantes.size() > 1) {
            throw new AnuncioIncompletoException("Faltan completar los campos: " + String.join(", ", camposFaltantes));
        }
    }

    private void validarFotos(NuevoAnuncioDto dto) {
        dto.getFotos().forEach(this::verificarCantidadDeMbs);
    }

    private void verificarCantidadDeMbs(ImagenUploadDto imagenUploadDto) {
        double numberToTransformInBytes = 0.75;
        double numberToTransformInMegabytes = 1000000;
        double sizeInMegaBytes = (imagenUploadDto.getDatosBase64().length() * numberToTransformInBytes)/numberToTransformInMegabytes;
        if (sizeInMegaBytes > IMAGE_SIZE_MAX) {
            throw new IllegalArgumentException("El tamaño de la foto supera los " + IMAGE_SIZE_MAX + "MB.");
        }
    }

    private String normalizarNombre(String str) {
        return str == null ? null :
                StringUtils.normalizeSpace(
                        StringUtils.stripAccents(str)
                                .toUpperCase().replaceAll("[^A-Z\\s]", "")
                );
    }

    public AnuncioResumidoDto anuncioToResumenDto(Anuncio anuncio) {
        return AnuncioResumidoDto.builder()
                .id(anuncio.getId())
                .tipo(anuncio.getTipo())
                .nombreMascota(anuncio.getNombreMascota())
                .especie(especieRepository.getOne(anuncio.getEspecieId()))
                .raza(anuncio.getRaza())
                .sexo(anuncio.getSexo())
                .tamanio(tamanioRepository.getOne(anuncio.getTamanioId()))
                .colores(anuncio.getColores())
                .fotoPrincipal(anuncio.getFotos().stream()
                        .min(Comparator.comparing(Imagen::getPosicion))
                        .map(foto -> ImagenDownloadDto.builder()
                                .posicion(foto.getPosicion())
                                .url(UriComponentsBuilder.fromPath("/")
                                        .pathSegment(PATH_DESCARGA_IMAGENES)
                                        .pathSegment(foto.getId().toString()).toUriString())
                                .build())
                        .orElse(null))
                .localidad(anuncio.getLocalidad())
                .fechaCreacion(anuncio.getFechaCreacion())
                .build();
    }

    public AnuncioDto anuncioToDto(Anuncio anuncio) {
        return AnuncioDto.builder()
                .id(anuncio.getId())
                .tipo(anuncio.getTipo())
                .nombreMascota(anuncio.getNombreMascota())
                .especie(especieRepository.getOne(anuncio.getEspecieId()))
                .raza(anuncio.getRaza())
                .sexo(anuncio.getSexo())
                .tamanio(tamanioRepository.getOne(anuncio.getTamanioId()))
                .franjaEtaria(franjaEtariaRepository.getOne(anuncio.getFranjaEtariaId()))
                .colores(anuncio.getColores())
                .tieneCollar(anuncio.getTieneCollar())
                .pelaje(pelajeRepository.getOne(anuncio.getPelajeId()))
                .fotos(anuncio.getFotos().stream()
                        .map(foto -> ImagenDownloadDto.builder()
                                .posicion(foto.getPosicion())
                                .url(UriComponentsBuilder.fromPath("/")
                                        .pathSegment(PATH_DESCARGA_IMAGENES)
                                        .pathSegment(foto.getId().toString()).toUriString())
                                .build())
                        .collect(Collectors.toSet()))
                .localidad(anuncio.getLocalidad())
                .comentario(anuncio.getComentario())
                .telefonoContacto(anuncio.getTelefonoContacto())
                .fechaCreacion(anuncio.getFechaCreacion())
                .build();
    }

}
