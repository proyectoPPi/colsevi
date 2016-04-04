<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Establecimientos</title>
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
					<h2>Establecimiento 
						<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
					</h2>
					
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
								<label>Dirección</label>
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
					<button type="button" id="btnBuscar" class="btn btn-default">Buscar</button>
										
					<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/General/Establecimiento/GuardarLocal.html?" id="formulario">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Administrar...</h4>
										</div>
										<div class="modal-body">
											<input type="hidden" id="id_establecimiento" name="id_establecimiento"/>
											<div class="form-group">
												<label>*Nombre</label>
												<input type="text" class="form-control" id="nombre" name="nombre" required  maxlength="15"/>
											</div>
											<div class="form-group">
												<label>*Descripci&#243;n</label>
												<input type="text" class="form-control" id="descripcion" name="descripcion" required maxlength="30"/>
											</div>
											<div class="form-group">
				                                 <label>*Hora de inicio</label>
		                                          <div class="input-group">
		                                              <input type="text" id="pruebabasicB" class="form-control timepicker-default" readonly>
		                                                <span class="input-group-btn">
		                                                <button class="btn btn-default" type="button"><i class="fa fa-clock-o"></i></button>
		                                                </span>
		                                          </div>
											</div>
											<div class="form-group">
				                                 <label>*Hora de cierre</label>
		                                          <div class="input-group">
		                                              <input type="text" id="pruebabasicA" class="form-control timepicker-default" >
		                                                <span class="input-group-btn">
		                                                <button class="btn btn-default" type="button"><i class="fa fa-clock-o"></i></button>
		                                                </span>
		                                          </div>
											</div>
											<div class="form-group">
												<label>*Correo</label>
												<input type="text" class="form-control" id="descripcion" name="descripcion" required maxlength="20"/>
											</div>
											<div class="form-group">
												<label>*Direcci&#243;n</label>
												<input type="text" class="form-control" id="descripcion" name="descripcion" required maxlength="20"/>
											</div>
											<div class="form-group">
												<label>*Tel&eacute;fono</label>
												<input type="text" class="form-control" id="descripcion" name="descripcion" required maxlength="20"/>
											</div>
											
										</div>
										<div class="modal-footer">
											<button class="btn btn-warning" type="submit">Guardar</button>
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
		</section>

		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/general/Establecimiento.js"></script>
	<script type="text/javascript">
		titulos = new Array();
		titulos["id_establecimiento"] = "ID";
		titulos["nombre"] = "Nombre";
		titulos["descripcion"] = "Descripci&#243;n";
		titulos["telefono"] = "Tel&eacute;fono";
		titulos["correo"] = "correo";
		titulos["hora_inicio"] = "Hora de inicio";
		titulos["hora_fin"] = "Hora de cierre";
		
	</script>
</body>
</html>