<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div id="content-wizard">
	<ul id="progressbar">
		<li><span>General</span></li>
		<li class="active"><span>Verificar</span></li>
		<li><span>Usuario</span></li>
	</ul>
</div>



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
			<button type="button" class="btn btn-primary" onclick="actualizar();">Actualizar</button>
			<button type="button" class="btn btn-primary" onclick="redireccionar();">Siguiente</button>
		</div>
	</form>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/PedidoWizardStep2.js"></script>
<script>
	initPaso2();
</script>
