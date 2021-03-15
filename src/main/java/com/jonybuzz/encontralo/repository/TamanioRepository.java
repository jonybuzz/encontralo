package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Tamanio;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class TamanioRepository extends InMemoryRepository<Tamanio, Integer> {

    @Override
    @PostConstruct
    protected final void init() {
        load(
                new Tamanio(1, "Chico", 1),
                new Tamanio(2, "Mediano", 1),
                new Tamanio(3, "Grande", 1),
                new Tamanio(4, "Único", 2)
        );
    }

    public Set<Tamanio> findByEspecieId(Integer id) {
        return this.entidades.entrySet().stream()
                .filter(entry -> entry.getValue().getEspecieId().equals(id))
                .map(Map.Entry::getValue)
                .collect(Collectors.toSet());
    }

}
