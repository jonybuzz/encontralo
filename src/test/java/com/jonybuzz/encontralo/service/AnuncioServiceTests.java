package com.jonybuzz.encontralo.service;

import com.jonybuzz.encontralo.ApplicationTests;
import com.jonybuzz.encontralo.dto.AnuncioResumidoDto;
import com.jonybuzz.encontralo.dto.ImagenDownloadDto;
import com.jonybuzz.encontralo.dto.ImagenUploadDto;
import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import com.jonybuzz.encontralo.model.Especie;
import com.jonybuzz.encontralo.model.FiltroAnuncios;
import com.jonybuzz.encontralo.model.Imagen;
import com.jonybuzz.encontralo.model.Sexo;
import com.jonybuzz.encontralo.model.TipoAnuncio;
import com.jonybuzz.encontralo.repository.AnuncioRepository;
import com.jonybuzz.encontralo.testutils.builder.AnuncioBuilder;
import com.jonybuzz.encontralo.testutils.builder.ImagenBuilder;
import lombok.SneakyThrows;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class AnuncioServiceTests extends ApplicationTests {

    @Autowired
    AnuncioService anuncioService;

    @Autowired
    AnuncioRepository anuncioRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager em;

    @Test
    @SneakyThrows
    void crearAnuncio_recibeAnuncioEstandar_persisteOkYDevuelveId() {

        NuevoAnuncioDto nuevoAnuncioDto = nuevoAnuncioPerroPerdido();
        //Ejecucion
        Long id = anuncioService.crearAnuncio(nuevoAnuncioDto);
        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "anuncio")).isEqualTo(1);
        assertThat(id).isNotNull();
        var anuncioPersistido = anuncioRepository.getOne(id);
        assertThat(anuncioPersistido.getColores()).hasSize(2);
        assertThat(anuncioPersistido.getNombreMascotaNormalizado()).isEqualTo("SENOR ITATI");
        assertThat(anuncioPersistido.getFechaCreacion()).isCloseTo(LocalDateTime.now(), within(5, SECONDS));
        assertThat(anuncioPersistido.getComentario()).isEqualTo("Tiene una chapita con mi teléfono! Revisar");
    }

    @Test
    @SneakyThrows
    void crearAnuncio_datosMinimos_persisteOkYDevuelveId() {

        NuevoAnuncioDto nuevoAnuncioDto = new NuevoAnuncioDto();
        nuevoAnuncioDto.setTipo(TipoAnuncio.PERDIDO);
        nuevoAnuncioDto.setEspecieId(1);
        nuevoAnuncioDto.setLocalidadId(2);
        nuevoAnuncioDto.setTelefonoContacto("21223");
        //Ejecucion
        Long id = anuncioService.crearAnuncio(nuevoAnuncioDto);
        assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "anuncio")).isEqualTo(1);
        assertThat(id).isNotNull();
        var anuncioPersistido = anuncioRepository.getOne(id);
        assertThat(anuncioPersistido.getColores()).isEmpty();
        assertThat(anuncioPersistido.getNombreMascotaNormalizado()).isNull();
        assertThat(anuncioPersistido.getFotos()).isEmpty();
        assertThat(anuncioPersistido.getTelefonoContacto()).isEqualTo("21223");
    }

    @SneakyThrows
    void crearAnuncio_recibeImagenDe5MB_lanzaIllegalArgumentExecption() {
        String base64 = getContentFromFile("image/image-5-mb.txt");
        NuevoAnuncioDto nuevoAnuncioDto = nuevoAnuncioPerroPerdido();
        nuevoAnuncioDto.setFotos(Set.of(ImagenUploadDto.builder()
            .posicion(1)
            .datosBase64(base64)
            .build()));
        //Ejecucion
        assertThatThrownBy(() -> anuncioService.crearAnuncio(nuevoAnuncioDto))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("El tamaño de la foto supera los 4MB.");
    }

    @Test
    void buscarAnunciosResumidos_recibeFiltroConTipoPerdidoYEspeciePerro_deveuelveSoloEsos() {
        var filtro = new FiltroAnuncios();
        filtro.setTipoAnuncio(TipoAnuncio.PERDIDO);
        filtro.setEspecieId(1);
        filtro.setPagina(1);
        Imagen fotoPerro2 = ImagenBuilder.vacia().build();
        Imagen fotoPerro = ImagenBuilder.vacia().build();
        AnuncioBuilder.perro().perdido().conFotos(fotoPerro, fotoPerro2).build(em); //OK
        AnuncioBuilder.gato().perdido().build(em);
        AnuncioBuilder.perro().encontrado().build(em);
        AnuncioBuilder.gato().encontrado().build(em);
        AnuncioBuilder.datosMinimos().encontrado().build(em);
        AnuncioBuilder.datosMinimos().razaPerro().perdido().build(em); //OK
        //Ejecucion
        var paginaAnuncios = anuncioService.buscarAnunciosResumidos(filtro);
        assertThat(paginaAnuncios).isNotEmpty();
        assertThat(paginaAnuncios.getPageable().getPageSize()).isEqualTo(15);
        assertThat(paginaAnuncios.getTotalPages()).isEqualTo(1);
        assertThat(paginaAnuncios.getTotalElements()).isEqualTo(2);
        Especie especiePerro = new Especie();
        especiePerro.setId(1);
        assertThat(paginaAnuncios.getContent())
                .extracting(AnuncioResumidoDto::getTipo, AnuncioResumidoDto::getEspecie)
                .containsExactly(Tuple.tuple(TipoAnuncio.PERDIDO, especiePerro), Tuple.tuple(TipoAnuncio.PERDIDO, especiePerro));
        assertThat(paginaAnuncios.getContent().get(0).getFotoPrincipal())
                .extracting(ImagenDownloadDto::getPosicion, ImagenDownloadDto::getUrl)
                .containsExactly(fotoPerro.getPosicion(), "/imagenes/" + fotoPerro.getId());
    }

    @Test
    void buscarAnunciosResumidos_recibeFiltroVacio_devuelveTodos() {
        var filtro = new FiltroAnuncios();
        filtro.setPagina(1);
        Imagen fotoPerro2 = ImagenBuilder.vacia().build();
        Imagen fotoPerro = ImagenBuilder.vacia().build();
        AnuncioBuilder.perro().perdido().conFotos(fotoPerro, fotoPerro2).build(em); //OK
        AnuncioBuilder.gato().perdido().build(em);
        AnuncioBuilder.perro().encontrado().build(em);
        AnuncioBuilder.gato().encontrado().build(em);
        AnuncioBuilder.datosMinimos().encontrado().build(em);
        AnuncioBuilder.datosMinimos().razaPerro().perdido().build(em); //OK
        //Ejecucion
        var paginaAnuncios = anuncioService.buscarAnunciosResumidos(filtro);
        assertThat(paginaAnuncios).isNotEmpty();
        assertThat(paginaAnuncios.getTotalElements()).isEqualTo(6);
    }

    @Test
    void buscarAnunciosResumidos_encuentraAnuncioConPocosDatos_deveuelveAnuncio() {
        AnuncioBuilder.vacio().encontrado().ahora().build(em);
        var filtro = new FiltroAnuncios();
        filtro.setTipoAnuncio(TipoAnuncio.ENCONTRADO);
        //Ejecucion
        var paginaAnuncios = anuncioService.buscarAnunciosResumidos(filtro);
        assertThat(paginaAnuncios.getTotalElements()).isEqualTo(1);
        assertThat(paginaAnuncios.getContent().get(0).getEspecie()).isNull();
        assertThat(paginaAnuncios.getContent().get(0).getTamanio()).isNull();
        assertThat(paginaAnuncios.getContent().get(0).getFotoPrincipal()).isNull();
        assertThat(paginaAnuncios.getContent().get(0).getColores()).isEmpty();
    }

    @Test
    void obtenerAnuncio_recibeIdAnuncioExistente_deveuelveAnuncio() {
        Imagen fotoPerro = ImagenBuilder.vacia().build();
        Anuncio anuncioGuardado = AnuncioBuilder.perro().encontrado().conFotos(fotoPerro).build(em);
        //Ejecucion
        var anuncio = anuncioService.obtenerAnuncio(anuncioGuardado.getId());
        assertThat(anuncio).isNotNull();
        assertThat(anuncio.getColores()).hasSize(1);
        assertThat(anuncio.getFotos())
                .extracting(ImagenDownloadDto::getPosicion, ImagenDownloadDto::getUrl)
                .containsExactly(Tuple.tuple(fotoPerro.getPosicion(), "/imagenes/" + fotoPerro.getId()));
    }

    @Test
    void obtenerAnuncio_recibeIdAnuncioConPocosDatos_deveuelveAnuncio() {
        Imagen fotoPerro = ImagenBuilder.vacia().build();
        Anuncio anuncioGuardado = AnuncioBuilder.vacio().encontrado().ahora().build(em);
        //Ejecucion
        var anuncio = anuncioService.obtenerAnuncio(anuncioGuardado.getId());
        assertThat(anuncio).isNotNull();
        assertThat(anuncio.getTipo()).isEqualTo(TipoAnuncio.ENCONTRADO);
        assertThat(anuncio.getColores()).isEmpty();
        assertThat(anuncio.getFotos()).isEmpty();
        assertThat(anuncio.getTamanio()).isNull();
        assertThat(anuncio.getEspecie()).isNull();
    }

    @Test
    void obtenerAnuncio_recibeIdAnuncioInexistente_lanzaExcepcion() {
        //Ejecucion
        assertThatThrownBy(() -> anuncioService.obtenerAnuncio(5L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("El anuncio #5 no existe.");
    }

    @SneakyThrows
    private NuevoAnuncioDto nuevoAnuncioPerroPerdido() {
        String base64 = getContentFromFile("image/image-8-kb.txt");
        return NuevoAnuncioDto.builder()
                .tipo(TipoAnuncio.PERDIDO)
                .nombreMascota("Señor Itatí,, ")
                .especieId(1)
                .razaId(3)
                .sexo(Sexo.MACHO)
                .tamanioId(3)
                .franjaEtariaId(3)
                .coloresIds(Set.of(2, 3))
                .tieneCollar(true)
                .pelajeId(1)
                .fotos(Set.of(ImagenUploadDto.builder()
                                .posicion(1)
                                .datosBase64(base64)
                                .build(),
                        ImagenUploadDto.builder()
                                .posicion(2)
                                .datosBase64(base64)
                                .build()))
                .localidadId(2)
                .comentario(" Tiene una chapita con mi teléfono! \n\r Revisar")
                .telefonoContacto("11-5678-0987")
                .build();
    }

}
