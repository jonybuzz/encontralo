package com.jonybuzz.encontralo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.SetUtils;

import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Especie implements IdEntity<Integer> {

    public Especie(Integer id, String nombre, Tamanio... tamanios) {
        this.id = id;
        this.nombre = nombre;
        this.tamanios = SetUtils.hashSet(tamanios);
    }

    @EqualsAndHashCode.Include
    private Integer id;

    private String nombre;

    private Set<Tamanio> tamanios;

}
