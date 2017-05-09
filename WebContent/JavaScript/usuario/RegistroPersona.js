jQuery(document).ready(function(){

	$('#Formulario').validate({
		rules: {
			nombre: {
				required : true
			},
			apellido: {
				required: true
			},
			rol:{
				required: true
			},
			tipo_doc:{
				required: true
			},
			documento:{
				required: true
			},
			usuario:{
				required: true
			},
			clave:{
				required: true
			},
			repetir:{
				required: true,
				equalTo: "#clave"
			}
		}  
     });
	
});


function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}
