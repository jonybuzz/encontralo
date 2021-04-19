package com.jonybuzz.encontralo.controller;

import com.jonybuzz.encontralo.service.importador.ImportadorAnuncios;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
@EnableScheduling
class ImportadorAnunciosScheduler {

    private final List<ImportadorAnuncios> importadores;

    public ImportadorAnunciosScheduler(List<ImportadorAnuncios> importadores) {
        this.importadores = importadores;
    }

    @Scheduled(cron = "${app.importador-anuncios.cron}", zone = "America/Argentina/Buenos_Aires")
    public void importar() {
        LocalDateTime desde = LocalDateTime.now().minusDays(1);
        LocalDateTime hasta = LocalDateTime.now();
        importadores.parallelStream().forEach(importador -> importador.importar(desde, hasta));
    }

}