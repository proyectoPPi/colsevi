jQuery(document).ready(function(){
	Tabla();
	Hformulario();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/general/Configuracion/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/general/Configuracion/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}