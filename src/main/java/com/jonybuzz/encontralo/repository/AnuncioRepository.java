package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
