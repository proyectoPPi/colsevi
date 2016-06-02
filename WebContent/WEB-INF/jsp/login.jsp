<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="ISO-8859-1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Brayan">
    <meta name="keyword" content="sevillana, Colonial, Pedidos, Domicilios">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.html">

    <title>Ingresar</title>

    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-reset.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

  <body class="login-body">

    <div class="container">
    <c:import url="plantilla/alertas.jsp"></c:import>

      <form class="form-signin" action="${pageContext.request.contextPath}/login/Ingresar.html" method="post">
        <h2 class="form-signin-heading">ColSevi</h2>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="Usuario" autofocus id="usuario" name="usuario" maxlength="20" required/>
            <input type="password" class="form-control" placeholder="Contrase�a" id="clave" name="clave" maxlength="20" required/>
            <label class="checkbox">
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Olvidaste tu contrase�a</a>
                </span>
                <br/>
            </label>
            <button class="btn btn-lg btn-login btn-block" type="submit">Ingresar</button>
            <div class="registration">
                    �No tienes cuenta todav�a?
                <a class="" href="#">
                    Crear cuenta
                </a>
            </div>
        </div>
      </form>

      <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                      <h4 class="modal-title">Recuperar contrase�a</h4>
                  </div>
                  <div class="modal-body">
                      <p>Introduzca su direcci�n de correo electr�nico a continuaci�n para restablecer la contrase�a.</p>
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