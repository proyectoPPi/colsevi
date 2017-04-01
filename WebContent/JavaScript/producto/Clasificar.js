jQuery(document).ready(function(){
	Tabla();
	Hformulario();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Ingrediente/Clasificar/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Ingrediente/Clasificar/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}