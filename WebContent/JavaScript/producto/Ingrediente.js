jQuery(document).ready(function(){
	Tabla();
	Hformulario();
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
	HLimpiar();
	jQuery('#clasificar').val(0);
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Ingrediente/Ing/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}