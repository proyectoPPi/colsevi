<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Establecimientos</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>
<body>
	<section class="page-wrapper">
	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section class="container">
			<div class="row">
			<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
				<h2>Establecimiento 
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
							<label>Descripci&#243;n</label>
							<input type="text" class="form-control" id="descripcionF" name="filtro"/>
						</div>
						<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
							<label>Direcci&#243;n</label>
							<input type="text" class="form-control" id="direccionF" name="filtro"/>
						</div>
						<div class="col-xs-12 col-md-12 col-lg-12"><br/>
							<button class="btn btn-button" onclick="Tabla(1);">Filtrar</button>
						</div>	
					</div>	
				</section>
				<!-- <div id="paginacion" class="col-md-12"></div> -->
				
				<div class=" col-xs-12 col-md-12 col-lg-12">
					<div id="tabla"></div>
					<div id="paginacion" class="col-md-12"></div>
				</div>
				<div id="dtBox"></div>
				<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content-wrap">
							<form method="post" action="${pageContext.request.contextPath}/General/Establecimiento/GuardarLocal.html?" id="formulario">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
										<h4 class="modal-title">Establecimiento...</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											
											<input type="hidden" id="id_establecimiento" name="id_establecimiento"/>
											<input type="hidden" id="id_direccion" name="id_direccion"/>
											<input type="hidden" id="id_telefono" name="id_telefono"/>
											<input type="hidden" id="id_correo" name="id_correo"/>
											
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<label>*Nombre</label>
												<input type="text" class="form-control" id="nombreEsta" name="nombreEsta" maxdata-bv-notempty="true" required="required" maxlength="30"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<label>*Descripci&#243;n</label>
												<input type="text" class="form-control" id="descipEsta" name="descipEsta" required="required" data-bv-notempty="true" maxlength="30"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				                                 <label>*Hora de inicio</label>
	                                              <input type="text" id="hora_inicio" name="hora_inicio" class="form-control" required="required"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
				                                 <label>*Hora de cierre</label>
	                                              <input type="text" id="hora_fin" name="hora_fin" class="form-control"  required="required" data-field="time" data-format="HH:mm"/>
											</div>
											<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
												<label>Correo</label>
												<input type="text" class="form-control" id="correo" name="correo" data-bv-notempty="true" maxlength="50"/>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
											
												<h4><strong>DIRECCI&Oacute;N</strong></h4>
												<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
													<label>*Direcci&#243;n</label>
													<input type="text" class="form-control" id="direccion" name="direccion" data-bv-notempty="true" required="required" maxlength="50"/>
												</div>
												<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
													<label>Barrio</label>
													<input type="text" class="form-control" id="barrio" name="barrio" data-bv-notempty="true" maxlength="50"/>
												</div>
												<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
													<label>Descripci&#243;n</label>
													<input type="text" class="form-control" id="descripDir" name="descripDir" data-bv-notempty="true" maxlength="30"/>
												</div>
											</div>
											<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
												<h4><strong>TEL&Eacute;FONO</strong></h4>
												<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
													<label>*Tel&eacute;fono</label>
													<input type="number" class="form-control" id="telefono" name="telefono" required="required" maxlength="10"/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
													<label>*Tipo T&eacute;lefono</label>
													<select class="form-control" id="telTipo" name="telTipo" required="required">
														<option value="0">Seleccione</option>
														<c:forEach items="${tipoTel}" var="tipo">
															<option value="${tipo.id_tipo_telefono}">${tipo.nombre}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button class="btn btn-warning" type="submit">Guardar</button>
										<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Limpiar();">Cerrar</button>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/general/Establecimiento.js"></script>
	<script type="text/javascript">
		titulos = new Array();
		titulos["id_establecimiento"] = "ID";
		titulos["nombreEsta"] = "Nombre";
		titulos["descipEsta"] = "Descripci&#243;n";
		titulos["hora_inicio"] = "Hora Inicio";
		titulos["hora_fin"] = "Hora Fin";
		titulos["direccion"] = "Direcci&#243;n";
		titulos["telefono"] = "Telefono";
		
		clase = new Array();
		clase['descipEsta'] = 'hidden-xs';
		clase['hora_inicio'] = 'hidden-xs hidden-sm';
		clase['hora_fin'] = 'hidden-xs  hidden-sm';
	</script>
</body>
</html>