jQuery(document).ready(function(){
	Tabla();
	
	$('#Formulario').validate({
		rules: {
			cantMov: {
				required : true,
				min: 1,
				number: true
			},
			unidadMov: {
				required: true
			},
			estaMov:{
				required: true
			}
		}  
     });
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Inventario/MateriaPrima/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function validarFormulario(){
	if(jQuery('#Formulario').valid())
		enviarFormulario();
}
