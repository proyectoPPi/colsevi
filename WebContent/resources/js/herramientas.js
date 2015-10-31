/*
 * Método para pintar la tabla
 */
function Tabla(opciones){
	var titulos = opciones.titulos;
	var Id = opciones.Id;
	
	jQuery.ajaxQueue({
		  url: opciones.url,
		}).done(function(result) {
			var data = jQuery.parseJSON( result );
			var html = ""; 
			if(data == ""){
				 jQuery(Id).html("No hay datos");
				 return;
			 }
			html='<table class="display table table-bordered table-striped dataTable"><thead><tr>';
			for(k in titulos){
				 html += "<th>"+opciones.titulos[k]+"</th>";
			 }
			html += "</tr></thead><tbody>";
			 for(i in data){
				 html += "<tr>";
				 for(k in titulos){
					 if(k=="ID"){
						 html += '<td><span><a onclick="prueba('+data[i][k]+');" data-toggle="modal" href="#myModal4">'+data[i][k]+'</a></span></td>';
					 }else{
						 html += "<td>"+data[i][k]+"</td>";
					 }
				 }
				 html += "</tr>";
			 }
			 html += "<tbody></table>";
			 jQuery(Id).html(html);
		});
}

