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
	HLimpliar();
	jQuery('#clasificar').val(0);
}

function Eliminar(){
	HEliminar("formulario", contexto + "/TipoProveedor/TipoProv/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}