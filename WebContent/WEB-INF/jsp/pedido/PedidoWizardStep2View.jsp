<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1 class="page-title">TU CARRITO</h1>
<div class="separator-2"></div>

<form action="#" method="post" id="formulario">
	<input type="hidden" id="consecutivo" name="consecutivo" value="${consecutivo}"/>
	<div id="vistaProducto"></div>
</form>
<div class="text-right">
	<form action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep2/continuar.html" method="post" id="continuar">
		<input type="hidden" id="secuencia" name="secuencia" value="${consecutivo}"/>
		<div class="form-group">
			<button type="button" class="btn btn-primary" onclick="actualizar();">Actualizar</button>
			<button type="button" class="btn btn-primary" onclick="redireccionar();">Siguiente</button>
		</div>
	</form>
</div>

<div id="mobileCartFooter" class="hidden-sm hidden-md hidden-lg" onclick="redireccionar();">
	<div class="cartQuantity"></div>
       <span class="viewCartLink">
           <span>Validar Pedido</span>
       </span>
       <div class="cartTotal">
           <span class="currency"></span>
       </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/PedidoWizardStep2.js"></script>
<script>
	initPaso2();
</script>

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

