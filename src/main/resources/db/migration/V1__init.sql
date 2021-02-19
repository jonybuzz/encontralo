CREATE TABLE especie (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  CONSTRAINT especie_pk PRIMARY KEY (id)
);

CREATE TABLE raza (
  id INT AUTO_INCREMENT,
  nombre VARCHAR(64) NOT NULL,
  especie_id INT NOT NULL,
  CONSTRAINT raza_pk PRIMARY KEY (id),
  CONSTRAINT raza_especie_fk FOREIGN KEY (especie_id) REFERENCES especie(id)
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
  CONSTRAINT anuncio_especie_fk FOREIGN KEY (especie_id) REFERENCES especie(id),
  CONSTRAINT anuncio_raza_fk FOREIGN KEY (raza_id) REFERENCES raza(id),
  CONSTRAINT anuncio_tamanio_fk FOREIGN KEY (tamanio_id) REFERENCES tamanio(id),
  CONSTRAINT anuncio_franja_etaria_fk FOREIGN KEY (franja_etaria_id) REFERENCES franja_etaria(id),
  CONSTRAINT anuncio_pelaje_fk FOREIGN KEY (pelaje_id) REFERENCES pelaje(id)
);

CREATE TABLE imagen (
  id INT AUTO_INCREMENT,
  posicion INT NOT NULL,
  datos MEDIUMBLOB NOT NULL,
  anuncio_id INT NOT NULL,
  CONSTRAINT imagen_pk PRIMARY KEY (id),
  CONSTRAINT imagen_anuncio_fk FOREIGN KEY (anuncio_id) REFERENCES anuncio(id)
);

CREATE TABLE color_x_anuncio (
  color_id INT NOT NULL,
  anuncio_id INT NOT NULL,
  CONSTRAINT color_x_anuncio_pk PRIMARY KEY (color_id, anuncio_id),
  CONSTRAINT color_x_anuncio_fk1 FOREIGN KEY (anuncio_id) REFERENCES anuncio(id),
  CONSTRAINT color_x_anuncio_fk2 FOREIGN KEY (color_id) REFERENCES color(id)
);