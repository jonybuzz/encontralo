package com.jonybuzz.encontralo.model;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class Especie {

    @Id
    @GeneratedValue
    private Integer id;

    private String nombre;

    @OneToMany
    private Set<Raza> razas;

    @OneToMany
    private Set<Tamanio> tamanios;

}
