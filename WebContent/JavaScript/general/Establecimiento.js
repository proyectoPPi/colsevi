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
				required: false,
				minlength: 5,
				maxlength: 120
			},
			direccion:{
				required: true
			},
			telefono:{
				required: true
			},
			telTipo:{
				required: true
			},
			hora_inicio: {
				required: true
			},
			hora_fin: {
				required: true
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

function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode
	return (key >= 48 && key <= 57)
}
