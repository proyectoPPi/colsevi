<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Inicial</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>
<body>
	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
			<div class="row">
				<img alt="" src="http://placehold.it/1920x1080">
			
				<div class="modal fade modal-dialog-center " id="ModalLogin" style="z-index: 100000;"
					tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog model-sm">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
								<h4 class="modal-title">Iniciar sesión</h4>
							</div>
							<div class="modal-body">
								<c:import url="/WEB-INF/jsp/login.jsp" />
							</div>
						</div>
					</div>
				</div>
				
			</div>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
</body>
</html>