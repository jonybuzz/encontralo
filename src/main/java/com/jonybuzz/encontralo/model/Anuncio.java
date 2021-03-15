package com.jonybuzz.encontralo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "sexo_id")
    @Enumerated(EnumType.ORDINAL)
    private Sexo sexo;

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

    @ManyToOne
    private Localidad localidad;

    private String comentario;

    private String telefonoContacto;

    private LocalDateTime fechaCreacion;

}
