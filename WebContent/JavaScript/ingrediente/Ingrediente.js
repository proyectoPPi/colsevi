jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Ingrediente/Ing/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
	jQuery('#clasificar').val(0);
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Ingrediente/Ing/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}