jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Proveedor/Prov/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina,
		clase: clase
	});
}

function Limpiar(){
	HLimpliar();
	jQuery('#tipoProv, #telTipo').val(0);
	jQuery('#barrio, #descripDir').val('');

}

function Eliminar(){
	HEliminar("formulario", contexto + "/Proveedor/Prov/Eliminar.html?");
}

function CargarFormulario(Id){
	jQuery('#barrio, #descripDir').val('');
	jQuery('#tipoProv, #telTipo').val(0);
	HCargarFormulario(Id);
}

function preprocesar(){
	HPreprocesar({
		url: contexto + "/Proveedor/Prov/preprocesador.html?",
		formulario: "formulario",
	});
}

