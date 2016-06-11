jQuery(document).ready(function(){
	Tabla();
	HColorPicker('color');
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Catalogo/Cat/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		color: color
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
