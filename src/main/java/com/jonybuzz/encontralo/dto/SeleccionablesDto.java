package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.Color;
import com.jonybuzz.encontralo.model.FranjaEtaria;
import com.jonybuzz.encontralo.model.Pelaje;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class SeleccionablesDto {

    private Set<Color> colores;
    private List<EspecieDto> especies;
    private Set<FranjaEtaria> franjasEtarias;
    private Set<Pelaje> pelajes;
}
