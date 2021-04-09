package com.jonybuzz.encontralo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Slf4j
public class WebClientConfig {

    @Bean
    WebClient facebookPageClient() {

        return WebClient.builder()
                .baseUrl("https://graph.facebook.com/v10.0")
                .build();
    }

}
