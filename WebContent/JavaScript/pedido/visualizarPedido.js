jQuery(document).ready(function(){
	Tabla();
	HiniciarAutocompletar(contexto + '/Pedido/Visualizar/autocompletar.html?', 'clienteV');
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Pedido/Visualizar/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

jQuery("#clienteV").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#clienteF').val(ui.item.id_producto);
	}
});
