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
			
				<h2>Ingredientes 
				</h2>
				<div class="separator-2"></div>
				
				<form action="${pageContext.request.contextPath}/Pedido/PedidoWizardStep1/crearPedido.html" method="post">
					<input type="hidden" id="consecutivo" name="consecutivo"/>
					<div class="form-group">
                       <label>Cliente</label>
                       <input type="text" class="form-control" id="cliente" placeholder="autocompletar"/>
                       <input type="hidden" id="CodigoPersona" name="CodigoPersona"/>
                     </div>
                     <div class="form-group">
                     	<label>Establecimiento</label>
	                     <select class="form-control" id="establecimiento" name="establecimiento">
							<option value="0">Establecimiento</option>
							<c:forEach items="${establecimiento}" var="est">
								<option value="${est.id_establecimiento}">${est.nombre}</option>
							</c:forEach>
						  </select>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">Siguiente</button>
					</div>
				</form>
				
				
			</div>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/WizardPedido.js"></script>
</body>
</html>