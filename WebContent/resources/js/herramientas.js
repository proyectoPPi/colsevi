/*
 * Método para pintar la tabla
 */
function Tabla(url,Id, titulos){
	jQuery.ajaxQueue({
		  url: url,
		}).done(function(result) {
			var data = jQuery.parseJSON( result );
			var html = ""; 
			if(data == ""){
				 jQuery(Id).html("No hay datos");
				 return;
			 }
			
			html='<table class="display table table-bordered table-striped dataTable"><thead><tr>';
			for(k in titulos){
				 html += "<th>"+titulos[k]+"</th>";
			 }
			html += "</tr></thead><tbody>";
			 for(i in data){
				 html += "<tr>";
				 for(k in titulos){
					 if(k=="Id"){
						 html += "<td><span onclick='prueba("+data[i][k]+");'>"+data[i][k]+"</span></td>";
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

