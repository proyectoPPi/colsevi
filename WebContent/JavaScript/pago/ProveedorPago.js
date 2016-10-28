var data;
jQuery(document).ready(function(){
	HiniciarAutocompletar(contexto + '/pago/Proveedor/autocompletar.html?', 'proveedorText');
	Tabla();
});

jQuery('#proveedorText').autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#prov').val(ui.item.id_proveedor);
	  cargarDeuda(ui.item.id_proveedor);
	}
});

function cargarDeuda(value){
	jQuery.ajaxQueue({
		url: contexto + "/pago/Proveedor/cargarDeuda.html?",
		 data:{prov: value},
	}).done(function(result) {
 		try{ 
 			data = jQuery.parseJSON(result); 
 			data= data['datos'];
 		} catch(err){ 
         	return; 
 		} 
 		Limpiar();
		for(i in data){
			var html = '';
			html += '<option value="'+data[i]['id_compra_proveedor']+'">'+data[i]['id_compra_proveedor']+'</option>';
			jQuery('#compra').append(html);
		}
	});
}

jQuery("#compra").change(function(){
	if(this.value != "" && this.value != "0"){
		for(i in data){
			if(data[i]['id_compra_proveedor'] == parseInt(this.value)){
				jQuery('#pendiente').val(data[i]['pendiente']);
				jQuery('#ValPend').val(data[i]['pendiente']);
				jQuery('#valp').val(data[i]['pendiente']);
			}
		}
	}
});

function Limpiar(){
	HLimpiar();
	jQuery('#compra, #pendiente,#ValPend').val('');
	jQuery('#compra').html('<option value="0">Seleccione</option>');
}

function Tabla(pagina){
	HTabla({
		url: contexto + "/pago/Proveedor/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		boton: boton,
		pagina:pagina
	});
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function verCompra(Id){
	window.location.href = contexto + "/Proveedor/Compra.html?Compra=" + BuscarRegistro(Id)['id_compra'];
}

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