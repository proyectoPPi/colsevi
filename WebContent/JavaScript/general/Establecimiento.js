jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/General/Establecimiento/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
	jQuery('#telTipo').val('0');
}

function Eliminar(){
	HEliminar("formulario", contexto + "/General/Establecimiento/EliminarEstablecimiento.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}