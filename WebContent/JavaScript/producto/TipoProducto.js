jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Producto/Tipo/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Producto/Tipo/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}