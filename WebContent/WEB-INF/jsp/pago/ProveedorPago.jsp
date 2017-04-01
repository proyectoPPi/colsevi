<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Pago Proveedores
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
<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/pago/Proveedor/guardar.html?" id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
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
								<input type="text" class="form-control" id="proveedorText"/>
								<input type="hidden" id="prov" name="prov"/>
							</div>
							<div class="col-xs-12 col-md-12">
								<label>*Compra</label>
								<select class="form-control" id="compra" name="compra">
									<option value="0">Seleccione</option>
								</select>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
								<label>Pendiente</label>
								<input type="text" class="form-control" id="pendiente" name="pendiente" readonly/>
								<input type="hidden" id="ValPend" name="ValPend">
							</div>
							<div class="col-xs-12 col-sm-6 col-md-6">
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
						<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Limpliar();">Cerrar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
				
<script type="text/javascript">
	titulos = new Array();
	titulos["id_pago_proveedor"] = "ID";
	titulos["fecha_pago"] = "Fecha Pago";
	titulos["observacion"] = "Observación";
	titulos["valor_pagado"] = "Valor Pagado";
	titulos["compra"] = "Acciones";
	
	boton = new Array();
	boton["compra"] = [
		{
       		metodo: "verCompra",
       		img: 'fa fa-shopping-bag fa-2x'
   		}
   	];
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pago/ProveedorPago.js"></script>
