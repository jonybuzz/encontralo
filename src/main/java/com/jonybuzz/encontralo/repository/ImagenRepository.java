package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

}
