package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.TipoAnuncio;
import com.jonybuzz.encontralo.model.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NuevoAnuncioDto {

    @NotNull
    private TipoAnuncio tipo;

    private String nombreMascota;

    private Integer especieId;

    private Integer razaId;

    private Integer tamanioId;

    private Integer franjaEtariaId;

    private Set<Integer> coloresIds;

    private Boolean tieneCollar;

    private Integer pelajeId;

    private Set<ImagenDto> fotos;

    private Ubicacion ubicacion;

    private String comentario;

    private String telefonoContacto;

}
