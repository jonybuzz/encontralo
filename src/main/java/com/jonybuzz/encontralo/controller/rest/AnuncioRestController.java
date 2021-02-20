package com.jonybuzz.encontralo.controller.rest;

import com.jonybuzz.encontralo.dto.AnuncioDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.FiltroAnuncios;
import com.jonybuzz.encontralo.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnuncioRestController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping("/api/anuncios")
    public Long crearAnuncio(@RequestBody NuevoAnuncioDto nuevoAnuncioDto) {
        return anuncioService.crearAnuncio(nuevoAnuncioDto);
    }

    @GetMapping("/api/anuncios")
    public Page<AnuncioDto> obtenerAnuncio(@RequestBody FiltroAnuncios filtro) {
        return anuncioService.buscarAnuncios(filtro);
    }


}