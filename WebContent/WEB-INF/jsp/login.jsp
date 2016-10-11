<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="ISO-8859-1"/>
    <title>Ingresar</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
</head>

  <body>
	<section class="page-wrapper">
	    <div class="container">
		    <c:import url="plantilla/alertas.jsp"></c:import>
	
			<div class="form-block center-block p-30 light-gray-bg border-clear">
				<h2 class="title text-left">Login</h2>
				<form class="form-horizontal text-left" action="${pageContext.request.contextPath}/login/Ingresar.html" method="post">
					<div class="form-group has-feedback">
						<label for="inputUserName" class="col-sm-3 control-label">Usuario</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="usuario" name="usuario" placeholder="Usuario" required>
							<i class="fa fa-user form-control-feedback"></i>
						</div>
					</div>
					<div class="form-group has-feedback">
						<label for="inputPassword" class="col-sm-3 control-label">Contraseña</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="clave" name="clave" placeholder="Contraseña" required>
							<i class="fa fa-lock form-control-feedback"></i>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-3 col-sm-8">
							<button type="submit" class="btn btn-group btn-default btn-animated">Ingresar <i class="fa fa-user"></i></button>
							<ul class="space-top">
								<li><a href="#">Olvidaste tu contraseña?</a></li>
							</ul>
						</div>
					</div>
				</form>
				<p>¿No tienes cuenta todavía? <a href="${pageContext.request.contextPath}/Usuario/ClienteRegistro.html">Crear cuenta</a></p>
			</div>
	
			<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
			    <div class="modal-dialog">
			        <div class="modal-content">
			            <div class="modal-header">
			                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                <h4 class="modal-title">Recuperar contraseña</h4>
			            </div>
			            <div class="modal-body">
			                <p>Introduzca su dirección de correo electrónico a continuación para restablecer la contraseña.</p>
			                <input type="text" name="email" id="email" placeholder="Correo" class="form-control placeholder-no-fix">
			
			            </div>
			            <div class="modal-footer">
			                <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
			                <button class="btn btn-success" type="button">Enviar</button>
			            </div>
			        </div>
			    </div>
			</div>
	
	    </div>
	</section>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	
	var contexto = "${pageContext.request.contextPath}";
	var subMenu = "${col.SubMenu}";

	jQuery("#error, #correcto, #peligro, #info").hide();
	if("${error}"  != undefined && "${error}" != ""){
		jQuery("#error").show();
	}else if("${correcto}"  != undefined && "${correcto}" != ""){
		jQuery("#correcto").show();
	}else if("${peligro}"  != undefined && "${peligro}" != ""){
		jQuery("#peligro").show();
	}else if("${info}"  != undefined && "${info}" != ""){
		jQuery("#info").show();
	}
	
	jQuery('#bread').remove();
	</script>
  </body>
</html>