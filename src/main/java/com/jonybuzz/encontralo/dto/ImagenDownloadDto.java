package com.jonybuzz.encontralo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImagenDownloadDto {

    private Integer posicion;
    private String url;

}
