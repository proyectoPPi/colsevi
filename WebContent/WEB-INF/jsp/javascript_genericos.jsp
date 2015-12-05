    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.dcjqaccordion.2.7.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.scrollTo.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.nicescroll.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/respond.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/slidebars.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common-scripts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/assets/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.pulsate.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common-scripts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/gritter.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.ajaxQueue.min.map"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/pulstate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/herramientas.js"></script>
    
    <script type="text/javascript">
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
    
    </script>