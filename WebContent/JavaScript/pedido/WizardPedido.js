jQuery(document).ready(function(){
	jQuery('#detalleContenido').hide();
	jQuery("#ModalUbicacion").modal('show');
	
	$('#formularioMotivo').validate({
		rules: {
			establecimiento: {
				required : true
			}
		}  
     });
});

function validarFormulario(){
	if($('#formularioMotivo').valid())
		enviarFormulario('formularioMotivo');
}

function detalle(secuencia){
	jQuery("#consecutivo, #sec").val(secuencia);
	jQuery("#ModalUbicacion").modal('hide');
	jQuery('#detalleContenido').show();
	HiniciarAutocompletar(contexto + '/Pedido/PedidoWizardStep1/completarProducto.html?', 'producto');
	tablaVista();
}

jQuery("#producto").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#codigo_prod').val(ui.item.id_producto);
	}
});

function tablaVista(){
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep1/listaProductos.html?",
		data: {consecutivo: jQuery('#consecutivo').val(), prod: jQuery('#codigo_prod').val()},
		async: false,
		method: 'construirVista'
	});
}

function construirVista(data){
	var html = '';
	
	if(data['records'].length > 0){
		data = data['records'];
		for(i in data){
			html +='<div class="col-md-3 col-sm-6 masonry-grid-item">';
			html +='<div class="listing-item white-bg bordered mb-20">';
			html +='<div class="overlay-container">';
			html +='<img src="http://localhost:9080/viewJSP/img/'+data[i]['imagen']+'" alt="">';
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
	}else{
		html = 'No hay productos con esa descripción';
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
	}
}

function Add(data){
	if(data.error == undefined)
		HMensaje(data.correcto, 'success');
	else
		HMensaje(data.error, 'danger');
}

function continuarPaso1(){
	HredireccionarVista(contexto + "/Pedido/PedidoWizardStep2.html?" + jQuery('#formulario').serialize());
}
