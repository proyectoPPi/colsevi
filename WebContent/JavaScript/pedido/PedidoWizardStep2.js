jQuery(document).ready(function(){
	HiniciarAutocompletar(contexto + '/Pedido/PedidoWizardStep2/autocompletar.html?', 'producto');
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep2/listaProductos.html?",
		data: {consecutivo: jQuery('#consecutivo').val()},
		async: false,
		method: 'construirVista'
	});
});

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
		html +='<span class="price">$'+data[i]['venta']+'</span>';
		html += '<p><input type="number" id="cantidad_'+data[i]['referencia']+'" name="cantidad" class="form-control"/>';
		html +='<a href="#" class="pull-right margin-clear btn btn-sm btn-default-transparent btn-animated" onclick="OpcionVista('+data[i]['id_producto']+',"'+data[i]['referencia']+'", 1);">Adicionar';
		html +='<i class="fa fa-shopping-cart"></i>';
		html +='</a></p></div></div></div></div>';
	}
	
	jQuery('#vistaProducto').html(html);
}

function OpcionVista(value, ref, opt){
	
	switch(opt) {
    case 1://Add
    	HAjax({
    		url: contexto + "/Pedido/PedidoWizardStep2/Adicionar.html?",
    		data: {consecutivo: jQuery('#consecutivo').val(), prod: value, cantidad: jQuery('#cantidad_'+ref).val()},
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
	if(data.error == undefined){
    	jQuery("#mensajeC").html(data.correcto);
    	jQuery("#correcto").show();
	}else{
    	jQuery("#mensajeE").html(data.error);
    	jQuery("#error").show();
	}
}