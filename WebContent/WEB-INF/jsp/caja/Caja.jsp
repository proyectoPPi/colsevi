<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Caja
	<a data-toggle="modal" href="#ModalFormulario" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
</h2>
<div class="separator-2"></div>
<section class="panel">
	<div class="panel-body">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<label>Nombre</label>
			<input type="text" class="form-control" id="nombreF" name="filtro"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<label>Descripción</label>
			<input type="text" class="form-control" id="descripcionF" name="filtro"/>
		</div>
		<div class="col-xs-12 col-md-12 col-lg-12"><br/>
			<button class="btn btn-button" onclick="Tabla(1);">Filtrar</button>
		</div>	
	</div>	
</section>

<div class=" col-xs-12 col-md-12 col-lg-12">
	<div id="tabla"></div>
	<div id="paginacion" class="col-md-12"></div>
</div>

<div class="modal fade modal-dialog-center " id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-jg">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Caja/Guardar.html?" 
				id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Flujo de Caja...</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-xs-12">
								<label>Establecimiento</label>
								<select class="form-control" id="establecimiento" name="establecimiento">
									<option value="">Seleccione</option>
									<c:forEach items="${listaEstablecimiento}" var="esta">
										<option value="${esta.id_establecimiento}">${esta.nombre}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-12">
								<label>*valor</label>
								<input type="number" class="form-control" id="valor_inicial" name="valor_inicial" />
							</div>
							<div class="col-xs-12">
								<label>*Fecha</label>
								<input type="text" class="form-control" id="fecha" name="fecha" />
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="button" onclick="validarFormulario();">Guardar</button>
						<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Limpiar();">Cerrar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="modal fade modal-dialog-center " id="FlujoCaja" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">Flujo de Caja...</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="id_caja" name="id_caja"/>
					<div class="row">
						<div class="col-xs-12" id="visorFLujo">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-warning" type="button" onclick="ComenzarEjecucion();">Ejecutar</button>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	
	titulos = new Array();
	titulos["id_caja"] = "ID";
	titulos["fecha_ejecucion"] = "Fecha de ejecucion";
	titulos["establecimiento"] = "Establecimiento";
	titulos["persona"] = "Responsable";
	titulos["valor_inicial"] = "valor Inicial";
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/caja/Caja.js"></script>
