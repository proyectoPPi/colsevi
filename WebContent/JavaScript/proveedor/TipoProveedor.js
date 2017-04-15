jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/TipoProveedor/TipoProv/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
	jQuery('#clasificar').val(0);
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/TipoProveedor/TipoProv/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}