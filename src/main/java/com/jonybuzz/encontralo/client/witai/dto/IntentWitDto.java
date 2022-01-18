package com.jonybuzz.encontralo.client.witai.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IntentWitDto {

    private String name;
    private BigDecimal confidence;
}
