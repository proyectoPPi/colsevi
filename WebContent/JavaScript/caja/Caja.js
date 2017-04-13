jQuery(document).ready(function(){
	Tabla();
	
	$('#Formulario').validate({
		rules: {
			establecimiento : {
				required : true
			},
			valor_inicial: {
				minlength: 1
			}
		}  
     });
	
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Caja/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina: pagina,
		metodo: 'DeterminarFlujo',
		modal: 'FlujoCaja'
	});
}

function Limpiar(){
	HLimpiar();
}

function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}

function DeterminarFlujo(Id){
	jQuery('#id_caja').val(Id);
}

function ComenzarEjecucion(){
	HAjax({
		url: contexto + "/Caja/Ejecutar.html?",
		data: {caja: jQuery('#id_caja').val()},
		async: true,
		method: 'ResultadoEjecucion'
	});
}

function ResultadoEjecucion(data){
	MostrarMensaje(data, 'FlujoCaja', undefined,'', 'F');
}


