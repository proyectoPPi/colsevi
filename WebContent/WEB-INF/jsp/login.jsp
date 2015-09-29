<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Brayan">
    <meta name="keyword" content="sevillana, Colonial, Pedidos, Domicilios">
    <link rel="shortcut icon" href="img/favicon.html">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    
    
   
    <link href="resources/css/bootstrap.min.css" type="text/css" media="screen" rel="stylesheet"/>
    <link href="resources/css/bootstrap-reset.css" type="text/css" media="screen" rel="stylesheet"/>
    <!--external css-->
    <link href="resources/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="resources/css/style.css" rel="stylesheet"/>
    <link href="resources/css/style-responsive.css" rel="stylesheet" />

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>

  <body class="login-body">

    <div class="container">

      <form class="form-signin" action="http://thevectorlab.net/flatlab/index.html">
        <h2 class="form-signin-heading">Col Sevi</h2>
        <div class="login-wrap">
            <input type="text" class="form-control" placeholder="Usuario" autofocus>
            <input type="password" class="form-control" placeholder="Contraseña">
            <label class="checkbox">
                
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> Olvidaste tu contraseña?</a>
                </span>
                <br/>
            </label>
            
            <button class="btn btn-lg btn-login btn-block" type="submit">Ingresar</button>
          
            <div class="registration">
                    ¿No tienes cuenta todavía?
                <a class="" href="registration.html">
                    Crear cuenta
                </a>
            </div>

        </div>

          <!-- Modal -->
          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
              <div class="modal-dialog">
                  <div class="modal-content">
                      <div class="modal-header">
                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                          <h4 class="modal-title">Recuperar contraseña?</h4>
                      </div>
                      <div class="modal-body">
                          <p>Introduzca su dirección de correo electrónico a continuación para restablecer la contraseña.</p>
                          <input type="text" name="email" placeholder="Correo" autocomplete="off" class="form-control placeholder-no-fix">

                      </div>
                      <div class="modal-footer">
                          <button data-dismiss="modal" class="btn btn-default" type="button">Cancelar</button>
                          <button class="btn btn-success" type="button">Enviar</button>
                      </div>
                  </div>
              </div>
          </div>
          <!-- modal -->

      </form>

    </div>



    <!-- js placed at the end of the document so the pages load faster -->
    <script src="resources/js/jquery.js"></script>
    <script src="resources/js/bootstrap.min.js"></script>
    


  </body>

<!-- Mirrored from thevectorlab.net/flatlab/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Fri, 25 Sep 2015 02:42:38 GMT -->
</html>
