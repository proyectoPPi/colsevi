//jQuery(document).ready(function(){
//	Tabla();
//});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Inventario/Vista/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Inventario/inv/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
	jQuery('#valorsin').val(BuscarRegistro(Id)['valorsin']);
}