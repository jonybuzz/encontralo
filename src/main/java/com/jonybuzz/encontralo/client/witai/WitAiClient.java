package com.jonybuzz.encontralo.client.witai;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jonybuzz.encontralo.client.witai.dto.ContextWitDto;
import com.jonybuzz.encontralo.client.witai.dto.MessageWitDto;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.time.ZonedDateTime;
import java.util.List;

import static com.jonybuzz.encontralo.client.ClientUtils.exchangeStrategies;

@Component
@Slf4j
public class WitAiClient {

    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    @Value("${app.client.witai.token}")
    private String token;
    @Value("${app.client.witai.version}")
    private String appVersion;

    public WitAiClient() {
        this.objectMapper = Jackson2ObjectMapperBuilder.json()
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
        this.webClient = WebClient.builder()
                .baseUrl("https://api.wit.ai")
                .exchangeStrategies(exchangeStrategies(objectMapper))
                .clientConnector(new ReactorClientHttpConnector(HttpClient
                        .create()
                        .wiretap("reactor.netty.http.client.HttpClient",
                                LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL
                        )))
                .build();
    }

    public Mono<MessageWitDto> getMessage(String mensaje, ZonedDateTime fechaMensaje) {
        var context = new ContextWitDto();
        context.setReferenceTime(fechaMensaje);
        try {
            var contextJson = objectMapper.writeValueAsString(context);
            log.warn(contextJson);

            return webClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .pathSegment("message")
                            .queryParam("v", appVersion)
                            .queryParam("q", mensaje)
                            .queryParam("context", "{context}")
                            .build(contextJson))
                    .headers(h -> {
                        h.setBearerAuth(token);
                        h.setContentType(MediaType.APPLICATION_JSON);
                        h.setAccept(List.of(MediaType.APPLICATION_JSON));
                    })
                    .retrieve().bodyToMono(MessageWitDto.class);
        } catch (JsonProcessingException ex) {
            log.error("Error inesperado armando request a Wit.ai.", ex);
            throw new IllegalArgumentException(ex);
        }
    }

}
