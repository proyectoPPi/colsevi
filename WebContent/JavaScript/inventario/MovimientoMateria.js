jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Inventario/MovimientoMateria/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		clase: clase
	});
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}