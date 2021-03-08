package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Sexo;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class SexoRepository extends InMemoryRepository<Sexo, Integer> {

    @Override
    @PostConstruct
    protected final void init() {
        load(
                new Sexo(1, "Macho"),
                new Sexo(2, "Hembra")
        );
    }

}
