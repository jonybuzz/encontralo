package com.jonybuzz.encontralo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
public class Sexo implements IdEntity<Integer> {

    @EqualsAndHashCode.Include
    private Integer id;

    private String nombre;

}
