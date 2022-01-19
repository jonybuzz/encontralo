package com.jonybuzz.encontralo.client.witai.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageWitDto {
    private String text;
    private List<IntentWitDto> intents;
    private EntitiesWitDto entities;
    private TraitsWitDto traits;
}
