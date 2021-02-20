package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.dto.ImagenDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import com.jonybuzz.encontralo.repository.ColorRepository;
import com.jonybuzz.encontralo.repository.RazaRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private ColorRepository colorRepository;

    @Transactional
    public Long crearAnuncio(NuevoAnuncioDto dto) {

        Anuncio anuncio = new Anuncio();
        anuncio.setTipo(dto.getTipo());
        anuncio.setNombreMascota(dto.getNombreMascota());
        anuncio.setNombreMascotaNormalizado(this.normalizar(dto.getNombreMascota()));
        anuncio.setEspecieId(dto.getEspecieId());
        anuncio.setRaza(razaRepository.getOne(dto.getRazaId()));
        anuncio.setTamanioId(dto.getTamanioId());
        anuncio.setFranjaEtariaId(dto.getFranjaEtariaId());
        anuncio.setColores(new HashSet<>(colorRepository.findAllById(dto.getColoresIds())));
        anuncio.setTieneCollar(dto.getTieneCollar());
        anuncio.setPelajeId(dto.getPelajeId());
        anuncio.setFotos(dto.getFotos().stream()
                .map(ImagenDto::toImagen).collect(Collectors.toSet()));
        anuncio.setUbicacion(dto.getUbicacion());
        anuncio.setComentario(dto.getComentario());
        anuncio.setTelefonoContacto(dto.getTelefonoContacto());
        return anuncioRepository.saveAndFlush(anuncio).getId();
    }

    private String normalizar(String str) {
        return StringUtils.stripAccents(StringUtils.normalizeSpace(str))
                .toUpperCase().replaceAll("[^A-Z\\s]", "");
    }

}
