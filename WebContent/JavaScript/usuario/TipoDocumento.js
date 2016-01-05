jQuery(document).ready(function(){
	Tabla();
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
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Usuario/TipoDocumento/EliminarTipoDocumento.html?");
}