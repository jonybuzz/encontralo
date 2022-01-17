package com.jonybuzz.encontralo.client.facebook.dto;

import lombok.Data;

import java.util.List;

@Data
public class RespuestaPaginadaFbDto<T> {
    private List<T> data;
    private PagingFbDto paging;
}
