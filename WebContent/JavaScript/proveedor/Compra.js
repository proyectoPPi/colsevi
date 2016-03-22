jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Proveedor/Compra/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

function CargarIngredientes(){
	if(jQuery('#clasificarIng').val() == ""){
		return;
	}
	
	jQuery.ajaxQueue({
		url: contexto + "/Proveedor/Compra/ClasificarIng.html?",
		 data:{clasificar: jQuery('#clasificarIng').val()},
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error ejecutando CargarIngredientes" + err); 
         	return; 
 		} 

		for(i in data){
			data[i]['id']
		}
	});
}