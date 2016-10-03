<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Cliente</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
	<link href="${pageContext.request.contextPath}/resources/css/estilosWizard.css" rel="stylesheet"/>
	
</head>
<body>
	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
	<section id="container">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
							
							
							
							<div class="f1">

                    		<h2 style="text-align: center;">
								<strong>REGISTRO DE CLIENTES</strong>
							</h2>
                    		<div class="f1-steps">
                    			<div class="f1-progress">
                    			    <div class="f1-progress-line" data-now-value="16.66" data-number-of-steps="3" style="width: 16.66%;"></div>
                    			</div>
                    			<div class="f1-step active">
                    				<div class="f1-step-icon"><i class="fa fa-gear"></i></div>
                    				<p>General</p>
                    			</div>
                    			<div class="f1-step">
                    				<div class="f1-step-icon"><i class="fa fa-phone"></i></div>
                    				<p>Contacto</p>
                    			</div>
                    		    <div class="f1-step">
                    				<div class="f1-step-icon"><i class="fa fa-user"></i></div>
                    				<p>Usuario</p>
                    			</div>
                    		</div>
                    		
                    		<fieldset>
                    		    <div class="col-md-6">
			                        <label>Nombre</label>
			                        <input type="text" class="form-control" id="nombre" name="nombre"/>
			                    </div>
			                    <div class="col-md-6">
			                        <label>Apellido</label>
			                        <input type="text" class="form-control" id="apellido" name="apellido"/>
			                    </div>
			                    <div class="col-md-6">
			                        <label>Genero</label>
			                        <select class="form-control" id="genero" name="genero">
			                        	<option value="0">Seleccione</option>
			                        	<option value="1">Masculino</option>
			                        	<option value="2">Femenino</option>
			                        </select>
			                    </div>
			                    <div class="col-md-6">
									<label>Tipo Doc</label>
									<select class="form-control" id="tipoDoc" name="tipoDoc">
										<option value="0">Seleccione</option>
										<c:forEach items="${listaTD}" var="doc">
											<option value="${doc.id_tipo_documento}">${doc.nombre}</option>
										</c:forEach>
									</select>
								</div>
			                    <div class="col-md-6">    
			                        <label>Documento</label>
			                        <input type="text" class="form-control" id="documento" name="documento"/>
		                        </div>
		                        <div class="col-md-6">    
			                        <label>Direccion</label>
			                        <input type="text" class="form-control" id="direccion" name="direccion"/>
		                        </div>
                                <div class="f1-buttons">
                                    <button type="button" class="btn btn-next">Next</button>
                                </div>
                            </fieldset>

                            <fieldset>
                                
								<div class="col-md-6">
			                        <label>Telefono Fijo</label>
			                        <input type="number" class="form-control" id="telFijo" name="telFijo"/>
		                        </div>
		                        <div class="col-md-6">    
			                        <label>Telefono Celular</label>
			                        <input type="number" class="form-control" id="telCel" name="telCel"/>
		                        </div>
		                        <div class="col-md-6">    
			                        <label>Telefono Contacto</label>
			                        <input type="number" class="form-control" id="telCon" name="telCon"/>
			                        </div> 

                                <div class="f1-buttons">
                                    <button type="button" class="btn btn-previous">Previous</button>
                                    <button type="button" class="btn btn-next">Next</button>
                                </div>
                            </fieldset>

                            <fieldset>
                                <div class="col-md-12">
			                        <label>Usuario</label>
			                        <input type="text" class="form-control" id="telCon" name="telCon"/>
		                        </div>
		                        <div class="col-md-6">    
			                        <label>Contraseña</label>
			                        <input type="text" class="form-control" id="telCon" name="telCon"/>
		                        </div>
		                        <div class="col-md-6">    
			                        <label>Confirmar contraseña</label>
			                        <input type="text" class="form-control" id="telCon" name="telCon"/>
			                    </div>
			                    
                                <div class="f1-buttons">
                                    <button type="button" class="btn btn-previous">Previous</button>
                                    <button type="submit" class="btn btn-submit">Submit</button>
                                </div>
                            </fieldset>
                    	</div>
							
							
<!-- 					        <div class="wizard"> -->
<!-- 					            <div class="wizard-inner"> -->
<!-- 					                <div class="connecting-line"></div> -->
<!-- 					                <ul class="nav nav-tabs" role="tablist"> -->
					
<!-- 					                    <li role="presentation" class="active"> -->
<!-- 					                        <a href="#step1" data-toggle="tab" aria-controls="step1" role="tab" title="General"> -->
<!-- 					                            <span class="round-tab"> -->
<!-- 					                                <i class="fa fa-user"></i> -->
<!-- 					                            </span> -->
<!-- 					                        </a> -->
<!-- 					                    </li> -->
					
<!-- 					                    <li role="presentation" class="disabled"> -->
<!-- 					                        <a href="#step2" data-toggle="tab" aria-controls="step2" role="tab" title="Telefonos"> -->
<!-- 					                            <span class="round-tab"> -->
<!-- 					                                <i class="fa fa-phone"></i> -->
<!-- 					                            </span> -->
<!-- 					                        </a> -->
<!-- 					                    </li> -->
<!-- 					                    <li role="presentation" class="disabled"> -->
<!-- 					                        <a href="#step3" data-toggle="tab" aria-controls="step3" role="tab" title="Usuario"> -->
<!-- 					                            <span class="round-tab"> -->
<!-- 					                                <i class="fa fa-user"></i> -->
<!-- 					                            </span> -->
<!-- 					                        </a> -->
<!-- 					                    </li> -->
<!-- 					                </ul> -->
<!-- 					            </div> -->
					
<!-- 					            <form role="form"> -->
<!-- 					                <div class="tab-content"> -->
<!-- 					                    <div class="tab-pane active" role="tabpanel" id="step1"> -->
<!-- 					                        
<!-- 					                        <ul class="col-md-12 pull-right"> -->
<!-- 					                        	<br/> -->
<!-- 					                            <li><button type="button" class="btn btn-primary next-step">Siguiente</button></li> -->
<!-- 					                        </ul> -->
<!-- 					                    </div> -->
<!-- 					                    <div class="tab-pane" role="tabpanel" id="step2"> -->
<!-- 					                         -->
					                        
<!-- 					                        <ul class=" list-inline pull-right col-md-12"> -->
<!-- 					                        	<br/> -->
<!-- 					                            <li><button type="button" class="btn btn-default prev-step">Anterior</button></li> -->
<!-- 					                            <li><button type="button" class="btn btn-primary next-step">Siguiente</button></li> -->
<!-- 					                        </ul> -->
<!-- 					                    </div> -->
<!-- 					                    <div class="tab-pane" role="tabpanel" id="step3"> -->
<!-- 						                      -->
<!-- 					                        <ul class=" list-inline pull-right col-md-12"> -->
<!-- 					                        	<br/> -->
<!-- 					                            <li><button type="button" class="btn btn-default prev-step">Anterior</button></li> -->
<!-- 					                            <li><button type="button" class="btn btn-default">Finalizar</button></li> -->
<!-- 					                        </ul> -->
<!-- 					                    </div> -->
<!-- 					                    <div class="clearfix"></div> -->
<!-- 					                </div> -->
<!-- 					            </form> -->
<!-- 					        </div> -->
					
				</div>
			</section>
		</section>
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>

		<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/usuario/ClienteRegistro.js"></script>
</body>
</html>