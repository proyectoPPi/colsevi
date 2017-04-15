jQuery(document).ready(function(){
	Tabla();
	Hformulario();
	HiniciarAutocompletar(contexto + '/Producto/Admin/autocompletar.html?', 'prodF');
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
	HLimpiar();
	jQuery('#clasificar, #tipoP, #count').val(0);
	jQuery('#IngDynamic > section > table > tbody > tr').remove();
	jQuery('#count').val(1);
	construirTabla();

	HAjax({
		url: contexto + "/Producto/Admin/ListaCatalogoPosibleProducto.html?",
		async: false,
		method: 'productInCatalog'
	});
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Producto/Admin/Eliminar.html?");
}

jQuery('#adicion').click(function(){
	jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);
	construirTabla();
});

function CargarFormulario(Id){
	HCargarFormulario(Id);
	jQuery('#IngDynamic > table > tbody > tr').remove();
	jQuery('#count').val('0');
	jQuery('#catalogActive, #catalogNoActive').val('');
	HAjax({
		url: contexto + "/Producto/Admin/cargarIng.html?",
		data: {producto: jQuery('#id_producto').val()},
		async: false,
		method: 'cargarIng'
	});
	
	HAjax({
		url: contexto + "/Producto/Admin/ListaCatalogoPosibleProducto.html?",
		data: {producto: Id},
		async: false,
		method: 'productInCatalog'
	});
}

function construirTabla(data){
	var html = '';
	var count = jQuery('#count').val();
	var campoIng = data !== undefined && data['id_ingrediente'] !== undefined ? data['id_ingrediente'] : '';
	var campoCant = data !== undefined && data['cantidad'] !== undefined ? data['cantidad'] : '';
	var nombreIng = data !== undefined && data['nombreIng'] !== undefined ? data['nombreIng'] : '';
	var medida = data !== undefined && data['medida'] !== undefined ? data['medida'] : ''; 

	html += '<tr>';
	html += '<td>';
		html += '<input type="text" class="form-control" id="ingredienteText" sec="idIng' + count + '" value="' + nombreIng + '"/>';
		html += '<input type="hidden" value="' + campoIng + '" name="idIng" id="idIng' + count + '"/>';
	html += '</td>';
	html += '<td>';
		html += '<input type="text" value="' + campoCant + '" class="form-control" name="cant"/>';
	html += '</td>';
	html += '<td>';
		html += '<select name="tipo" class="form-control">' + medida + '</select>';
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
		  var dit = this;
		  
		  jQuery.ajaxQueue({
				url: contexto + "/Producto/Admin/MedidaDetalle.html?",
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

function productInCatalog(data){
	try{ 
		var html = '';
		for(i in data){
				html += '<div class="col-xs-6 col-lg-2"><label>';
				html += '<input type="checkbox" value="'+data[i]['id']+'" name="catalogo"';
				data[i]['select'] !== undefined ? html += ' checked' : '';
				html += '>' + data[i]['nombreC'] + '</label></div>';
		}
		jQuery('#detalleCatalog').html(html);
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

function preprocesar(){
	$("input[name=catalogo]").each(function(){
	    if(this.checked){
	    	jQuery('#catalogActive').val(jQuery('#catalogActive').val() + this.value + ',');
	    }else{
	    	jQuery('#catalogNoActive').val(jQuery('#catalogNoActive').val() + this.value + ',');
	    }
	});
	HPreprocesar({
		url: contexto + "/Producto/Admin/preprocesador.html?",
		formulario: "formulario",
	});
}