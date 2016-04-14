jQuery(document).ready(function(){
	Tabla();
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
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Ingrediente/Clasificar/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}