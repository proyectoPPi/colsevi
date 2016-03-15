<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Pedidos</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
	<style>
		/**
 * This work is licensed under the Creative Commons
 * Attribution 3.0 Unported License. To view a copy
 * of this license, visit http://creativecommons.org/licenses/by/3.0/.
 *
 * Author: Girish Sharma <scrapmachines@gmail.com>
 */

/* Demo specific styles begin */

* {margin: 0; padding: 0;}
body {	
	background: #eee;
}
.loader {
  top: 50%;
}
/* Demo specific styles end */

/* Loader with three blocks */
.loader, .loader:before, .loader:after {
  display: inline-block;
  width: 100%;
  height: 10px;
  position: absolute;
  z-index: 100000;
  animation: loading 4s cubic-bezier(.1,.85,.9,.15) infinite, loading-opacity 2s ease-in-out infinite alternate;
  background: linear-gradient(to right, teal 0px, teal 10px, transparent 10px)  no-repeat 0px 0px / 10px 10px;
  content: ' ';
}
.loader {
  animation-delay: .1s;
}
.loader:after {
  animation-delay: .2s;
}
@keyframes loading-opacity {
	0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 1;
  }
}
@keyframes loading {
	0% {
    background-position: -50% 0px;
  }
  100% {
    background-position: 150% 0px;
  }
}

/* 2 more loading blocks */
.loader.more:before, .loader.more:after {
  content: ' ■';
  color: teal;
  top: 0;
  line-height: 6px;
  font-size: 22px;
  font-family: "Times New Roman";
  vertical-align: top;
  animation: loading 4s cubic-bezier(.1,.85,.9,.15) infinite, loading-font 4s cubic-bezier(.1,.85,.9,.15) infinite !important;
}
.loader.more:before {
  animation-delay: 0s,.2s !important;
}
.loader.more {
  overflow: hidden;
  opacity: 0;
  animation: loading 4s cubic-bezier(.1,.85,.9,.15) infinite, loading-opacity 2s ease-in-out infinite alternate;
  animation-delay: .6s,.4s !important;
}
.loader.more:after {
  animation-delay: .4s,.8s !important;
}
@keyframes loading-font {
	0% {
    text-indent: calc(-50% - 5px);
  }
  100% {
    text-indent: calc(150% - 10px);
  }
}
	</style>
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