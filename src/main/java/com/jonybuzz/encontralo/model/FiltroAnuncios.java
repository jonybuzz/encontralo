package com.jonybuzz.encontralo.model;

import lombok.Data;

@Data
public class FiltroAnuncios {

    private TipoAnuncio tipoAnuncio;
    private Integer especieId;
    private Integer pagina = 1;

}
