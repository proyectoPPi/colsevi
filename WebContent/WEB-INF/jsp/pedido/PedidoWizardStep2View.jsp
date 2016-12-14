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
		<div class="light-gray-bg section">
			<div class="container">
				<div class="sorting-filters text-center mb-20">
					<div class="form-inline">
						<input type="hidden" id="consecutivo" name="consecutivo" value="${consecutivo}"/>
						<div class="form-group">
							<label>Producto</label>
							<input type="text" class="form-control" id="producto" id="producto"/>
							<input type="hidden" id="codigo_prod" name="codigo_prod"/>
						</div>
						
						<div class="form-group">
							<a href="#" class="btn btn-warning">Buscar</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<section class="container">
			<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
			
				<div id="vistaProducto"></div>
				
				<form action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep3.html" method="post">
					<input type="hidden" id="secuencia" name="secuencia" value="${consecutivo}"/>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Siguiente</button>
					</div>
				</form>
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/PedidoWizardStep2.js"></script>
</body>
</html>