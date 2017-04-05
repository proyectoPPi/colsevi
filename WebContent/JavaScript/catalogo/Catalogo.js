jQuery(document).ready(function(){
	Tabla();
	Hformulario();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Catalogo/Cat/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	jQuery('[href=#general]').tab('show');
	jQuery('#detalleCatalog').html('');
	jQuery('#catalogActive').val('');
	HLimpiar();
	
	HAjax({
		url: contexto + "/Catalogo/Cat/ListaCatalogoPosibleProducto.html?",
		async: false,
		method: 'productInCatalog'
	});
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Catalogo/Cat/Eliminar.html?");
}

function CargarFormulario(Id){
	jQuery('[href=#general]').tab('show');
	jQuery('#detalleCatalog').html('');
	jQuery('#catalogActive').val('');
	HCargarFormulario(Id);
	
	HAjax({
		url: contexto + "/Catalogo/Cat/ListaCatalogoPosibleProducto.html?",
		data: {catalogo: Id},
		async: false,
		method: 'productInCatalog'
	});
}

function productInCatalog(data){
	try{ 
		var html = '';
		for(i in data){
				html += '<div class="col-xs-6 col-lg-2"><label>';
				html += '<input type="checkbox" value="'+data[i]['id']+'" name="catalogo"';
				data[i]['select'] !== undefined ? html += ' checked' : '';
				html += '>' + data[i]['nombre'] + '</label></div>';
		}
		jQuery('#detalleCatalog').html(html);
	} catch(err){ 
     	return; 
	} 
}

