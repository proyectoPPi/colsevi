function initPaso1(){
	if(jQuery('#consecutivo').val() === '')
		jQuery("#ModalUbicacion").modal('show');
	else
		detalle();
}
function detalle(){
	HiniciarAutocompletar(contexto + '/Pedido/PedidoWizardStep1/completarProducto.html?', 'producto');
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep1/listaProductos.html?",
		data: {consecutivo: jQuery('#consecutivo').val()},
		async: false,
		method: 'construirVista'
	});
}

function construirVista(data){
	var html = '';
	data = data['records'];
	for(i in data){
		html +='<div class="col-md-3 col-sm-6 masonry-grid-item">';
		html +='<div class="listing-item white-bg bordered mb-20">';
		html +='<div class="overlay-container">';
		html +='<img src="http://cdn2.salud180.com/sites/www.salud180.com/files/cheescake.jpg" alt="">';
		html +='<a class="overlay-link popup-img-single" href="images/product-1.jpg"><i class="fa fa-search-plus"></i></a>';
		html +='<div class="overlay-to-top links">';
		html +='<span class="small">';
		html +='<a href="#" class="btn-sm-link"><i class="fa fa-heart-o pr-10"></i>Favoritos</a>';
		html +='<a href="#" class="btn-sm-link"><i class="icon-link pr-5"></i>Detalles</a>';
		html +='</span></div></div>';
		html +='<div class="body">';
		html +='<h3><a href="shop-product.html">'+data[i]['nombre']+'</a></h3>';
		html +='<div class="elements-list clearfix">';
		html +='<div class="col-xs-12" style="text-align:center;margin-bottom:5px;"><span class="price">$'+data[i]['venta']+'</span></div>';
		html +='<div class="col-xs-4" style="padding-right: 0px !important; padding-left: 0px !important;"><input type="number" id="cantidad_'+data[i]['id_producto']+'" name="cantidad" class="form-control"/></div>';
		html +='<div class="col-xs-8" style="padding-right: 0px !important; padding-left: 0px !important;"><a href="#" class="pull-right margin-clear btn btn-default-transparent btn-animated" onclick="OpcionVista('+data[i]['id_producto']+', 1);">Adicionar';
		html +='<i class="fa fa-shopping-cart"></i>';
		html +='</a></div></div></div></div></div></div>';
	}
	
	jQuery('#vistaProducto').html(html);
}

function OpcionVista(value, opt){
	
	switch(opt) {
    case 1://Add
    	HAjax({
    		url: contexto + "/Pedido/PedidoWizardStep1/Adicionar.html?",
    		data: {consecutivo: jQuery('#consecutivo').val(), prod: value, cantidad: jQuery('#cantidad_'+value).val()},
    		async: false,
    		method: 'Add'
    	});
        break;
    case n:
    	alert(value);
        break;
	}
}

function Add(data){
	if(data.error == undefined)
		HMensaje(data.correcto, 'success');
	else
		HMensaje(data.error, 'danger');
}













//jQuery("#cliente").autocomplete({
//   select: function(e, ui) {
//        this.value = ui.item.value;
//        jQuery('#CodigoPersona').val(ui.item.id_persona);
//    }
//});
//
//function cargarHijos(value){
//	jQuery.ajaxQueue({
//	  url: contexto + "/Pedido/Flujo/hijos.html?",
//	  data:{padre: value},
//	}).done(function(result) {
//		try{
//			data = jQuery.parseJSON(result);
//		} catch(err){
//			console.log("Error ejecutando cargarHijos" + err);
//        	return;
//		}
//		var html = '';
//		for(i in data['records']){
//			html +=  '<button type="button" class="btn btn-danger" onclick="productos('+data['records'][i]['id']+');" style="margin: 2px;">'+data['records'][i]['nombre']+'</button>';
//		}
//		jQuery('#GrupoHijos').html(html);
//	});
//}
//
//function productos(value){
//	jQuery.ajaxQueue({
//	  url: contexto + "/Pedido/Flujo/listaProductos.html?",
//	  data:{tipo: value, esta : jQuery('#estab').val()},
//	}).done(function(result) {
//		try{
//			data = jQuery.parseJSON(result);
//		} catch(err){
//			console.log("Error ejecutando Productos" + err);
//			jQuery('#VistaProductos').html('No hay productos disponibles');
//        	return;
//		}
//		
//		if(data['records'].length == 0){
//			jQuery('#VistaProductos').html('No hay productos disponibles');
//        	return;
//		}
//		var html = '';
//		
//		for(i in data['records']){
//			html += '<div class="col-lg-12 rowProducts">';
//			html += '<div class="col-xs-12 col-lg-2">';
//			html += '<center><a href="#">';
//			html += '<img src="http://rollthemes.com/demo/html/zupa/images/food/1.jpg" style="margin:10px; width: 120px;border-radius: 4px;">';
//			html += '</a></center>';
//			html += '</div>';
//			html += '<div class="col-xs-12 col-lg-8">';
//			html += '<h3><strong>'+data['records'][i]['nombre']+'</strong></h3>';
//			html += '<h4>'+data['records'][i]['descripcion']+'</h4>';
//			html += '</div>';
//			html += '<div class="col-xs-12 col-lg-2">';
//			html += '<h3 class="fieldsOrder">$ '+data['records'][i]['venta']+'</h3>';
//			html += '<input type="number" id="cantidad'+data['records'][i]['id_producto']+'" class="form-control" value="1"/>';
//			html += '<button data-toggle="button" class="btn btn-white" onclick="Adicionar('+data['records'][i]['id_producto']+');">';
//			html += '<i class="fa fa-shopping-cart text-info"></i> Adicionar';
//			html += '</button>';
//			html += '</div>';
//			html += '</div>';
//		}
//		
//		jQuery('#VistaProductos').html(html);
//	});
//}
//
//function Adicionar(value){
//	jQuery.ajaxQueue({
//	  url: contexto + "/Pedido/Flujo/Adicionar.html?",
//	  data:{prod: value, cantidad: jQuery('#cantidad'+value).val(),persona: jQuery('#CodigoPersona').val(), pedido: jQuery('#pedidosec').val()},
//	}).done(function(result) {
//		try{
//			data = jQuery.parseJSON(result);
//		} catch(err){
//			console.log("Error ejecutando Adicionar" + err);
//        	return;
//		}		
//		if(data.error == undefined){
//	    	jQuery("#mensajeC").html(data.correcto);
//	    	jQuery("#correcto").show();
//		}else{
//	    	jQuery("#mensajeE").html(data.error);
//	    	jQuery("#error").show();
//		}
//		
//	});
//}
//
//function cargarDetalle(){
//	HTabla({
//		url: contexto + "/Pedido/Flujo/obtenerDet.html?",
//		Id: "#detallePed",
//		titulos: titulos,
//		pagina:1,
//		campo: campo,
//		boton: boton,
//		clase: clase
//	});
//}
//
//function eliminarProd(value, option){
//	option.parentNode.parentNode.parentNode.remove();
//}
//
//jQuery('#actprod').click(function(){
//	var total = '';
//	try{
//		
//		var cantTbl = document.getElementsByName("cantTbl");
//		
//		for(var i=0; i<cantTbl.length; i++){
//			var cantidad = jQuery("#campo"+parseInt(i+1)).val();
//			var prod = cantTbl[i].id.split('campo')[1];
//			total = total.concat("&prod" + i + "=" + prod);
//			total = total.concat("&cant" + i + "=" + cantidad);
//		}
//		
//		jQuery.ajax({
//			  url: contexto + "/Pedido/Flujo/Actualizar.html?cantTbl=" + cantTbl.length + "&pedido="+ jQuery('#pedidosec').val() + "&persona=" + jQuery('#CodigoPersona').val()+ total,
//			}).done(function(result) {
//				try{
//					data = jQuery.parseJSON(result);
//				} catch(err){
//					console.log("Error ejecutando Adicionar" + err);
//		        	return;
//				}		
//				if(data.Error == undefined){
//			    	jQuery("#mensajeC").html(data.correcto);
//			    	jQuery("#correcto").show();
//				}else{
//			    	jQuery("#mensajeE").html(data.Error);
//			    	jQuery("#error").show();
//				}
//			});
//	}catch (e) {
//		alert('error');
//	}
//});
//
//function terminar(){
//
//	if(jQuery('#pago').is(':checked')){
//		jQuery('#pago').val('true');
//	}else{
//		jQuery('#pago').val('false');
//	}
//	
//	jQuery('#formterm').submit();
//}
//
//jQuery("#estab").change(function() {
//	
//	jQuery.ajaxQueue({
//	  url: contexto + "/Pedido/Flujo/Adicionar.html?",
//	  data:{persona: jQuery('#CodigoPersona').val(), pedido: jQuery('#pedidosec').val(), establecimiento: jQuery("#estab").val()},
//	}).done(function(result) {
//		try{
//			data = jQuery.parseJSON(result);
//		} catch(err){
//			console.log("Error ejecutando Adicionar" + err);
//        	return;
//		}		
//		if(data['warning'] != undefined){
//	    	jQuery("#mensajeC").html(data.correcto);
//	    	jQuery("#correcto").show();
//		}
//		
//	});
//	
//	
//});
