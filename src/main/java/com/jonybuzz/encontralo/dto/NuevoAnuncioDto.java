package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
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

    private Integer sexoId;

    private Integer franjaEtariaId;

    private Set<Integer> coloresIds = new HashSet<>();

    private Boolean tieneCollar;

    private Integer pelajeId;

    private Set<ImagenUploadDto> fotos = new HashSet<>();

    private Integer localidadId;

    private String comentario;

    private String telefonoContacto;

}
