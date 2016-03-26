jQuery(document).ready(function(){
	Tabla();
	HDatetimePicker('dtBox',false);
	jQuery('#dynamic').hide();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Proveedor/Compra/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

function CargarIngredientes(){
	jQuery('#Ing > select, #Ing > label').remove();
	if(jQuery('#clasificarIng').val() == ""){
		return;
		jQuery('#dynamic').hide();
	}
	
	jQuery.ajaxQueue({
		url: contexto + "/Proveedor/Compra/ClasificarIng.html?",
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
}

function Adicionar(){
	if(jQuery('#IngSelect').val() != "0"){
		jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);		
		var html ='<tr>';
		html += '<td><input type="text" value="'+jQuery('#IngSelect option:selected').text()+'" class="fieldDynamic" readonly /><input type="hidden" name="idIng'+jQuery('#count').val()+'" value="'+jQuery('#IngSelect').val()+'"/></td>';
		html += '<td><input type="text" value="'+jQuery('#cantidad').val()+'" class="fieldDynamic" readonly name="cant'+jQuery('#count').val()+'" /></td>';
		html += '<td><input type="text" value="'+jQuery('#tipopeso option:selected').text()+'" class="fieldDynamic" readonly /><input type="hidden" name="tipo'+jQuery('#count').val()+'" value="'+jQuery('#tipopeso').val()+'"/></td>';
		html += '<td><input type="text" name="fecha'+jQuery('#count').val()+'" data-field="date" data-format="yyyy-MM-dd" class="form-control"/></td>';		
		html += '<td><buttton data-toggle="button" class="btn btn-white" onclick="EliminarDet(this);"><i class="fa fa-remove text-info"></i></button></td>';
		html += '</tr>';
		jQuery('#IngDynamic > table > tbody').append(html);

	}
}

function Limpiar(){
	jQuery('#IngDynamic > table > tbody > tr').remove();
	jQuery('#id_compra').val('');
}

function EliminarDet(option){
	option.parentNode.parentNode.remove();
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
	cargarIng();
}

function cargarIng(){
	jQuery.ajaxQueue({
		url: contexto + "/Proveedor/Compra/cargarIng.html?",
		 data:{compra: jQuery('#id_compra').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error ejecutando CargarIng" + err); 
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
}