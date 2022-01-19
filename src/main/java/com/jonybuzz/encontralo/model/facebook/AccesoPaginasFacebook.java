package com.jonybuzz.encontralo.model.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccesoPaginasFacebook {

    @Id
    private String idPagina;

    private String accessToken;

}
