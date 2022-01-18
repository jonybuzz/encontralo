package com.jonybuzz.encontralo.client.witai.dto;

import lombok.Data;

import java.util.List;

@Data
public class MessageWitDto {
    private List<IntentWitDto> intents;
    private EntitiesWitDto entities;
}
