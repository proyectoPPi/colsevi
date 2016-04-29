<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Cierre de Caja</title>
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
				
					<h2>Cierre de Caja
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
						<div id="tabla"></div>
						<div id="paginacion" class="col-md-12"></div>
					</div>
					<div id="dtBox"></div>
					<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-jg">
							<div class="modal-content-wrap">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">Cierre de Caja...</h4>
									</div>
									<div class="modal-body">
										<input type="hidden" id="id_clasificar_ingrediente" name="id_clasificar_ingrediente"/>
										<div class="form-group">
											<label>*fecha</label>
											<input type="text" class="form-control" id="nombre" name="nombre" data-field="date"/>
										</div>
										<div class="form-group">
											<label>*Mensaje</label>
											<input type="text" class="form-control" id="mensaje" name="mensaje" data-bv-notempty="true"/>
										</div>
									</div>
									<div class="modal-footer">
										<button class="btn btn-warning" type="button" id="generar">Generar</button>
										<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpliar();">Cerrar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/caja/Cierre.js"></script>
	<script type="text/javascript">
		
		titulos = new Array();
		titulos["id_cierre_caja"] = "ID";
		titulos["nombre"] = "Responsable";
		titulos["mensaje"] = "Mensaje";
		titulos["fecha_ejecucion"] = "Fecha de ejecucion";
		titulos["fecha_cierre"] = "Fecha de Cierre";
		
	</script>
</body>
</html>