package com.jonybuzz.encontralo.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Imagen {

    @Id
    @GeneratedValue
    private Long id;
    private Integer posicion;
    private byte[] datos;
}
