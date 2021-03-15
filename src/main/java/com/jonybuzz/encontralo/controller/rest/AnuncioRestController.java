package com.jonybuzz.encontralo.controller.rest;

import com.jonybuzz.encontralo.dto.AnuncioResumidoDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.FiltroAnuncios;
import com.jonybuzz.encontralo.model.TipoAnuncio;
import com.jonybuzz.encontralo.service.AnuncioService;
import com.jonybuzz.encontralo.service.exception.AnuncioIncompletoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnuncioRestController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping("/api/anuncios")
    public Long crearAnuncio(@RequestBody NuevoAnuncioDto nuevoAnuncioDto) throws AnuncioIncompletoException {
        return anuncioService.crearAnuncio(nuevoAnuncioDto);
    }

    @GetMapping("/api/anuncios")
    public Page<AnuncioResumidoDto> buscarAnunciosResumidos(
            @RequestParam(required = false) TipoAnuncio tipoAnuncio,
            @RequestParam(required = false) Integer especieId,
            @RequestParam(defaultValue = "1") Integer pagina) {
        FiltroAnuncios filtro = new FiltroAnuncios(tipoAnuncio, especieId, pagina);
        return anuncioService.buscarAnunciosResumidos(filtro);
    }


}
