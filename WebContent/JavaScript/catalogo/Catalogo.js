jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Catalogo/Cat/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}


function Limpiar(){
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Catalogo/Cat/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}
