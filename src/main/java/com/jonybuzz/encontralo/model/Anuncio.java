package com.jonybuzz.encontralo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
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

    private Integer especieId;

    @ManyToOne
    private Raza raza;

    private Integer tamanioId;

    private Integer franjaEtariaId;

    @ManyToMany
    @JoinTable(name = "anuncio_colores")
    private Set<Color> colores;

    private Boolean tieneCollar;

    private Integer pelajeId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "anuncio_fotos")
    private Set<Imagen> fotos;

    @Embedded
    @AttributeOverride(name = "lat", column = @Column(name = "ubicacion_lat"))
    @AttributeOverride(name = "lon", column = @Column(name = "ubicacion_lon"))
    private Ubicacion ubicacion;

    private String comentario;

    private String telefonoContacto;

}
