package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Pelaje;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class PelajeRepository extends InMemoryRepository<Pelaje, Integer> {

    @Override
    @PostConstruct
    protected final void init() {
        load(
                new Pelaje(1, "Corto"),
                new Pelaje(1, "Largo")
        );
    }

}
