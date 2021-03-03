package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NuevoAnuncioDto {

    private TipoAnuncio tipo;

    private String nombreMascota;

    private Integer especieId;

    private Integer razaId;

    private Integer tamanioId;

    private Integer franjaEtariaId;

    private Set<Integer> coloresIds;

    private Boolean tieneCollar;

    private Integer pelajeId;

    private Set<ImagenUploadDto> fotos;

    private Integer localidadId;

    private String comentario;

    private String telefonoContacto;

}
