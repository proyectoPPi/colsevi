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
	else
		alert(123);
}

function Eliminar(){
	HEliminar("formulario", contexto + "/Producto/Tipo/Eliminar.html?");
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}