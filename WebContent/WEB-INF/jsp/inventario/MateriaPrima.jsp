<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>Materia Prima
	<a data-toggle="modal" href="#CrearMateria" onclick="Limpiar();"><i class="fa fa-plus-circle"></i></a>
</h2>
<div class="separator-2"></div>
<section class="panel">
	<div class="panel-body">
		<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
			<label>Lote</label>
			<input type="number" class="form-control" id="loteF" name="filtro"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
			<label>Cantidad</label>
			<input type="text" class="form-control" id="cantidadF" name="filtro"/>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
			<label>Unidad de Medida</label>
			<select class="form-control" id="unidadMF" name="filtro">
				<option value="0">Seleccione</option>
				<c:forEach items="${ListaUM}" var="um">
					<option value="${um.id_unidad_peso}">${um.nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
			<label>Establecimiento</label>
			<select class="form-control" id="establecimientoF" name="filtro">
				<option value="0">Seleccione</option>
				<c:forEach items="${ListaE}" var="esta">
					<option value="${esta.id_establecimiento}">${esta.nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
			<label>Fecha de vencimiento</label>
			<input type="text" id="vencimiento" name="filtro" class="form-control" />
		</div>
		<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
			<label>Compra</label>
			<input type="text" id="compra" name="filtro" class="form-control" />
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
<div class="modal fade modal-dialog-center" id="ModalFormulario" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Inventario/MateriaPrima/GuardarMovimiento.html?" id="Formulario" data-modal="ModalFormulario" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">Materia Prima...</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<input type="hidden" id="lote" name="lote"/>
							
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Ingrediente</label>
								<input type="text" class="form-control" id="nombreIng" name="nombreIng" disabled/>
							</div>
							
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Cantidad</label>
								<input type="number" class="form-control" id="cantidad" name="cantidad" disabled/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Unidad medida</label>
								<select class="form-control" id="id_unidad_peso" name="id_unidad_peso" disabled>
									<option value="0">Seleccione</option>
									<c:forEach items="${ListaUM}" var="um">
										<option value="${um.id_unidad_peso}">${um.nombre}</option>
									</c:forEach>
								</select>
								<br/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>Establecimiento</label>
								<select class="form-control" id="id_establecimiento" name="id_establecimiento" disabled>
									<option value="0">Seleccione</option>
									<c:forEach items="${ListaE}" var="esta">
										<option value="${esta.id_establecimiento}">${esta.nombre}</option>
									</c:forEach>
								</select>
							</div>
							
							<h4 class="col-lg-12"><strong>Realizar Movimiento</strong></h4>
								<hr/>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>* Cantidad</label>
								<input type="number" class="form-control" id="cantMov" name="cantMov"/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>* Unidad medida</label>
								<select class="form-control" id="unidadMov" name="unidadMov">
									<option value="">Seleccione</option>
									<c:forEach items="${ListaUM}" var="um">
										<option value="${um.id_unidad_peso}">${um.nombre}</option>
									</c:forEach>
								</select>
								<br/>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>* Establecimiento</label>
								<select class="form-control" id="estaMov" name="estaMov">
									<option value="">Seleccione</option>
									<c:forEach items="${ListaE}" var="esta">
										<option value="${esta.id_establecimiento}">${esta.nombre}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<label>* Motivo</label>
								<select class="form-control" id="motivoMov" name="motivoMov">
									<option value="3">Prestamo</option>
									<option value="4">Pago</option>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="button" onclick="validarFormulario();">Guardar</button>
						<button data-dismiss="modal" class="btn btn-default" type="button" onclick="Limpliar();">Cerrar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>


<div class="modal fade modal-dialog-center " id="CrearMateria" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content-wrap">
			<form method="post" action="${pageContext.request.contextPath}/Ingrediente/Clasificar/Guardar.html?" id="Formulario" data-modal="CrearMateria" data-tabla="Tabla">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i class="fa fa-times-circle"></i></button>
						<h4 class="modal-title">Clasificar...</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<input type="hidden" id="id_clasificar_ingrediente" name="id_clasificar_ingrediente"/>
							<input type="hidden" value="0" id="count" name="count"/>
							<div class="col-lg-12" style="text-align: right;">
								<button type="button" class="btn btn-primary" id="adicion">Adicionar</button>
							</div>
							
							<div id="IngDynamic" class="col-xs-12 col-sm-12 col-md-12">
								<section id="flip-scroll">
									<table class="table table-bordered table-striped table-condensed cf">
										<thead class="cf">
											<tr>
												<th>Ingrediente</th>
												<th>Cantidad</th>
												<th>Tipo de Peso</th>
												<th>Vence</th>
												<th></th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</section>						
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn btn-warning" type="submit">Guardar</button>
						<button data-dismiss="modal" class="btn btn-gray-transparent" type="button" onclick="Limpiar();">Cerrar</button>
						<button data-dismiss="modal" class="btn btn-danger" type="button" onclick="Eliminar();">Eliminar</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>



<script type="text/javascript">
	titulos = new Array();
	titulos["lote"] = "ID";
	titulos["loteid"] = "Lote";
	titulos["id_compra"] = "Compra";
	titulos["nombreEsta"] = "Establecimiento";
	titulos["nombreIng"] = "Ingrediente";
	titulos["cantidad"] = "Cantidad";
	titulos["nombreUp"] = "Unidad de Medida";
	titulos["fecha_vencimiento"] = "Fecha Vencimiento";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/inventario/MateriaPrima.js"></script>
