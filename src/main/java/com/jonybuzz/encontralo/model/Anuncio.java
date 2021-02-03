package com.jonybuzz.encontralo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Anuncio {

    @Id
    @GeneratedValue
    private Integer id;

    private TipoPost tipo;

    private String nombreMascota;

    private String nombreNormalizado;

    @ManyToOne
    private Especie especie;

    @ManyToOne
    private Raza raza;

    @ManyToOne
    private Tamanio tamanio;

    @ManyToOne
    private FranjaEtaria franjaEtaria;

    @OneToMany
    private Set<Color> colores;

    private Boolean tieneCollar;

    @ManyToOne
    private Pelaje pelaje;

    @OneToMany
    private List<Imagen> fotos;

    @Embedded
    private Ubicacion ubicacion;

    private String comentario;

    private String telefonoContacto;

}
