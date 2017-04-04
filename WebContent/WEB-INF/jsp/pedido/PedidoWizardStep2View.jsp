<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1 class="page-title">Verificar</h1>
<div class="separator-2"></div>

<form action="#" method="post" id="formulario">
	<input type="hidden" id="consecutivo" name="consecutivo" value="${consecutivo}"/>
	<div id="vistaProducto"></div>
</form>
<div class="text-right">
	<form action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep2/continuar.html" method="post" id="continuar">
		<input type="hidden" id="secuencia" name="secuencia" value="${consecutivo}"/>
		<div class="form-group">
			<button type="button" class="btn btn-primary" onclick="actualizar();">Siguiente</button>
		</div>
	</form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/PedidoWizardStep2.js"></script>
<script>
	initPaso2();
</script>
