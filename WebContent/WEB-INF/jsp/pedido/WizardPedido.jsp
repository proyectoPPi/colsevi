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
						 <div class="col-md-12"> 
	               			 <div class="card wizard-card ct-wizard-orange" id="wizard">
    		                	<div class="wizard-header">
            		            	<h3>
                    		    	   <b>Pedidos</b>
                        			</h3>
                    		   </div>
		                    	<ul class="steps">
		                            <li><a href="#cliente"  data-target="#cliente" data-toggle="tab">Cliente</a></li>
		                            <li><a href="#producto" data-target="#producto" data-toggle="tab">Productos</a></li>
		                            <li><a href="#address"  data-target="#address" data-toggle="tab">Dirección</a></li>
		                        </ul>
                        		<div class="tab-content">
		                            <div class="tab-pane" id="cliente">
		                              <div class="row">
		                                  <h4 class="info-text"> Selección de Cliente</h4>
		                                  <div class="col-sm-12">
		                                      <div class="form-group">
		                                        <label>Cliente</label>
		                                        <input type="text" class="form-control" id="clienteF" placeholder="autocompletar"/>
		                                        <input type="hidden" id="CodigoPersona"/>
		                                      </div>
		                                  </div>
		                                  
		                              </div>
		                            </div>
		                            <div class="tab-pane" id="producto">
		                                <select class="form-control" id="estadoF" name="filtro" onchange="cargarHijos(this.value);">
											<option value="">Seleccione</option>
											<c:forEach items="${tipoPadre}" var="tipo">
												<option value="${tipo.id_tipo_producto}">${tipo.nombre}</option>
											</c:forEach>
										</select>
										<div class="row" style="padding: 20px;">
		                            		<div class="btn-group" id="GrupoHijos" style="left: 30%"></div>
										</div>
										<div id="VistaProductos" class="col-md-12"></div>
		                            </div>
		                            <div class="tab-pane" id="address">
		                                <div class="row">
		                                    
		                                    
		                                </div>
		                            </div>
                        		</div>
                        		<div class="wizard-footer">
	                            	<div class="pull-right">
	                                    <input type='button' class='btn btn-next btn-fill btn-warning btn-wd btn-sm' name='next' value='Siguiente' />
	                                    <input type='button' class='btn btn-finish btn-fill btn-warning btn-wd btn-sm' data-last="Finish" name='finish' value='Finalizar' />
	                                </div>
	                                <div class="pull-left">
             	                       <input type='button' class='btn btn-previous btn-fill btn-default btn-wd btn-sm' name='previous' value='Anterior' />
                	                </div>
                    	            <div class="clearfix"></div>
                        	</div>	
                		</div>
            		</div>
 
				</div>
			</section>
		</section>

		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/pedido/WizardPedido.js"></script>
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