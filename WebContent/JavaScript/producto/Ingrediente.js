jQuery(document).ready(function(){
	Tabla();
	
	$('#Formulario').validate({
		rules: {
			nombre: {
				required : true
			},
			descripcion: {
				required: false
			},
			medida:{
				required: true
			}
		}  
     });
	
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Ingrediente/Ing/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Ingrediente/Ing/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}

