package com.jonybuzz.encontralo.client.witai.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class EntitiesWitDto {
    @JsonProperty("especie:especie")
    private List<EntityWitDto> especie;
    @JsonProperty("color:color")
    private List<EntityWitDto> color;
    @JsonProperty("edad:edad")
    private List<EntityWitDto> edad;
    @JsonProperty("localidad:localidad")
    private List<EntityWitDto> localidad;
    @JsonProperty("nombre:nombre")
    private List<EntityWitDto> nombre;
    @JsonProperty("pelaje:pelaje")
    private List<EntityWitDto> pelaje;
    @JsonProperty("raza:raza")
    private List<EntityWitDto> raza;
    @JsonProperty("sexo:sexo")
    private List<EntityWitDto> sexo;
    @JsonProperty("tamanio:tamanio")
    private List<EntityWitDto> tamanio;
    @JsonProperty("tieneCollar:tieneCollar")
    private List<EntityWitDto> tieneCollar;
    @JsonProperty("wit$datetime:datetime")
    private List<DatetimeEntityWitDto> datetime;
    @JsonProperty("wit$phone_number:phone_number")
    private List<EntityWitDto> phoneNumber;
    @JsonProperty("wit$email:email")
    private List<EntityWitDto> email;

    @Data
    public static class EntityWitDto {
        private String value;
    }

    @Data
    public static class DatetimeEntityWitDto {
        private ZonedDateTime value;
    }
}
