package com.jonybuzz.encontralo.dto;

import com.jonybuzz.encontralo.model.Imagen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagenUploadDto {

    private Integer posicion;
    private String datosBase64;

    public Imagen toImagen() {
        var imagen = new Imagen();
        imagen.setPosicion(this.posicion);
        imagen.setDatos(Base64.getDecoder().decode(this.datosBase64));
        return imagen;
    }
}
