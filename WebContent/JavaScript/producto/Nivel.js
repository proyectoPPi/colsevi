jQuery(document).ready(function(){
	Tabla();
	Hformulario();
	HColorPicker('color');
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Receta/Nivel/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		color: color
	});
}

jQuery('#color').on('changeColor', function(e,color){
	if(color==null) {
		//Color Transparente
		jQuery('.color-fill-icon', jQuery(this)).addClass('colorpicker-color');
	} else {
		jQuery('.color-fill-icon', jQuery(this)).removeClass('colorpicker-color');
		jQuery('.color-fill-icon', jQuery(this)).css('background-color', color);
		jQuery('#icono').val(color);
	}
});

function Limpiar(){
	HLimpiar();
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Receta/Nivel/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}
