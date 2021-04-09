package com.jonybuzz.encontralo.dto.facebook;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RespuestaPaginadaFacebookDto {
    private List<Map> data;
    private PagingFacebookDto paging;
}
