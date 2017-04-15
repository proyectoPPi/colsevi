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
			tipoProv:{
				required: true
			},
			direccion:{
				required: true
			},
			barrio:{
				required: true
			},
			descripDir: {
				required: false
			},
			telefono: {
				required: true,
				maxlength: 10,
				number: true,
				digits: true
			},
			telTipo: {
				required: true
			}
		}  
     });
	
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Proveedor/Prov/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpiar();
	jQuery('#barrio, #descripDir').val('');
}

function Eliminar(){
	HEliminar("Formulario", contexto + "/Proveedor/Prov/Eliminar.html?");
}

function CargarFormulario(Id){
	jQuery('#barrio, #descripDir').val('');
	HCargarFormulario(Id);
}