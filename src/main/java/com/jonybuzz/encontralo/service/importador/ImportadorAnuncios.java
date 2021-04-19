package com.jonybuzz.encontralo.service.importador;

import java.time.LocalDateTime;

public interface ImportadorAnuncios {

    void importar(LocalDateTime desde, LocalDateTime hasta);

}
