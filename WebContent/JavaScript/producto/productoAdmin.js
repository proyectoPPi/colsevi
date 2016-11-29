jQuery(document).ready(function(){
	Tabla();
	HiniciarAutocompletar(contexto + '/Producto/Admin/buscarProd.html?', 'prodF');
	jQuery('#dynamic').hide();
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
		url: contexto + "/Producto/Admin/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
	jQuery('#clasificar, #tipoP, #count').val(0);
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Producto/Admin/Eliminar.html?");
}

jQuery('#adicion').click(function(){
	jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);
	construirTabla();
});

function CargarFormulario(Id){
	HCargarFormulario(Id);
	jQuery('#IngDynamic > table > tbody > tr').remove();
	jQuery('#count').val('0');
	HAjax({
		url: contexto + "/Producto/Admin/cargarIng.html?",
		data: {producto: jQuery('#id_producto').val()},
		async: false,
		method: 'cargarIng'
	});
	
//	HAjax({
//		url: contexto + "/Producto/Admin/ProdInCatalog.html?",
//		data: {producto: Id},
//		async: false,
//		method: 'productInCatalog'
//	});
}

function construirTabla(data){
	var html = '';
	var count = jQuery('#count').val();
	var campoIng = data !== undefined && data['id_ingrediente'] !== undefined ? data['id_ingrediente'] : '';
	var campoCant = data !== undefined && data['cantidad'] !== undefined ? data['cantidad'] : '';
	var campoTipo = data !== undefined && data['id_tipo_peso'] !== undefined ? data['id_tipo_peso'] : '';
	var nombreIng = data !== undefined && data['nombreIng'] !== undefined ? data['nombreIng'] : '';
	
	html += '<tr>';
	html += '<td>';
		html += '<input type="text" class="form-control" id="ingredienteText" sec="idIng' + count + '" value="' + nombreIng + '"/>';
		html += '<input type="hidden" value="' + campoIng + '" name="idIng" id="idIng' + count + '"/>';
	html += '</td>';
	html += '<td>';
		html += '<input type="text" value="' + campoCant + '" class="form-control" name="cant"/>';
	html += '</td>';
	html += '<td>';
		html += '<select name="tipo" class="form-control">' + VMed(campoTipo) + '</select>';
	html += '</td>';
	html += '<td>';
		html += '<a href="#" onclick="EliminarDet(this);"><i class="fa fa-times-circle fa-2x"></i></a>';
	html += '</td>';
	html += '</tr>';
	
	jQuery('#IngDynamic > table > tbody').append(html);
	HiniciarAutocompletar(contexto + '/Producto/Admin/autocompletar.html?', 'ingredienteText');
	jQuery( "input[id=ingredienteText]" ).autocomplete({
		  select: function(e, ui) {
		  this.value = ui.item.value;
		  jQuery('#' + this.getAttribute('sec')).val(ui.item.id_ingrediente);
		}
	});
}

function cargarIng(data){
	try{ 
		data = data['dato'];
		for(i in data){
			jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);		
			construirTabla(data[i]);
		}
	} catch(err){ 
     	return; 
	} 
}

function EliminarDet(option){
	option.parentNode.parentNode.remove();
}

jQuery("#prodF").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#prodV').val(ui.item.id_producto);
	}
});

jQuery("#localImage").click(function() {
	jQuery('#fileview').click();

});

function VMed(value){
	var html = '<option value"0">Seleccione</option>';
	for(i in LMedida){
		if(value == LMedida[i]['id'])
			html += '<option value='+LMedida[i]['id']+' selected>'+LMedida[i]['nombre']+'</option>';
		else
			html += '<option value='+LMedida[i]['id']+'>'+LMedida[i]['nombre']+'</option>';
	}
	return html;
}
