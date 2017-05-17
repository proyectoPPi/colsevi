<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>App Colsevi</title>
</head>
<body>

<c:import url="/WEB-INF/jsp/plantilla/javascript_genericos.jsp"></c:import>
	<script>
		jQuery(document).ready(function(){
			window.location.href = contexto;
		});
	</script>
</body>
</html>