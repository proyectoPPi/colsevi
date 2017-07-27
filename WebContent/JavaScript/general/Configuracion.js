jQuery(document).ready(function(){
	Tabla();
	
	$('#Formulario').validate({
		rules: {
			descripcion: {
				required: false,
				minlength: 5,
				maxlength: 120
			},
			valor:{
				required: true,
				maxlength: 100,
				minlength: 1
			},
		}  
     });
	
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/general/Configuracion/tabla.html?",
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

function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}

jQuery("#configuracionRegenerar").click(function(){
	jQuery.ajaxQueue({
		url: contexto + '/reconstruirConfiguracion',
	}).done(function(result) {
 		try{ 
 			data = jQuery.parseJSON(result);
 			if(data["error"] !== undefined){
 				HMensaje(data["error"], 'danger');
 			}else{
 				HMensaje(data["correcto"], 'success');
 			}
 		} catch(err){ 
 			console.log("Error ejecutando HGrafico" + err); 
 		} 
	});
});