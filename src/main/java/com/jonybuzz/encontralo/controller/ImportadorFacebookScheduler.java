package com.jonybuzz.encontralo.controller;

import com.jonybuzz.encontralo.client.FacebookClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
class ImportadorFacebookScheduler {

    private final FacebookClient facebookClient;

    public ImportadorFacebookScheduler(FacebookClient facebookClient) {
        this.facebookClient = facebookClient;
    }

    @Scheduled(cron = "${facebook.importador.cron}", zone = "America/Argentina/Buenos_Aires")
    public void importar() {
        Object dto = facebookClient.getFeed("100634498749998").block();
        log.warn("=====" + dto);
    }

}