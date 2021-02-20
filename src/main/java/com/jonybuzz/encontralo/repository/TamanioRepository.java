package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Tamanio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class TamanioRepository extends InMemoryRepository<Tamanio, Integer> {

    @Autowired
    private EspecieRepository especieRepository;

    @Override
    @PostConstruct
    protected final void init() {
        load(
                especieRepository.findAll().stream()
                        .flatMap(especie -> especie.getTamanios().stream())
                        .toArray(Tamanio[]::new)
        );
    }

}
