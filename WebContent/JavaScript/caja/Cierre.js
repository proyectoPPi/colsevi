jQuery(document).ready(function(){
	Tabla();
	validarF();
	HDatetimePicker('dtBox',false);
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Caja/CierreCaja/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function validarF(){
	HValidador('formulario');
}