<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui-1.10.1.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.form.js"></script>
   
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modernizr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/SmoothScroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pace.min.js"></script>
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
   
   	if(subMenu === ''){
   		jQuery('#menuSecundario').hide();
   	}
   	if(sesion === 'T'){
   		jQuery('#menuHeader').hide();
   	}
</script>