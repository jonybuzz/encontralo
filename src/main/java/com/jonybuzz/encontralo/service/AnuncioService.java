package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.dto.AnuncioDto;
import com.jonybuzz.encontralo.dto.ImagenDownloadDto;
import com.jonybuzz.encontralo.dto.ImagenUploadDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.model.FiltroAnuncios;
import com.jonybuzz.encontralo.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.EMPTY_SET;

@Service
public class AnuncioService {

    public static final int PAGE_SIZE = 15;
    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private RazaRepository razaRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private EspecieRepository especieRepository;
    @Autowired
    private TamanioRepository tamanioRepository;
    @Autowired
    private FranjaEtariaRepository franjaEtariaRepository;
    @Autowired
    private PelajeRepository pelajeRepository;

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
                .map(ImagenUploadDto::toImagen).collect(Collectors.toSet()));
        anuncio.setUbicacion(dto.getUbicacion());
        anuncio.setComentario(dto.getComentario());
        anuncio.setTelefonoContacto(dto.getTelefonoContacto());
        return anuncioRepository.saveAndFlush(anuncio).getId();
    }

    @Transactional(readOnly = true)
    public Page<AnuncioDto> buscarAnuncios(FiltroAnuncios filtro) {
        var anuncioBuscado = new Anuncio();
        anuncioBuscado.setTipo(filtro.getTipoAnuncio());
        anuncioBuscado.setEspecieId(filtro.getEspecieId());
        Page<Anuncio> pagina = anuncioRepository.findAll(
                Example.of(anuncioBuscado, ExampleMatcher.matchingAll().withIgnoreNullValues()),
                PageRequest.of(filtro.getPagina() - 1, PAGE_SIZE));
        List<AnuncioDto> dtos = pagina.stream().map(this::anuncioToDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pagina.getPageable(), pagina.getTotalElements());
    }

    private String normalizar(String str) {
        return StringUtils.stripAccents(StringUtils.normalizeSpace(str))
                .toUpperCase().replaceAll("[^A-Z\\s]", "");
    }

    public AnuncioDto anuncioToDto(Anuncio anuncio) {
        return AnuncioDto.builder()
                .id(anuncio.getId())
                .tipo(anuncio.getTipo())
                .nombreMascota(anuncio.getNombreMascota())
                .especie(especieRepository.getOne(anuncio.getEspecieId()))
                .raza(anuncio.getRaza())
                .tamanio(tamanioRepository.getOne(anuncio.getTamanioId()))
                .franjaEtaria(franjaEtariaRepository.getOne(anuncio.getFranjaEtariaId()))
                .colores(anuncio.getColores() == null ? EMPTY_SET : anuncio.getColores())
                .tieneCollar(anuncio.getTieneCollar())
                .pelaje(pelajeRepository.getOne(anuncio.getPelajeId()))
                .fotos(anuncio.getFotos() == null ? EMPTY_SET : anuncio.getFotos().stream()
                        .map(foto -> ImagenDownloadDto.builder()
                                .posicion(foto.getPosicion())
                                .url("/recursos/imagenes/" + foto.getId())
                                .build())
                        .collect(Collectors.toSet()))
                .ubicacion(anuncio.getUbicacion())
                .comentario(anuncio.getComentario())
                .telefonoContacto(anuncio.getTelefonoContacto())
                .build();
    }

}
