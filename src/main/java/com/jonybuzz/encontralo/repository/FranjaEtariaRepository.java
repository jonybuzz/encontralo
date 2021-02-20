package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.FranjaEtaria;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class FranjaEtariaRepository extends InMemoryRepository<FranjaEtaria, Integer> {

    @Override
    @PostConstruct
    protected final void init() {
        load(
                new FranjaEtaria(1, "Cachorro", "hasta 6 meses"),
                new FranjaEtaria(2, "Adulto joven", "6 meses a 2 años"),
                new FranjaEtaria(3, "Adulto", "2 a 7 años"),
                new FranjaEtaria(4, "Adulto mayor", "más de 7 años")
        );
    }

}
