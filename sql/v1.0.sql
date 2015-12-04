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
    nombre_rol INT NOT NULL,
    codigo_rol VARCHAR(80) NOT NULL,
    pagina_defecto INT NOT NULL,
	PRIMARY KEY(id_rol),
    CONSTRAINT fk_paginadefecto FOREIGN KEY (pagina_defecto)
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

