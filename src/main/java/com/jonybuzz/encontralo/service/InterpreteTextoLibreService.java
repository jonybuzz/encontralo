package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.client.witai.WitAiClient;
import com.jonybuzz.encontralo.model.Anuncio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class InterpreteTextoLibreService {

    @Autowired
    private WitAiClient witAiClient;

    public Anuncio interpretarDatosAnuncio(String mensaje, ZonedDateTime fecha) {
        witAiClient.getMessage(mensaje, fecha).block();
        return new Anuncio();
    }
}
