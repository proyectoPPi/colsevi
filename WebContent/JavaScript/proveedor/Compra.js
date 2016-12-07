jQuery(document).ready(function(){
	Tabla();
});

jQuery('#adicion').click(function(){
	jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);
	construirTbl();
});
function inicialTabla(){
	jQuery('#count').val(1);
	construirTbl();
}

function construirTbl(data){
	var medida = data !== undefined && data['medida'] !== undefined ? data['medida'] : ''; 
	var count = jQuery('#count').val();
	var html ='<tr>';
	html += '<td>';
		html += '<input type="text" class="form-control" id="ingredienteText" sec="idIng' + count + '"/>';
		html += '<input type="hidden" name="idIng' + count + '" id="idIng' + count + '" />';
	html += '</td>';
	html += '<td>';
		html += '<input type="text" class="form-control" name="cant' + count + '" />';
		html += '<input type="hidden" name="lote' + count + '" />';
	html += '</td>';
	html += '<td>';
		html += '<select name="tipo' + count + '" class="form-control">' + medida + '</select>';
	html += '</td>';
	html += '<td>';
		html += '<input type="text" name="fecha' + count + '" data-field="date" data-format="yyyy-MM-dd" class="form-control"/>';
	html += '</td>';
	html += '<td>';
		html += '<input type="text" name="vunitario' + count + '" class="form-control"/>';
	html += '</td>';
	html += '<td>';
		html += '<a href="#" onclick="EliminarDet(this);"><i class="fa fa-times-circle fa-2x"></i></a>';
	html += '</td>';
	html += '</tr>';
	
	jQuery('#IngDynamic > section > table > tbody').append(html);
	
	if(data !== undefined){
		data['nombreIng'];
		jQuery( "input[sec*='idIng" + count+ "']" ).val(data['nombreIng']);
		jQuery( "input[name*='idIng" + count+ "']" ).val(data['id_ingrediente']);
		jQuery( "input[name*='cant" + count+ "']" ).val(data['cantidad']);
		jQuery( "input[name*='lote" + count+ "']" ).val(data['lote']);
		jQuery( "select[name*='tipo" + count+ "']" ).val(data['id_tipo_peso']);
		jQuery( "input[name*='fecha" + count+ "']" ).val(data['fecha_vencimiento']);
		jQuery( "input[name*='vunitario" + count+ "']" ).val(data['vunitario']);
	}
	
	HiniciarAutocompletar(contexto + '/Proveedor/Compra/autocompletar.html?', 'ingredienteText');
	
	jQuery( "input[id=ingredienteText]" ).autocomplete({
		  select: function(e, ui) {
		  this.value = ui.item.value;
		  jQuery('#' + this.getAttribute('sec')).val(ui.item.id_ingrediente);
		  var dit = this;
		  
		  jQuery.ajaxQueue({
				url: contexto + "/Proveedor/Compra/MedidaDetalle.html?",
				 data:{medida: ui.item.id_unidad_medida},
			}).done(function(result) {
				var data; 
		 		try{ 
		 			data = jQuery.parseJSON(result); 
		 		} catch(err){ 
		         	return; 
		 		} 
		 		var html = '<option value"0">Seleccione</option>';
		 		for(i in data){
		 			html += '<option value="' + data[i]['id'] + '" >' + data[i]['nombre'] + '</option>';
		 		}
		 		dit.parentNode.parentNode.childNodes[2].querySelector('select').innerHTML = html;
			});
		}
	});
}

function EliminarDet(option){
	option.parentNode.parentNode.remove();
}

function Tabla(pagina){
	
	if(jQuery('#valorMF').is(':checked')){
		jQuery('#valorMF').val('true');
	}else{
		jQuery('#valorMF').val('false');
	}
	if(jQuery('#fechaMF').is(':checked')){
		jQuery('#fechaMF').val('true');
	}else{
		jQuery('#fechaMF').val('false');
	}
	
	HTabla({
		url: contexto + "/Proveedor/Compra/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		boton: boton
	});
	
	jQuery('#com').val('');
}

function Limpiar(){
	jQuery('#IngDynamic > section > table > tbody > tr').remove();
	jQuery('#id_compra').val('');
	jQuery('#count').val('0');
	HLimpiar();
	jQuery('#validarModificacion').show();
}

function CargarFormulario(Id){
	jQuery('#validarModificacion').show();
	jQuery('[href=#producto]').tab('show');
	HCargarFormulario(Id);
	validarModificacion();
	inicialTabla();
	jQuery("#pagado").prop("checked", BuscarRegistro(Id)['pagado'] == "SI" ? true : false);
	jQuery('#count').val('0');
	jQuery('#IngDynamic > section > table > tbody > tr').remove();
	
	cargarDetalle(BuscarRegistro(Id)['detalle']);
}

function cargarDetalle(data){
	for(i in data){
		jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);
		construirTbl(data[i]);
	}
}

function MotivoModal(value){
	jQuery('#id_compraMotiv').val(value);
	jQuery("#ModalMotivo").modal('show');
	jQuery('#motivo').val(BuscarRegistro(value)['motivo']);
}

function validarModificacion(){
	jQuery.ajaxQueue({
		url: contexto + "/Proveedor/Compra/ValidarModificacion.html?",
		 data:{id_compra: jQuery('#id_compra').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error ejecutando validarModificacion" + err); 
 			jQuery('#dynamic').hide();
         	return; 
 		} 
 		if(data['error'] != undefined && data['error'] != ''){
 			jQuery('#validarModificacion').hide();
 		}
	});
}

function preprocesar(){
	HPreprocesar({
		url: contexto + "/Proveedor/Compra/preprocesador.html?",
		formulario: "formulario",
	});
}
