jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Inventario/MovimientoMateria/tabla.html?",
		Id: "#tabla",
		link: true,
		titulos: titulos,
		pagina:pagina
	});
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}