function initPaso2(){
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep2/listaProductos.html?",
		data: {consecutivo: jQuery('#consecutivo').val()},
		async: false,
		method: 'construirVista'
	});
};

function construirVista(data){
	//alert(data);
	var suma = 0;
	jQuery('#vistaProducto').html('');
	data = data['records'];
	var html = '';
	html +='<table class="table cart table-hover table-colored">';	
	html +='<tbody>';
	
	for(i in data){
		console.log(data[i]);
		html +='<tr>';
		html +='<input type="hidden" name="producto" value="'+data[i]['prodId']+'"/>';
		html +='<td class="product"><a href="shop-product.html">'+data[i]['nombre']+ '</a>&nbsp;x&nbsp;'+data[i]['cantidad'] +' <small>'+data[i]['descripcion']+'</small></td>';
		html +='<td class="amount">'+data[i]['sub_total']+' <i class="fa fa-times-circle" onclick="eliminarDetalle();"></i></td>';
		html +='</tr>';
		suma += parseInt(data[i]['suma']);
	}
	
//	html +='<tr>';
//	html +='<td class="total-quantity" colspan="4">Total ' + data.length + ' producto(s)</td>';
//	html +='<td class="total-amount">$' + suma + '</td>';
//	html +='</tr>';
	html +='</tbody>';
	html +='</table>';
	
	jQuery('.cartQuantity').html(data.length);
	jQuery('.currency').html(suma);

	jQuery('#vistaProducto').html(html);
}

function eliminarDetalle(){
	alert(1);
}

function actualizar(){
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep2/Actualizar.html?" + jQuery('#formulario').serialize(),
		async: false,
		method: 'mostrarMensaje'
	});
}

function mostrarMensaje(data){
	if(data.error == undefined){
		HMensaje(data.correcto, 'success');
		initPaso2();
	}else
		HMensaje(data.error, 'danger');
	
}

function redireccionar(){
	HredireccionarVista(contexto + "/Pedido/PedidoWizardStep3.html?" + jQuery('#continuar').serialize());
}
