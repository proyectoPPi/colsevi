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
	HLimpiar();
	iniciarTipoProducto();
}

function iniciarTipoProducto(){
	var html = '<tr><td>';
	html += '<select class="form-control" id="tipoProducto" name="tipoProducto">';
	for(i in arrayListaTP){
		html += '<option value="' + arrayListaTP[i]['id'] + '">' + arrayListaTP[i]['nombre'] + '</option>';
	}
	html += '</select>';
	html += '</td><td>';
	html += '<input type="number" class="form-control" id="cantidadPlato" name="cantidadPlato"/>';
	html += '</td></tr>';
	jQuery('#contenido > table > tbody').append(html);
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Ingrediente/Clasificar/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id); 
	alert(BuscarRegistro(Id)['detalle']); // cargar detalle 
}

function validarFormulario(){
	if($('#Formulario').valid()){
		enviarFormulario();
	}
}
