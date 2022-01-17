package com.jonybuzz.encontralo.client.facebook.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorFacebookDto {
    private DetalleErrorFacebookDto error;

    @Data
    public static class DetalleErrorFacebookDto {
        private String message;
        private String type;
        private String code;
        @JsonProperty("fbtrace_id")
        private String traceId;
    }
}
