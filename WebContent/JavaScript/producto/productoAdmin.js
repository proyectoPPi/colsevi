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
		pagina:pagina,
		clase: clase
	});
}

function Limpiar(){
	HLimpliar();
	jQuery('#clasificar, #tipoP').val(0);
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Producto/Admin/Eliminar.html?");
}


jQuery( "#clasificarIng" ).change(function() {
	
	jQuery('#Ing > select, #Ing > label').remove();
	if(jQuery('#clasificarIng').val() == ""){
		jQuery('#dynamic').hide();
		return;
	}
	
	jQuery.ajaxQueue({
		url: contexto + "/Producto/Admin/ClasificarIng.html?",
		 data:{clasificar: jQuery('#clasificarIng').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error ejecutando CargarIngredientes" + err); 
         	return; 
         	jQuery('#dynamic').hide();
 		} 
 		
 		var html = '<label>Ingrediente</label>';
 			html += '<select id="IngSelect" name="IngSelect" class="form-control">';
 			html += '<option value="">Seleccione</option>';
		for(i in data){
			html += '<option value="'+data[i]['id']+'">'+data[i]['nombre']+'</option>';
		}
		html += '</select>';
		jQuery('#Ing').html(html);
		jQuery('#dynamic').show();
	});
	
});

jQuery('#adicion').click(function(){
	try{
		if(jQuery('#IngSelect').val() != "" && jQuery('#tipopeso').val() != "" && parseInt(jQuery('#cantidad').val()) > 0){
			if(!buscarIngrediente()){
				jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);		
				var html ='<tr>';
				html += '<td><input type="text" value="'+jQuery('#IngSelect option:selected').text()+'" class="fieldDynamic" readonly /><input type="hidden" name="idIng'+jQuery('#count').val()+'" value="'+jQuery('#IngSelect').val()+'"/></td>';
				html += '<td><input type="text" value="'+jQuery('#cantidad').val()+'" class="fieldDynamic" readonly name="cant'+jQuery('#count').val()+'" /></td>';
				html += '<td class="hidden-xs"><input type="text" value="'+jQuery('#tipopeso option:selected').text()+'" class="fieldDynamic" readonly /><input type="hidden" name="tipo'+jQuery('#count').val()+'" value="'+jQuery('#tipopeso').val()+'"/></td>';
				html += '<td><buttton data-toggle="button" class="btn btn-white" onclick="EliminarDet(this);"><i class="fa fa-remove text-info"></i></button></td>';
				html += '</tr>';
				
			}

			jQuery('#IngDynamic > table > tbody').append(html);
		}
	}catch (e) {
		alert('error');
	}
});

function buscarIngrediente(){
	var count = jQuery('#count').val();
	for(i = 0; i <= count; i++) {
		if(jQuery( "input[name='idIng"+i+"']" ).val() == jQuery('#IngSelect').val()){
			jQuery('input[name="cant'+ i+'"]').val(parseInt(jQuery('input[name="cant'+ i+'"]').val()) + parseInt(jQuery('#cantidad').val()));
			return true;
		}
	}
	return false;
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
	jQuery('#IngDynamic > table > tbody > tr').remove();
	jQuery('#count, #tipoP').val('0');
	cargarIng();
}


function cargarIng(){
	jQuery.ajaxQueue({
		url: contexto + "/Producto/Admin/cargarIng.html?",
		 data:{producto: jQuery('#id_producto').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 			data = data['dato'];
 		} catch(err){ 
 			console.log("Error ejecutando CargarIng" + err); 
         	return; 
         	jQuery('#dynamic').hide();
 		} 
 		
 		var html = '';
		for(i in data){
			jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);		
			
			html += '<tr>';
			html += '<td><input type="text" value="'+data[i]['nombreIng']+'" class="fieldDynamic" readonly /><input type="hidden" name="idIng'+jQuery('#count').val()+'" value="'+data[i]['id_ingrediente']+'"/></td>';
			html += '<td><input type="text" value="'+data[i]['cantidad']+'" class="fieldDynamic" readonly name="cant'+jQuery('#count').val()+'" /><input type="hidden" name="lote'+jQuery('#count').val()+'" value="'+data[i]['lote']+'"/></td>';
			html += '<td class="hidden-xs"><input type="text" value="'+data[i]['nombreTp']+'" class="fieldDynamic" readonly /><input type="hidden" name="tipo'+jQuery('#count').val()+'" value="'+data[i]['id_tipo_peso']+'"/></td>';
			html += '<td><buttton data-toggle="button" class="btn btn-white" onclick="EliminarDet(this);"><i class="fa fa-remove text-info"></i></button></td>';
			html += '</tr>';
		}
		jQuery('#IngDynamic > table > tbody').append(html);
	});
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