package com.jonybuzz.encontralo.model;

import lombok.Data;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Data
public class Ubicacion {

    private BigDecimal lat;
    private BigDecimal lon;

}
