package com.jonybuzz.encontralo.controller;

import com.jonybuzz.encontralo.model.Imagen;
import com.jonybuzz.encontralo.repository.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;

@Controller
public class ImagenesController {

    @Autowired
    private ImagenRepository imagenRepository;

    @GetMapping(value = "/imagenes/{imagenId}")
    public HttpEntity<byte[]> descargarImagen(@PathVariable Long imagenId) {

        Imagen imagen = imagenRepository.findById(imagenId)
                .orElseThrow(() -> new EntityNotFoundException("Imagen no encontrada"));
        byte[] datos = imagen.getDatos();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(datos.length);
        headers.setContentDisposition(
                ContentDisposition.builder("attachment").filename("ENC" + imagen.getId() + ".jpg").build());
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new HttpEntity<>(datos, headers);
    }

}
