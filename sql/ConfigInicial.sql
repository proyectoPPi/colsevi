INSERT INTO estado_pedido (id_estado_pedido, nombre, descripcion) VALUES(1,"CANCELADO","CANCELADO");
INSERT INTO estado_pedido (id_estado_pedido, nombre, descripcion) VALUES(2,"BORRADOR","BORRADOR");
INSERT INTO estado_pedido (id_estado_pedido, nombre, descripcion) VALUES(3,"NUEVO","NUEVO");
INSERT INTO estado_pedido (id_estado_pedido, nombre, descripcion) VALUES(4,"PREPARACION","PREPARACION");
INSERT INTO estado_pedido (id_estado_pedido, nombre, descripcion) VALUES(5,"ENTREGADO","ENTREGADO");

INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(1,"ADMIN","ADMIN");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(2,"EMPLEADO","EMPLEADO");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(3,"CLIENTE","CLIENTE");
INSERT INTO rol (id_rol, nombre_rol, codigo_rol) VALUES(4,"INVITADO","INVITADO");

INSERT INTO tipo_documento(id_tipo_documento,nombre, descripcion) VALUES(1, 'Cédula', 'Cédula de ciudadanía');

INSERT INTO persona(id_persona, tipo_doc, documento, genero, nombre, apellido) VALUES(1, 1, 1234567890, 'M', 'ADMIN', 'SUPER');
INSERT INTO usuario(id_usuario, id_persona, usuario, clave, estado, primer_login, id_rol) VALUES(1, 1, 'admincol', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'T', 'F', 1);

INSERT INTO unidad_medida (id_unidad_medida, nombre, codigo) VALUES (1, 'Masa', '');
INSERT INTO unidad_medida (id_unidad_medida, nombre, codigo) VALUES (2, 'Volumen', 'Vol');

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
