
INSERT INTO persona(id_persona, tipo_doc, documento, genero, nombre, apellido) VALUES(1, 1, 1234567890, 'M', 'ADMIN', 'SUPER');
INSERT INTO usuario(id_usuario, id_persona, usuario, clave, estado, primer_login, id_rol) VALUES(1, 1, 'admincol', '', 'T', 'F', 1);
INSERT INTO tipo_documento(id_tipo_documento,nombr, descripcion) VALUES(1, 'CÈdula', 'CÈdula de ciudadanÌa');

INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(1,"Cereales","son las semillas de las plantas gram√≠neas: trigo, avena, cebada, arroz, ma√≠z, etc.");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(2,"RaÌces y TubÈrculos"," Son alimentos altos en carbohidratos, con un contenido aceptable de fibra diet√©tica, vitamina C (yuca), provitamina A y potasio (yaut√≠a, apio).");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(3,"Frutas y Hortalizas","FRUTAS: fruto comestible, fruta carnoza, se come sin preparaci√≥n. HORTALIZA: Parte comestible de una planta.");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(4,"Carnes","Parte del cuerpo de los animales preparada para el consumo humano.");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(5,"Huevos","Huevo que ponen las aves, especialmente la gallina, y que se toma como alimento.");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(6,"Leguminosas y Frutos Secos","Fruto comestible de algunos √°rboles o plantas que, est√° envuelto en una c√°scara dura y tiene un bajo contenido en agua.");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(7,"Lacteos y Derivados","Se  encuentran  en  la  leche  de  vaca,  cabra,  yogurt, Queso,  kumis.");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(8,"Grasas y Aceites","Las grasas y aceites de origen vegetal o animal son triglic√©ridos o tambi√©n llamados √©steres de la glicerina.");
INSERT INTO clasificar_ingrediente (id_clasificar_ingrediente, nombre, descripcion) VALUES(9,"azucares y dulces","az√∫car, panela, miel, confites, bocadillo, arequipe, gaseosas, chocolatinas, etc.");

INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(1,1,"arro","trigo");
INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(2,4,"filete merluza","filete");
INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(3,7,"crema de leche","crema de leche");
INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(4,3,"cebolla de huevo","cebolla de huevo");
INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(5,8,"sal","sal");
INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(6,2,"pimienta","pimienta");
INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(7,3,"orÈgano","orÈgano");
INSERT INTO ingrediente (id_ingrediente, id_clasificar_ingrediente, nombre, descripcion) VALUES(8,3,"mostaza","mostaza");

INSERT INTO tipo_telefono (id_tipo_telefono, nombre, descripcion) VALUES(1,"Celular","Celular");
INSERT INTO tipo_telefono (id_tipo_telefono, nombre, descripcion) VALUES(2,"Fijo","Fijo");
INSERT INTO tipo_telefono (id_tipo_telefono, nombre, descripcion) VALUES(3,"Contacto","Contacto");

INSERT INTO motivo (id_motivo, nombre) VALUES(1,"Asignacion");
INSERT INTO motivo (id_motivo, nombre) VALUES(2,"Devolucion");
INSERT INTO motivo (id_motivo, nombre) VALUES(3,"Prestamo");
INSERT INTO motivo (id_motivo, nombre) VALUES(4,"Pago");
INSERT INTO motivo (id_motivo, nombre) VALUES(5,"Gasto");

INSERT INTO unidad_peso (id_unidad_peso, nombre, descripcion, codigo) VALUES(1,"Kilogramo","1000 gramos","Kg");
INSERT INTO unidad_peso (id_unidad_peso, nombre, descripcion, codigo) VALUES(2,"Libra","0,5 kilogramos","Lb");
INSERT INTO unidad_peso (id_unidad_peso, nombre, descripcion, codigo) VALUES(3,"Gramo","Gramo","Gr");
INSERT INTO unidad_peso (id_unidad_peso, nombre, descripcion, codigo) VALUES(4,"Litro","1000 mililitros","Lt");

INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(1,"ADMIN","ADMIN");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(2,"EMPLEADO","EMPLEADO");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(3,"CLIENTE","CLIENTE");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(4,"INVITADO","INVITADO");

INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(9,"CLASIFICARING","/Ingrediente/Clasificar.html","Clasificar Ingrediente","","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(10,"ING","/Ingrediente/Ing.html","Ingredientes","fa fa-angle-right","9","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(11,"PRODUCTO","/Producto/Admin.html","Productos","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(12,"NIVELDIF","/Receta/Nivel.html","Dificultad Receta","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(13,"RECETARIO","/Recetario.html","Recetario","","10,11,12","1");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(14,"PAGOPROV","/pago/Proveedor.html","Pago provedores","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(15,"TIPOPROVEEDOR","/TipoProveedor/TipoProv.html","Tipos Provedor","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(16,"PROVEEDOR","/Proveedor/Prov.html","Proveedores","fa fa-angle-right","15","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(17,"MOVMATERIA","/Inventario/MovimientoMateria.html","Movimientos Materia","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(18,"MATPRIMA","/Inventario/MateriaPrima.html","Materia Prima","fa fa-angle-right","17","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(19,"COMPRA","/Proveedor/Compra.html","Compras","","14,16,18","1");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(20,"INV","/Inventario/Inv.html","Inventario","","","1");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(21,"ESTA","/General/Establecimiento.html","Establecimento","","","0");

INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"9");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"10");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"11");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"12");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"13");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"14");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"15");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"16");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"17");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"18");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"19");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"20");
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,"21");