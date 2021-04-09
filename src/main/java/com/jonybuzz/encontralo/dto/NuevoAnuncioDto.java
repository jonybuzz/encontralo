package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.Sexo;
import com.jonybuzz.encontralo.model.TipoAnuncio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NuevoAnuncioDto {

    @NotNull(message = "tipo")
    private TipoAnuncio tipo;

    private String nombreMascota;

    @NotNull(message = "especie")
    private Integer especieId;

    private Integer razaId;

    private Integer tamanioId;

    private Sexo sexo;

    private Integer franjaEtariaId;

    private Set<Integer> coloresIds = new HashSet<>();

    private Boolean tieneCollar;

    private Integer pelajeId;

    private Set<ImagenUploadDto> fotos = new HashSet<>();

    @NotNull(message = "localidad")
    private Integer localidadId;

    private String comentario;

    @NotNull(message = "telefono")
    private String telefonoContacto;

}
