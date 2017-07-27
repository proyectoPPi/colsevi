<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Inicial</title>
	<c:import url="/WEB-INF/jsp/plantilla/estilos_genericos.jsp" />
	<style type="text/css">
		
.stepwizard-step p {
    margin-top: 10px;    
}

.process-row {
    display: table-row;
}

.process {
    display: table;     
    position: relative;
    text-align: center;
    margin: 0px auto;
}

.process-step button[disabled] {
    opacity: 1 !important;
    filter: alpha(opacity=100) !important;
}

.process-step {    
    display: table-cell;
    text-align: center;
    position: relative;
}

.process-step p {
    margin-top:10px;
    
}

.btn-circle {
  width: 140px;
  height: 140px;
  text-align: center;
  padding: 6px 0;
  font-size: 12px;
  line-height: 1.428571429;
  border-radius: 50%;
  background-color: white;
}

.btn-circle > i{
	color: #BDBDBD;
}

.btn-cicle-time{
	width: 100px !important;
	height: 100px !important;
}

.stepSpacing{
	width: 150px;
    border: 1px solid white;
    display: block;
    height: 0px;
    position: relative;
    float: left;
}

.stepTitle{
    text-transform: uppercase;
    margin: 10px 0px;
    font-weight: 600;
    font-size: 19px;
    line-height: 30px;
    color: white;
}

.stepText {
    clear: both;
    color: white;
}

.title-content{
    font-size: 45px;
    line-height: 52px;
    padding-bottom: 80px;
    padding-top: 60px;
    color: white;
}

.descriptionText{
	display: inline-block;
    text-transform: uppercase;
    top: calc(50% + 10px);
    white-space: nowrap;
    color: white;
    font-weight: 100;
    font-size: 45px;
    line-height: 52px;
}

.timeText{
	padding-top: 80px;
}
.timeIcon{
	display: inline-block;
}
	</style>
</head>
<body>
	<section class="page-wrapper">
		<c:import url="/WEB-INF/jsp/plantilla/menu.jsp"></c:import>
		<c:import url="/WEB-INF/jsp/plantilla/encabezado.jsp"></c:import>
		
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
			<section>
				<div class="row">
					<img alt="" src="https://raw.githubusercontent.com/proyectoPPi/colsevi/demo1.0/WebContent/resources/IMG_6843.png">
				</div>
			</section>
			<section class="section pv-40 index-content-bg hidden-xs" style="text-align: center;">
				<div class="row">
					<div class="title-content">
						¿Cómo funciona?
					</div>
					<div class="process">
					    <div class="process-row">
					        <div class="process-step">
					            <button type="button" class="btn btn-circle" disabled="disabled"><i class="fa fa-sign-in fa-5x"></i></button>
					            <div class="stepTitle">
						            <span class="ri-trans">seleccionar</span>
						        </div>
						        <div class="stepText">
						             <span>Elige tu</span>
						             <span>restaurante</span>
						         </div>
					        </div>
					        <div class="stepSpacing"></div>
					        <div class="process-step">
					            <button type="button" class="btn btn-circle" disabled="disabled"><i class="fa fa-cutlery fa-5x"></i></button>
					             <div class="stepTitle">
						            <span>seleccionar</span>
						        </div>
						        <div class="stepText">
						             <span>Elige tu</span>
						             <span>plato</span>
						         </div>
					        </div> 
					        <div class="stepSpacing"></div>
					         <div class="process-step">
					            <button type="button" class="btn btn-circle" disabled="disabled"><i class="fa fa-money fa-5x"></i></button>
					             <div class="stepTitle">
						            <span>Pagar</span>
						        </div>
						        <div class="stepText">
						             <span>Realiza tu <br/>pago en <br/>efectivo</span>
						         </div>
					        </div>
					         <div class="stepSpacing"></div>
					        <div class="process-step">
					            <button type="button" class="btn btn-circle" disabled="disabled"><i class="fa fa-commenting-o fa-5x"></i></button>
					             <div class="stepTitle">
						            <span>Evaluar</span>
						        </div>
						        <div class="stepText">
						             <span>Disfruta del <br/>pedido y evalua el <br/>servicio</span>
						         </div>
					        </div> 
					    </div>
					</div>
					
					<div class="row timeText">
						<div class="descriptionText">TIEMPO MEDIO</div> 
						 <div class="timeIcon">
					            <button type="button" class="btn btn-circle btn-cicle-time" disabled="disabled">20 <br/>MIN</button>
					        </div> 
						<div class="descriptionText">PARA LA ENTREGA</div>
					</div>
					
				</div>
			</section>
			
			
		<c:import url="/WEB-INF/jsp/plantilla/pie_pagina.jsp"></c:import>
	</section>
	<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
</body>
</html>