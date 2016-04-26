<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Inventario</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
	<style>
		.success {
	    	background:  #3F8A24;
	    }
	    .error {
	    	background:  #ff6c60;
	    }
	</style>
</head>
<body>

	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
	<section id="container" class="">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
					<h2>Inventario 
						<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
					</h2>
					
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
								<label>*Direcci&#243;n</label>
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
								<form method="post" action="${pageContext.request.contextPath}/Inventario/Inv/Guardar.html?" id="formulario">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Inventario...</h4>
										</div>
										<div class="modal-body">
											<div class="row">
												
												<input type="hidden" id="id_inventario" name="id_inventario"/>
												<input type="hidden" id="id_producto" name="id_producto"/>
												<input type="hidden" id="secuencia" name="secuencia"/>
												<input type="hidden" id="detalle" name="detalle"/>
												
												<select id="listaUnidad" style="display: none;">
													<option value="0">Seleccione</option>
													<c:forEach items="${listaUnidad}" var="um">
														<option value="${um.id_unidad_peso}">${um.nombre}</option>
													</c:forEach>
												</select>
												
												<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
													<section class="panel">
								                          <header class="panel-heading tab-bg-dark-navy-blue ">
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
										                                 <label>*Producto</label>
							                                              <input type="text" id="nombreProd" name="nombreProd" class="form-control" data-bv-notempty="true"/>
																	</div>
																	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
																		<label>*Cantidad</label>
																		<input type="text" class="form-control" id="cantSolicitada" name="cantSolicitada"/>
																	</div>
																	
																	<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
																		<label>Establecimiento</label>
																		<select class="form-control" id="establecimiento" name="establecimiento">
																			<option value="0">Seleccione</option>
																			<c:forEach items="${listaEsta}" var="esta">
																				<option value="${esta.id_establecimiento}">${esta.nombre}</option>
																			</c:forEach>
																		</select>
																	</div>
								                                  
								                                  </div>
								                                  <div id="ingview" class="tab-pane">
								                                  	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
								                                  		<div id="viewData"></div>
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
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/inventario/inventarioVista.js"></script>
	<script type="text/javascript">
		titulos = new Array();
		titulos["id_producto"] = "ID";
		titulos["nombreProd"] = "Producto";
		titulos["establecimiento"] = "Establecimiento";
		titulos["disponible"] = "disponible";
		titulos["compromiso"] = "compromiso";
		
		clase = new Array();
		clase['descipEsta'] = 'hidden-xs';
		clase['hora_inicio'] = 'hidden-xs hidden-sm';
		clase['hora_fin'] = 'hidden-xs  hidden-sm';
	</script>
</body>
</html>