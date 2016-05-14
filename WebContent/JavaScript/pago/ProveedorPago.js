var data;
jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	
	HTabla({
		url: contexto + "/pago/Proveedor/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		accion: accion,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

function CargarFormulario(Id){
	jQuery('#compra').val('');
	jQuery('#compra').html('<option value="0">Seleccione</option>');
	HCargarFormulario(Id);
}

function verCompra(Id){
	window.location.href = contexto + "/Proveedor/Compra.html?Compra=" + BuscarRegistro(Id)['id_compra'];
}

jQuery('#prov').change(function(e) { 
	jQuery.ajaxQueue({
		url: contexto + "/pago/Proveedor/cargarDeuda.html?",
		 data:{prov: jQuery('#prov').val()},
	}).done(function(result) {
 		try{ 
 			data = jQuery.parseJSON(result); 
 			data= data['datos'];
 		} catch(err){ 
         	return; 
 		} 
 		
		for(i in data){
			var html = '';
			html += '<option value="'+data[i]['id']+'">'+data[i]['id']+'</option>';
			jQuery('#compra').append(html);
		}
	});
	
});

jQuery("#compra").change(function(){
	if(jQuery('#compra').val() != "" && jQuery('#compra').val() != "0"){
		for(i in data){
			if(data[i]['id'] == parseInt(jQuery('#compra').val())){
				jQuery('#pendiente').val(data[i]['pendiente']);
				jQuery('#valp').val(data[i]['pendiente']);
			}
		}
	}
});

jQuery("#valorP").keyup(function(){
	if(jQuery('#valorP').val() != "0" && jQuery('#valorP').val() != "" && jQuery('#valorP').val() != " "){
		var pend = parseInt(jQuery('#valp').val());
		var valor = parseInt(jQuery('#valorP').val());
		var result = pend - valor;
		if(result < 0){
			alert("Seleccionar un valor menor o igual a la compra");
		}
		jQuery('#pendiente').val(result);
	}else{
		jQuery('#pendiente').val(jQuery('#valp').val());
	}
});