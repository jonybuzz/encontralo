package com.jonybuzz.encontralo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Especie {

    public Especie() {
    }

    public Especie(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @OneToMany
    private Set<Raza> razas;

    @OneToMany
    private Set<Tamanio> tamanios;

}
