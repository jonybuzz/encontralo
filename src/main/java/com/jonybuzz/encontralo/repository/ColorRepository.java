package com.jonybuzz.encontralo.repository;

import com.jonybuzz.encontralo.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
