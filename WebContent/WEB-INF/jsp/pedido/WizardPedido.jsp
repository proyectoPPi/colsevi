<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Pedidos</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/gsdk-base.css" />
</head>
<body>

	<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
	<section id="container" class="">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<c:import url="/WEB-INF/jsp/plantilla/alertas.jsp"></c:import>
	               			 <div class="card wizard-card ct-wizard-orange" id="wizard">
    		                	<div class="wizard-header">
            		            	<h3>
                    		    	   <b>Pedidos</b>
                        			</h3>
                    		   </div>
		                    	<ul class="steps">
		                            <li><a href="#cliente" data-target="#cliente" data-toggle="tab">Cliente</a></li>
		                            <li><a href="#producto" data-target="#producto" data-toggle="tab">Productos</a></li>
		                            <li><a href="#res" data-target="#res" data-toggle="tab">Resumen</a></li>
		                            <li><a href="#dir" data-target="#dir" data-toggle="tab">Dirección</a></li>
		                        </ul>
                        		<div class="tab-content">
		                            <div class="tab-pane" id="cliente">
		                              <div class="row">
		                                  <h4 class="info-text"> Selección de Cliente</h4>
		                                  <div class="col-sm-12">
		                                   <div class="form-group">
		                                        <label>Motivo</label>
		                                        <select class="form-control" id="interno">
		                                        	<option value="0">general</option>
		                                        	<option value="1">interno</option>
		                                        	<option value="2">invitado</option>
		                                        </select>
		                                      </div>
		                                      <div class="form-group">
		                                        <label>Cliente</label>
		                                        <input type="text" class="form-control" id="clienteF" placeholder="autocompletar"/>
		                                        <input type="hidden" id="CodigoPersona" name="filtro"/>
		                                        <input type="hidden" id="pedidosec" name="filtro"/>
		                                      </div>
											  <select class="form-control" id="estab">
												<option value="0">Establecimiento</option>
												<c:forEach items="${establecimiento}" var="est">
													<option value="${est.id_establecimiento}">${est.nombre}</option>
												</c:forEach>
											  </select>
		                                  </div>
		                              </div>
		                            </div>
		                            <div class="tab-pane" id="producto">
		                            	<div class="col-xs-12 col-md-12">
			                                <select class="form-control" id="tipoprod" onchange="cargarHijos(this.value);">
												<option value="">Tipo producto</option>
												<c:forEach items="${tipoPadre}" var="tipo">
													<option value="${tipo.id_tipo_producto}">${tipo.nombre}</option>
												</c:forEach>
											</select>
										</div>
										<div class="row">
		                            		<div id="GrupoHijos" ></div>
										</div>
										<div id="VistaProductos" class="col-md-12"></div>
		                            </div>
		                            <div class="tab-pane" id="res">
		                                <div class="row">
		                                   <div id="detallePed"></div> 
		                                   <div style="text-align:center;">
		                                    	<button type="button" class="btn btn-primary" id="actprod">Actualizar</button>
		                                    </div>
		                                </div>
		                            </div>
		                            <div class="tab-pane" id="dir">
		                                <div class="row">
		                                	<form action="/Pedido/Flujo/terminar" method="post" id="formterm">
			                                	<div class="col-md-12" style="text-align:center;">
				                                	<label>Salón</label>
				                                   	<input type="checkbox">
				                                   	<label>Domicilio</label>
				                                   	<input type="checkbox">
			                                   	</div>
			                                   	<div class="col-md-12" >
				                                	<label>Sugerencias</label>
				                                   	<input type="text" class="form-control" id="comentario" name="comentario">
			                                   	</div>
			                                   	<div class="col-md-12" style="text-align:center;">
				                                	<label>Pagado</label>
				                                   	<input type="checkbox" id="pago" name="pago">
			                                   	</div>
		                                   	</form>
		                                </div>
		                            </div>
                        		</div>
                        		<div class="wizard-footer">
	                            	<div class="pull-right">
	                                    <input type='button' class='btn btn-next btn-fill btn-warning btn-wd btn-sm' name='next' value='Siguiente' />
	                                    <input type='button' class='btn btn-finish btn-fill btn-warning btn-wd btn-sm' data-last="Finish" name='finish' value='Finalizar' onclick="terminar();"/>
	                                </div>
	                                <div class="pull-left">
             	                       <input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm' name='previous' value='Anterior' />
                	                </div>
                    	            <div class="clearfix"></div>
                        	</div>	
                		</div>
				</div>
			</section>
		</section>

		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.bootstrap.wizard.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/wizard.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/WizardPedido.js"></script>
	<script type="text/javascript">
		titulos = new Array();
		titulos["prod"] = "ID";
		titulos["nombre"] = "Producto";
		titulos["cantidad"] = "cantidad";
		titulos["sub_total"] = "Sub total";
		titulos["ref"] = "Accion";
		titulos["prodId"] = "prodId";
		
		campo = new Array();
		campo["cantidad"] = "";
		
		clase = new Array();
		clase['prodId'] = 'hidden';
		
		boton = new Array();
		boton['ref'] = [
   			{
   				color: "btn-primary",
          		img: 'fa fa-times',
          		metodo: "eliminarProd"
   			}
   		];
		
	</script>
</body>
</html>