package com.jonybuzz.encontralo.client;

import com.jonybuzz.encontralo.dto.facebook.RespuestaPaginadaFacebookDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class FacebookClient {

    private final WebClient facebookPageClient;
    private final FacebookAccessTokenRepository accessTokenRepository;

    public FacebookClient(FacebookAccessTokenRepository accessTokenRepository) {
        this.accessTokenRepository = accessTokenRepository;
        this.facebookPageClient = WebClient.builder()
                .baseUrl("https://graph.facebook.com/v10.0")
                .build();
    }

    public Mono<RespuestaPaginadaFacebookDto> getFeed(String pageId) {
        return facebookPageClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .pathSegment(pageId, "feed")
                        .queryParam("fields", "message", "permalink_url", "created_time", "attachments")
                        .queryParam("limit", "10")
                        .queryParam("access_token", accessTokenRepository.getPageAccessToken(pageId))
                        .build())
                .retrieve().bodyToMono(RespuestaPaginadaFacebookDto.class);
    }

}
