jQuery(document).ready(function(){
//	HiniciarAutocompletar(contexto + '/Pedido/PedidoWizardStep2/autocompletar.html?', 'producto');
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep2/listaProductos.html?",
		data: {consecutivo: jQuery('#consecutivo').val()},
		async: false,
		method: 'construirVista'
	});
});

function construirVista(data){
	var suma = '';
	jQuery('#vistaProducto').html('');
	data = data['records'];
	var html = '';
	html +='<table class="table cart table-hover table-colored">';	
	html +='<thead>';
	html +='<tr>';
	html +='<th>Producto </th>';
	html +='<th>Precio </th>';
	html +='<th>Cantidad</th>';
	html +='<th>Remover </th>';		
	html +='<th class="amount">Total </th>';
	html +='</tr>';
	html +='</thead>';
	html +='<tbody>';
	
	for(i in data){
		html +='<tr class="remove-data">';
		html +='<td class="product"><a href="shop-product.html">'+data[i]['nombre']+'</a> <small>'+data[i]['descripcion']+'</small></td>';
		html +='<td class="price">'+data[i]['venta']+'</td>';
		html +='<td class="quantity">';
		html +='<div class="form-group">';
		html +='<input type="text" class="form-control" value="'+data[i]['cantidad']+'" name="cantidad">';
		html +='<input type="hidden" name="producto" value="'+data[i]['prodId']+'"/>';
		html +='</div>';											
		html +='</td>';
		html +='<td class="remove"><a class="btn btn-remove btn-sm btn-default">Remover</a></td>';
		html +='<td class="amount">'+data[i]['sub_total']+'</td>';
		html +='</tr>';
		suma += data[i]['suma'];
	}
	
	html +='<tr>';
	html +='<td class="total-quantity" colspan="4">Total ' + data.length + ' producto(s)</td>';
	html +='<td class="total-amount">$' + suma + '</td>';
	html +='</tr>';
	html +='</tbody>';
	html +='</table>';

	jQuery('#vistaProducto').html(html);
}

function actualizar(){
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep2/Actualizar.html?" + jQuery('#formulario').serialize(),
		async: false,
		method: 'Confirmar'
	});
}

function Confirmar(data){
	if(data.error == undefined){
    	jQuery('#continuar').submit();
	}
}