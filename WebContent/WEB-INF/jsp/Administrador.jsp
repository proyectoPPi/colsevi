<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Administrador</title>
	<c:import url="estilos_genericos.jsp"></c:import>
	<c:import url="javascript_genericos.jsp"></c:import>
	<style type="text/css">
		#footer {
    width: 100%;
    position: absolute;
    bottom: 0;
    left: 0;
}
	</style>
</head>
<body>

	<section id="container" class="">
		<header class="header white-bg">
			<div class="sidebar-toggle-box">
				<div data-original-title="Toggle Navigation" data-placement="right" class="fa fa-bars tooltips"></div>
			</div>
			<a href="index.html" class="logo">COL<span>SEVI</span></a>
		</header>
		<c:import url="menu.jsp"></c:import>
		<section id="main-content">
			<section class="wrapper">
				<div class="row">
					<section class="panel">
						<header class="panel-heading">Administrador 
							<span class="tools pull-right">
								<a class="btn btn-danger" data-toggle="modal" href="#myModal4">Crear</a>
                            </span>
						</header>

						<div id="tabla"></div>
						
						<ul class="pagination pagination-sm pull-right">
							<li><a href="#">«</a></li>
							<li><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">»</a></li>
						</ul>
					</section>

					<div class="modal fade modal-dialog-center " id="myModal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog ">
							<div class="modal-content-wrap">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title">Administrar...</h4>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label>prueba</label>
											<input type="text" class="form-control" id="prueba" name="prueba"/>
										</div>
										<div class="form-group">
											<label>prueba</label>
											<input type="text" class="form-control" id="prueba" name="prueba"/>
										</div>
										<div class="form-group">
											<label>prueba</label>
											<input type="text" class="form-control" id="prueba" name="prueba"/>
										</div>
										<div class="form-group">
											<label>prueba</label>
											<input type="text" class="form-control" id="prueba" name="prueba"/>
										</div>
									</div>
									<div class="modal-footer">
										<button class="btn btn-warning" type="button">Guardar</button>
										<button data-dismiss="modal" class="btn btn-default" type="button">Cerrar</button>
									</div>
								</div>
							</div>
						</div>
					</div>

			</section>
		</section>

		<footer class="site-footer" id="footer">
			<div class="text-center">2015 &copy; Desarrollo.</div>
		</footer>
	</section>
	<script type="text/javascript">
	titulos = new Array();
	titulos["ID"] = "ID";
	titulos["pedido"] = "hola";
	titulos["nombre"] = "hola";
	titulos["cliente"] = "hola";
	
	Tabla({
		url: "tabla.html",
		Id: "#tabla",
		titulos: titulos
	});
	
// 	<tr>
// 	<td><a href="#">103455</a></td>
// 	<td class="hidden-phone">$ 100.000</td>
// 	<td>camilo</td>
// 	<td><span class="label label-success label-mini">Nuevo</span></td>
// 	<td><button class="btn btn-danger btn-xs">
// 			<i class="fa fa-trash-o "></i>
// 		</button></td>
// </tr>
	
	</script>
</body>
</html>