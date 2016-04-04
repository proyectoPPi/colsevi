jQuery(document).ready(function(){
	Tabla();
	HDatetime('pruebabasicA');
	HDatetime('pruebabasicB');
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/General/Establecimiento/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function Limpiar(){
	HLimpliar();
}

function Eliminar(){
	HEliminar("formulario", contexto + "/General/Establecimiento/EliminarEstablecimiento.html?");
}

function printDiv(tabla) {
    //Get the HTML of div
    var divElements = document.getElementById('tabla').innerHTML;
    //Get the HTML of whole page
    var oldPage = document.body.innerHTML;

    //Reset the page's HTML with div's HTML only
    document.body.innerHTML = 
      "<html><head><title></title></head><body>" + 
      divElements + "</body>";

    //Print Page
    window.print();

    //Restore orignal HTML
    document.body.innerHTML = oldPage;

  
}




