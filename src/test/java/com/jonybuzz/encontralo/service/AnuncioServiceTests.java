package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.ApplicationTests;
import com.jonybuzz.encontralo.dto.ImagenDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.TipoAnuncio;
import com.jonybuzz.encontralo.model.Ubicacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class AnuncioServiceTests extends ApplicationTests {

    @Autowired
    AnuncioService anuncioService;

    @Test
    public void crearAnuncio_recibeAnuncioEstandar_persisteYDevuelveId() {

        NuevoAnuncioDto nuevoAnuncioDto = nuevoAnuncioEstandar();
        Long id = anuncioService.crearAnuncio(nuevoAnuncioDto);
        assertThat(id).isNotNull();
    }

    private NuevoAnuncioDto nuevoAnuncioEstandar() {
        NuevoAnuncioDto nuevoAnuncioDto = NuevoAnuncioDto.builder()
                .tipo(TipoAnuncio.PERDIDO)
                .nombreMascota("Señor Itatí,,")
                .especieId(1)
                .razaId(3)
                .tamanioId(4)
                .franjaEtariaId(3)
                .coloresIds(Set.of(5, 6))
                .tieneCollar(true)
                .pelajeId(1)
                .fotos(Set.of(ImagenDto.builder()
                                .posicion(1).datosBase64("R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==")
                                .build(),
                        ImagenDto.builder()
                                .posicion(2).datosBase64("R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==")
                                .build()))
                .ubicacion(new Ubicacion(new BigDecimal("37.1234"), new BigDecimal("157.0001")))
                .comentario("Tiene una chapita con mi teléfono!")
                .telefonoContacto("11-5678-0987")
                .build();
        return nuevoAnuncioDto;
    }
}
