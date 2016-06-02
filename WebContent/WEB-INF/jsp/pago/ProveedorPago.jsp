<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Pago Proveedor</title>
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
					<h2>Pago Proveedores
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
					<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/pago/Proveedor/guardar.html?" id="formulario">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Registro Pago...</h4>
										</div>
										<div class="modal-body">
										<input type="hidden" id="valp" />
											<div class="row">
												<div class="col-md-12">
													<label>*Proveedor</label>
													<select class="form-control" id="prov" name="prov">
														<option value="">Seleccione</option>
														<c:forEach items="${proveedorLista}" var="proveedor">
															<option value="${proveedor.id_proveedor}">${proveedor.nombre}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-xs-12 col-md-12">
													<label>*Compra</label>
													<select class="form-control" id="compra" name="compra">
														<option value="0">Seleccione</option>
													</select>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-12">
													<label>Pendiente</label>
													<input type="text" class="form-control" id="pendiente" name="pendiente" readonly/>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-12">
													<label>*Valor a Pagar</label>
													<input type="text" class="form-control" id="valorP" name="valorP"/>
												</div>
												<div class="col-xs-12 col-md-12">
													<label>*Observaciones</label>
													<input type="text" class="form-control" id="observacion" name="observacion"/>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pago/ProveedorPago.js"></script>
	<script type="text/javascript">
		
		titulos = new Array();
		titulos["id_pago_proveedor"] = "ID";
		titulos["fecha_pago"] = "Fecha Pago";
		titulos["observacion"] = "Observación";
		titulos["valor_pagado"] = "Valor Pagado";
		titulos["compra"] = "Acciones";
		
		accion = new Array();
		accion["compra"] = [
   			{
           		metodo: "verCompra",
           		label: "compra"
    		}
    	];
	</script>
</body>
</html>