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
				
					<h2>Compras
						<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></a></i>
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
					
					<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/Proveedor/Compra/Guardar.html?" id="formulario">
									<div class="modal-content modal-lg">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Seguimiento de Compras</h4>
										</div>
										<div class="modal-body">
											<div class="row">
											<input type="hidden" id="id_compra" name="id_compra"/>
												<div class="col-xs-12 col-sm-6 col-md-6">
													<label>Proveedor</label>
													<select class="form-control" id="estadoF" name="filtro">
														<option value="">Seleccione</option>
														<c:forEach items="${listaProveedores}" var="proveedor">
															<option value="${proveedor.id_proveedor}">${proveedor.nombre}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-6">
													<label>Fecha Compra</label>
													<input type="text" class="form-control" id="nombre" name="nombre" data-bv-notempty="true"/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-6">
													<label>Clasificación Ingrediente</label>
													<select class="form-control" id="clasificarIng" onchange="CargarIngredientes();">
														<option value="">Seleccione</option>
														<c:forEach items="${listaClasificar}" var="ing">
															<option value="${ing.id_clasificar_ingrediente}">${ing.nombre}</option>
														</c:forEach>
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
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/proveedor/Compra.js"></script>
	<script type="text/javascript">
		
		titulos = new Array();
		titulos["id_compra"] = "ID";
		titulos["valor"] = "valor";
		titulos["descripcion"] = "Descripci&#243;n";
		
	</script>
</body>
</html>