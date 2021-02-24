package com.jonybuzz.encontralo.testutils.builder;

import com.jonybuzz.encontralo.model.*;
import com.somospnt.test.builder.AbstractPersistenceBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class AnuncioBuilder extends AbstractPersistenceBuilder<Anuncio> {

    private AnuncioBuilder() {
        this.instance = new Anuncio();
    }

    public static AnuncioBuilder perro() {
        var builder = new AnuncioBuilder();
        builder.instance.setTipo(TipoAnuncio.PERDIDO);
        builder.instance.setNombreMascota("Señor Itatí,,");
        builder.instance.setEspecieId(1);
        var mestizo = new Raza();
        mestizo.setId(1);
        mestizo.setEspecieId(1);
        mestizo.setNombre("Mestizo");
        builder.instance.setRaza(mestizo);
        builder.instance.setTamanioId(3);
        builder.instance.setFranjaEtariaId(3);
        var marron = new Color();
        marron.setId(2);
        marron.setNombre("Marrón");
        marron.setCodigoHex("FAFAFA");
        builder.instance.setColores(Set.of(marron));
        builder.instance.setTieneCollar(true);
        builder.instance.setPelajeId(1);
        builder.instance.setUbicacion(new Ubicacion(new BigDecimal("37.1234"), new BigDecimal("157.0001")));
        builder.instance.setComentario("Tiene una chapita con mi teléfono!");
        builder.instance.setTelefonoContacto("11-5678-0987");
        builder.instance.setFechaCreacion(LocalDateTime.of(2020, 1, 29, 12, 0));
        return builder;
    }

    public static AnuncioBuilder gato() {
        var builder = new AnuncioBuilder();
        builder.instance.setTipo(TipoAnuncio.PERDIDO);
        builder.instance.setNombreMascota("Michi");
        builder.instance.setEspecieId(2);
        var mestizo = new Raza();
        mestizo.setId(1);
        mestizo.setEspecieId(1);
        mestizo.setNombre("Mestizo");
        builder.instance.setRaza(mestizo);
        builder.instance.setTamanioId(4);
        builder.instance.setFranjaEtariaId(3);
        var marron = new Color();
        marron.setId(2);
        marron.setNombre("Marrón");
        marron.setCodigoHex("FAFAFA");
        builder.instance.setColores(Set.of(marron));
        builder.instance.setTieneCollar(true);
        builder.instance.setPelajeId(1);
        builder.instance.setUbicacion(new Ubicacion(new BigDecimal("0.1234"), new BigDecimal("0.0001")));
        builder.instance.setComentario("Tiene un moño");
        builder.instance.setTelefonoContacto("11-0000-8888");
        builder.instance.setFechaCreacion(LocalDateTime.of(2020, 1, 29, 12, 0));
        return builder;
    }

    public AnuncioBuilder perdido() {
        this.instance.setTipo(TipoAnuncio.PERDIDO);
        return this;
    }

    public AnuncioBuilder encontrado() {
        this.instance.setTipo(TipoAnuncio.ENCONTRADO);
        return this;
    }

    public AnuncioBuilder conFotos(Imagen... imgs) {
        HashSet<Imagen> set = new HashSet<>();
        for (int i = 0; i < imgs.length; i++) {
            imgs[i].setPosicion(i+1);
            set.add(imgs[i]);
        }
        this.instance.setFotos(set);
        return this;
    }

}
