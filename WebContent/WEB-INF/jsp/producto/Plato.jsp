<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Platos 
	<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
</h2>
<div class="separator-2"></div>
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

<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1"	 
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Plato/Guardar.html?" 
				id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
						<h4 class="modal-title">Configurar platos...</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="id_plato" name="id_plato"/>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<label>*Nombre</label>
								<input type="text" class="form-control" id="nombre" name="nombre"/>
							</div>
							<div class="col-xs-12 col-md-6">
								<label>*Valor</label>
								<input type="text" class="form-control" id="valor" name="valor"/>
							</div>
							<div class="col-xs-12 col-sm-6">
								<label>*Establecimiento</label>
								<select class="form-control" id="id_establecimiento" name="id_establecimiento">
									<option value="">Seleccione</option>
									<c:forEach items="${listaE}" var="esta">
										<option value="${esta.id_establecimiento}">${esta.nombre}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-xs-12 text-right">
								<a onclick="iniciarTipoProducto();"><i class="fa fa-plus-circle fa-2x"></i></a>
							</div>
						</div>
						<div class="row">
							<div id="contenido" class="col-xs-12">
								<table class="table table-bordered table-striped table-condensed">
									<thead>
										<tr>
											<th>Tipo de producto</th>
											<th>Cantidad</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
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
	titulos["id_plato"] = "ID";
	titulos["nombre"] = "Nombre";
	titulos["id_establecimiento"] = "Establecimiento";
	titulos["valor"] = "Valor";
	
	var arrayListaTP = new Array();
	<c:forEach items="${listaTP}" var="tipo">
		arrayListaTP.push({id: "${tipo.id_tipo_producto}", nombre: "${tipo.nombre}"});
	</c:forEach>
	
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/producto/Plato.js"></script>
