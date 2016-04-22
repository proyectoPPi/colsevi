<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Compras</title>
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
					<h2>Compras<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a></h2>
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
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
                                  <label>Fecha</label>
                                  <input type="text" class="form-control" id="FechaF" data-field="datetime" data-format="yyyy-MM-dd hh:mm:ss AA"/>
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
						<div class="modal-dialog modal-lg">
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
													<label>*Proveedor</label>
													<select class="form-control" id="proveedor" name="proveedor">
														<option value="">Seleccione</option>
														<c:forEach items="${listaProveedores}" var="proveedor">
															<option value="${proveedor.id_proveedor}">${proveedor.nombre}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-6">
													<label>*Establecimiento</label>
													<select class="form-control" id="establecimiento" name="establecimiento">
														<option value="">Seleccione</option>
														<c:forEach items="${listaEstablecimiento}" var="esta">
															<option value="${esta.id_establecimiento}">${esta.nombre}</option>
														</c:forEach>
													</select>
												</div>
												<div class="col-xs-12 col-sm-6 col-md-6">
													<label>*Fecha Compra</label>
													<input type="text" class="form-control" id="fecha_compra" name="fecha_compra" data-field="datetime" data-format="yyyy-MM-dd HH:mm:ss"/>
												</div>
												<div class="col-xs-6 col-sm-3 col-md-3">
													<label>*Valor</label>
													<input type="text" class="form-control" id="valorsin" name="valorsin"/>
												</div>
												<div class="col-xs-6 col-sm-3 col-md-3">
													<label>Pagado</label> <br/>
													<input type="checkbox" id="pagado" name="pagado" data-toggle="switch"/>
												</div>
												
												<div class="col-xs-12 col-sm-6 col-md-6">
													<label>*Clasificación Ingrediente</label>
													<select class="form-control" id="clasificarIng">
														<option value="">Seleccione</option>
														<c:forEach items="${listaClasificar}" var="ing">
															<option value="${ing.id_clasificar_ingrediente}">${ing.nombre}</option>
														</c:forEach>
													</select>
												</div>
												<input type="hidden" value="0" id="count" name="count"/>
												<div class="col-xs-12 col-sm-12 col-md-12" id="dynamic">
													<div class="col-xs-12 col-sm-3 col-md-3">
														<div id="Ing"></div>	
													</div>
													<div class="col-xs-6 col-sm-3 col-md-3">
														<label>*cantidad</label>
														<input type="number" class="form-control" id="cantidad" name="cantidad" min="0"/>
													</div>
													<div class="col-xs-12 col-sm-3 col-md-3">
														<label>*Tipo peso</label>
														<select class="form-control" id="tipopeso">
															<option value="">Seleccione</option>
															<c:forEach items="${listaTipoPeso}" var="tipo">
																<option value="${tipo.id_unidad_peso}">${tipo.nombre}</option>
															</c:forEach>
														</select>
													</div>
													<div class="col-xs-6 col-sm-3 col-md-3"><br/>
														<button type="button" class="btn btn-primary" id="adicion"> Adicionar</button>
													</div>
												</div>
												<div id="IngDynamic" class="col-xs-12 col-sm-12 col-md-12">
												<br/>
													<table class="display table table-bordered table-striped dataTable">
														<thead>
															<tr>
																<th>Ingrediente</th>
																<th>Cantidad</th>
																<th class="hidden-xs">Tipo de Peso</th>
																<th>Fecha de vencimiento</th>
																<th>Acción</th>
															</tr>
														</thead>
														<tbody></tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-warning" type="submit">Guardar</button>
											<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpliar();" data-dismiss="modal">Cerrar</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					
					<div class="modal fade modal-dialog-center " id="ModalMotivo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-lg">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/Proveedor/Compra/GuardarMotivo.html?" id="formulario">
									<div class="modal-content modal-lg">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Motivo de cancelación de la compra</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<input type="hidden" id="id_compraMotiv" name="id_compraMotiv"/>
												<div class="col-xs-12 col-sm-12 col-md-12">
													<label>Motivo</label>
													<input type="text" class="form-control" id="motivo" name="motivo"/>
												</div>
					
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-warning" type="submit">Guardar</button>
											<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpliar();" data-dismiss="modal">Cerrar</button>
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
		titulos["fecha_compra"] = "Fecha compra";
		titulos["valor"] = "valor";
		titulos["proveedor"] = "Proveedor";
		titulos["pagado"] = "Pagado";
		titulos["establecimiento"] = "establecimiento";
		titulos["Estado"] = "Estado";
		titulos["id_compraBoton"] = "Estado";
		
		clase = new Array();
		clase['proveedor'] = 'hidden-xs';
		clase['Estado'] = 'hidden-xs';
		
		boton = new Array();
		boton['id_compraBoton'] = [
   			{
   				color: "btn-primary",
          		img: 'fa fa-sort-desc',
          		metodo: "MotivoModal"
   			}
   		];
		
	</script>
</body>
</html>