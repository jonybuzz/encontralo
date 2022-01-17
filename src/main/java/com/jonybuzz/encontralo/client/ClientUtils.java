package com.jonybuzz.encontralo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;

public class ClientUtils {

    private ClientUtils() {
        throw new UnsupportedOperationException("Clase utilitaria");
    }

    public static ExchangeStrategies exchangeStrategies(ObjectMapper objectMapper) {
        return ExchangeStrategies.builder()
                .codecs(clientDefaultCodecsConfigurer -> {
                            clientDefaultCodecsConfigurer.defaultCodecs()
                                    .jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
                            clientDefaultCodecsConfigurer.defaultCodecs()
                                    .jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                        }
                ).build();
    }
}
