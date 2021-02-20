package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Especie;
import com.jonybuzz.encontralo.model.Tamanio;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class EspecieRepository extends InMemoryRepository<Especie, Integer> {

    @Override
    @PostConstruct
    protected final void init() {
        load(
                new Especie(1, "Perro",
                        new Tamanio(1, "Chico"),
                        new Tamanio(2, "Mediano"),
                        new Tamanio(3, "Grande")),
                new Especie(2, "Gato",
                        new Tamanio(4, "Unico"))
        );
    }

}
