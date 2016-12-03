jQuery(document).ready(function(){
	Tabla();
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
	HEliminar("formulario", contexto + "/Catalogo/Cat/Eliminar.html?");
}

function CargarFormulario(Id){
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
				html += '<input type="checkbox" value="'+data[i]['id']+'" name="producto"';
				data[i]['select'] !== undefined ? html += ' checked' : '';
				html += '>' + data[i]['nombre'] + '</label></div>';
		}
		jQuery('#detalleCatalog').html(html);
	} catch(err){ 
     	return; 
	} 
}

function preprocesar(){
	$("input[name=catalogo]").each(function(){
	    if(this.checked){
	    	jQuery('#catalogActive').val(jQuery('#catalogActive').val() + this.value + ',');
	    }
	});
	HPreprocesar({
		url: contexto + "/Catalogo/Cat/preprocesador.html?",
		formulario: "formulario",
	});
}
