<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Proveedor</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>
<body>
	<section class="page-wrapper">
	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section class="container">
			<div class="row">
			<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
			
				<h2>Proveedores 
					<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
				</h2>
				<section class="panel">
					<div class="panel-body">
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
							<label>Nombre</label>
							<input type="text" class="form-control" id="nombreF" name="filtro"/>
						</div>
						
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
							<label>Tipo proveedor</label>
							<select class="form-control" id="tipoProvF" name="filtro">
								<option value="0">Seleccione</option>
								<c:forEach items="${listaTipoProv}" var="tipoProv">
									<option value="${tipoProv.id_tipo_proveedor}">${tipoProv.nombre}</option>
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
					<div class="modal-dialog modal-lg">
						<div class="modal-content-wrap">
							<form method="post" action="${pageContext.request.contextPath}/Proveedor/Prov/Guardar.html?" id="formulario">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">Proveedores</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="alert alert-danger fade in" id="errorDivF" style="display: none;">
												 <button data-dismiss="alert" class="close close-sm" type="button">
												 	 <i class="fa fa-times"></i>
												 </button>
												 <strong>Error! </strong> <div id="mensajeEr"></div>
										    </div>
											<input type="hidden" id="id_proveedor" name="id_proveedor"/>
											<input type="hidden" id="id_direccion" name="id_direccion"/>
											<input type="hidden" id="id_telefono" name="id_telefono"/>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<label>*Nombre</label>
												<input type="text" class="form-control" id="nombre" name="nombre" maxlength="30"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<label>*Descripci&#243;n</label>
												<input type="text" class="form-control" id="descripcion" name="descripcion" maxlength="50"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
												<label>Tipo proveedor</label>
												<select class="form-control" id="tipoProv" name="tipoProv">
													<option value="0">Seleccione</option>
														<c:forEach items="${listaTipoProv}" var="tipoProv">
															<option value="${tipoProv.id_tipo_proveedor}">${tipoProv.nombre}</option>
														</c:forEach>
												</select>
											</div>
											
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<h4><strong>Dirección</strong></h4>
												<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
													<label>*Direcci&#243;n</label>
													<input type="text" class="form-control" id="direccion" name="direccion" maxlength="50"/>
												</div>
												<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
													<label>*Barrio</label>
													<input type="text" class="form-control" id="barrio" name="barrio" maxlength="50"/>
												</div>
												<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
													<label>*Descipción</label>
													<input type="text" class="form-control" id="descripDir" name="descripDir" maxlength="100"/>
												</div>
											</div>
											
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<h4><strong>Teléfono</strong></h4>
												<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
													<label>*Número</label>
													<input type="text" class="form-control" id="telefono" name="telefono" maxlength="10"/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
													<label>Tipo teléfono</label>
													<select class="form-control" id="telTipo" name="telTipo">
														<option value="0">Seleccione</option>
														<c:forEach items="${listaTipoTel}" var="tipoTel">
															<option value="${tipoTel.id_tipo_telefono}">${tipoTel.nombre}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button class="btn btn-warning" type="button" onclick="preprocesar();">Guardar</button>
										<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpliar();">Cerrar</button>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/proveedor/Proveedor.js"></script>
	<script type="text/javascript">
		
		titulos = new Array();
		titulos["id_proveedor"] = "ID";
		titulos["nombre"] = "Nombre";
		titulos["tipoProv"] = "Tipo Proveedor";
		titulos["telefono"] = "Tel&eacute;fono";
		titulos["direccion"] = "Direcci&#243;n";
		titulos["descripcion"] = "Descripci&#243;n";
		
		clase = new Array();
		clase['telefono'] = 'hidden-xs';
		clase['descripcion'] = 'hidden-xs';
		clase['tipoProv'] = 'hidden-xs';
		
	</script>
</body>
</html>