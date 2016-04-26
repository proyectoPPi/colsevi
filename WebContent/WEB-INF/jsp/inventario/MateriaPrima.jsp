<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Materia Prima</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />

</head>
<body>

	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
	<section id="container" class="">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
				
					<h2>Materia Prima</h2>
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
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/inventario/MateriaPrima.js"></script>
	<script type="text/javascript">
		
		titulos = new Array();
		titulos["lote"] = "ID";
		titulos["loteid"] = "Lote";
		titulos["nombreIng"] = "Ingrediente";
		titulos["cantidad"] = "Cantidad";
		titulos["nombreUp"] = "Unidad de Medida";
		titulos["fecha_vencimiento"] = "Fecha Vencimiento";
		titulos["nombreEsta"] = "Establecimiento";
		
		clase = new Array();
		clase['fecha_vencimiento'] = 'hidden-xs';
		clase['nombreEsta'] = 'hidden-xs';
		
	</script>
</body>
</html>