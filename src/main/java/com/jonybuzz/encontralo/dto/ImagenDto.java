package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.Imagen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.nio.charset.StandardCharsets.UTF_8;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagenDto {

    private Integer posicion;
    private String datosBase64;

    public Imagen toImagen() {
        var imagen = new Imagen();
        imagen.setPosicion(this.posicion);
        imagen.setDatos(java.util.Base64.getDecoder().decode(this.datosBase64.getBytes(UTF_8)));
        return imagen;
    }
}
