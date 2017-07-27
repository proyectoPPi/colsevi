<script type="text/javascript">
	var configuracion = new Array();
	configuracion['CANT_REGISTROS_PAGINA'] = '${col.configuracion.CANT_REGISTROS_PAGINA}';
	configuracion['DIA_VENCER_INGREDIENTE'] = '${col.configuracion.DIA_VENCER_INGREDIENTE}';
	configuracion['CORREO'] = '${col.configuracion.CORREO}';
	configuracion['IMAGEN'] = '${col.configuracion.IMAGEN}';
	
	// front
	configuracion['VER_CONTACTENOS'] = '${col.configuracion.VER_CONTACTENOS}';
	configuracion['MISION'] = '${col.configuracion.MISION}';
	configuracion['VISION'] = '${col.configuracion.VISION}';
</script>


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.1.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.bootstrap.wizard.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/SmoothScroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-notify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/moment-with-locales.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Chart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.countdown.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.countdown.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/herramientas.js"></script>
   
<script type="text/javascript">
   	var contexto = "${pageContext.request.contextPath}";
   	var subMenu = '${col.SubMenu}';
   	var sesion = new Array();
   	sesion['rol'] = '${col.sesion.rol}';
   	sesion['persona'] = '${col.sesion.persona}';
   	sesion['usuario'] = '${col.sesion.usuario}';
   	
   	if(sesion['rol'] != ''){
   		jQuery('#Login').hide();
   		jQuery('#CerrarSesion').show();
   	}else{
   		jQuery('#Login').show();
   		jQuery('#CerrarSesion').hide();
   	}
</script>