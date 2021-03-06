<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Ingredientes</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />

</head>
<body>

	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section class="container">
			<div class="row">
			<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
			
				<h2>Ingredientes 
					<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
				</h2>
				<div class="separator-2"></div>
				<section class="panel">
					<div class="panel-body">
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
							<label>Nombre</label>
							<input type="text" class="form-control" id="nombreF" name="filtro"/>
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
							<label>Descripción</label>
							<input type="text" class="form-control" id="descripcionF" name="filtro"/>
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
							<label>Clasificar</label>
							<select class="form-control" id="clasificarF" name="filtro">
								<option value="0">Seleccione</option>
								<c:forEach items="${listaClasificar}" var="clasificar">
									<option value="${clasificar.id_clasificar_ingrediente}">${clasificar.nombre}</option>
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
				
				<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content-wrap">
							<form method="post" action="${pageContext.request.contextPath}/Ingrediente/Ing/Guardar.html?" id="formulario">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
										<h4 class="modal-title">Ingredientes</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<input type="hidden" id="id_ingrediente" name="id_ingrediente"/>
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<label>*Nombre</label>
												<input type="text" class="form-control" id="nombre" name="nombre" data-bv-notempty="true"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<label>*Descripci&#243;n</label>
												<input type="text" class="form-control" id="descripcion" name="descripcion" data-bv-notempty="true"/>
											</div>
										
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<label>Clasificar</label>
												<select class="form-control" id="clasificar" name="clasificar">
													<option value="0">Seleccione</option>
													<c:forEach items="${listaClasificar}" var="clasificar">
														<option value="${clasificar.id_clasificar_ingrediente}">${clasificar.nombre}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button class="btn btn-warning" type="submit">Guardar</button>
										<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Limpliar();">Cerrar</button>
										<button data-dismiss="modal" class="btn btn-danger" type="button" onclick="Eliminar();">Eliminar</button>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/producto/Ingrediente.js"></script>
	<script type="text/javascript">
		
		titulos = new Array();
		titulos["id_ingrediente"] = "ID";
		titulos["nombre"] = "Nombre";
		titulos["clasificar"] = "Clasificación";
		titulos["descripcion"] = "Descripci&#243;n";
		
		
	</script>
</body>
</html>