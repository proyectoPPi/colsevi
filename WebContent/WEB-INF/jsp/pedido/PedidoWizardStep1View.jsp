<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade modal-dialog-center" id="ModalUbicacion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep1/crearPedido.html" 
				id="formularioMotivo" data-modal="ModalUbicacion" data-tabla="detalle" data-resp="consecutivo">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
						<h4 class="modal-title">Ubicación</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
		                     <select class="form-control" id="establecimiento" name="establecimiento">
								<option value="0">Por favor seleccione un establecimiento</option>
								<c:forEach items="${establecimiento}" var="est">
									<option value="${est.id_establecimiento}">${est.nombre}</option>
								</c:forEach>
							 </select>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="submit">Guardar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div id="detalleContenido">
	<div class="light-gray-bg section" id="filtroPedidoWizard">
		<div class="container">
			<div class="col-xs-3">
				<label>Producto</label>
				<input type="text" class="form-control" id="producto" id="producto"/>
				<input type="hidden" id="codigo_prod" name="codigo_prod"/>
			</div>
			
			<div class="col-xs-12 text-center">
				<a href="#" class="btn btn-warning">Buscar</a>
				</div>
		</div>
	</div>
			
	<section class="container" style="margin-top: 30px;">
		<div class="row">
			<input type="hidden" id="consecutivo" name="consecutivo"/>
		
			
			<div id="vistaProducto"></div>
			
			<form action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep2.html" method="post" id="formulario">
				<input type="hidden" id="sec" name="sec"/>
				<div class="col-xs-12">
					<button type="button" class="btn btn-primary" onclick="continuarPaso1();">Siguiente</button>
				</div>
			</form>
			
		</div>
	</section>
</div>		
<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/WizardPedido.js"></script>
<script>
	initPaso1();
</script>
