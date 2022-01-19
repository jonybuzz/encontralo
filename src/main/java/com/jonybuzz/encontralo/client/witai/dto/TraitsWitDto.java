package com.jonybuzz.encontralo.client.witai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TraitsWitDto {

    @JsonProperty("TIPO")
    private List<TraitWitDto> tipo;

    @Data
    public static class TraitWitDto {
        private String value;
        private BigDecimal confidence;
    }

}
