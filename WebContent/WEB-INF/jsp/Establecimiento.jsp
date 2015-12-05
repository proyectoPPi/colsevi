<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8" />
	<title>Administrar Locales</title>
	<c:import url="estilos_genericos.jsp"></c:import>

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
				<c:import url="alertas.jsp"></c:import>
					<section class="panel">
						<header class="panel-heading">Locales 
							<span class="tools pull-right">
								<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle fa-8"></i></a>
                            </span>
						</header>
						

						<div id="tabla"></div>
						<div id="paginacion"></div>
						
						<button class="btn btn btn-default" type="submit" onclick="printDiv('tabla')" >Imprimir</button>
						
					</section>

					<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-sm">
							<div class="modal-content-wrap">
								<form method="post" action="${pageContext.request.contextPath}/General/Establecimiento/GuardarLocal.html?" id="formulario">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
											<h4 class="modal-title">Administrar...</h4>
										</div>
										<div class="modal-body">
											<input type="hidden" id="id_establecimiento" name="id_establecimiento"/>
											<div class="form-group">
												<label>*Nombre</label>
												<input type="text" class="form-control" id="nombre" name="nombre" required  maxlength="15"/>
											</div>
											<div class="form-group">
												<label>*Descripci&#243;n</label>
												<input type="text" class="form-control" id="descripcion" name="descripcion" required maxlength="30"/>
											</div>
										</div>
										<div class="modal-footer">
											<button class="btn btn-warning" type="submit">Guardar</button>
											<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpliar();">Cerrar</button>
											<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Eliminar();">Eliminar</button>
										</div>
									</div>
								</form>
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
	<c:import url="javascript_genericos.jsp"></c:import>
	<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/Local.js"></script>
	<script type="text/javascript">
	titulos = new Array();
	titulos["id_establecimiento"] = "ID";
	titulos["nombre"] = "Nombre";
	titulos["descripcion"] = "Descripci&#243;n";
	
	var contextPath = "${pageContext.request.contextPath}";
	

	
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