package com.jonybuzz.encontralo.model;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Raza {
    private String nombre;
    private Integer especieId;
}
