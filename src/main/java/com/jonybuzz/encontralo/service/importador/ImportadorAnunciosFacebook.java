package com.jonybuzz.encontralo.service.importador;

import com.jonybuzz.encontralo.client.facebook.FacebookClient;
import com.jonybuzz.encontralo.client.facebook.dto.PostFbDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.model.facebook.AccesoPaginasFacebook;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import com.jonybuzz.encontralo.repository.facebook.AccesoPaginasFacebookRepository;
import com.jonybuzz.encontralo.security.AesEncryptionUtil;
import com.jonybuzz.encontralo.service.InterpreteTextoLibreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
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
    private InterpreteTextoLibreService interpreteTextoLibreService;

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
                var anuncios = paginaFeed.getData().stream()
                        .map(this::facebookPostToAnuncio)
                        .collect(Collectors.toList());
                anuncioRepository.saveAll(anuncios);
            } catch (GeneralSecurityException ex) {
                log.error("Error desencriptando password para Facebook token.", ex);
            } catch (IllegalArgumentException ex) {
                log.error("Error en la respuesta de Facebook.", ex);
            }
        }

        log.info("Importacion de Facebook finalizada.");
    }

    private Anuncio facebookPostToAnuncio(PostFbDto post) {
        //TODO procesamiento con Wit
        return interpreteTextoLibreService.interpretarDatosAnuncio(post.getMessage(), post.getCreatedTime());
    }

}
