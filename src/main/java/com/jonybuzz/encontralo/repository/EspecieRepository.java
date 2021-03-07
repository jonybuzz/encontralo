package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Especie;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class EspecieRepository extends InMemoryRepository<Especie, Integer> {

    @Override
    @PostConstruct
    protected final void init() {
        load(
                new Especie(1, "Perro"),
                new Especie(2, "Gato")
        );
    }

}
