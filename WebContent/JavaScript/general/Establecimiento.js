jQuery(document).ready(function(){
	Tabla();
	HMask('telefono', '9999999');
	HDatetimePicker('dtBox',false);
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/General/Establecimiento/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		clase: clase
	});
}

function Limpiar(){
	HLimpliar();
	jQuery('#telTipo').val('0');
}

function Eliminar(){
	HEliminar("formulario", contexto + "/General/Establecimiento/EliminarEstablecimiento.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}