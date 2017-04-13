
select * from compra_proveedor;

-- compras 
SELECT COUNT(id_compra_proveedor) AS cantidad, SUM(valor) AS valor, CP.id_establecimiento AS establecimiento FROM compra_proveedor CP
INNER JOIN caja C ON id_caja = 2 AND CP.id_establecimiento = C.id_establecimiento
WHERE 1 = 1 AND (motivo IS null OR motivo = '') AND CP.fecha_compra like CONCAT(C.fecha_ejecucion,'%');

-- compras pendientes por pagar
SELECT count(id_compra_proveedor) as cantidad, SUM(pendiente) AS valor, C.id_establecimiento AS establecimiento FROM compra_proveedor CP
INNER JOIN caja C ON id_caja = 2 AND CP.id_establecimiento = C.id_establecimiento
WHERE 1 = 1 AND (motivo IS null OR motivo = '') AND CP.fecha_compra like CONCAT(C.fecha_ejecucion,'%') AND pendiente != 0;

-- compras pagadas
SELECT count(id_compra_proveedor) as cantidad, SUM(valor) AS valor, C.id_establecimiento AS establecimiento FROM compra_proveedor CP
INNER JOIN caja C ON id_caja = 2 AND CP.id_establecimiento = C.id_establecimiento
WHERE 1 = 1 AND (motivo IS null OR motivo = '') AND CP.fecha_compra like CONCAT(C.fecha_ejecucion,'%') AND pendiente = 0;



-- CONSOLIDAR COMPRAS
INSERT INTO pago_proveedor (id_compra, fecha_pago, valor_pagado, observacion)
SELECT id_compra_proveedor AS id_compra, NOW() AS fecha_pago, valor AS valor_pagado, 'Pago por consolidación' AS observacion
FROM compra_proveedor CP
INNER JOIN caja C ON id_caja = 2 AND CP.id_establecimiento = C.id_establecimiento
WHERE 1 = 1 AND (motivo IS null OR motivo = '') AND CP.fecha_compra like CONCAT(C.fecha_ejecucion,'%') AND pendiente != 0 AND pagado = true;

UPDATE compra_proveedor CP
JOIN caja C
ON C.id_caja = 2 AND CP.id_establecimiento = C.id_establecimiento
SET CP.pendiente = 0
WHERE 1 = 1 AND (motivo IS null OR motivo = '') AND CP.fecha_compra like CONCAT(C.fecha_ejecucion,'%') AND pendiente != 0 AND pagado = true;

-- MATERIA PRIMA < 5 DÍAS PARA VENCER
SELECT datediff(MAX(fecha_vencimiento), MIN(NOW())) AS vence, lote, cantidad, I.nombre, UP.nombre
FROM materia_prima MP
INNER JOIN caja C ON C.id_caja = 2 AND C.id_establecimiento = MP.id_establecimiento
INNER JOIN ingrediente I ON I.id_ingrediente = MP.id_ingrediente
INNER JOIN unidad_peso UP ON UP.id_unidad_peso = MP.id_unidad_peso
WHERE TIMESTAMPDIFF(DAY,NOW(),fecha_vencimiento) < 5;

-- pedidos
SELECT count(id_pedido) as cantidad, SUM(total) AS valor FROM pedido
WHERE id_estado_pedido != 1;

-- Pedidos Pagados
SELECT count(id_pedido) as cantidad, SUM(total) AS valor FROM pedido
WHERE id_estado_pedido != 1 
GROUP BY id_establecimiento;




