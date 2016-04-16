jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Receta/Nivel/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Receta/Nivel/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}