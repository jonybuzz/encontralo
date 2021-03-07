INSERT INTO raza
(id, especie_id, nombre        ) VALUES
( 1,          1, 'Mestizo'     ),
( 2,          1, 'Bulldog'     ),
( 3,          1, 'Maltés'      ),
( 4,          1, 'Caniche Toy' ),
( 5,          2, 'Siamés'      );

INSERT INTO color
(id, nombre  , codigo_hex) VALUES
( 1, 'Beige' , 'F9E4B7'  ),
( 2, 'Negro' , '0C0C0C'  ),
( 3, 'Blanco', 'FAFAFA'  ),
( 4, 'Marrón', '693B1F'  );

INSERT INTO localidad
(id, nombre      , provincia) VALUES
( 1, 'Lanús'     , 'Buenos Aires'  ),
( 2, 'Avellaneda', 'Buenos Aires'  ),
( 3, 'Quilmes'   , 'Buenos Aires'  );