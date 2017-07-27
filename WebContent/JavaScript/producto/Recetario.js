jQuery(document).ready(function(){
	Tabla();
	HiniciarAutocompletar(contexto + '/Recetario/buscarProd.html?', 'prod');
	HiniciarAutocompletar(contexto + '/Recetario/buscarProd.html?', 'prodF');
});

function Filtro(){
	var filtro = "&";
	jQuery("[name*='filtro']").each(function() {
		filtro += $(this).attr("id") +"="+ $(this).val() + "&";
	});
	return filtro;
}

function Limite(pagina){
	if(pagina==undefined) pagina = 1;
	return "&Inicio=" + (pagina-1)*cantPagina + "&Final=" + cantPagina;
}

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
		url: contexto + "/Recetario/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Paginacion(pagina, total){
	jQuery("#paginacion").html('');
	var n = total;
	var NPaginas = Math.ceil(n/cantPagina);
	if(NPaginas == 0){
		NPaginas = 1;
	}
	
	var Inicio = (pagina-4) <= 0 ? 1 : pagina - 4;
	var Final = (pagina+4) >= NPaginas ? NPaginas : pagina + 4;
	
	var html = '';
	if(pagina>1){
		html += '<li><a href="javascript:void(Tabla(1));">&laquo;</a></li>';
		html += '<li><a href="javascript:void(Tabla('+parseInt(pagina-1)+'));">&lsaquo;</a></li>';
	}
	for(var i=Inicio; i<=Final; i++){
		html += i==pagina ? '<li class="active"><a href="javascript:void(Tabla('+i+'));">'+i+'</a></li>':'<li class="hidden-xs"><a href="javascript:void(Tabla('+i+'));">'+i+'</a></li>';
	}
	if(pagina<NPaginas){
		html += '<li><a href="javascript:void(Tabla('+parseInt(pagina+1)+'));">&rsaquo;</a></li>';
		html += '<li><a href="javascript:void(Tabla('+NPaginas+'));">&raquo;</a></li>';
	}
	jQuery("#paginacion").append('<ul class="dataTables_paginate paging_bootstrap pagination">'+html+'</ul>');
}

function Limpiar(){
	jQuery('#id_producto').val('');
	jQuery('#prod').val('');
	jQuery('#tiempo').val('');
	jQuery('#dificultad').val('0');
	jQuery('#id_receta').val('');
	jQuery('#detalle').html('');
	jQuery('#secuencia').val('0');
	jQuery('#prod').prop('disabled', false);
}

function CargarFormulario(Id){
	
		jQuery('#id_producto').val('');
		jQuery('#prod').val('');
		jQuery('#tiempo').val('');
		jQuery('#dificultad').val('0');
		jQuery('#id_receta').val('0');
		jQuery('#detalle').html('');
		jQuery('#secuencia').val('0');
		jQuery('#prod').prop('disabled', true);
	
	jQuery.ajaxQueue({
		url: contexto + "/Recetario/detalle.html?",
		data:{id:Id}
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error" + err); 
         	return; 
 		} 
 		var detalle = data['detalle'];
 		var producto = data['producto'];
 		var receta = data['receta'];
 		
 		for(i in detalle){
 			jQuery('#secuencia').val(parseInt(jQuery('#secuencia').val()) + 1);
 			var html = '<div class="col-md-12">';
 			html += '<div class="col-xs-10 col-md-11">';
 			html += '<input type="text" class="form-control" value="'+detalle[i]['texto']+'" id="texto'+jQuery('#secuencia').val()+'" name="texto'+jQuery('#secuencia').val()+'" />';
 			html += '</div>';
 			html += '<div class="col-xs-2 col-md-1">';
 			html += '<button class="btn btn-white" onclick="eliminarP(this);"><i class="fa fa-times"></i></button>';
 			html += '</div>';
 			html += '</div>';
 			jQuery('#detalle').append(html);
 		}
 		
 		jQuery('#id_producto').val(producto['id_prod']);
 		jQuery('#prod').val(producto['nombreProd']);
 		jQuery('#tiempo').val(receta['tiempo']);
 		jQuery('#dificultad').val(receta['difi']);
 		jQuery('#id_receta').val(Id);
 		
	});
}

function eliminarP(option){
	option.parentNode.parentNode.remove();
}

jQuery('#adicionarTexto').click(function(){
	try{
		jQuery('#secuencia').val(parseInt(jQuery('#secuencia').val()) + 1);
		var html = '<div class="col-md-12">';
		html += '<div class="col-xs-10 col-md-11">';
		html += '<input type="text" class="form-control" value="" id="texto'+jQuery('#secuencia').val()+'" name="texto'+jQuery('#secuencia').val()+'" />';
		html += '</div>';
		html += '<div class="col-xs-2 col-md-1">';
		html += '<button class="btn btn-white" onclick="eliminarP(this);"><i class="fa fa-times"></i></button>';
		html += '</div>';
		html += '<br/></div>';
		
		jQuery('#detalle').append(html);
	}catch (e) {
		alert('error');
	}
});

jQuery("#prod").autocomplete({
	   select: function(e, ui) {
     this.value = ui.item.value;
     jQuery('#id_producto').val(ui.item.id_producto);
 }
});

jQuery("#prodF").autocomplete({
	   select: function(e, ui) {
     this.value = ui.item.value;
     jQuery('#prodV').val(ui.item.id_producto);
 }
});
