CREATE TABLE especie (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  CONSTRAINT especie_pk PRIMARY KEY (id)
);

CREATE TABLE raza (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  especie_id INT NOT NULL,
  CONSTRAINT raza_pk PRIMARY KEY (id)
);

CREATE TABLE tamanio (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  especie_id INT NOT NULL,
  CONSTRAINT tamanio_pk PRIMARY KEY (id),
  CONSTRAINT tamanio_especie_fk FOREIGN KEY (especie_id) REFERENCES especie(id)
);

CREATE TABLE franja_etaria (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  CONSTRAINT franja_etaria_pk PRIMARY KEY (id)
);

CREATE TABLE color (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  codigo_hex VARCHAR(6) NOT NULL,
  CONSTRAINT color_pk PRIMARY KEY (id)
);

CREATE TABLE pelaje (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  CONSTRAINT pelaje_pk PRIMARY KEY (id)
);

CREATE TABLE anuncio (
  id INT AUTO_INCREMENT,
  tipo INT NOT NULL,
  nombre_mascota VARCHAR(255),
  nombre_mascota_normalizado VARCHAR(255),
  especie_id INT,
  raza_id INT,
  tamanio_id INT,
  franja_etaria_id INT,
  tiene_collar BOOLEAN,
  pelaje_id INT,
  fotos_id INT,
  ubicacion_lat DOUBLE,
  ubicacion_lon DOUBLE,
  comentario VARCHAR,
  telefono_contacto VARCHAR(255),
  CONSTRAINT anuncio_pk PRIMARY KEY (id),
  CONSTRAINT anuncio_raza_fk FOREIGN KEY (raza_id) REFERENCES raza(id)
);

CREATE TABLE imagen (
  id INT AUTO_INCREMENT,
  posicion INT NOT NULL,
  datos MEDIUMBLOB NOT NULL,
  CONSTRAINT imagen_pk PRIMARY KEY (id)
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