package com.jonybuzz.encontralo.client.witai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class EntitiesWitDto {
    @JsonProperty("especie:especie")
    private EntityWitDto especie;
    @JsonProperty("color:color")
    private EntityWitDto color;
    @JsonProperty("edad:edad")
    private EntityWitDto edad;
    @JsonProperty("localidad:localidad")
    private EntityWitDto localidad;
    @JsonProperty("nombre:nombre")
    private EntityWitDto nombre;
    @JsonProperty("pelaje:pelaje")
    private EntityWitDto pelaje;
    @JsonProperty("raza:raza")
    private EntityWitDto raza;
    @JsonProperty("sexo:sexo")
    private EntityWitDto sexo;
    @JsonProperty("tamanio:tamanio")
    private EntityWitDto tamanio;
    @JsonProperty("tieneCollar:tieneCollar")
    private EntityWitDto tieneCollar;
    @JsonProperty("wit$datetime:datetime")
    private DatetimeEntityWitDto datetime;
    @JsonProperty("wit$phone_number:phone_number")
    private EntityWitDto phoneNumber;
    @JsonProperty("wit$email:email")
    private EntityWitDto email;

    @Data
    private static class EntityWitDto {
        private String value;
    }

    @Data
    private static class DatetimeEntityWitDto {
        private ZonedDateTime value;
    }
}
