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







CREATE TABLE tipo_proveedor(
    id_tipo_proveedor INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_tipo_proveedor)
);

CREATE TABLE proveedor (
	id_proveedor INT AUTO_INCREMENT,
	id_direccion INT,
    id_tipo_proveedor INT,
    id_telefono INT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80) NOT NULL,
    PRIMARY KEY(id_proveedor),
    CONSTRAINT fk_direccionProveedor FOREIGN KEY (id_direccion) REFERENCES direccion(id_direccion),
    CONSTRAINT fk_tipo_proveedor FOREIGN KEY (id_tipo_proveedor) REFERENCES tipo_proveedor(id_tipo_proveedor),
    CONSTRAINT fk_telefono_proveedor FOREIGN KEY (id_telefono) REFERENCES telefono(id_telefono)
);

CREATE TABLE compra(
	id_compra INT AUTO_INCREMENT,
    id_proveedor INT NOT NULL,
    valor DECIMAL(12,2) NOT NULL,
    fecha_compra DATETIME NOT NULL,
    PRIMARY KEY(id_compra),
	CONSTRAINT fk_compra_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE tipo_peso(
    id_tipo_peso INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY(id_tipo_peso)
);


CREATE TABLE ingrediente(
    id_ingrediente INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY(id_ingrediente)
);

CREATE TABLE compra_x_ingrediente(
	id_compra INT,
    id_ingrediente INT,
    id_tipo_peso INT NOT NULL,
    lote VARCHAR(12) NOT NULL,
    fecha_vencimiento DATETIME NOT NULL,
    PRIMARY KEY(id_compra,id_ingrediente),
	CONSTRAINT fk_comprasXIngrediente FOREIGN KEY (id_compra) REFERENCES compra(id_compra),
    CONSTRAINT fk_comprasXIngrediente2 FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id_ingrediente),
    CONSTRAINT fk_comprasXIngrediente3 FOREIGN KEY (id_tipo_peso) REFERENCES tipo_peso(id_tipo_peso)
);

CREATE TABLE tipo_producto(
    id_tipo_producto INT AUTO_INCREMENT,
    padre INT DEFAULT NULL,
	nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY(id_tipo_producto),
    CONSTRAINT fk_id_tipo_producto FOREIGN KEY (padre) REFERENCES tipo_producto(id_tipo_producto)
);

CREATE TABLE producto(
    id_producto INT AUTO_INCREMENT,
    id_tipo_producto INT,
    referencia VARCHAR(8) DEFAULT NULL,
	nombre VARCHAR(40) NOT NULL,
    descripcion VARCHAR(120) DEFAULT NULL,
    costo DECIMAL(12,2),
    venta DECIMAL(12,2),
    imagen VARCHAR (50) DEFAULT 'imagenFotoProducto.jpg',
    PRIMARY KEY(id_producto),
    UNIQUE (referencia),
    CONSTRAINT fk_tipo_producto_Producto FOREIGN KEY (id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto)
);


CREATE TABLE ingrediente_x_producto(
    id_ingrediente INT,
	id_producto INT,
    PRIMARY KEY(id_ingrediente,id_producto),
    CONSTRAINT fk_producto_ingrediente FOREIGN KEY (id_producto) REFERENCES tipo_producto(id_tipo_producto),
    CONSTRAINT fk_ingredienteProducto FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id_ingrediente)
);


CREATE TABLE receta(
    id_receta INT AUTO_INCREMENT,
	id_producto INT,
    preparacion VARCHAR(2000),
    PRIMARY KEY(id_receta),
    CONSTRAINT fk_recetaProducto FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE catalogo(
    id_catalogo INT AUTO_INCREMENT,
	id_establecimiento INT,
    nombre VARCHAR(50),
    descripcion VARCHAR(80),
    PRIMARY KEY(id_catalogo),
    CONSTRAINT fk_establecimientoCatalogo FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento)
);

CREATE TABLE catalogo_x_producto(
    id_catalogo INT,
	id_producto INT,
    PRIMARY KEY(id_catalogo,id_producto),
    CONSTRAINT fk_producto_catalogo FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_catalogoProductoCatalogo FOREIGN KEY (id_catalogo) REFERENCES catalogo(id_catalogo)
);

CREATE TABLE estado_pedido(
    id_estado_pedido INT AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
    descripcion VARCHAR(70) DEFAULT NULL,
    PRIMARY KEY(id_estado_pedido)
);

CREATE TABLE pedido(
    id_pedido INT AUTO_INCREMENT,
	id_persona INT,
    id_estado_pedido INT,
    total DECIMAL(12,2) DEFAULT NULL,
    fecha_pedido  DATETIME,
    pagado BOOLEAN DEFAULT false,
    PRIMARY KEY(id_pedido),
    CONSTRAINT fk_personaPedido FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    CONSTRAINT fk_estadoPedido FOREIGN KEY (id_estado_pedido) REFERENCES estado_pedido(id_estado_pedido)
);

CREATE TABLE detalle_pedido(
    id_detalle_pedido INT AUTO_INCREMENT,
	id_pedido INT,
    id_producto INT,
    cantidad INT NOT NULL,
    sub_total DECIMAL(12,2) DEFAULT NULL,
    PRIMARY KEY(id_detalle_pedido),
    CONSTRAINT fk_secuenciaPedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_detalleProductoPed FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE inventario(
    id_inventario INT AUTO_INCREMENT,
	id_catalogo INT,
    id_producto INT,
    disponible INT,
    comprometido INT,
    PRIMARY KEY(id_inventario),
    CONSTRAINT fk_catalogoInventario FOREIGN KEY (id_catalogo) REFERENCES catalogo(id_catalogo),
    CONSTRAINT fk_productoInventario FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE categoria_modulo(
    id_categoria_modulo INT AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
    tabla VARCHAR(60) DEFAULT NULL,
    PRIMARY KEY(id_categoria_modulo)
);

CREATE TABLE pago(
    id_pago INT AUTO_INCREMENT,
	id_categoria_modulo INT,
    documento INT,
    pendiente DECIMAL(12,2),
    fecha DATETIME,
    PRIMARY KEY(id_pago),
    CONSTRAINT fk_categoriaPago FOREIGN KEY (id_categoria_modulo) REFERENCES categoria_modulo(id_categoria_modulo)
);

CREATE TABLE detalle_pago(
    id_detalle_pago INT AUTO_INCREMENT,
	id_pago INT,
    fecha DATETIME,
    valor_pagado DECIMAL(12,2),
    observacion VARCHAR(150),
    PRIMARY KEY(id_detalle_pago),
    CONSTRAINT fk_detallePago FOREIGN KEY (id_pago) REFERENCES pago(id_pago)
);

CREATE TABLE deuda(
    id_deuda INT AUTO_INCREMENT,
	id_categoria_modulo INT,
    documento INT,
    fecha_registro DATETIME,
    valor_deuda DECIMAL(12,2),
    observacion VARCHAR(250),
    PRIMARY KEY(id_deuda),
    CONSTRAINT fk_categoriaDeuda FOREIGN KEY (id_categoria_modulo) REFERENCES categoria_modulo(id_categoria_modulo)
);

CREATE TABLE cobro_categoria(
	id_cobro_categoria INT AUTO_INCREMENT,
    nombre VARCHAR(50),
    PRIMARY KEY(id_cobro_categoria)
);

CREATE TABLE cobro(
    id_cobro INT AUTO_INCREMENT,
	id_cobro_categoria INT,
    id_deuda INT,
    fecha_cobro DATETIME,
    comentario VARCHAR(250),
    PRIMARY KEY(id_cobro),
    CONSTRAINT fk_cobroDeuda FOREIGN KEY (id_deuda) REFERENCES deuda(id_deuda),
    CONSTRAINT fk_cobrocategoria FOREIGN KEY (id_cobro_categoria) REFERENCES cobro_categoria(id_cobro_categoria)
);

CREATE TABLE motivo(
	id_motivo INT AUTO_INCREMENT,
    nombre VARCHAR(70),
    PRIMARY KEY(id_motivo)
);

CREATE TABLE movimiento_inventario(
    id_movimiento_inventario INT AUTO_INCREMENT,
	transicion INT,
    documento INT,
    id_motivo INT,
    id_categoria_modulo INT,
    cantidad INT,
    PRIMARY KEY(id_movimiento_inventario),
    CONSTRAINT fk_movimientoCategoria FOREIGN KEY (id_categoria_modulo) REFERENCES categoria_modulo(id_categoria_modulo),
    CONSTRAINT fk_motivoMovimiento FOREIGN KEY (id_motivo) REFERENCES motivo(id_motivo)
);


ALTER TABLE persona ADD COLUMN nombre VARCHAR(60);
ALTER TABLE persona ADD COLUMN apellido VARCHAR(60);