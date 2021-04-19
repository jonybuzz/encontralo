package com.jonybuzz.encontralo.service.importador;

import com.jonybuzz.encontralo.client.FacebookClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ImportadorAnunciosFacebook implements ImportadorAnuncios {

    private final FacebookClient facebookClient;

    public ImportadorAnunciosFacebook(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    @Override
    public void importar(LocalDateTime desde, LocalDateTime hasta) {
        Object dto = facebookClient.getFeed("100634498749998").block();
        log.warn("=====" + dto);
    }

}
