package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.model.Especie;
import com.jonybuzz.encontralo.model.Raza;
import com.jonybuzz.encontralo.model.Tamanio;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Transactional
    public Long crearAnuncio(NuevoAnuncioDto dto) {

        Anuncio anuncio = new Anuncio();

        anuncio.setTipo(dto.getTipo());

        anuncio.setNombreMascota(dto.getNombreMascota());

        anuncio.setEspecie(new Especie(dto.getEspecieId()));

        Raza raza = new Raza();
        raza.setId(dto.getRazaId());
        anuncio.setRaza(raza);

        Tamanio tamanio = new Tamanio();
        tamanio.setId(dto.getTamanioId());
        anuncio.setTamanio(tamanio);

        //HACER TODOS ENUM??

        return anuncioRepository.save(anuncio).getId();
    }
}
