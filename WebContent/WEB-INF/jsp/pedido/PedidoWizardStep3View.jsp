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
				
				<form action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep3/continuar.html" method="post" id="continuar">
					<input type="hidden" id="secuencia" name="secuencia" value="${secuencia}"/>
					
					<div class="col-xs-6">
						<label>Seleccione algún cliente</label>
						<input type="text" class="form-control" id="cliente" id="cliente"/>
						<input type="hidden" id="persona" name="persona"/>
					</div>
					
					<div class="col-xs-12 text-right">
						<button type="submit" class="btn btn-primary">Siguiente</button>
					</div>
					
				</form>
				
				
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/PedidoWizardStep3.js"></script>
</body>
</html>