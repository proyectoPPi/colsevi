<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Ingredientes</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>
<body>
	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section class="container">
			<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
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
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/PedidoWizardStep2.js"></script>
</body>
</html>