package com.jonybuzz.encontralo.service.importador;

import com.jonybuzz.encontralo.model.OrigenAnuncio;

import java.time.LocalDateTime;

public interface ImportadorAnuncios {

    OrigenAnuncio getOrigen();

    void importar(LocalDateTime desde, LocalDateTime hasta);

}
