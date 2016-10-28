jQuery(document).ready(function(){
	Tabla();
});

function construirSelectTipoPeso(){
	var html = '';
	for(i in LPeso){
		html += '<option value='+LPeso[i]['id']+'>'+LPeso[i]['nombre']+'</option>';
	}
	return html;
}

jQuery('#adicion').click(function(){
	jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);
	construirTbl();
});
function inicialTabla(){
	jQuery('#count').val(1);
	construirTbl();
}

function construirTbl(data){
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
		html += '<select name="tipo' + count + '" class="form-control">' + construirSelectTipoPeso() + '</select>';
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
	jQuery('#validarModificacion').show();
}

function CargarFormulario(Id){
	jQuery('#validarModificacion').show();
	jQuery('[href=#producto]').tab('show');
	jQuery("#pagado").prop("checked", BuscarRegistro(Id)['pagado'] == "SI" ? true : false);
	jQuery('#motivo').val(BuscarRegistro(Id)['motivo']);	
	jQuery('#count').val('0');
	jQuery('#IngDynamic > section > table > tbody > tr').remove();
	validarModificacion();
	HCargarFormulario(Id);
//	jQuery('#valorsin').val(BuscarRegistro(Id)['valorsin']);
}

jQuery('#carga').click(function(){
	jQuery('#IngDynamic > section > table > tbody > tr').remove();
	jQuery('#count').val('0');
	cargarIng();
});

function cargarIng(){
	jQuery.ajaxQueue({
		url: contexto + "/Proveedor/Compra/cargarIng.html?",
		 data:{compra: jQuery('#id_compra').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 			data = data['dato'];
 		} catch(err){ 
 			console.log("Error ejecutando CargarIng" + err); 
 			jQuery('#dynamic').hide();
         	return; 
 		} 
 		
 		var html = '';
		for(i in data){
			jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);		
			construirTbl(data[i]);
		}
	});
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
