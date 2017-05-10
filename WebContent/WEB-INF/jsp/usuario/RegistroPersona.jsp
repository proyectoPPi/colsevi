<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Personas</h2>
<div class="separator-2"></div>

<form method="post" action="${pageContext.request.contextPath}/Usuario/RegistroPersona/Grabar.html?" 
	id="Formulario" data-modal="">
		
	<input type="hidden" id="id_persona" name="id_persona" value="${bean.id_persona}"/>
	<input type="hidden" id="id_usuario" name="id_usuario" value="${bean.id_usuario}"/>
	<input type="hidden" id="id_direccion" name="id_direccion" value="${bean.id_direccion}"/>
	<input type="hidden" id="id_telFijo" name="id_telFijo" value="${bean.id_telFijo}"/>
   	<input type="hidden" id="id_telCel" name="id_telCel" value="${bean.id_telCel}"/>
    <input type="hidden" id="id_telCon" name="id_telCon" value="${bean.id_telCon}"/>

	<div class="row">
		<div class="col-md-6">
			<label>* Nombre</label> 
			<input type="text" class="form-control"	id="nombre" name="nombre" value="${bean.nombre}" />
		</div>
		
		<div class="col-md-6">
	        <label>* Apellido</label>
	        <input type="text" class="form-control" id="apellido" name="apellido" value="${bean.apellido}"/>
	    </div>
    </div>
    
	<div class="row">
		<div class="col-md-6" id="TipoCliente">
			<label>* Tipo Cliente</label> 
			<select class="form-control" id="tipo_doc" name="tipo_doc">
				<option value="">Seleccione</option>
				<c:forEach items="${tipoDoc}" var="doc">
					<option value="${doc.id_tipo_documento}">${doc.nombre}</option>
				</c:forEach>
			</select>
		</div>
		
		<div class="col-md-6">    
			<label>* Documento</label>
			<input type="number" class="form-control" id="documento"name="documento" value="${bean.documento}"/>
		</div>
	</div>

	<div class="row">	
		<div class="col-md-6" id="ROL">
			<label>* Perfil</label> 
			<select class="form-control" id="rolPersona" name="rolPersona">
				<option value="">Seleccione</option>
				<c:forEach items="${LRol}" var="r">
					<option value="${r.id_rol}">${r.nombre_rol}</option>
				</c:forEach>
			</select>
		</div>
	
		<div class="col-md-6">
			<label>*Género</label> 
			<select class="form-control" id="genero" name="genero">
				<option value="1">Masculino</option>
				<option value="2">Femenino</option>
			</select>
		</div>
	</div>
	
	<div class="separator with-icon"><i class="fa fa-search bordered"></i></div>
	
	<div class="row">
		<div class="col-xs-12 col-md-4">
			<label>Dirección</label> 
			<input type="text" class="form-control" id="direccion" name="direccion" value="${bean.direccion}" />
		</div>
		<div class="col-xs-12 col-md-4">
			<label>Barrio</label> 
			<input type="text" class="form-control"	id="barrio" name="barrio" value="${bean.barrio}" />
		</div>
		
		<div class="col-xs-12 col-md-4">
			<label>Descripción</label> 
			<input type="text" class="form-control" id="descripcion" name="descripcion" value="${bean.descripcion}" />
		</div>
	</div>

	<div class="separator with-icon"><i class="fa fa-phone bordered"></i></div>
	
	<div class="row">
		<div class="col-xs-12 col-md-4">
			<label>Fijo</label> 
			<input type="number" class="form-control" id="telFijo" name="telFijo" value="${bean.telFijo}"/>
		</div>
		
		<div class="col-xs-12 col-md-4">
			<label>Celular</label> 
			<input type="number" class="form-control" id="telCel" name="telCel" value="${bean.telCel}"/>
		</div>
		
		<div class="col-xs-12 col-md-4">
			<label>Contacto</label> 
			<input type="number" class="form-control" id="telCon" name="telCon" value="${bean.telCon}"/>
		</div>
	</div>
	
	<div class="separator with-icon"><i class="fa fa-user bordered"></i></div>

	<div class="row">	
		<div class="col-xs-12">
			<label>* Usuario</label> 
			<input type="text" class="form-control" id="usuario" name="usuario" value="${bean.usuario}" />
		</div>	
		
		<div class="col-md-6">
			<label>* Contraseña</label> 
			<input type="password" class="form-control" id="clave" name="clave" value="${bean.clave}" />
		</div>
		
		<div class="col-md-6">
			<label>* Confirmar contraseña</label> 
			<input type="password" class="form-control" id="repetir" name="repetir" />
		</div>
	</div>	
		
	<div class="text-right">
		<div class="col-xs-12">
			<button class="btn btn-warning" type="button" onclick="validarFormulario();">Guardar</button>
		</div>
	</div>
	
</form>

<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/usuario/RegistroPersona.js"></script>
		<script type="text/javascript">
			if(sesion['rol'] == '3')
				jQuery('#TipoCliente').hide();
			
			var TD = '${bean.tipo_doc}';
			if (TD != '')
				jQuery('#tipo_doc').val(TD);
			var GEN = '${bean.genero}';
			if (TD != '')
				jQuery('#genero').val(GEN);
			var U = '${bean.usuario}';
			if (U != '')
				jQuery('#usuario').prop('disabled', true);
		</script>
