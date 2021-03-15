package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Integer> {

}
