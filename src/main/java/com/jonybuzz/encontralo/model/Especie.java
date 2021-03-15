package com.jonybuzz.encontralo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Especie implements IdEntity<Integer> {

    public Especie(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @EqualsAndHashCode.Include
    private Integer id;

    private String nombre;

}
