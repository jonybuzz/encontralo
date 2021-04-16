package com.jonybuzz.encontralo.controller;

import com.google.gson.Gson;
import com.jonybuzz.encontralo.ApplicationTests;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.TipoAnuncio;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AnuncioControllerTests extends ApplicationTests {

  @Autowired
  protected MockMvc mockMvc;

  @Test
  @SneakyThrows
  public void crearAnuncio_datosMinimos_devuelveOk() {
    NuevoAnuncioDto nuevoAnuncioDto = new NuevoAnuncioDto();
    nuevoAnuncioDto.setTipo(TipoAnuncio.PERDIDO);
    nuevoAnuncioDto.setEspecieId(1);
    nuevoAnuncioDto.setLocalidadId(2);
    nuevoAnuncioDto.setTelefonoContacto("21223");

    Gson gson = new Gson();

    mockMvc
        .perform(post("/api/anuncios")
            .content(gson.toJson(nuevoAnuncioDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();
  }

  @Test
  @SneakyThrows
  public void crearAnuncio_tipoNull_devuelveBadRequest() {
    NuevoAnuncioDto nuevoAnuncioDto = new NuevoAnuncioDto();
    nuevoAnuncioDto.setEspecieId(1);
    nuevoAnuncioDto.setLocalidadId(2);
    nuevoAnuncioDto.setTelefonoContacto("21223");

    Gson gson = new Gson();

    mockMvc
        .perform(post("/api/anuncios")
            .content(gson.toJson(nuevoAnuncioDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Faltan completar el campo tipo.")))
        .andReturn();
  }

  @Test
  @SneakyThrows
  public void crearAnuncio_especieNull_devuelveBadRequest() {
    NuevoAnuncioDto nuevoAnuncioDto = new NuevoAnuncioDto();
    nuevoAnuncioDto.setTipo(TipoAnuncio.PERDIDO);
    nuevoAnuncioDto.setLocalidadId(2);
    nuevoAnuncioDto.setTelefonoContacto("21223");

    Gson gson = new Gson();

    mockMvc
        .perform(post("/api/anuncios")
            .content(gson.toJson(nuevoAnuncioDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Faltan completar el campo especie.")))
        .andReturn();
  }

  @Test
  @SneakyThrows
  public void crearAnuncio_localidadNull_devuelveBadRequest() {
    NuevoAnuncioDto nuevoAnuncioDto = new NuevoAnuncioDto();
    nuevoAnuncioDto.setTipo(TipoAnuncio.PERDIDO);
    nuevoAnuncioDto.setEspecieId(1);
    nuevoAnuncioDto.setTelefonoContacto("21223");

    Gson gson = new Gson();

    mockMvc
        .perform(post("/api/anuncios")
            .content(gson.toJson(nuevoAnuncioDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Faltan completar el campo localidad.")))
        .andReturn();
  }

  @Test
  @SneakyThrows
  public void crearAnuncio_telefonoNull_devuelveBadRequest() {
    NuevoAnuncioDto nuevoAnuncioDto = new NuevoAnuncioDto();
    nuevoAnuncioDto.setTipo(TipoAnuncio.PERDIDO);
    nuevoAnuncioDto.setEspecieId(1);
    nuevoAnuncioDto.setLocalidadId(2);

    Gson gson = new Gson();

    mockMvc
        .perform(post("/api/anuncios")
            .content(gson.toJson(nuevoAnuncioDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Faltan completar el campo telefono.")))
        .andReturn();
  }

  @Test
  @SneakyThrows
  public void crearAnuncio_telefonoNullWithTypeNull_devuelveBadRequestWithMessageWithTwoError() {
    NuevoAnuncioDto nuevoAnuncioDto = new NuevoAnuncioDto();
    nuevoAnuncioDto.setEspecieId(1);
    nuevoAnuncioDto.setLocalidadId(2);

    Gson gson = new Gson();

    mockMvc
        .perform(post("/api/anuncios")
            .content(gson.toJson(nuevoAnuncioDto))
            .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(containsString("Faltan completar los campos: telefono, tipo.")))
        .andReturn();
  }

}
