package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.ApplicationTests;
import com.jonybuzz.encontralo.dto.ImagenDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.model.TipoAnuncio;
import com.jonybuzz.encontralo.model.Ubicacion;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class AnuncioServiceTests extends ApplicationTests {

    @Autowired
    AnuncioService anuncioService;

    @Autowired
    AnuncioRepository anuncioRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void crearAnuncio_recibeAnuncioEstandar_persisteOkYDevuelveId() {

        NuevoAnuncioDto nuevoAnuncioDto = nuevoAnuncioEstandar();
        Long id = anuncioService.crearAnuncio(nuevoAnuncioDto);
        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "anuncio")).isEqualTo(1);
        assertThat(id).isNotNull();
        var anuncioPersistido = anuncioRepository.getOne(id);
        assertThat(anuncioPersistido.getColores()).hasSize(2);
        assertThat(anuncioPersistido.getNombreMascotaNormalizado()).isEqualTo("SENOR ITATI");
    }

    private NuevoAnuncioDto nuevoAnuncioEstandar() {
        return NuevoAnuncioDto.builder()
                .tipo(TipoAnuncio.PERDIDO)
                .nombreMascota("Señor Itatí,,")
                .especieId(1)
                .razaId(3)
                .tamanioId(3)
                .franjaEtariaId(3)
                .coloresIds(Set.of(2, 3))
                .tieneCollar(true)
                .pelajeId(1)
                .fotos(Set.of(ImagenDto.builder()
                                .posicion(1)
                                .datosBase64("R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==")
                                .build(),
                        ImagenDto.builder()
                                .posicion(2)
                                .datosBase64("R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==")
                                .build()))
                .ubicacion(new Ubicacion(new BigDecimal("37.1234"), new BigDecimal("157.0001")))
                .comentario("Tiene una chapita con mi teléfono!")
                .telefonoContacto("11-5678-0987")
                .build();
    }
}
