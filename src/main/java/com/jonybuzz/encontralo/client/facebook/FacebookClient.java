package com.jonybuzz.encontralo.client.facebook;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.jonybuzz.encontralo.client.facebook.dto.FeedPaginadoFbDto;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.jonybuzz.encontralo.client.ClientUtils.exchangeStrategies;

@Component
public class FacebookClient {

    private final WebClient facebookPageClient;

    public FacebookClient() {
        var objectMapper = Jackson2ObjectMapperBuilder.json()
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
        this.facebookPageClient = WebClient.builder()
                .baseUrl("https://graph.facebook.com/v12.0")
                .exchangeStrategies(exchangeStrategies(objectMapper))
                .build();
    }

    public Mono<FeedPaginadoFbDto> getFeed(String pageId, String accessToken) {
        return facebookPageClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(pageId, "feed")
                        .queryParam("fields", "message", "permalink_url", "created_time", "attachments")
                        .queryParam("limit", "10")
                        .queryParam("access_token", accessToken)
                        .build())
                .retrieve().bodyToMono(FeedPaginadoFbDto.class);
    }

}
