    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slidebars.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common-scripts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.validate.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/colorpicker/js/bootstrap-colorpicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/colorpicker/js/bootstrap-colorpicker-plus.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dcjqaccordion.2.7.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/respond.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.pulsate.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gritter.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.map"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pulstate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/fuelux/js/spinner.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/bootstrap-datetimepicker/js/DateTimePicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/bootstrap-datetimepicker/js/i18n/DateTimePicker-i18n-es.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/jquery-multi-select/js/jquery.multi-select.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/jquery-multi-select/js/jquery.quicksearch.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap-switch.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.tagsinput.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/ga.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/jquery-ui/jquery-ui-1.10.1.custom.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/herramientas.js"></script>
    
    <script type="text/javascript">
    	
    	var contexto = "${pageContext.request.contextPath}";
    	var subMenu = "${col.SubMenu}";
    
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
    	
    	if(subMenu == ""){
    		jQuery('#bread').remove();
    	}
    
    </script>