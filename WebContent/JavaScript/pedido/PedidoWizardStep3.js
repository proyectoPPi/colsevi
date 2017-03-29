jQuery(document).ready(function(){
	HiniciarAutocompletar(contexto + '/Pedido/PedidoWizardStep3/autocompletar.html?', 'cliente');
});

jQuery("#cliente").autocomplete({
	  select: function(e, ui) {
	  this.value = ui.item.value;
	  jQuery('#persona').val(ui.item.id_persona);
	}
});

