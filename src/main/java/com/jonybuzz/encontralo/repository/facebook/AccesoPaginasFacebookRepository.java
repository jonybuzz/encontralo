package com.jonybuzz.encontralo.repository.facebook;

import com.jonybuzz.encontralo.model.facebook.AccesoPaginasFacebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccesoPaginasFacebookRepository extends JpaRepository<AccesoPaginasFacebook, String> {
}
