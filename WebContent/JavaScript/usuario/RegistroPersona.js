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
				required: true,
				maxlength: 10,
				number: true,
				digits: true
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
			},
			telFijo:{
				maxlength: 10,
				number: true,
				digits: true
			},
			telCel:{
				maxlength: 10,
				number: true,
				digits: true
			}
		}  
     });
	
});


function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}
