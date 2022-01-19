package com.jonybuzz.encontralo.service.importador;

import com.jonybuzz.encontralo.ApplicationTests;
import com.jonybuzz.encontralo.model.Color;
import com.jonybuzz.encontralo.model.FranjaEtaria;
import com.jonybuzz.encontralo.model.Localidad;
import com.jonybuzz.encontralo.model.Pelaje;
import com.jonybuzz.encontralo.model.Raza;
import com.jonybuzz.encontralo.model.Sexo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

class ImportadorAnunciosFacebookTests extends ApplicationTests {

    @Autowired
    ImportadorAnunciosFacebook importadorAnunciosFacebook;

    @Test
    void obtenerSeleccionables_estaTodoOk_devuelveDtoCompleto() {
        importadorAnunciosFacebook.importar(LocalDateTime.of(2021, 1, 10, 12, 30), LocalDateTime.of(2021, 1, 10, 12, 30));
    }

}
