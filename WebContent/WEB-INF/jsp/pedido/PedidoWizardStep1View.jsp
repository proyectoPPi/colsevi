<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <c:import url="/WEB-INF/jsp/plantilla/contador.jsp"></c:import> --%>

<div class="modal fade modal-dialog-center" id="ModalUbicacion" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" 
aria-hidden="true" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep1/crearPedido.html" 
				id="formularioMotivo" data-modal="ModalUbicacion" data-tabla="detalle" data-resp="consecutivo">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Ubicación</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
		                     <select class="form-control" id="establecimiento" name="establecimiento">
								<option value="">Por favor seleccione un establecimiento</option>
								<c:forEach items="${establecimiento}" var="est">
									<option value="${est.id_establecimiento}">${est.nombre}</option>
								</c:forEach>
							 </select>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="button" onclick="validarFormulario();">Guardar</button>
						<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Cancelar();">Canclar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade modal-dialog-center" id="modalProductoCarrito" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content-wrap">
			<div class="modal-content">
				<div class="imgProduct"></div>
				<div class="modal-body">
					<input type="hidden" id="CarritoProductoModalDetalle" />
					<div class="row" id="productTitle"></div>
					<div class="row">
						<div class="col-md-6" style="float: left;">
							<div class="col-xs-12 col-md-3" style="margin-top: 9px;">
								CANTIDAD:
							</div>
							<div class="col-xs-12 col-md-7">
							    <div class="input-group">
				          			<span class="input-group-btn">
				          				<button type="button" class="close btn-number" disabled="disabled" data-type="minus" data-field="cantidadCarritoDetalle">
				          					<i class="fa fa-minus-circle" style="margin-right: 10px;"></i>
				          				</button>
					          		</span>
						          	<input type="text" id="cantidadCarritoDetalle" name="cantidadCarritoDetalle" 
						          		class="form-control input-number" value="1" min="1" max="10">
						          	<span class="input-group-btn">
						          		<button type="button" class="close btn-number" data-type="plus" data-field="cantidadCarritoDetalle">
						          			<i class="fa fa-plus-circle" style="margin-left: 10px;"></i>
						          		</button>
						          	</span>
						      	</div>
							</div>
							
						</div>
						<div class="col-xs-12 col-md-6 totalmodalProduct" style="float: right;">
		                    <label>TOTAL:</label>
							<span id="precioModalDetalleCarrito">zzz</span>
						</div>
					</div>
				</div>
				<div class="modal-footer" style="padding: 0px;">
					<button class="btn btn-warning" type="button" onclick="OpcionVista(1);" style="width: 100%; margin: 0px;text-transform: uppercase; padding-top: 12px;padding-bottom: 12px;font-weight: bold;">Guardar</button>
				</div>
			</div>
		</div>
	</div>
</div>

<style>
.filterProductWizard{
	border-top: 1px solid #BDBDBD;
    background-color: white;
    box-shadow: 0px 3px 3px rgba(0, 0, 0, 0.3);
    position: relative;
    padding: 0px;
}
</style>

<div id="detalleContenido">
	<section class="section index-content-bg" style="text-align: center;" >
	</section>
	
	<section class="section filterProductWizard">
		<input type="hidden" id="codigo_prod" name="codigo_prod"/>
		<div class="row">
			<div class="col-md-4 text-right">
				<div class="has-feedback" style="margin: 10px;">
					<input type="text" class="form-control" id="producto" id="producto" style="border-radius: 24px !important;">
					<i class="fa fa-search form-control-feedback"></i>
				</div>
			</div>
		</div>
	</section>
	
	<section class="container" style="margin-top: 30px;">
		<div class="row">
			<input type="hidden" id="consecutivo" name="consecutivo"/>
			<div class="col-xs-12 col-md-9" style="padding-right: 8px;padding-left: 8px;">
				<div id="vistaProducto"></div>
			</div>
			<div class="hidden-xs col-md-3" style="padding-right: 8px;padding-left: 8px;">
				<div class="row">
					<div class="contenedorCarrito">
						<div class="carrito">
							<div class="tituloCarrito">
								<span class="floatLeft">TU CARRITO</span>
								<i style="color: #BDBDBD;" class="fa fa-shopping-cart fa-2x precio"></i>
							</div>
							<div class="contenedorProductos">
								<div id="productosDetalle">
									<div class="Carritovacio">
										<i class="fa fa-shopping-bag fa-5x"></i>
										<br/>
										<span style="display: block;margin-top: 20px;">No has añadido ningún producto a tu carrito de la compra.</span>
									</div>
								</div>
								<div class="total row">
									<span class="floatLeft" style="float: left;">TOTAL:</span> 
									<span class="precioCarrito precio">0</span>
								</div>
							</div>
							<div class="ValidarCarrito" onclick="continuarPaso1();">VALIDAR</div>
						</div>
					</div>
				</div>
			</div>
			<form action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep2.html" method="post" id="formulario">
				<input type="hidden" id="sec" name="sec"/>
			</form>
		</div>
		<div id="mobileCartFooter" class="hidden-sm hidden-md hidden-lg" onclick="continuarPaso1();">
			<div class="cartQuantity">0</div>
	        <span class="viewCartLink">
	            <span>Ver el carrito</span>
	        </span>
	        <div class="cartTotal">
	            <span class="currency"></span>
	        </div>
		</div>
	</section>
	
	<section class="container" style="margin-top: 30px;">
		comentarios de nuestros clientes
	</section>
	
</div>		
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/WizardPedido.js"></script>

<style>
.contenedorCarrito{
/* 	position: fixed; */
}
.carrito{
	background-color: white;
    border: 1px solid #BDBDBD;
    border-radius: 5px;
    overflow: hidden;
    box-shadow: 0px 0px 5px 1px #EEE;
}
.tituloCarrito{
	    border-bottom: 1px solid #BDBDBD;
    padding: 13.5px 20px 13.5px 20px;
    text-transform: uppercase;
    font-weight: 600;
    }
.total{
	background-color: #F5F5F5;
	padding: 13.5px 20px;
    margin: 0px;
}

.precio{
	float: right;
}

.floatLeft{
	text-align: left !important;
}
    
.ValidarCarrito{
	    width: 100%;
    background-color: #00a58c;
    color: white;
    text-transform: uppercase;
    padding: 8px 0px;
    cursor: pointer;
    display: inline-block;
    text-align: center;
    font-weight: 600;
}
.Carritovacio{
	margin: 20px;
    color: #BDBDBD;
    text-align: center;
    font-size: 16px;
    line-height: 20px;
}
.nombreDetalle{
	width: auto;
    float: left;
    color: #ef7e8a;
}

#productosDetalle{
	margin: 20px;
}

#mobileCartFooter{
	position: fixed;
    bottom: 0px;
    width: calc(100% - 40px);
    text-align: center;
    background-color: #00a58c;
    color: white;
    height: 50px;
    padding: 0px 20px;
    cursor: pointer;
    z-index: 10;
    font-weight: 600;
}
#mobileCartFooter .cartQuantity {
    float: left;
    background-color: white;
    height: 30px;
    width: 30px;
    border-radius: 15px;
    text-align: center;
    color: #00a58c;
    line-height: 30px !important;
    font-size: 18px;
    line-height: 24px;
    position: relative;
    top: 50%;
    -webkit-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
    }
#mobileCartFooter .viewCartLink {
    display: inline-block;
    font-weight: 600;
    position: relative;
    top: 50%;
    -webkit-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
}

#mobileCartFooter .cartTotal {
    float: right;
    font-weight: 600;
    position: relative;
    top: 50%;
    -webkit-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
}

.imgProduct{    
height: 180px;
    width: 100%;
    }
    
.totalmodalProduct {
    font-size: 26px;
    font-weight: 100;
    float: right;
}

#productTitle {
    margin-bottom: 20px;
    font-weight: 600;
    font-size: 35px;
}
</style>
