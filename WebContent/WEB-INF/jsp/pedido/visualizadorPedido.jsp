<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Pedidos 
	<a onclick="crearPedido();"><i class="fa fa-plus-circle"></i></a>
</h2>
<div class="separator-2"></div>
<section class="panel">
	<div class="panel-body">
		<div class="col-xs-12 col-sm-6 col-md-3">
			<label>Cliente</label>
			<input type="text" class="form-control" id="clienteV" name="clienteV"/>
			<input type="hidden" id="clienteF" name="filtro"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3">
			<label>Estado</label>
			<select class="form-control" id="estadoF" name="filtro">
				<option value="">Seleccione</option>
				<c:forEach items="${listaEstado}" var="estado">
					<option value="${estado.id_estado_pedido}">${estado.nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-12"><br/>
			<button class="btn btn-button" onclick="Tabla(1);">Filtrar</button>
		</div>	
	</div>	
</section>

<div class="col-md-12">
	<div id="tabla"></div>
	<div id="paginacion" class="col-md-12"></div>
</div>

<div class="modal fade modal-dialog-center " id="DetallePedido" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
					<h4 class="modal-title">Detalle Pedido</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<section class="panel">
		                          <header class="panel-heading tab-bg-dark-navy-blue">
		                              <ul class="nav nav-tabs">
		                                  <li class="active">
		                                      <a data-toggle="tab" href="#general">Producto</a>
		                                  </li>
		                                  <li class="">
		                                      <a data-toggle="tab" href="#detalleProducto">Detalle producto</a>
		                                  </li>
		                              </ul>
		                          </header>
		                          <div class="panel-body">
		                              <div class="tab-content">
		                                  <div id="general" class="tab-pane active">
		                                  		<div class="col-md-12">
		                                  			<div class="col-xs-12 col-md-4">
		                                  				<h3>Datos del cliente</h3>
		                                  				<div class="separator-2"></div>
		                                  				<label>Nombre: </label><span></span><br/>
		                                  				<label>Documento: </label><span></span><br/>
		                                  				<label>Direccion: </label><span></span><br/>
		                                  				<label>Descripcion: </label><span></span><br/>
		                                  				<label>Telefono: </label><span></span><br/>
		                                  			</div>
		                                  			<div class="col-xs-12 col-md-4">
		                                  				<h3>Datos del pedido</h3>
		                                  				<div class="separator-2"></div>
		                                  				<label>Numero del pedido: </label><span></span><br/>
		                                  				<label>Estado: </label><span></span><br/>
		                                  				<label>Fecha de creacion: </label><span></span><br/>
		                                  				<label>Total: </label><span></span><br/>
		                                  				<label>Domicilio: </label><span></span><br/>
		                                  			</div>
		                                  			<div class="col-xs-12 col-md-4">
		                                  				<h3>Estado del pedido</h3>
		                                  				<div class="separator-2"></div>
		                                  				<label>El estado del pedido es</label>
		                                  				<select id="estadoPedido" class="form-control">
		                                  					
		                                  				</select>
		                                  				<input type="checkbox" />Notificar por correo <br/>
		                                  				<button type="button" class="btn">Cambiar el estado</button>
		                                  			</div>
		                                  		</div>
		                                  </div>
		                                  <div id="detalleProducto" class="tab-pane">
			                                  	<div class="col-md-12">
													<div id="tablaDetalle"></div>
												</div>
		                                  </div>
	                                 </div>
	                              </div>
                            </section>
                        </div>
					</div>
				</div>
				<div class="modal-footer">
						<button class="btn btn-warning" type="button" onclick="ContinuarPedido();" id="pedidoModal">Continuar</button>
						<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="LimpiarDetalle();">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	titulos = new Array();
	titulos["id"] = "ID";
	titulos["id_pedido"] = "Pedido";
	titulos["nombreCompleto"] = "Cliente";
	titulos["estado"] = "Estado";
	titulos["fecha_pedido"] = "fecha";
	titulos["total"] = "Total";
	titulos["pagado"] = "Pagado";
	
	tituloDetalle = new Array();
	tituloDetalle["nombreP"] = "producto";
	tituloDetalle["venta"] = "precio";
	
	var mensajeFlujo = '${correcto}';
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/visualizarPedido.js"></script>