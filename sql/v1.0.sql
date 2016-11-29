DROP DATABASE IF EXISTS colsevi;
CREATE DATABASE colsevi;
USER colsevi;

CREATE TABLE establecimiento(
	id_establecimiento INT AUTO_INCREMENT,
    id_direccion INT,
    id_telefono INT,
    id_correo INT,
    nombre VARCHAR (30) NOT NULL,
    descripcion VARCHAR (30) NOT NULL,
    hora_inicio VARCHAR(7),
    hora_fin VARCHAR(7),
    PRIMARY KEY(id_establecimiento)
);

CREATE TABLE tipo_documento(
	id_tipo_documento INT AUTO_INCREMENT,
    nombre VARCHAR (30) NOT NULL,
    descripcion VARCHAR (40) NOT NULL,
    PRIMARY KEY(id_tipo_documento)
);

CREATE TABLE persona(
	id_persona INT AUTO_INCREMENT,
    tipo_doc INT NOT NULL,
    documento VARCHAR (30) NOT NULL,
    genero VARCHAR (1) NOT NULL,
    nombre VARCHAR(60),
    apellido VARCHAR(60),
    PRIMARY KEY(id_persona),
	UNIQUE (documento),
    CONSTRAINT fk_tdocPer FOREIGN KEY (tipo_doc) REFERENCES tipo_documento(id_tipo_documento)
);

CREATE TABLE rol(
	id_rol INT AUTO_INCREMENT,
    nombre_rol VARCHAR(40) NOT NULL,
    codigo_rol VARCHAR(80) NOT NULL,
	PRIMARY KEY(id_rol)
);

CREATE TABLE usuario(
	id_usuario INT AUTO_INCREMENT,
    id_persona INT NOT NULL,
	usuario VARCHAR(40) NOT NULL,
    clave VARCHAR(80) NOT NULL,
    estado VARCHAR(1) NOT NULL,
    primer_login VARCHAR(1) NOT NULL,
    id_rol INT NOT NULL,
	PRIMARY KEY(id_usuario),
    UNIQUE (usuario),
    CONSTRAINT fk_Persona FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    CONSTRAINT fk_rolu FOREIGN KEY (id_rol) REFERENCES rol(id_rol)
);

CREATE TABLE pagina(
	id_pagina INT AUTO_INCREMENT,
    codigo VARCHAR (30) NOT NULL,
    url VARCHAR (50) NOT NULL,
    nombre VARCHAR (50) NOT NULL,
    icono VARCHAR (50),
    padrePagina VARCHAR(60) DEFAULT NULL,
    menu BOOLEAN DEFAULT FALSE,
    PRIMARY KEY(id_pagina)
);

CREATE TABLE pagina_x_rol(
	id_rol INT NOT NULL,
    id_pagina INT NOT NULL,
	PRIMARY KEY(id_rol, id_pagina),
    CONSTRAINT fk_pagr FOREIGN KEY (id_rol) REFERENCES rol(id_rol),
    CONSTRAINT fk_rolp FOREIGN KEY (id_pagina) REFERENCES pagina(id_pagina)
);

CREATE TABLE tipo_telefono(
	id_tipo_telefono INT AUTO_INCREMENT,
    nombre VARCHAR (30) NOT NULL,
	descripcion VARCHAR (60) NOT NULL,
    PRIMARY KEY(id_tipo_telefono)
);

CREATE TABLE telefono(
	id_telefono INT AUTO_INCREMENT,
    telefono VARCHAR(20)  NOT NULL,
    id_tipo_telefono INT NOT NULL,
    id_persona INT DEFAULT NULL,
	CONSTRAINT fk_tt FOREIGN KEY (id_tipo_telefono) REFERENCES tipo_telefono(id_tipo_telefono),
    CONSTRAINT fk_pert FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    PRIMARY KEY(id_telefono)
);

CREATE TABLE direccion(
	id_direccion INT AUTO_INCREMENT,
    id_persona INT DEFAULT NULL,
	direccion VARCHAR (50) NOT NULL,
    barrio VARCHAR (60),
    descripcion VARCHAR (60),
    CONSTRAINT fk_dirp FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    PRIMARY KEY(id_direccion)
);

CREATE TABLE correo(
	id_correo INT AUTO_INCREMENT,
    id_persona INT DEFAULT NULL,
	correo VARCHAR (50) NOT NULL,
    PRIMARY KEY(id_correo),
	CONSTRAINT fk_corp FOREIGN KEY (id_persona) REFERENCES persona(id_persona)
);

ALTER TABLE establecimiento ADD CONSTRAINT fk_dire FOREIGN KEY (id_direccion) REFERENCES direccion(id_direccion);
ALTER TABLE establecimiento ADD CONSTRAINT fk_tele FOREIGN KEY (id_telefono) REFERENCES telefono(id_telefono);
ALTER TABLE establecimiento ADD CONSTRAINT fk_core FOREIGN KEY (id_correo) REFERENCES correo(id_correo);

CREATE TABLE tipo_proveedor(
    id_tipo_proveedor INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(120) NOT NULL,
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
    CONSTRAINT fk_dirprov FOREIGN KEY (id_direccion) REFERENCES direccion(id_direccion),
    CONSTRAINT fk_tipoprop FOREIGN KEY (id_tipo_proveedor) REFERENCES tipo_proveedor(id_tipo_proveedor),
    CONSTRAINT fk_telprop FOREIGN KEY (id_telefono) REFERENCES telefono(id_telefono)
);

CREATE TABLE compra_proveedor(
	id_compra_proveedor INT AUTO_INCREMENT,
    id_proveedor INT NOT NULL,
    id_establecimiento INT,
    valor DECIMAL(12,2) NOT NULL,
    fecha_compra DATETIME NOT NULL,
    iva INTEGER,
    pagado BOOLEAN DEFAULT FALSE,
    motivo VARCHAR(200),
    pendiente DECIMAL(12,2),
    observacion VARCHAR(500),
    PRIMARY KEY(id_compra_proveedor),
    CONSTRAINT fk_comprae FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento),
	CONSTRAINT fk_comprap FOREIGN KEY (id_proveedor) REFERENCES proveedor(id_proveedor)
);

CREATE TABLE pago_proveedor(
	id_pago_proveedor INT AUTO_INCREMENT,
    id_compra INT NOT NULL,
    pendiente DECIMAL(12,2),
    fecha_pago DATETIME,
    valor_pagado DECIMAL(12,2),
    observacion VARCHAR(150),
    PRIMARY KEY(id_pago_proveedor),
	CONSTRAINT fk_PagoProv FOREIGN KEY (id_compra) REFERENCES compra_proveedor(id_compra_proveedor)
);

CREATE TABLE clasificar_ingrediente(
	id_clasificar_ingrediente INT AUTO_INCREMENT,
    nombre VARCHAR(40),
    descripcion VARCHAR(250),
    PRIMARY KEY(id_clasificar_ingrediente)
);

CREATE TABLE unidad_medida(
    id_unidad_medida INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    codigo VARCHAR(4) DEFAULT NULL,
    UNIQUE (codigo),
    PRIMARY KEY(id_unidad_medida)
);

CREATE TABLE ingrediente(
    id_ingrediente INT AUTO_INCREMENT,
    id_clasificar_ingrediente INT,
    id_unidad_medida INT,
	nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY(id_ingrediente),
    CONSTRAINT fk_clasing FOREIGN KEY (id_clasificar_ingrediente) REFERENCES clasificar_ingrediente(id_clasificar_ingrediente),
    CONSTRAINT fk_medidaIng FOREIGN KEY (id_unidad_medida) REFERENCES unidad_medida(id_unidad_medida)
);

CREATE TABLE unidad_peso(
    id_unidad_peso INT AUTO_INCREMENT,
    id_unidad_medida INT NOT NULL,
	nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80) DEFAULT NULL,
    codigo VARCHAR(4) DEFAULT NULL,
    PRIMARY KEY(id_unidad_peso),
    CONSTRAINT fk_unidadMedida FOREIGN KEY (id_unidad_medida) REFERENCES unidad_medida(id_unidad_medida)
);

CREATE TABLE materia_prima(
	lote INT AUTO_INCREMENT,
    id_unidad_peso INT,
    id_ingrediente INT,
    id_establecimiento INT,
    cantidad DOUBLE,
    fecha_vencimiento DATETIME,
    PRIMARY KEY(lote),
    CONSTRAINT fk_ump FOREIGN KEY (id_unidad_peso) REFERENCES unidad_peso(id_unidad_peso),
    CONSTRAINT fk_imp FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id_ingrediente),
    CONSTRAINT fk_emp FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento)
);

CREATE TABLE compra_x_ingrediente(
    id_compra INT,
    id_ingrediente INT,
    id_unidad_peso INT NOT NULL,
    cantidad DOUBLE NOT NULL,
    vunitario DECIMAL(12,2),
    fecha_vencimiento DATETIME,
    lote INT,
    PRIMARY KEY(id_compra,id_ingrediente),
	CONSTRAINT fk_cxi FOREIGN KEY (id_compra) REFERENCES compra_proveedor(id_compra_proveedor),
    CONSTRAINT fk_icx FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id_ingrediente),
    CONSTRAINT fk_ucxi FOREIGN KEY (id_unidad_peso) REFERENCES unidad_peso(id_unidad_peso),
    CONSTRAINT fk_lcxi FOREIGN KEY (lote) REFERENCES materia_prima(lote)
);

ALTER TABLE compra_x_ingrediente AUTO_INCREMENT = 1000;

CREATE TABLE motivo(
    id_motivo INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY(id_motivo)
);

CREATE TABLE movimiento_materia(
	id_movimiento_materia INT AUTO_INCREMENT,
    id_motivo INT NOT NULL,
    lote INT NOT NULL,
    id_unidad_peso INT NOT NULL,
    id_establecimiento INT,
    cantidad DOUBLE,
	fecha_movimiento DATETIME NOT NULL,
    PRIMARY KEY(id_movimiento_materia),
    CONSTRAINT fk_movcomMotivo FOREIGN KEY (id_motivo) REFERENCES motivo(id_motivo),
    CONSTRAINT fk_matxIngMov FOREIGN KEY (lote) REFERENCES materia_prima(lote),
    CONSTRAINT fk_umc FOREIGN KEY (id_unidad_peso) REFERENCES unidad_peso(id_unidad_peso),
    CONSTRAINT fk_movIngEst FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento)
);

CREATE TABLE tipo_producto(
    id_tipo_producto INT AUTO_INCREMENT,
	nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80) DEFAULT NULL,
    PRIMARY KEY(id_tipo_producto)
);

CREATE TABLE producto(
    id_producto INT AUTO_INCREMENT,
    id_tipo_producto INT,
    referencia VARCHAR(8) DEFAULT NULL,
	nombre VARCHAR(40) NOT NULL,
    descripcion VARCHAR(120) DEFAULT NULL,
    venta DECIMAL(12,2),
    imagen VARCHAR (50) DEFAULT 'imagenFotoProducto.jpg',
    cantidadMin INT NOT NULL,
    PRIMARY KEY(id_producto),
    UNIQUE (referencia),
    CONSTRAINT fk_tipo_producto_Producto FOREIGN KEY (id_tipo_producto) REFERENCES tipo_producto(id_tipo_producto)
);

CREATE TABLE ingrediente_x_producto(
    id_ingrediente INT,
	id_producto INT,
	cantidad INT,
	id_unidad_peso INT,
    PRIMARY KEY(id_ingrediente,id_producto),
    CONSTRAINT fk_producto_ing FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_ingProducto FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id_ingrediente),
    CONSTRAINT fk_ingProdUP FOREIGN KEY (id_unidad_peso) REFERENCES unidad_peso(id_unidad_peso)
);

CREATE TABLE dificultad_receta(
	id_dificultad_receta INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(80),
    icono VARCHAR (15),
    PRIMARY KEY(id_dificultad_receta)
);

CREATE TABLE receta(
    id_receta INT AUTO_INCREMENT,
	id_producto INT,
    id_dificultad_receta INT,
    tiempo VARCHAR(5),
    PRIMARY KEY(id_receta),
    CONSTRAINT fk_recetaProducto FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_recetaDif FOREIGN KEY (id_dificultad_receta) REFERENCES dificultad_receta(id_dificultad_receta)
);

CREATE TABLE preparacion_receta(
    id_preparacion_receta INT AUTO_INCREMENT,
	id_receta INT,
    preparacion VARCHAR(300),
    PRIMARY KEY(id_preparacion_receta),
    CONSTRAINT fk_receta FOREIGN KEY (id_receta) REFERENCES receta(id_receta)
);

CREATE TABLE catalogo(
    id_catalogo INT AUTO_INCREMENT,
	id_establecimiento INT,
    nombre VARCHAR(50),
    descripcion VARCHAR(80),
    vigente BOOLEAN DEFAULT FALSE,
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

CREATE TABLE inventario(
	id_inventario INT AUTO_INCREMENT,
    id_establecimiento INT,
    id_producto INT,
    disponible INT,
    compromiso INT,
    PRIMARY KEY(id_inventario),
    CONSTRAINT fk_invEst FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento),
	CONSTRAINT fk_invProd FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE movimiento_inventario(
	id_movimiento INT AUTO_INCREMENT,
    id_inventario INT,
    id_establecimiento INT,
    id_producto INT,
    disponible INT,
    compromiso INT,
    PRIMARY KEY(id_movimiento, id_inventario),
    CONSTRAINT fk_invmovEst FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento),
	CONSTRAINT fk_invmovProd FOREIGN KEY (id_producto) REFERENCES producto(id_producto),
    CONSTRAINT fk_invmovInv FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario)
);

CREATE TABLE estado_pedido(
    id_estado_pedido INT AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
    descripcion VARCHAR(70) DEFAULT NULL,
    PRIMARY KEY(id_estado_pedido)
);

CREATE TABLE pedido(
    id_pedido INT AUTO_INCREMENT,
	id_persona INT NOT NULL,
    id_estado_pedido INT NOT NULL,
    id_establecimiento INT NOT NULL,
    comentario VARCHAR(2000),
    total DECIMAL(12,2) DEFAULT NULL,
    fecha_pedido  DATETIME NOT NULL,
    motivo INT DEFAULT NULL,
    pagado BOOLEAN DEFAULT false,
    PRIMARY KEY(id_pedido),
    CONSTRAINT fk_perPed FOREIGN KEY (id_persona) REFERENCES persona(id_persona),
    CONSTRAINT fk_estPed FOREIGN KEY (id_estado_pedido) REFERENCES estado_pedido(id_estado_pedido),
    CONSTRAINT fk_estaPed FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento)
);

CREATE TABLE detalle_pedido(
    id_detalle_pedido INT AUTO_INCREMENT,
	id_pedido INT,
    id_producto INT,
    cantidad INT NOT NULL,
    sub_total DECIMAL(12,2) DEFAULT NULL,
    PRIMARY KEY(id_detalle_pedido),
    CONSTRAINT fk_secPedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_prodPed FOREIGN KEY (id_producto) REFERENCES producto(id_producto)
);

CREATE TABLE pago_pedido(
	id_pago_pedido INT AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    pendiente DECIMAL(12,2),
    fecha_pag DATETIME NOT NULL,
    valor_pago DECIMAL(12,2) NOT NULL,
    observacion VARCHAR(300),
    PRIMARY KEY(id_pago_pedido),
    CONSTRAINT fk_pagoPed FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
);

CREATE TABLE deuda_pedido(
	id_deuda_pedido INT AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    pendiente DECIMAL(12,2),
    fecha_registro DATETIME NOT NULL,
    observacion VARCHAR(300),
    PRIMARY KEY(id_deuda_pedido),
    CONSTRAINT fk_deudaPed FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido)
);

CREATE TABLE categoria_cobro(
	id_categoria_cobro INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL, 
    PRIMARY KEY(id_categoria_cobro)
);

CREATE TABLE cobro(
	id_cobro INT AUTO_INCREMENT,
    id_deuda INT NOT NULL,
    id_categoria_cobro INT,
    fecha_cobro DATETIME NOT NULL,
    comentario VARCHAR(300),
    PRIMARY KEY(id_cobro),
    CONSTRAINT fk_cobroPed FOREIGN KEY (id_deuda) REFERENCES deuda_pedido(id_deuda_pedido),
    CONSTRAINT fk_cobroCat FOREIGN KEY (id_categoria_cobro) REFERENCES categoria_cobro(id_categoria_cobro)
);

CREATE TABLE inventario_x_materia(
    id_inventario INT,
    id_ingrediente INT,
	lote INT,
    id_unidad_peso INT,
    cantidad DOUBLE,
    CONSTRAINT fk_invCom1 FOREIGN KEY (id_inventario) REFERENCES inventario(id_inventario),
	CONSTRAINT fk_invCom2 FOREIGN KEY (id_ingrediente) REFERENCES ingrediente(id_ingrediente),
    CONSTRAINT fk_invCom3 FOREIGN KEY (lote) REFERENCES materia_prima(lote),
    CONSTRAINT fk_invCom4 FOREIGN KEY (id_unidad_peso) REFERENCES unidad_peso(id_unidad_peso)
);

CREATE TABLE plato(
	id_plato INT AUTO_INCREMENT,
    id_establecimiento INT NOT NULL,
    nombre VARCHAR(60) NOT NULL,
    PRIMARY KEY(id_plato),
    CONSTRAINT fk_estabPlato FOREIGN KEY (id_establecimiento) REFERENCES establecimiento(id_establecimiento)
);