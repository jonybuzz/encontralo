package com.jonybuzz.encontralo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ubicacion {

    private BigDecimal lat;
    private BigDecimal lon;

}
