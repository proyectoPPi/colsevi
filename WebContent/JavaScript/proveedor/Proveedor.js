jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Proveedor/Prov/tabla.html?",
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
	HEliminar("formulario", contexto + "/Proveedor/Prov/Eliminar.html?");
}