jQuery(document).ready(function(){
	Tabla();
	HDatetimePicker('dtBox',false);
	jQuery('#dynamic').hide();
});

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
		clase: clase,
		boton: boton
	});
	jQuery('#com').val('');
}

jQuery('#adicion').click(function(){
	try{
		if(jQuery('#IngSelect').val() != "" && jQuery('#tipopeso').val() != "" && parseInt(jQuery('#cantidad').val()) > 0){
			if(!buscarIngrediente()){
				jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);		
				var html ='<tr>';
				html += '<td><input type="text" value="'+jQuery('#IngSelect option:selected').text()+'" class="fieldDynamic" readonly /><input type="hidden" name="idIng'+jQuery('#count').val()+'" value="'+jQuery('#IngSelect').val()+'"/></td>';
				html += '<td><input type="text" value="'+jQuery('#cantidad').val()+'" class="fieldDynamic" readonly name="cant'+jQuery('#count').val()+'" /></td>';
				html += '<td class="hidden-xs"><input type="text" value="'+jQuery('#tipopeso option:selected').text()+'" class="fieldDynamic" readonly /><input type="hidden" name="tipo'+jQuery('#count').val()+'" value="'+jQuery('#tipopeso').val()+'"/></td>';
				html += '<td><input type="text" name="fecha'+jQuery('#count').val()+'" data-field="date" data-format="yyyy-MM-dd" class="form-control"/></td>';		
				html += '<td><buttton data-toggle="button" class="btn btn-white" onclick="EliminarDet(this);"><i class="fa fa-remove text-info"></i></button></td>';
				html += '</tr>';
				
			}

			jQuery('#IngDynamic > table > tbody').append(html);
		}
	}catch (e) {
		alert('error');
	}
});

jQuery( "#clasificarIng" ).change(function() {
	
	jQuery('#Ing > select, #Ing > label').remove();
	if(jQuery('#clasificarIng').val() == ""){
		jQuery('#dynamic').hide();
		return;
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
 			jQuery('#dynamic').hide();
         	return; 
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

function Limpiar(){
	jQuery('#IngDynamic > table > tbody > tr').remove();
	jQuery('#id_compra').val('');
	jQuery('#count').val('0');
}

function EliminarDet(option){
	option.parentNode.parentNode.remove();
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
	jQuery("#pagado").prop("checked", false);
	jQuery('#IngDynamic > table > tbody > tr').remove();
	jQuery('#count').val('0');
	cargarIng();
	jQuery('#valorsin').val(BuscarRegistro(Id)['valorsin']);
	if(BuscarRegistro(Id)['pagado'] == "SI"){
		jQuery("#pagado").prop("checked", true);
	}
	jQuery('#motivo').val(BuscarRegistro(Id)['motivo']);
//	validarModificacion();
}

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
			
			html += '<tr>';
			html += '<td><input type="text" value="'+data[i]['nombreIng']+'" class="fieldDynamic" readonly /><input type="hidden" name="idIng'+jQuery('#count').val()+'" value="'+data[i]['id_ingrediente']+'"/></td>';
			html += '<td><input type="text" value="'+data[i]['cantidad']+'" class="fieldDynamic" readonly name="cant'+jQuery('#count').val()+'" /><input type="hidden" name="lote'+jQuery('#count').val()+'" value="'+data[i]['lote']+'"/></td>';
			html += '<td class="hidden-xs"><input type="text" value="'+data[i]['nombreTp']+'" class="fieldDynamic" readonly /><input type="hidden" name="tipo'+jQuery('#count').val()+'" value="'+data[i]['id_tipo_peso']+'"/></td>';
			html += '<td><input type="text" name="fecha'+jQuery('#count').val()+'" value="'+data[i]['fecha_vencimiento']+'" data-field="date" data-format="yyyy-MM-dd" class="form-control"/></td>';		
			html += '<td><buttton data-toggle="button" class="btn btn-white" onclick="EliminarDet(this);"><i class="fa fa-remove text-info"></i></button></td>';
			html += '</tr>';
		}
		jQuery('#IngDynamic > table > tbody').append(html);
	});
}

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

function MotivoModal(value){
	jQuery('#id_compraMotiv').val(value);
	jQuery("#ModalMotivo").modal('show');
	jQuert('#motivo').val(BuscarRegistro(Id)['motivo']);
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
 			console.log("Error ejecutando CargarIng" + err); 
 			jQuery('#dynamic').hide();
         	return; 
 		} 
 		if(data['error'] != ''){
 			jQuery('#validarModificacion').hide();
 		}
	});
}