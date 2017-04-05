jQuery(document).ready(function(){
	Tabla();
	Hformulario();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Usuario/TipoDocumento/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Usuario/TipoDocumento/EliminarTipoDocumento.html?");
}

function preprocesar(){
	HPreprocesar({
		url: contexto + "/Usuario/TipoDocumento/preprocesador.html?",
		formulario: "formulario",
	});
}