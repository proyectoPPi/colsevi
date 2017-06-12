jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Plato/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	jQuery('#contenido > table > tbody > tr').remove();
	HLimpiar();
}

function iniciarTipoProducto(valor){
	var idplato = valor !== undefined && valor['id'] !== undefined ? valor['id'] : '';
	var cantplato = valor !== undefined && valor['cant'] !== undefined ? valor['cant'] : '';
	
	var html = '<tr><td>';
	html += '<select class="form-control" id="tipoProducto" name="tipoProducto">';
	for(i in arrayListaTP){
		html += '<option value="' + arrayListaTP[i]['id'] + '" ';
		if(idplato === arrayListaTP[i]['id'])
			html += 'selected';
		html += ' >' + arrayListaTP[i]['nombre'] + '</option>';
	}
	html += '</select>';
	html += '</td><td>';
	html += '<input type="number" class="form-control" id="cantidadPlato" name="cantidadPlato" value="'+cantplato+'"/>';
	html += '</td></tr>';
	jQuery('#contenido > table > tbody').append(html);
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Plato/Eliminar.html?");
}

function CargarFormulario(Id){
	jQuery('#contenido > table > tbody > tr').remove();
	HCargarFormulario(Id); 
	var detalle = BuscarRegistro(Id)['detalle']; // cargar detalle
	for(i in detalle){
		iniciarTipoProducto(detalle[i]);
	}
}

function validarFormulario(){
	if($('#Formulario').valid()){
		enviarFormulario();
	}
}
