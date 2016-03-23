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
 		
 		var html = '<label>Ingrediente</label><select id="IngSelect" name="IngSelect" class="form-control">';
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
		
		var html ='<tr><td>'+jQuery('#IngSelect option:selected').text()+jQuery('#IngSelect').val()+'</td><td>'+jQuery('#cantidad').val()+'</td>';
		html += '<td><buttton data-toggle="button" class="btn btn-white" onclick="Adicionar('+1+');"><i class="fa fa-remove text-info"></i></button></td></tr>';
		jQuery('#IngDynamic > table > tbody').append(html);
	}
}