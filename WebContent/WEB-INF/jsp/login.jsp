<form class="form-horizontal text-left" action="${pageContext.request.contextPath}/login/Ingresar.html" method="post">
	<div class="form-group has-feedback">
		<label for="inputUserName" class="col-sm-3 control-label">Usuario</label>
		<div class="col-sm-8">
			<input type="text" class="form-control" id="usuario" name="usuario" placeholder="Usuario"/>
			<i class="fa fa-user form-control-feedback"></i>
		</div>
	</div>
	<div class="form-group has-feedback">
		<label for="inputPassword" class="col-sm-3 control-label">Contrase�a</label>
		<div class="col-sm-8">
			<input type="password" class="form-control" id="clave" name="clave" placeholder="Contrase�a"/>
			<i class="fa fa-lock form-control-feedback"></i>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-3 col-sm-8">
			<button type="submit" class="btn btn-group btn-default btn-animated">Ingresar <i class="fa fa-user"></i></button>
			<a href="#">Olvidaste tu contrase�a?</a>
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
