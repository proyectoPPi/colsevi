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

jQuery("#clienteV").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#clienteF').val(ui.item.id_producto);
	}
});
