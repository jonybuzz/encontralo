package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.ApplicationTests;
import com.jonybuzz.encontralo.dto.AnuncioDto;
import com.jonybuzz.encontralo.dto.ImagenDownloadDto;
import com.jonybuzz.encontralo.dto.ImagenUploadDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.dto.SeleccionablesDto;
import com.jonybuzz.encontralo.model.Color;
import com.jonybuzz.encontralo.model.Especie;
import com.jonybuzz.encontralo.model.FiltroAnuncios;
import com.jonybuzz.encontralo.model.FranjaEtaria;
import com.jonybuzz.encontralo.model.Imagen;
import com.jonybuzz.encontralo.model.Pelaje;
import com.jonybuzz.encontralo.model.Raza;
import com.jonybuzz.encontralo.model.TipoAnuncio;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import com.jonybuzz.encontralo.testutils.builder.AnuncioBuilder;
import com.jonybuzz.encontralo.testutils.builder.ImagenBuilder;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Set;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class SeleccionablesServiceTests extends ApplicationTests {

    @Autowired
    SeleccionablesService seleccionablesService;

    @Autowired
    EntityManager em;

    @Test
    void obtenerSeleccionables_estaTodoOk_devuelveDtoCompleto() {
        SeleccionablesDto dto = seleccionablesService.obtenerSeleccionables();

        assertThat(dto.getColores()).extracting(Color::getNombre)
                .containsExactlyInAnyOrder("Beige", "Negro", "Blanco", "Marrón");
        assertThat(dto.getEspecies()).hasSize(2);
        assertThat(dto.getEspecies().get(0).getRazas()).extracting(Raza::getNombre)
                .containsExactlyInAnyOrder("Mestizo", "Bulldog", "Maltés", "Caniche Toy");
        assertThat(dto.getEspecies().get(1).getRazas()).extracting(Raza::getNombre)
                .containsExactlyInAnyOrder("Siamés");
        assertThat(dto.getFranjasEtarias()).extracting(FranjaEtaria::getNombre)
                .containsExactlyInAnyOrder("Cachorro", "Adulto joven", "Adulto", "Adulto mayor");
        assertThat(dto.getPelajes()).extracting(Pelaje::getNombre)
                .containsExactlyInAnyOrder("Corto", "Largo");
    }

}
