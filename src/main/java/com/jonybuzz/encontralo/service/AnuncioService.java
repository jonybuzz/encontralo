package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.dto.AnuncioDto;
import com.jonybuzz.encontralo.dto.ImagenDownloadDto;
import com.jonybuzz.encontralo.dto.ImagenUploadDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.model.FiltroAnuncios;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import com.jonybuzz.encontralo.repository.ColorRepository;
import com.jonybuzz.encontralo.repository.EspecieRepository;
import com.jonybuzz.encontralo.repository.FranjaEtariaRepository;
import com.jonybuzz.encontralo.repository.LocalidadRepository;
import com.jonybuzz.encontralo.repository.PelajeRepository;
import com.jonybuzz.encontralo.repository.RazaRepository;
import com.jonybuzz.encontralo.repository.SexoRepository;
import com.jonybuzz.encontralo.repository.TamanioRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

@Service
public class AnuncioService {

    public static final int PAGE_SIZE = 15;
    public static final String PATH_DESCARGA_IMAGENES = "imagenes";
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
    @Autowired
    private LocalidadRepository localidadRepository;
    @Autowired
    private SexoRepository sexoRepository;

    @Transactional
    public Long crearAnuncio(NuevoAnuncioDto dto) {

        Anuncio anuncio = new Anuncio();
        anuncio.setTipo(dto.getTipo());
        anuncio.setNombreMascota(dto.getNombreMascota());
        anuncio.setNombreMascotaNormalizado(this.normalizarNombre(dto.getNombreMascota()));
        anuncio.setEspecieId(dto.getEspecieId());
        anuncio.setRaza(razaRepository.getOne(dto.getRazaId()));
        anuncio.setSexoId(dto.getSexoId());
        anuncio.setTamanioId(dto.getTamanioId());
        anuncio.setFranjaEtariaId(dto.getFranjaEtariaId());
        anuncio.setColores(new HashSet<>(colorRepository.findAllById(dto.getColoresIds())));
        anuncio.setTieneCollar(dto.getTieneCollar());
        anuncio.setPelajeId(dto.getPelajeId());
        anuncio.setFotos(dto.getFotos().stream()
                .map(ImagenUploadDto::toImagen).collect(Collectors.toSet()));
        anuncio.setLocalidad(localidadRepository.getOne(dto.getLocalidadId()));
        anuncio.setComentario(StringUtils.normalizeSpace(dto.getComentario()));
        anuncio.setTelefonoContacto(dto.getTelefonoContacto());
        anuncio.setFechaCreacion(LocalDateTime.now());
        return anuncioRepository.saveAndFlush(anuncio).getId();
    }

    @Transactional(readOnly = true)
    public Page<AnuncioDto> buscarAnuncios(FiltroAnuncios filtro) {
        var anuncioBuscado = new Anuncio();
        anuncioBuscado.setTipo(filtro.getTipoAnuncio());
        anuncioBuscado.setEspecieId(filtro.getEspecieId());
        Page<Anuncio> pagina = anuncioRepository.findAll(
                Example.of(anuncioBuscado),
                PageRequest.of(filtro.getPagina() - 1, PAGE_SIZE));
        List<AnuncioDto> dtos = pagina.stream().map(this::anuncioToDto).collect(Collectors.toList());
        return new PageImpl<>(dtos, pagina.getPageable(), pagina.getTotalElements());
    }

    private String normalizarNombre(String str) {
        return StringUtils.stripAccents(StringUtils.normalizeSpace(str))
                .toUpperCase().replaceAll("[^A-Z\\s]", "");
    }

    public AnuncioDto anuncioToDto(Anuncio anuncio) {
        return AnuncioDto.builder()
                .id(anuncio.getId())
                .tipo(anuncio.getTipo())
                .nombreMascota(anuncio.getNombreMascota())
                .especie(anuncio.getEspecieId() == null ? null : especieRepository.getOne(anuncio.getEspecieId()))
                .raza(anuncio.getRaza())
                .sexo(anuncio.getSexoId() == null ? null : sexoRepository.getOne(anuncio.getSexoId()))
                .tamanio(anuncio.getTamanioId() == null ? null : tamanioRepository.getOne(anuncio.getTamanioId()))
                .franjaEtaria(anuncio.getFranjaEtariaId() == null ? null : franjaEtariaRepository.getOne(anuncio.getFranjaEtariaId()))
                .colores(anuncio.getColores() == null ? emptySet() : anuncio.getColores())
                .tieneCollar(anuncio.getTieneCollar())
                .pelaje(anuncio.getPelajeId() == null ? null : pelajeRepository.getOne(anuncio.getPelajeId()))
                .fotos(anuncio.getFotos() == null ? emptySet() : anuncio.getFotos().stream()
                        .map(foto -> ImagenDownloadDto.builder()
                                .posicion(foto.getPosicion())
                                .url(UriComponentsBuilder.fromPath("/")
                                        .pathSegment(PATH_DESCARGA_IMAGENES)
                                        .pathSegment(foto.getId().toString()).toUriString())
                                .build())
                        .collect(Collectors.toSet()))
                .localidad(anuncio.getLocalidad())
                .comentario(anuncio.getComentario())
                .telefonoContacto(anuncio.getTelefonoContacto())
                .fechaCreacion(anuncio.getFechaCreacion())
                .build();
    }

}
