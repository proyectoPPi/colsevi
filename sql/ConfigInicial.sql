
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(1,"ADMIN","ADMIN");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(2,"EMPLEADO","EMPLEADO");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(3,"CLIENTE","CLIENTE");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(4,"INVITADO","INVITADO");

INSERT INTO tipo_documento(id_tipo_documento,nombre, descripcion) VALUES(1, 'CÈdula', 'CÈdula de ciudadanÌa');

INSERT INTO persona(id_persona, tipo_doc, documento, genero, nombre, apellido) VALUES(1, 1, 1234567890, 'M', 'ADMIN', 'SUPER');
INSERT INTO usuario(id_usuario, id_persona, usuario, clave, estado, primer_login, id_rol) VALUES(1, 1, 'admincol', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'T', 'F', 1);

INSERT INTO unidad_medida (id_unidad_medida, nombre, codigo) VALUES (1, 'Masa', '');
INSERT INTO unidad_medida (id_unidad_medida, nombre, codigo) VALUES (2, 'Volumen', 'Vol');
  
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

INSERT INTO unidad_peso (id_unidad_peso, id_unidad_medida, nombre, descripcion, codigo) VALUES(1,1,"Kilogramo","1000 gramos","Kg");
INSERT INTO unidad_peso (id_unidad_peso, id_unidad_medida, nombre, descripcion, codigo) VALUES(2,1,"Libra","0,5 kilogramos","Lb");
INSERT INTO unidad_peso (id_unidad_peso, id_unidad_medida, nombre, descripcion, codigo) VALUES(3,1,"Gramo","Gramo","Gr");
INSERT INTO unidad_peso (id_unidad_peso, id_unidad_medida, nombre, descripcion, codigo) VALUES(4,2,"Litro","1000 mililitros","Lt");

INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(1,"CLASIFICARING","/Ingrediente/Clasificar.html","Clasificar Ingrediente","","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(2,"ING","/Ingrediente/Ing.html","Ingredientes","fa fa-angle-right","9","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(3,"PRODUCTO","/Producto/Admin.html","Productos","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(4,"NIVELDIF","/Receta/Nivel.html","Dificultad Receta","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(5,"RECETARIO","/Recetario.html","Recetario","","10,11,12","1");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(6,"PAGOPROV","/pago/Proveedor.html","Pago provedores","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(7,"TIPOPROVEEDOR","/TipoProveedor/TipoProv.html","Tipos Provedor","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(8,"PROVEEDOR","/Proveedor/Prov.html","Proveedores","fa fa-angle-right","15","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(9,"MOVMATERIA","/Inventario/MovimientoMateria.html","Movimientos Materia","fa fa-angle-right","","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(10,"MATPRIMA","/Inventario/MateriaPrima.html","Materia Prima","fa fa-angle-right","17","0");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(11,"COMPRA","/Proveedor/Compra.html","Compras","","14,16,18","1");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(12,"INV","/Inventario/Inv.html","Inventario","","","1");
INSERT INTO pagina (id_pagina, codigo, url, nombre, icono, padrePagina, menu) VALUES(13,"ESTA","/General/Establecimiento.html","Establecimento","","","0");

INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,1);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,2);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,3);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,4);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,5);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,6);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,7);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,8);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,9);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,10);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,11);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,12);
INSERT INTO pagina_x_rol (id_rol, id_pagina) VALUES(1,13);