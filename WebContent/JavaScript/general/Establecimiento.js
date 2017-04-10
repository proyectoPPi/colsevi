jQuery(document).ready(function(){
	HDatetimePicker('hora_inicio','LT');
	HDatetimePicker('hora_fin','LT');
	Tabla();
	
	$('#Formulario').validate({
		rules: {
			nombreEsta: {
				required : true,
				minlength: 5,
				maxlength: 40
			},
			descipEsta: {
				minlength: 5,
				maxlength: 120
			}
		}  
     });
	
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/General/Establecimiento/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
	jQuery('#telTipo').val('0');
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/General/Establecimiento/EliminarEstablecimiento.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}
