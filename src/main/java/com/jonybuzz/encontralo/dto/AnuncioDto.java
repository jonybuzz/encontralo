package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AnuncioDto {

    private Long id;

    private TipoAnuncio tipo;

    private String nombreMascota;

    private Especie especie;

    private Raza raza;

    private Tamanio tamanio;

    private FranjaEtaria franjaEtaria;

    private Set<Color> colores;

    private Boolean tieneCollar;

    private Pelaje pelaje;

    private Set<ImagenDownloadDto> fotos;

    private Ubicacion ubicacion;

    private String comentario;

    private String telefonoContacto;

}
