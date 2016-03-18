jQuery(document).ready(function(){
	HiniciarAutocompletar(contexto + '/Pedido/Flujo/autocompletar.html?', 'clienteF', true);
});

function cargarHijos(value){
	jQuery.ajaxQueue({
	  url: contexto + "/Pedido/Flujo/hijos.html?",
	  data:{padre: value},
	}).done(function(result) {
		data = jQuery.parseJSON( result );
		var html = '';
		for(i in data['records']){
			html +=  '<a class="btn btn-danger" onclick="productos('+data['records'][i]['id']+');" href="#">'+data['records'][i]['nombre']+'</a>';
		}
		jQuery('#GrupoHijos').html(html);
	});
}

function productos(value){
	jQuery.ajaxQueue({
	  url: contexto + "/Pedido/Flujo/listaProductos.html?",
	  data:{tipo: value},
	}).done(function(result) {
		data = jQuery.parseJSON( result );
		var html = '';
		
		for(i in data['records']){
			html += '<div class="col-lg-12 rowProducts">';
			html += '<div class="col-xs-12 col-lg-2">';
			html += '<center><a href="#">';
			html += '<img src="http://rollthemes.com/demo/html/zupa/images/food/1.jpg" style="padding-top:20px; width: 120px;border-radius: 8px;">';
			html += '</a></center>';
			html += '</div>';
			html += '<div class="col-xs-12 col-lg-8">';
			html += '<h3 class="fieldsOrder">'+data['records'][i]['nombre']+'</h3>';
			html += '<p>'+data['records'][i]['descripcion']+'</p>';
			html += '</div>';
			html += '<div class="col-xs-12 col-lg-2">';
			html += '<h3 class="fieldsOrder">$ '+data['records'][i]['venta']+'</h3>';
			html += '<span class="rating">';
			html += '<span class="star"></span>';
			html += '<span class="star"></span>';
			html += '<span class="star"></span>';
			html += '<span class="star"></span>';
			html += '<span class="star"></span>';
			html += '</span>';
			html += '<input type="number" id="cantidad'+data['records'][i]['id_producto']+'" class="form-control" value="1"/>';
			html += '<button data-toggle="button" class="btn btn-white" onclick="Adicionar('+data['records'][i]['id_producto']+');">';
			html += '<i class="fa fa-shopping-cart text-info"></i> Adicionar';
			html += '</button>';
			html += '</div>';
			html += '</div>';
		}
		
		jQuery('#VistaProductos').html(html);
	});
}

function Adicionar(value){
	alert(jQuery('#cantidad'+value).val());
}

jQuery("#clienteF").autocomplete({
   select: function(e, ui) {
        this.value = ui.item.value;
        jQuery('#CodigoPersona').val(ui.item.id_persona);
    }
});

