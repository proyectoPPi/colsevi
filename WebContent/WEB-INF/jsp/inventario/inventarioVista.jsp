<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Inventario 
	<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
</h2>
<div class="separator-2"></div>

<section class="panel">
	<div class="panel-body">
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<label>Producto</label>
			<input type="text" class="form-control" id="prodF" name="filtro"/>
			<input type="hidden" id="prodV" name="filtro"/>
		</div>
		<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4">
			<label>Establecimiento</label>
			<select class="form-control" id="estaF" name="filtro">
				<option value="0">Seleccione</option>
				<c:forEach items="${listaEsta}" var="esta">
					<option value="${esta.id_establecimiento}">${esta.nombre}</option>
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
			<form method="post" action="${pageContext.request.contextPath}/Inventario/Inv/Guardar.html?" id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
						<h4 class="modal-title">Inventario...</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="alert alert-danger fade in" id="errorDivF" style="display: none;">
								<button data-dismiss="alert" class="close close-sm" type="button">
									<i class="fa fa-times"></i>
								</button>
								<strong>Error! </strong> <div id="mensajeEr"></div>
							</div>
																			
							<input type="hidden" id="id_inventario" name="id_inventario"/>
							<input type="hidden" id="id_producto" name="id_producto"/>
							<input type="hidden" id="secuencia" name="secuencia"/>
							<input type="hidden" id="detalle" name="detalle"/>
							
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
		                                              <input type="text" id="nombreProd" name="nombreProd" class="form-control"/>
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
			                                  	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="dataTables">
<!-- 							                                  		<div id="dataTables"></div> -->
												</div>
			                                  </div>
			                              </div>
			                          </div>
                 							</section>
                							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="button" onclick="validarFormulario();">Guardar</button>
						<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Limpiar();">Cerrar</button>
						<button data-dismiss="modal" class="btn btn-danger" type="button" onclick="Eliminar();">Eliminar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	titulos = new Array();
	titulos["id_producto"] = "ID";
	titulos["nombreProd"] = "Producto";
	titulos["establecimiento"] = "Establecimiento";
	titulos["disponible"] = "disponible";
	titulos["compromiso"] = "compromiso";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/inventario/inventarioVista.js"></script>
