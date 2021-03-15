package com.jonybuzz.encontralo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroAnuncios {

    private TipoAnuncio tipoAnuncio;
    private Integer especieId;
    private Integer pagina = 1;

}
