var swP;
jQuery(document).ready(function(){
	swP = true;
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
	  tablaVista(ui.item.id_producto);
	}
});

function tablaVista(value){
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep1/listaProductos.html?",
		data: {consecutivo: jQuery('#consecutivo').val(), prod: value !== undefined ? value : jQuery('#codigo_prod').val()},
		async: false,
		method: 'construirVista'
	});
}

function construirVista(data){
	var html = '';
	
	if(data['records'].length > 0){
		data = data['records'];
		for(i in data){
			html +='<div class="col-md-4" style="padding-right: 8px;padding-left: 8px;margin-top: 14px;" onclick="modalDetalleProductoCarrito(\''+data[i]['imagen']+'\',\''+data[i]['venta']+'\',\''+data[i]['nombre']+'\',\''+data[i]['id_producto']+'\');">';
			html +='<div class="listing-item white-bg bordered mb-20">';
			html +='<div class="overlay-container">';
			html +='<img src="http://localhost:9080/viewJSP/img/'+data[i]['imagen']+'" alt="">';
			html +='<a class="overlay-link popup-img-single"><span style="color: white;font-weight: bold;">AÑADIR A TU CARRITO</span></a>';
			html +='</div>';
			html +='<div class="body elements-list clearfix">';
			html +='<div class="col-md-6"><h3 style="float: left;">'+data[i]['nombre']+'</h3></div>';
			html +='<div class="col-md-6" style="text-align:center;margin-bottom:5px;"><span class="price">'+data[i]['venta']+' $</span></div>';
			html +='</div></div></div></div></div>';
			
//			html +='<div class="col-md-6"><h3><a href="shop-product.html">'+data[i]['nombre']+'</a></h3></div>';
//			html +='<div class="col-md-6" style="text-align:center;margin-bottom:5px;"><span class="price">$ '+data[i]['venta']+'</span></div>';
//			html +='<div class="elements-list clearfix">';
//			html +='<div class="col-xs-4" style="padding-right: 0px !important; padding-left: 0px !important;"><input type="number" id="cantidad_'+data[i]['id_producto']+'" name="cantidad" class="form-control"/></div>';
//			html +='<div class="col-xs-8" style="padding-right: 0px !important; padding-left: 0px !important;"><a href="#" class="pull-right margin-clear btn btn-default-transparent btn-animated" onclick="OpcionVista('+data[i]['id_producto']+', 1);">Adicionar';
//			html +='<i class="fa fa-shopping-cart"></i>';
//			html +='</a></div></div></div></div></div></div>';
		}
	}else{
		html = 'No hay productos con esa descripción';
	}
	jQuery('#vistaProducto').html(html);
}

function modalDetalleProductoCarrito(img, venta, nombre, idP,cantidad){
	//cargar modal;
	jQuery("#modalProductoCarrito").modal('show');
	var url = 'http://localhost:9080/viewJSP/img/'+img;
	jQuery(".imgProduct").css("background-image", "url("+url+")"); 
	jQuery("#precioModalDetalleCarrito").html(venta + " $");
	jQuery("#productTitle").html(nombre);
	jQuery("#CarritoProductoModalDetalle").val(idP);
	if(cantidad !== undefined)
		jQuery('#cantidadCarritoDetalle').val(cantidad);
}

function OpcionVista(opt){
	switch(opt) {
    case 1://Add
    	HAjax({
    		url: contexto + "/Pedido/PedidoWizardStep1/Adicionar.html?",
    		data: {consecutivo: jQuery('#consecutivo').val(), prod: jQuery("#CarritoProductoModalDetalle").val(), cantidad: jQuery('#cantidadCarritoDetalle').val()},
    		async: false,
    		method: 'Add'
    	});
        break;
	}
}

function Add(data){
	jQuery("#modalProductoCarrito").modal('hide');
	if(data.error == undefined){
//		HMensaje(data.correcto, 'success');
		PintarDetalle(data);
	}else
		HMensaje(data.error, 'danger');
}

function PintarDetalle(obj){
	var html = '<div class="row">';
	html += '<label class="nombreDetalle" onclick="modalDetalleProductoCarrito(\''+obj['imagenP']+'\',\''+obj.ventavistaP+'\',\''+obj.nombreP+'\',\''+obj.idP+'\', \''+obj.cantidadP+'\');">'+obj.nombreP+'</label>';
	html += '<span style="float: left;">&nbsp;x&nbsp;'+obj.cantidadP+'</span>';
	html += '<span style="float: right;">$ '+obj.ventavistaP+' <i class="fa fa-times-circle" onclick="eliminarDetalle($(this),'+obj.idP+','+obj.cantidadP+','+ obj.ventaP+');"></i></span>';
	html += '</div>';
	
	if(swP){
		jQuery('#productosDetalle').html('');
		swP = false;
	}

	jQuery('#productosDetalle').prepend(html);
	jQuery('.precioCarrito, .currency').html(parseInt(jQuery('.precioCarrito').html()) + obj.ventaP);
	jQuery('.cartQuantity').html(parseInt(jQuery('.cartQuantity').html()) + obj.cantidadP);

}

function eliminarDetalle(obj, id, cantidad, venta){
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep1/eliminarDetalle.html?",
		data: {consecutivo: jQuery('#consecutivo').val(), prod: id},
		async: false,
		method: 'ResultadoEliminar'
	});
}

function ResultadoEliminar(result){
	alert(result);
}

function continuarPaso1(){
	HredireccionarVista(contexto + "/Pedido/PedidoWizardStep2.html?" + jQuery('#formulario').serialize());
}

function Cancelar(){
	HredireccionarVista(contexto + "/Pedido/Visualizar.html?");
}

$('.btn-number').click(function(e){
    e.preventDefault();
    
    fieldName = $(this).attr('data-field');
    type      = $(this).attr('data-type');
    var input = $("input[name='"+fieldName+"']");
    var currentVal = parseInt(input.val());
    if (!isNaN(currentVal)) {
        if(type == 'minus') {
            
            if(currentVal > input.attr('min')) {
                input.val(currentVal - 1).change();
            } 
            if(parseInt(input.val()) == input.attr('min')) {
                $(this).attr('disabled', true);
            }

        } else if(type == 'plus') {

            if(currentVal < input.attr('max')) {
                input.val(currentVal + 1).change();
            }
            if(parseInt(input.val()) == input.attr('max')) {
                $(this).attr('disabled', true);
            }

        }
    } else {
        input.val(0);
    }
});
$('.input-number').focusin(function(){
   $(this).data('oldValue', $(this).val());
});
$('.input-number').change(function() {
    
    minValue =  parseInt($(this).attr('min'));
    maxValue =  parseInt($(this).attr('max'));
    valueCurrent = parseInt($(this).val());
    
    name = $(this).attr('name');
    if(valueCurrent >= minValue) {
        $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
    } else {
        alert('Sorry, the minimum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    if(valueCurrent <= maxValue) {
        $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
    } else {
        alert('Sorry, the maximum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    
    
});
$(".input-number").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
             // Allow: Ctrl+A
            (e.keyCode == 65 && e.ctrlKey === true) || 
             // Allow: home, end, left, right
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 // let it happen, don't do anything
                 return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
