<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Pedidos</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
	<section id="container" class="">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
				<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
					<h2>Pedidos </h2>
					<section class="panel">
						<div class="panel-body">
							<div class="col-xs-12 col-sm-6 col-md-3">
								<label>Documento</label>
								<input type="text" class="form-control" id="documentoF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3">
								<label>Cliente</label>
								<input type="text" class="form-control" id="clienteF" name="filtro"/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3">
								<label>Estado</label>
								<select class="form-control" id="estadoF" name="filtro">
									<option value="">Seleccione</option>
									<c:forEach items="${listaEstado}" var="estado">
										<option value="${estado.id_estado_pedido}">${estado.nombre}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-12"><br/>
								<button class="btn btn-button" onclick="Tabla(1);">Filtrar</button>
							</div>	
						</div>	
					</section>
					<div class="col-md-12">
						<div id="tabla"></div>
					<div id="paginacion" class="col-md-12"></div>
					</div>
				</div>
			</section>
		</section>

		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/visualizarPedido.js"></script>
	<script type="text/javascript">
		titulos = new Array();
		titulos["id_pedido"] = "ID";
		titulos["documento"] = "Documento";
		titulos["nombreCompleto"] = "Nombre";
		titulos["estado"] = "Estado";
		titulos["fecha_pedido"] = "fecha";
		titulos["pagado"] = "Pagado";
		titulos["total"] = "Total";
		
	</script>
</body>
</html>