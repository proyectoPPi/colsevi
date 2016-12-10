<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Dashboard Admin</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>
<body>

	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section class="container">
			<div class="row">
			<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
			
				<h2>Admin
				</h2>
				<div class="separator-2"></div>
			
				<div class="col-lg-4">
				<canvas id="CompraXEstablecimientoXDia" width="200" height="200"></canvas>
				</div>	
			
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/dashboard/Admin.js"></script>
</body>
</html>