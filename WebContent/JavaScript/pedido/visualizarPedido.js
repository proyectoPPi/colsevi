jQuery(document).ready(function(){
	if(mensajeFlujo != '')
		HMensaje(mensajeFlujo, 'success');
	Tabla();
	HiniciarAutocompletar(contexto + '/Pedido/Visualizar/autocompletar.html?', 'clienteV');
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Pedido/Visualizar/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		modal: 'DetallePedido'
	});
}

function Limpiar(){
	HLimpiar();
}

function LimpiarDetalle(){
	jQuery('#tablaDetalle').html('');
}

jQuery("#clienteV").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#clienteF').val(ui.item.id_producto);
	}
});


function crearPedido(){
	HredireccionarVista(contexto + "/Pedido/PedidoWizardStep1",undefined ,'T');
}

function CargarFormulario(id){
	if(BuscarRegistro(id)['idEstado'] !== 2)
		jQuery('#ContinuarPedido').hide();
	else
		jQuery('#ContinuarPedido').show();
	HCargarFormulario(id);
	HTablaSimple({
		url: contexto + "/Pedido/Visualizar/cargarDetalle.html?id_pedido=" + id,
		Id: "#tablaDetalle",
		titulos: tituloDetalle
	});
}