CREATE DATABASE COLSEVI;

CREATE TABLE general_local(
	id_local INTEGER NOT NULL,
    nombre VARCHAR (30) NOT NULL,
    descripcion VARCHAR (30) NOT NULL,
    PRIMARY KEY(id_local)
);

CREATE TABLE general_pagina(
	id_pagina INTEGER NOT NULL,
    nivel INTEGER NOT NULL,
    codigo VARCHAR (30) NOT NULL,
    url VARCHAR (50) NOT NULL,
    nombre VARCHAR (50) NOT NULL,
    PRIMARY KEY(id_pagina)
);
