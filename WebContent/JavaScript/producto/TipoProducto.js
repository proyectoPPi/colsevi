jQuery(document).ready(function(){
	Tabla();
	
	$('#Formulario').validate({
		rules: {
			nombre : {
				required : true,
				minlength: 5,
				maxlength: 50
			},
			descripcion: {
				minlength: 5,
				maxlength: 120
			}
		}  
     });
	
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Producto/Tipo/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
}

function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Producto/Tipo/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}