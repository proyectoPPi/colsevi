<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Materia Prima</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>
<body>

	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
			<section class="container">
				<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
				
					<h2>Materia Prima</h2>
					<section class="panel">
						<div class="panel-body">
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Lote</label>
								<input type="number" class="form-control" id="loteF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Cantidad</label>
								<input type="text" class="form-control" id="cantidadF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Unidad de Medida</label>
								<select class="form-control" id="unidadMF" name="filtro">
									<option value="0">Seleccione</option>
									<c:forEach items="${ListaUM}" var="um">
										<option value="${um.id_unidad_peso}">${um.nombre}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
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
					<div class="modal fade modal-dialog-center" id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/Inventario/MateriaPrima/GuardarMovimiento.html?" id="formulario">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Materia Prima...</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<input type="hidden" id="lote" name="lote"/>
												
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Ingrediente</label>
													<input type="text" class="form-control" id="nombreIng" name="nombreIng" disabled/>
												</div>
												
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Cantidad</label>
													<input type="number" class="form-control" id="cantidad" name="cantidad" disabled/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Unidad medida</label>
													<select class="form-control" id="id_unidad_peso" name="id_unidad_peso" disabled>
														<option value="0">Seleccione</option>
														<c:forEach items="${ListaUM}" var="um">
															<option value="${um.id_unidad_peso}">${um.nombre}</option>
														</c:forEach>
													</select>
													<br/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Establecimiento</label>
													<select class="form-control" id="id_establecimiento" name="id_establecimiento" disabled>
														<option value="0">Seleccione</option>
														<c:forEach items="${ListaE}" var="esta">
															<option value="${esta.id_establecimiento}">${esta.nombre}</option>
														</c:forEach>
													</select>
												</div>
												
												<div class="col-lg-12">
													<h4><strong>Realizar Movimiento</strong></h4>
													<hr/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Cantidad</label>
													<input type="number" class="form-control" id="cantMov" name="cantMov"/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Unidad medida</label>
													<select class="form-control" id="unidadMov" name="unidadMov">
														<option value="0">Seleccione</option>
														<c:forEach items="${ListaUM}" var="um">
															<option value="${um.id_unidad_peso}">${um.nombre}</option>
														</c:forEach>
													</select>
													<br/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Establecimiento</label>
													<select class="form-control" id="estaMov" name="estaMov">
														<option value="0">Seleccione</option>
														<c:forEach items="${ListaE}" var="esta">
															<option value="${esta.id_establecimiento}">${esta.nombre}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
													<label>Motivo</label>
													<select class="form-control" id="motivoMov" name="motivoMov">
														<option value="3">Prestamo</option>
														<option value="4">Pago</option>
													</select>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-warning" type="submit">Guardar</button>
											<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpliar();">Cerrar</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
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