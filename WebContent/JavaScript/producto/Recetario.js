jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	jQuery.ajaxQueue({
		url: contexto + "/Recetario/tabla.html?",
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error" + err); 
 			jQuery('#tabla').html("<br/>No hay datos");
         	return; 
 		} 
 		data = data['datos'];
 		for(i in data){
 			var html = '';
			html +='<div class="col-xs-12 col-lg-3">';
			html +='<section class="panel">';
			html +='<div class="pro-img-box">';
			html +='<div class="btn-group" style="position:absolute;top: 10px;left: 85%;">';
			html +='<button data-toggle="dropdown" class="btn btn-white" aria-pressed="false"><i class="fa fa-ellipsis-v"></i></button>';
			html +='<ul role="menu" class="dropdown-menu">';
			html +='<li><a data-toggle="modal" href="#ModalFormulario" onclick="CargarFormulario('+data[i]['id_receta']+');" href="#">Receta</a></li>';
			html +='<li><a href="'+contexto+'/Producto/Admin.html?id=12">Producto</a></li>';
			html +='</ul>';
			html +='</div>';
			html +='<img src="http://thevectorlab.net/flatlab/img/product-list/pro-1.jpg">';
			html +='</div>';
			html +='<div class="panel-body">';
			html +='<div class="row">';
			html +='<h4><strong>'+data[i]['nombreProd']+'</strong></h4>';
			html +='<hr/>';
			html +='<label class="col-md-5"><span class="recetaD" style="background: '+data[i]['icono']+';"></span>'+data[i]['nombreDif']+'</label>';
			html +='<label class="col-md-6"><i class="fa fa-clock-o"></i>'+data[i]['tiempo']+' minutos</label>';
			html +='</div>';
			html +='</div>';
			html +='</section>';
			html +='</div>';
 		}
 		jQuery('#tabla').html(html);
 		dataMap["total"] = data['total'];
 		organizarPaginacion(1);
	});
}

function Limpiar(){
	jQuery('#id_producto').val('');
	jQuery('#prod').val('');
	jQuery('#tiempo').val('');
	jQuery('#dificultad').val('');
	jQuery('#id_dificultad_receta').val('');
	jQuery('#detalle').html('');
	jQuery('#secuencia').val('');
}

function CargarFormulario(Id){
	
		jQuery('#id_producto').val('');
		jQuery('#prod').val('');
		jQuery('#tiempo').val('');
		jQuery('#dificultad').val('');
		jQuery('#id_dificultad_receta').val('');
		jQuery('#detalle').html('');
		jQuery('#secuencia').val('');
	
	jQuery.ajaxQueue({
		url: contexto + "/Recetario/detalle.html?",
		data:{id:Id}
	}).done(function(result) {
		var data; 
		var secuencia = '';
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
 			var html = '';
 			html += '<div class="col-md-11">';
 			html += '<input type="text" class="form-control" value="'+detalle[i]['texto']+'" id="texto'+detalle[i]['id_preparacion']+'" name="texto'+detalle[i]['id_preparacion']+'" />';
 			html += '</div>';
 			html += '<div class="col-md-1">';
 			html += '<button class="btn btn-white" onclick="eliminarP(this);"><i class="fa fa-times"></i></button>';
 			html += '</div>';
 			jQuery('#detalle').html(html);
 			secuencia += detalle[i]['id_preparacion'] +',';
 		}
 		
 		jQuery('#secuencia').val(secuencia);
 		
 		jQuery('#id_producto').val(producto['id_prod']);
 		jQuery('#prod').val(producto['nombreProd']);
 		jQuery('#tiempo').val(receta['tiempo']);
 		jQuery('#dificultad').val(receta['difi']);
 		jQuery('#id_dificultad_receta').val(Id);
 		
	});
}

function eliminarP(option){
	option.parentNode.parentNode.remove();
}
