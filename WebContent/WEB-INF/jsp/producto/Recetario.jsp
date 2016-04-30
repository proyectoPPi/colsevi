<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Recetario</title>
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
				
					<h2>Recetario
						<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
					</h2>
					<section class="panel">
						<div class="panel-body">
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
								<label>Nombre</label>
								<input type="text" class="form-control" id="nombreF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
								<label>Descripción</label>
								<input type="text" class="form-control" id="descripcionF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-md-12 col-lg-12"><br/>
								<button class="btn btn-button" onclick="Tabla(1);">Filtrar</button>
							</div>	
						</div>	
					</section>
				
					<div class=" col-xs-12 col-md-12 col-lg-12">
						<div id="tabla" class="row product-list"></div>
						<div id="paginacion" class="col-md-12"></div>
					</div>
					<div class="modal fade modal-dialog-center" id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/Receta/Nivel/Guardar.html?" id="formulario">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Recetario...</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<input type="hidden" id="id_dificultad_receta" name="id_dificultad_receta"/>
												<input type="hidden" id="id_producto" name="id_producto"/>
												<input type="hidden" id="secuencia" name="secuencia"/>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<label>*producto</label>
													<input type="text" class="form-control" id="prod" name="prod" data-bv-notempty="true"/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<label>*Tiempo</label>
													<input type="number" class="form-control" id="tiempo" name="tiempo" min="0"/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<label>Dificultad Receta</label>
													<select class="form-control" id="dificultad" name="dificultad">
														<option value="0">Seleccione</option>
														<c:forEach items="${ListaD}" var="difi">
															<option value="${difi.id_dificultad_receta}">${difi.nombre}</option>
														</c:forEach>
													</select>
													<br/>
												</div>
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<h5><strong>Preparacion</strong><button type="button" class="btn btn-white"><i class="fa fa-plus"></i></button></h5>
													<div class="col-lg-12" id="detalle"></div>
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
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/producto/Recetario.js"></script>
</body>
</html>