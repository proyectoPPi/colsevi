<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Movimiento materia</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />

</head>
<body class="page-loader-1 wide pace-done">

	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
			<section class="container">
				<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
					<h2>Movimiento materia</h2>
					<div class="separator-2"></div>
					<section class="panel">
						<div class="panel-body">
							<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
								<label>Lote</label>
								<input type="number" class="form-control" id="loteF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
								<label>Cantidad</label>
								<input type="text" class="form-control" id="cantidadF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
								<label>Unidad de Medida</label>
								<select class="form-control" id="unidadMF" name="filtro">
									<option value="0">Seleccione</option>
									<c:forEach items="${ListaUM}" var="um">
										<option value="${um.id_unidad_peso}">${um.nombre}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
								<label>Establecimiento</label>
								<select class="form-control" id="establecimientoF" name="filtro">
									<option value="0">Seleccione</option>
									<c:forEach items="${ListaE}" var="esta">
										<option value="${esta.id_establecimiento}">${esta.nombre}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Fecha de movimiento</label>
								<input type="text" id="fechaMov" name="filtro" class="form-control" />
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Compra</label>
								<input type="text" id="compra" name="filtro" class="form-control" />
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Motivo</label>
								<select class="form-control" id="motivoMov" name="filtro">
									<option value="0">Seleccione</option>
									<option value="3">Prestamo</option>
									<option value="4">Pago</option>
								</select>
							</div>
							<div class="col-xs-12 col-md-12 col-lg-12"><br/>
								<button class="btn btn-button" onclick="Tabla(1);">Filtrar</button>
							</div>	
						</div>	
					</section>
				
					<div class=" col-xs-12 col-md-12 col-lg-12">
						<div id="tabla"></div>
						<div id="paginacion" class="col-md-12"></div>
					</div>
				</div>
			</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/inventario/MovimientoMateria.js"></script>
	<script type="text/javascript">
		
		titulos = new Array();
		titulos["id_movimiento_materia"] = "ID";
		titulos["lote"] = "Lote";
		titulos["motivo"] = "Motivo";
		titulos["fecha_movimiento"] = "Fecha movimiento";
		titulos["um"] = "Unidad de Medida";
		titulos["cantidad"] = "Cantidad";
		titulos["esta"] = "Establecimiento";
		
	</script>
</body>
</html>