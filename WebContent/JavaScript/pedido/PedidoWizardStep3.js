jQuery(document).ready(function(){
	HiniciarAutocompletar(contexto + '/Pedido/PedidoWizardStep3/autocompletar.html?', 'cliente');
});

jQuery("#cliente").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#persona').val(ui.item.id_persona);
	}
});

function Finalizar(){
	HAjax({
		url: contexto + "/Pedido/PedidoWizardStep3/continuar.html?" + jQuery('#continuar').serialize(),
		async: false,
		method: 'mostrarMensaje'
	});
}

function mostrarMensaje(data){
	if(data.error == undefined)
		HredireccionarVista(contexto + "/Pedido/Visualizar.html?correcto=" + data.correcto);
	else
		HMensaje(data.error, 'danger');
	
}

