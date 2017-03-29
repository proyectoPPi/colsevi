   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.1.custom.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.js"></script>
   
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/modernizr.js"></script>
<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/SmoothScroll.js"></script>
<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-notify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/moment-with-locales.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Chart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/herramientas.js"></script>
   
<script type="text/javascript">
   	var contexto = "${pageContext.request.contextPath}";
   	var subMenu = '${col.SubMenu}';
   	var sesion = '${col.sesion}';
   
   	if("${error}"  != undefined && "${error}" != ""){
   		HMensaje('${error}', 'danger');
   	}else if("${correcto}"  != undefined && "${correcto}" != ""){
   		HMensaje('${correcto}', 'success');	
   	}else if("${peligro}"  != undefined && "${peligro}" != ""){
   		jQuery("#peligro").show();
   		HMensaje('${peligro}', 'warning');
   	}else if("${info}"  != undefined && "${info}" != ""){
   		jQuery("#info").show();
   		HMensaje('${info}', 'info');
   	}
   	
   	if(subMenu === ''){
   		jQuery('#menuSecundario').hide();
   	}
   	if(sesion === 'T'){
   		jQuery('#menuHeader').hide();
   	}
</script>