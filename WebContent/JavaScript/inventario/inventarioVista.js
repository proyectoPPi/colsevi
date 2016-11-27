jQuery(document).ready(function(){
	Tabla();
	HiniciarAutocompletar(contexto + '/Inventario/Inv/buscarProd.html?', 'prodF');
	HiniciarAutocompletar(contexto + '/Inventario/Inv/buscarProd.html?', 'nombreProd');
});

function Tabla(pagina){
	
	if(jQuery('#prodF').val() == ""){
		jQuery('#prodV').val('');
	}
	if(jQuery('#mayorF').is(':checked')){
		jQuery('#mayorF').val('true');
	}else{
		jQuery('#mayorF').val('false');
	}
	
	HTabla({
		url: contexto + "/Inventario/Inv/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Inventario/inv/Eliminar.html?");
}

function CargarFormulario(Id){
	jQuery('[href=#producto]').tab('show');
	HCargarFormulario(Id);
	jQuery('#cantSolicitada').val(BuscarRegistro(Id)['disponible']);
	jQuery('#formGuardar').show();
}

jQuery('#dataTables').click(function(e) { 
	jQuery('#detalle').val("1");
});

jQuery('#carga').click(function(){
	jQuery('#dataTables, #secuencia').html('');
	HAjax({
		url: contexto + "/Inventario/Inv/cargarInv.html?",
		data: {prod: jQuery('#id_producto').val(), cantidad: jQuery('#cantSolicitada').val(), establecimiento: jQuery('#establecimiento').val()},
		async: false,
		posicion: 'datos',
		method: 'ingredienteRequerido'
	});
});

function ingredienteRequerido(data){
	data = data['datos'];
		var html = '';
		for(i in data){
			html = '<h3><strong>' + data[i]['nombreIng'] + '</strong>: ' + data[i]['cantidadProd'] + ' ' + data[i]['codUM'] + '</h3><hr/>';
			html += iniciarTabla();
			if(data[i]['detalle'] !== undefined){
				html += cargarInv(data[i]['detalle']);	
			}else{
				html += TablaVacia();
			}
			
			jQuery('#dataTables').append(html);
		}
}

function iniciarTabla(){
	var html = '<table class="table table-bordered table-striped"><thead><tr>';
	html += '<th>Cantidad</th><th>Medida</th><th>F. Vencimiento</th><th>Lote</th>';
	html += '</tr></thead><tbody>';
	return html;
}

function TablaVacia(){
	var html = '';
	html += '<tr>';
	html += '<td colspan="4" style="text-align:center;font-weight: bold;">NO HAY DATOS</td>';
	html += '</tr>';
	html += '</tbody></table>';
	
	return html;
}

function cargarInv(detalle){
	var html = '';
		for(i in detalle){
			html += '<tr>';
			html += '<td>';
			html += '<input name="cant" type="text" class="form-control" value="'+detalle[i]['cantAsig']+'"/>';
			html += '<input type="hidden" value="'+detalle[i]['id_ingrediente']+'" name="ing"/> ';
			html += '</td>';
			html += '<td>';
			html += '<select class="form-control" name="um" value="' + detalle[i]['umAsig'] + '">' + construirSelectTipoPeso() + '</select>';
			html += '</td>';
			html += '<td>';
			html += '<label class="form-control" value="'+ detalle[i]['fecha_vencimiento'] + '"></label>';
			html += '</td>';
			html += '<td>';
			html += '<input type="text" class="form-control" value="'+ detalle[i]['lote'] + '" name="lote"/>';
			html += '</td>';
			jQuery('#secuencia').val(jQuery('#secuencia').val() + detalle[i]['lote'] + ',');
			
			html += '</tr>';
		}
		html += '</tbody></table>';
		return html;
}

function construirSelectTipoPeso(){
	var html = '';
	for(j in LPeso){
		html += '<option value='+LPeso[j]['id']+'>'+LPeso[j]['nombre']+'</option>';
	}
	return html;
}

jQuery("#prodF").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#prodV').val(ui.item.id_producto);
	}
});

function preprocesar(){
	HPreprocesar({
		url: contexto + "/Inventario/Inv/preprocesador.html?",
		formulario: "formulario",
	});
}