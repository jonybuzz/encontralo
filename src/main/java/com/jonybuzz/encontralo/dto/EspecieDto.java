package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.Raza;
import com.jonybuzz.encontralo.model.Tamanio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EspecieDto {

    private Integer id;
    private Set<Raza> razas;
    private Set<Tamanio> tamanios;

}
