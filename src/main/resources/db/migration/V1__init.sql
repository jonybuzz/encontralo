CREATE TABLE raza (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  especie_id INT NOT NULL,
  CONSTRAINT raza_pk PRIMARY KEY (id),
  CONSTRAINT raza_uq UNIQUE (nombre, especie_id)
);

CREATE TABLE color (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  codigo_hex VARCHAR(6) NOT NULL,
  CONSTRAINT color_pk PRIMARY KEY (id),
  CONSTRAINT color_uq UNIQUE (nombre),
  CONSTRAINT color_hex_uq UNIQUE (codigo_hex)
);

CREATE TABLE imagen (
  id INT AUTO_INCREMENT,
  posicion INT NOT NULL,
  datos MEDIUMBLOB NOT NULL,
  CONSTRAINT imagen_pk PRIMARY KEY (id)
);

CREATE TABLE localidad (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  provincia VARCHAR(64) NOT NULL,
  CONSTRAINT localidad_pk PRIMARY KEY (id),
  CONSTRAINT localidad_uq UNIQUE (nombre, provincia)
);

CREATE TABLE anuncio (
  id INT AUTO_INCREMENT,
  tipo INT NOT NULL,
  nombre_mascota VARCHAR(100),
  nombre_mascota_normalizado VARCHAR(100),
  especie_id INT,
  raza_id INT,
  sexo_id INT,
  tamanio_id INT,
  franja_etaria_id INT,
  tiene_collar BOOLEAN,
  pelaje_id INT,
  fotos_id INT,
  localidad_id INT,
  comentario VARCHAR(255),
  telefono_contacto VARCHAR(255),
  fecha_creacion DATETIME NOT NULL,
  CONSTRAINT anuncio_pk PRIMARY KEY (id),
  CONSTRAINT anuncio_raza_fk FOREIGN KEY (raza_id) REFERENCES raza(id),
  CONSTRAINT anuncio_localidad_fk FOREIGN KEY (localidad_id) REFERENCES localidad(id)
);

CREATE TABLE anuncio_fotos (
  fotos_id INT NOT NULL,
  anuncio_id INT NOT NULL,
  CONSTRAINT anuncio_fotos_pk PRIMARY KEY (fotos_id, anuncio_id),
  CONSTRAINT anuncio_fotos_fk1 FOREIGN KEY (fotos_id) REFERENCES imagen(id),
  CONSTRAINT anuncio_fotos_fk2 FOREIGN KEY (anuncio_id) REFERENCES anuncio(id)
);

CREATE TABLE anuncio_colores (
  colores_id INT NOT NULL,
  anuncio_id INT NOT NULL,
  CONSTRAINT anuncio_colores_pk PRIMARY KEY (colores_id, anuncio_id),
  CONSTRAINT anuncio_colores_fk1 FOREIGN KEY (anuncio_id) REFERENCES anuncio(id),
  CONSTRAINT anuncio_colores_fk2 FOREIGN KEY (colores_id) REFERENCES color(id)
);

CREATE TABLE acceso_paginas_facebook (
  id_pagina VARCHAR(50) NOT NULL,
  access_token VARCHAR(255) NOT NULL,
  CONSTRAINT acceso_paginas_facebook_pk PRIMARY KEY (id_pagina)
);