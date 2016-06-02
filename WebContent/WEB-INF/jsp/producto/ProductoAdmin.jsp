<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Productos</title>
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
				
					<h2>Productos 
						<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
					</h2>
					<section class="panel">
						<div class="panel-body">
							<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
								<label>Producto</label>
								<input type="text" class="form-control" id="prodF" name="prodF" value="${prod}"/>
								<input type="hidden" id="prodV" name="filtro" value="${prod}"/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
								<label>Clasificar</label>
								<select class="form-control" id="clasificarF" name="filtro">
									<option value="0">Tipo Producto</option>
									<c:forEach items="${listaTipo}" var="tipo">
										<c:if test="${tipo.seleccionable}">
											<option value="${tipo.id}" disabled>${tipo.nombre}</option>	
										</c:if>
										<c:if test="${!tipo.seleccionable}">
											<option value="${tipo.id}">--- ${tipo.nombre}</option>	
										</c:if>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
								<label>Venta</label>
								<input type="number" class="form-control" id="ventaF" name="filtro"/>
								<label>Mayor valor</label> <input type="checkbox" id="mayorF" name="filtro"/>
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
						<div class="modal-dialog  modal-lg">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/Producto/Admin/Guardar.html?" id="formulario">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Producto</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												<input type="hidden" id="id_producto" name="id_producto"/>
												
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<section class="panel">
								                          <header class="panel-heading tab-bg-dark-navy-blue">
								                              <ul class="nav nav-tabs">
								                                  <li class="active">
								                                      <a data-toggle="tab" href="#producto">Producto</a>
								                                  </li>
								                                  <li class="">
								                                      <a data-toggle="tab" href="#ingview" id="carga">Ingredientes</a>
								                                  </li>
								                              </ul>
								                          </header>
								                          <div class="panel-body">
								                              <div class="tab-content">
								                                  <div id="producto" class="tab-pane active">
								                                  	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
																		<label>*Referencia</label>
																		<input type="text" class="form-control" id="referencia" name="referencia" data-bv-notempty="true"/>
																	</div>
																	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
																		<label>Clasificar</label>
																		<select class="form-control" id="tipoP" name="tipoP">
																			<option value="0">Tipo Producto</option>
																			<c:forEach items="${listaTipo}" var="tipo">
																				<c:if test="${tipo.seleccionable}">
																					<option value="${tipo.id}" disabled>${tipo.nombre}</option>	
																				</c:if>
																				<c:if test="${!tipo.seleccionable}">
																					<option value="${tipo.id}">--- ${tipo.nombre}</option>	
																				</c:if>
																			</c:forEach>
																		</select>
																	</div>
																	
																	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
																		<label>*Nombre</label>
																		<input type="text" class="form-control" id="nombre" name="nombre" data-bv-notempty="true"/>
																	</div>
																	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
																		<label>*Descripci&#243;n</label>
																		<input type="text" class="form-control" id="descripcion" name="descripcion" data-bv-notempty="true"/>
																	</div>
																	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
																		<label>*Precio de venta</label>
																		<input type="number" class="form-control" id="venta" name="venta" data-bv-notempty="true"/>
																	</div>
								                                  
								                                  </div>
								                                  <div id="ingview" class="tab-pane">
								                                  	<div class="col-xs-12 col-sm-6 col-md-6">
																		<label>Clasificación Ingrediente</label>
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
																			<label>cantidad</label>
																			<input type="number" class="form-control" id="cantidad" name="cantidad" min="0"/>
																		</div>
																		<div class="col-xs-12 col-sm-3 col-md-3">
																			<label>Tipo peso</label>
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
																					<th class="hidden-xs">TipoPeso</th>
																					<th>Acción</th>
																				</tr>
																			</thead>
																			<tbody></tbody>
																		</table>
																	</div>
								                                  </div>
								                              </div>
								                          </div>
	                     							</section>
                     							</div>
												
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/producto/productoAdmin.js"></script>
	<script type="text/javascript">
	
		
		titulos = new Array();
		titulos["id_producto"] = "ID";
		titulos["referencia"] = "Referencia";
		titulos["nombre"] = "Nombre";
		titulos["descripcion"] = "Descripci&#243;n";
		titulos["tipoP"] = "Tipo";
		titulos["venta"] = "Venta";
		
		clase = new Array();
		clase['tipoP'] = 'hidden-xs';
		clase['descripcion'] = 'hidden-xs';
		
	</script>
</body>
</html>