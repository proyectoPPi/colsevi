jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Proveedor/Prov/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		clase: clase
	});
}

function Limpiar(){
	HLimpliar();
	jQuery('#tipoProv').val(0);
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Proveedor/Prov/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}