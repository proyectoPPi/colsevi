<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Empleado</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
	<link href="${pageContext.request.contextPath}/resources/css/estilosWizard.css" rel="stylesheet"/>
</head>
<body>
	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
			<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
			<section class="container">
				<div class="row">
					<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
					<form method="post" action="${pageContext.request.contextPath}/Usuario/EmpleadoRegistro/Grabar.html?" id="formulario">
						<div class="f1">
	                   		<h2 style="text-align: center;">
								<strong>REGISTRO DE EMPLEADOS</strong>
							</h2>
							<div class="separator-2"></div>
	                   		<div class="f1-steps">
	                   			<div class="f1-progress">
	                   			    	<div class="f1-progress-line" data-now-value="16.66" data-number-of-steps="4" style="width: 16.66%;"></div>
	                   			</div>
	                   			<div class="f1-step active">
	                   				<div class="f1-step-icon"><i class="fa fa-gear"></i></div>
	                   				<p>General</p>
	                   			</div>
		                   			<div class="f1-step">
		                   				<div class="f1-step-icon"><i class="fa fa-phone"></i></div>
		                   				<p>Contácto</p>
		                   			</div>
		                   			<div class="f1-step">
		                   				<div class="f1-step-icon"><i class="fa fa-road"></i></div>
		                   				<p>Dirección</p>
		                   			</div>
	                   		    <div class="f1-step">
	                   				<div class="f1-step-icon"><i class="fa fa-user"></i></div>
	                   				<p>Usuario</p>
	                   			</div>
	                   		</div>
	                   		
	                   		<fieldset data-step="1">
	                   			<input type="hidden" id="id_persona" name="id_persona" value="${bean.id_persona}"/>
	                   			<input type="hidden" id="id_usuario" name="id_usuario" value="${bean.id_usuario}"/>
	                   			<input type="hidden" id="id_direccion" name="id_direccion" value="${bean.id_direccion}"/>
	                   			<input type="hidden" id="id_telFijo" name="id_telFijo" value="${bean.id_telFijo}"/>
	                       		<input type="hidden" id="id_telCel" name="id_telCel" value="${bean.id_telCel}"/>
		                       	<input type="hidden" id="id_telCon" name="id_telCon" value="${bean.id_telCon}"/>
	                   			
	                   		    <div class="col-md-6">
			                        <label>Nombre</label>
			                        <input type="text" class="form-control" id="nombre" name="nombre" value="${bean.nombre}" maxlength="40"/>
			                    </div>
			                    <div class="col-md-6">
			                        <label>Apellido</label>
			                        <input type="text" class="form-control" id="apellido" name="apellido" value="${bean.apellido}" maxlength="40"/>
			                    </div>
			                    <div class="col-md-6">
			                        <label>Género</label>
			                        <select class="form-control" id="genero" name="genero">
			                        	<option value="0">Seleccione</option>
			                        	<option value="1">Masculino</option>
			                        	<option value="2">Femenino</option>
			                        </select>
			                    </div>
			                    <div class="col-md-6">
									<label>Tipo Doc</label>
									<select class="form-control" id="tipo_doc" name="tipo_doc">
										<option value="0">Seleccione</option>
										<c:forEach items="${tipoDoc}" var="doc">
											<option value="${doc.id_tipo_documento}">${doc.nombre}</option>
										</c:forEach>
									</select>
								</div>
			                    <div class="col-md-6">    
			                        <label>Documento</label>
			                        <input type="text" class="form-control" id="documento" name="documento" value="${bean.documento}" maxlength="10"/>
		                        </div>
		                        
		                        <div class="col-lg-12">
	                     	   		<br/>
		                            <div class="f1-buttons">
		                                <button type="button" class="btn btn-next">Siguiente</button>
		                            </div>
	                            </div>
	                       </fieldset>
	
								<fieldset data-step="2">
									<div class="col-md-6">    
				                        <label>Dirección</label>
				                        <input type="text" class="form-control" id="direccion" name="direccion"  value="${bean.direccion}" maxlength="40"/>
			                        </div>
			                        <div class="col-md-6">    
				                        <label>Barrio</label>
				                        <input type="text" class="form-control" id="barrio" name="barrio"  value="${bean.barrio}" maxlength="40"/>
			                        </div>
			                        <div class="col-md-6">    
				                        <label>Descripción</label>
				                        <input type="text" class="form-control" id="descripcion" name="descripcion"  value="${bean.descripcion}" maxlength="40"/>
			                        </div>
			                        <div class="col-lg-12">
		                     	   		<br/>
				                         <div class="f1-buttons">
					                        <button type="button" class="btn btn-previous">Anterior</button>
					                        <button type="button" class="btn btn-next">Siguiente</button>
				                        </div>
			                        </div>
								</fieldset>
									
		                        <fieldset data-step="3">
									<div class="col-md-6">
			                       		<label>Teléfono Fijo</label>
				                       	<input type="number" class="form-control" id="telFijo" name="telFijo" value="${bean.telFijo}" maxlength="7"/>
				                    </div>
			                      	<div class="col-md-6">    
				                       	<label>Teléfono Celular</label>
				                       	<input type="number" class="form-control" id="telCel" name="telCel" value="${bean.telCel}" maxlength="10"/>
			                      	</div>
			                      	<div class="col-md-6">    
				                       	<label>Teléfono Contácto</label>
				                       	<input type="number" class="form-control" id="telCon" name="telCon" value="${bean.telCon}" maxlength="7"/>
			                       	</div> 
			                       	<div class="col-lg-12">
		                     	   		<br/>
				                        <div class="f1-buttons">
					                        <button type="button" class="btn btn-previous">Anterior</button>
					                        <button type="button" class="btn btn-next">Siguiente</button>
			                        	</div>
			                        </div>
		                       </fieldset>
	                       <fieldset data-step="4">
	                           <div class="col-md-12">
			                      <label>Usuario</label>
			                      <input type="text" class="form-control" id="usuario" name="usuario" value="${bean.usuario}"/>
	                     	   </div>
	                     	   <div class="col-md-6">    
	                     	   	  <label>Contraseña</label>
	                     	   	  <input type="password" class="form-control" id="clave" name="clave" value="${bean.clave}"/>
	                     	   </div>
	                     	   <div class="col-md-6">    
	                     	   	  <label>Confirmar contraseña</label>
	                     	   	  <input type="password" class="form-control" id="repetir" name="repetir"/>
	                     	   </div>
	                     	   <div class="col-lg-12">
	                     	   		<br/>
		                           <div class="f1-buttons">
		                               <button type="button" class="btn btn-previous">Anterior</button>
		                               <button type="submit" class="btn btn-submit">Guardar</button>
		                           </div>
	                           </div>
	                       </fieldset>
	                       
	                   	</div>
                   	</form>
				</div>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>

		<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/usuario/EmpleadoRegistro.js"></script>
		<script type="text/javascript">
			var TD = '${bean.tipo_doc}';
			if(TD != '') jQuery('#tipo_doc').val(TD);
			var GEN = '${bean.genero}';
			if(TD != '') jQuery('#genero').val(GEN);
			var U = '${bean.usuario}';
			if(U != '')jQuery('#usuario').prop('disabled', true);
		
		</script>
</body>
</html>