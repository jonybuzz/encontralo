package com.jonybuzz.encontralo.testutils.builder;

import com.jonybuzz.encontralo.model.*;
import com.somospnt.test.builder.AbstractPersistenceBuilder;
import java.math.BigDecimal;
import java.util.Set;

public class ImagenBuilder extends AbstractPersistenceBuilder<Imagen> {

    private ImagenBuilder() {
        this.instance = new Imagen();
    }

    public static ImagenBuilder vacia() {
        var builder = new ImagenBuilder();
        builder.instance.setPosicion(1);
        builder.instance.setDatos("".getBytes());
        return builder;
    }

}
