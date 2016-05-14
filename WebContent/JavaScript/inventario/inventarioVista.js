jQuery(document).ready(function(){
	Tabla();
	HiniciarAutocompletar(contexto + '/Inventario/Inv/buscarProd.html?', 'prodF');
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
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Inventario/inv/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
	jQuery('#cantSolicitada').val(BuscarRegistro(Id)['disponible']);
}

jQuery('#viewData').click(function(e) { 
	jQuery('#detalle').val("1");
});

jQuery('#carga').click(function(){
	
	jQuery('#viewData').html('');
	
	jQuery.ajaxQueue({
		url: contexto + "/Inventario/Inv/cargarInv.html?",
		 data:{prod: jQuery('#id_producto').val(), cantidad: jQuery('#cantSolicitada').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error" + err); 
         	return; 
         	jQuery('#dynamic').hide();
 		} 
 		data = data['datos'];
 		for(i in data){
 			var html = '';
 			html += '<h3><strong>' + data[i]['nombreIng'] + '</strong>: ' + data[i]['cantidadProd'] + ' ' + data[i]['codUM'] + '</h3><hr/>';
 			jQuery(html).appendTo('#viewData');
 			cargarInv(data[i]['id_ingrediente'],data[i]['id_unidad_peso'], data[i]['cantidadProd']);
 		}
	});
});

function cargarInv(ingrediente,id_unidad_peso, cant){

	jQuery.ajaxQueue({
		url: contexto + "/Inventario/Inv/cargarIng.html?",
		 data:{ing: ingrediente, um: id_unidad_peso, cantidad: cant, establecimiento: jQuery('#establecimiento').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error" + err); 
         	return; 
         	jQuery('#dynamic').hide();
 		} 
 		data = data['datos'];
 		if(data.length == 0){
 			jQuery('<p>No hay materia prima disponible</p>').appendTo('#viewData');
 		}
 		for(i in data){
 			var html = '';
			html += '<div class="col-xs-12 col-md-3">';
			html += '<section class="panel">';
			html += '<input type="hidden" value="' + data[i]['id_ingrediente'] + '"  id="ing'+data[i]['lote']+'" name="ing'+data[i]['lote']+'"/> ';
			
			if(data[i]['color'] == true ){
				html += '<div class="weather-bg success">';
			}else{
				html += '<div class="weather-bg error">';
			}
			html += '<div class="panel-body">';
			html += '<div class="row">';
			html += '<div class="col-xs-12">';
			html += data[i]['cantidad'] + ' ' + data[i]['codUM'];
			html += '<input type="text" class="form-control" id="cant'+data[i]['lote']+'" id="cant'+data[i]['lote']+'" name="cant'+data[i]['lote']+'" value="'+data[i]['cantAsig']+'"/>';
			html += '</div>';
			html += '<div class="col-xs-12"><br/>';
			html += '<select class="form-control" id="um'+data[i]['lote']+'" name="um'+data[i]['lote']+'" value="'+data[i]['umAsig']+'">';
			
			jQuery('#listaUnidad > option').each(function(){
				html += '<option';
				if($(this).val() == data[i]['umAsig']){
					html += ' selected="selected"';
				}
				html += ' value='+$(this).val()+'>'+$(this).text() + '</option>';
	        });
			
			html += jQuery('#listaUnidad > option') + '</select>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			html += '</div>';
			html += '<footer class="weather-category">';
			html += '<ul>';
			html += '<li class="active">';
			html += ' <h5>Lote</h5>';
			html += data[i]['lote'];
			html += '</li>';
			html += '<li>';
			html += '<h5>vence</h5>';
			html += data[i]['fecha_vencimiento'];
			html += '</li>';
			html += '</ul>';
			html += '</footer>';
			html += '</section>';
			html += '</div>';
			jQuery(html).appendTo('#viewData');
			jQuery('#secuencia').val(jQuery('#secuencia').val() + data[i]['lote'] + ',');
 		}
	});
}

jQuery("#prodF").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#prodV').val(ui.item.id_producto);
	}
});