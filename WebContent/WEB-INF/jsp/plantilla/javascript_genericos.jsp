    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
       <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/jquery-ui/jquery-ui-1.10.1.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.map"></script>
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/modernizr.js"></script>
	<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/jquery.browser.js"></script>
	<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/SmoothScroll.js"></script>
	<script src="http://htmlcoder.me/preview/the_project/v.1.3/template/plugins/waypoints/jquery.waypoints.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
	
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/herramientas.js"></script>
    
    <script type="text/javascript">
    	
    	var contexto = "${pageContext.request.contextPath}";
    	var subMenu = '${col.SubMenu}';
    	var sesion = '${col.sesion}';
    
    	jQuery("#error, #correcto, #peligro, #info").hide();
    	if("${error}"  != undefined && "${error}" != ""){
    		jQuery("#error").show();
    	}else if("${correcto}"  != undefined && "${correcto}" != ""){
    		jQuery("#correcto").show();
    	}else if("${peligro}"  != undefined && "${peligro}" != ""){
    		jQuery("#peligro").show();
    	}else if("${info}"  != undefined && "${info}" != ""){
    		jQuery("#info").show();
    	}
    	
    	if(subMenu === ''){
    		jQuery('#menuSecundario').hide();
    	}
    	if(sesion === 'T'){
    		jQuery('#menuHeader').hide();
    	}
    
    </script>