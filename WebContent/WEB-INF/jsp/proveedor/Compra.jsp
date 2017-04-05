<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>Compras a proveedor
	<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
</h2>
<div class="separator-2"></div>
<section class="panel">
	<div class="panel-body">
		<input type="hidden" id="com" name="filtro" value="${com}"/>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<label>Pagado</label> 
			<select id="pagadoF" name="filtro" class="form-control">
				<option value="0">Seleccione</option>
				<option value="1">SI</option>
				<option value="2">NO</option>
			</select>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<label>Estado</label> 
			<select id="estadoF" name="filtro" class="form-control">
				<option value="0">Seleccione</option>
				<option value="1">Alta</option>
				<option value="2">Baja</option>
			</select>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
                            <label>Proveedor</label>
                            <select class="form-control" id="provF" name="filtro">
				<option value="">Seleccione</option>
				<c:forEach items="${listaProveedores}" var="proveedor">
					<option value="${proveedor.id_proveedor}">${proveedor.nombre}</option>
				</c:forEach>
			</select>
                       </div>
                       <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<label>Fecha Compra</label>
			<input type="text" class="form-control" id="fechaF" name="filtro"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<label>valor menor</label>
			<input type="number" class="form-control" id="valorF" name="filtro"/>
			<label>valor mayor</label> <input type="checkbox" id="valorMF" name="filtro"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
			<label>Establecimiento</label>
			<select class="form-control" id="estaF" name="filtro">
				<option value="">Seleccione</option>
				<c:forEach items="${listaEstablecimiento}" var="esta">
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
<div id="dtBox"></div>

<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Proveedor/Compra/Guardar.html?" id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
				<div class="modal-content modal-lg">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Seguimiento de Compras</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="alert alert-danger fade in" id="errorDivF" style="display: none;">
								 <button data-dismiss="alert" class="close close-sm" type="button">
								 	 <i class="fa fa-times"></i>
								 </button>
								 <strong>Error! </strong> <div id="mensajeEr"></div>
						    </div>
							<input type="hidden" id="id_compra" name="id_compra"/>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<section class="panel">
		                          <header class="panel-heading tab-bg-dark-navy-blue">
		                              <ul class="nav nav-tabs">
		                                  <li class="active">
		                                      <a data-toggle="tab" href="#producto">Compra</a>
		                                  </li>
		                                  <li class="">
		                                      <a data-toggle="tab" href="#ingview" id="carga">Ingredientes</a>
		                                  </li>
		                              </ul>
		                          </header>
		                          <div class="panel-body">
		                              <div class="tab-content">
		                                  <div id="producto" class="tab-pane active">
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
												<label>Valor</label>
												<input type="text" class="form-control" id="valorsin" name="valorsin" disabled="disabled"/>
											</div>
											<div class="col-xs-6 col-sm-3 col-md-3">
												<label>Pagado</label> <br/>
												<input type="checkbox" id="pagado" name="pagado" data-toggle="switch"/>
											</div>
		                                  	
		                                  </div>
		                                  <div id="ingview" class="tab-pane">
											<input type="hidden" value="0" id="count" name="count"/>
											<div class="col-lg-12" style="text-align: right;">
												<button type="button" class="btn btn-primary" id="adicion"> Adicionar</button>
											</div>
											<div id="IngDynamic" class="col-xs-12 col-sm-12 col-md-12">
												<br/>
												<section id="flip-scroll">
													<table class="table table-bordered table-striped table-condensed cf">
														<thead class="cf">
															<tr>
																<th>Ingrediente</th>
																<th>Cantidad</th>
																<th>Tipo de Peso</th>
																<th>Vence</th>
																<th>V. Unitario</th>
																<th></th>
															</tr>
														</thead>
														<tbody></tbody>
													</table>
												</section>
											</div>
		                                  </div>
		                              </div>
		                          </div>
    							</section>
   							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="submit" id="validarModificacion">Guardar</button>
						<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpiar();">Cerrar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade modal-dialog-center " id="ModalMotivo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Proveedor/Compra/GuardarMotivo.html?" id="formularioMotivo" data-modal="ModalMotivo" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Cancelación de la compra</h4>
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

<script type="text/javascript">
	titulos = new Array();
	titulos["id_compra"] = "ID";
	titulos["compra"] = "Secuencia compra";
	titulos["fecha_compra"] = "Fecha compra";
	titulos["valor"] = "valor";
	titulos["proveedor"] = "Proveedor";
	titulos["pagado"] = "Pagado";
	titulos["establecimiento"] = "establecimiento";
	titulos["Estado"] = "Estado";
	titulos["id_compraBoton"] = "";
	
	boton = new Array();
	boton['id_compraBoton'] = [
  			{
         		img: 'fa fa-times-circle fa-2x',
         		metodo: "MotivoModal"
  			}
  		];
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/proveedor/Compra.js"></script>
