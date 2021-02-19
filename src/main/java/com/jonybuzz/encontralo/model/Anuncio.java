package com.jonybuzz.encontralo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Anuncio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TipoAnuncio tipo;

    private String nombreMascota;

    private String nombreMascotaNormalizado;

    @ManyToOne
    private Especie especie;

    @ManyToOne
    private Raza raza;

    @ManyToOne
    private Tamanio tamanio;

    @ManyToOne
    private FranjaEtaria franjaEtaria;

    @ManyToMany
    private Set<Color> colores;

    private Boolean tieneCollar;

    @ManyToOne
    private Pelaje pelaje;

    @OneToMany
    private Set<Imagen> fotos;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "lat", column = @Column(name = "ubicacion_lat")),
            @AttributeOverride( name = "lon", column = @Column(name = "ubicacion_lon"))
    })
    private Ubicacion ubicacion;

    private String comentario;

    private String telefonoContacto;

}
