package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer> {

    Set<Raza> findByEspecieId(Integer id);

}
