CREATE DATABASE COLSEVI;

CREATE TABLE COLSEVI.tipo_documento(
	id_tipo_documento INT AUTO_INCREMENT,
    nombre VARCHAR (30) NOT NULL,
    descripcion VARCHAR (30) NOT NULL,
    PRIMARY KEY(id_tipo_documento)
);

CREATE TABLE COLSEVI.persona(
	id_persona INT AUTO_INCREMENT,
    tipo_doc INT NOT NULL,
    documento VARCHAR (30) NOT NULL,
    genero VARCHAR (1) NOT NULL,
    PRIMARY KEY(id_persona),
	UNIQUE (documento),
    CONSTRAINT fk_tipoDocPersona FOREIGN KEY (tipo_doc)
	REFERENCES COLSEVI.tipo_documento(id_tipo_documento)
);

CREATE TABLE COLSEVI.usuario(
	id_usuario INT AUTO_INCREMENT,
	usuario VARCHAR(40) NOT NULL,
    id_persona INT NOT NULL,
    clave VARCHAR(80) NOT NULL,
    estado VARCHAR(1) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    primer_login VARCHAR(1) NOT NULL,
	PRIMARY KEY(id_usuario),
    UNIQUE (usuario),
    CONSTRAINT fk_Persona FOREIGN KEY (id_persona)
	REFERENCES COLSEVI.persona(id_persona)
);

CREATE TABLE COLSEVI.pagina(
	id_pagina INT AUTO_INCREMENT,
    nivel INTEGER NOT NULL,
    codigo VARCHAR (30) NOT NULL,
    url VARCHAR (50) NOT NULL,
    nombre VARCHAR (50) NOT NULL,
    PRIMARY KEY(id_pagina)
);

CREATE TABLE COLSEVI.rol(
	id_rol INT AUTO_INCREMENT,
    nombre_rol VARCHAR(40) NOT NULL,
    codigo_rol VARCHAR(80) NOT NULL,
    pagina_defecto INT NOT NULL,
	PRIMARY KEY(id_rol),
    CONSTRAINT fk_paginadefecto FOREIGN KEY (pagina_defecto)
	REFERENCES COLSEVI.pagina(id_pagina)
);

CREATE TABLE COLSEVI.pagina_x_rol(
	id_rol INT NOT NULL,
    id_pagina INT NOT NULL,
	PRIMARY KEY(id_rol, id_pagina),
    CONSTRAINT fk_idrolpagina FOREIGN KEY (id_rol)
	REFERENCES COLSEVI.rol(id_rol),
    CONSTRAINT fk_paginaId FOREIGN KEY (id_pagina)
	REFERENCES COLSEVI.pagina(id_pagina)
);

CREATE TABLE COLSEVI.usuario_x_rol(
	id_rol INT NOT NULL,
    id_usuario INT NOT NULL,
	PRIMARY KEY(id_rol, id_usuario),
    CONSTRAINT fk_idrol FOREIGN KEY (id_rol)
	REFERENCES COLSEVI.rol(id_rol),
    CONSTRAINT fk_UxPersona FOREIGN KEY (id_usuario)
	REFERENCES COLSEVI.usuario(id_usuario)
);

CREATE TABLE COLSEVI.establecimiento(
	id_establecimiento INT AUTO_INCREMENT,
    nombre VARCHAR (30) NOT NULL,
    descripcion VARCHAR (30) NOT NULL,
    PRIMARY KEY(id_establecimiento)
);

INSERT INTO COLSEVI.tipo_documento VALUES(null,'Cedula','Cedula de Ciudadania');
INSERT INTO COLSEVI.persona VALUES(null,1,960102613128,'F');
INSERT INTO COLSEVI.usuario VALUES(null,'ppiAdmin',1,'711383a59fda05336fd2ccf70c8059d1523eb41a','T','brayycard@hotmail.com','F');
INSERT INTO COLSEVI.pagina VALUES(null,0,'ESTABLECIMIENTO','/General/Establecimiento.html','Establecimiento');
INSERT INTO COLSEVI.rol VALUES(null,1,'ADMIN',1);
INSERT INTO COLSEVI.pagina_x_rol VALUES(1,1);
INSERT INTO COLSEVI.usuario_x_rol VALUES(1,1);

ALTER TABLE COLSEVI.establecimiento ADD COLUMN estadovisible VARCHAR(1)  DEFAULT 'T';
ALTER TABLE COLSEVI.establecimiento ADD COLUMN hora_inicio VARCHAR(7);
ALTER TABLE COLSEVI.establecimiento ADD COLUMN hora_fin VARCHAR(7);
ALTER TABLE COLSEVI.establecimiento ADD COLUMN id_direccion INT;
ALTER TABLE COLSEVI.establecimiento ADD COLUMN id_telefono INT;
ALTER TABLE COLSEVI.establecimiento ADD COLUMN id_correo INT; 

CREATE TABLE COLSEVI.tipo_telefono(
	id_tipo_telefono INT AUTO_INCREMENT,
    nombre VARCHAR (20) NOT NULL,
	descripcion VARCHAR (40) NOT NULL,
    PRIMARY KEY(id_tipo_telefono)
);

CREATE TABLE COLSEVI.telefono(
	id_telefono INT AUTO_INCREMENT,
    telefono INT  NOT NULL,
    id_tipo_telefono INT NOT NULL,
    id_persona INT DEFAULT NULL,
	CONSTRAINT fk_tipo_telefono FOREIGN KEY (id_tipo_telefono)
    REFERENCES COLSEVI.tipo_telefono(id_tipo_telefono),
    CONSTRAINT fk_id_persona_telefono FOREIGN KEY (id_persona)
    REFERENCES COLSEVI.persona(id_persona),
    PRIMARY KEY(id_telefono)
);

CREATE TABLE COLSEVI.direccion(
	id_direccion INT AUTO_INCREMENT,
    id_persona INT DEFAULT NULL,
	direccion VARCHAR (20) NOT NULL,
    barrio VARCHAR (20) NOT NULL,
    descripcion VARCHAR (40) NOT NULL,
    CONSTRAINT fk_id_persona_direccion FOREIGN KEY (id_persona)
    REFERENCES COLSEVI.persona(id_persona),
    PRIMARY KEY(id_direccion)
);

CREATE TABLE COLSEVI.correo(
	id_correo INT AUTO_INCREMENT,
    id_persona INT DEFAULT NULL,
    CONSTRAINT fk_id_persona_correo FOREIGN KEY (id_persona)
    REFERENCES COLSEVI.persona(id_persona),
	correo VARCHAR (50) NOT NULL,
    PRIMARY KEY(id_correo)
);

ALTER TABLE COLSEVI.establecimiento ADD CONSTRAINT fk_id_direccion FOREIGN KEY (id_direccion)
REFERENCES COLSEVI.direccion(id_direccion);
ALTER TABLE COLSEVI.establecimiento ADD CONSTRAINT fk_id_telefono FOREIGN KEY (id_telefono)
REFERENCES COLSEVI.telefono(id_telefono);
ALTER TABLE COLSEVI.establecimiento ADD CONSTRAINT fk_id_correo FOREIGN KEY (id_correo)
REFERENCES COLSEVI.correo(id_correo);