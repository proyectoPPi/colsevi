<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Personas
	<a onclick="CrearPersona();"><i class="fa fa-plus-circle"></i></a>
</h2>
<div class="separator-2"></div>
<section class="panel">
	<div class="panel-body">
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			<label>*Nombre</label>
			<input type="text" class="form-control" id="nombreF" name="filtro" />
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
	<div class="modal-dialog">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Producto/Tipo/Guardar.html?" id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
						<h4 class="modal-title">Detalle Persona...</h4>
					</div>
					<div class="modal-body">
						<input type="hidden" id="persona" name="persona"/>
						<div class="row">
							<div class="col-xs-12 col-md-6">
								<label>*Nombre</label>
								<label id="nombre" id="nombre"></label>
							</div>
							<div class="col-xs-12 col-md-6">
								<label>Apellido</label>
								<input type="text" class="form-control" id="descripcion" name="descripcion"/>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="button" id="guardar" onclick="EditarPersona();">Editar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>


<script type="text/javascript">
	titulos = new Array();
	titulos["persona"] = "ID";
	titulos["nombre"] = "Nombre";
	titulos["descripcion"] = "Descripci&#243;n";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/usuario/Persona.js"></script>
