jQuery(document).ready(function(){
	Tabla();
	
	$('#Formulario').validate({
		rules: {
			establecimiento : {
				required : true
			},
			valor_inicial: {
				minlength: 1
			}
		}  
     });
	
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Caja/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina: pagina,
		metodo: 'DeterminarFlujo',
		modal: 'FlujoCaja'
	});
}

function Limpiar(){
	HLimpiar();
}

function validarFormulario(){
	if($('#Formulario').valid())
		enviarFormulario();
}

function DeterminarFlujo(Id){
	jQuery('#id_caja').val(Id);
}

function ComenzarEjecucion(){
	jQuery('#visorFLujo').html('');
	HAjax({
		url: contexto + "/Caja/Ejecutar.html?",
		data: {caja: jQuery('#id_caja').val()},
		async: true,
		method: 'ResultadoEjecucion'
	});
}

function ResultadoEjecucion(data){
	jQuery('#visorFLujo').html('');
	MostrarMensaje(data, 'FlujoCaja', undefined,'', 'F');
	
	HAjax({
		url: contexto + "/Caja/DetalleEjecucion.html?",
		data: {caja: jQuery('#id_caja').val()},
		async: true,
		method: 'DetalleEjecucion'
	});
}

function DetalleEjecucion(data){
	var compras = data['Compras'];
	
	var html = '<table class="table table-bordered"><thead>';
	html += "<th>Cantidad</th>";
	html += "<th>Total</th>";
	html += "</thead><tbody>";
	for(i in compras){
		html += "<tr>";
		html += '<td colspan="2" style="text-align:center;font-weight:bold;">' + compras[i]['Tipo'] +'</td>';
		html += "</tr>";
		html += "<tr>";
		html += '<td>' + compras[i]['cantidad'] +'</td>';
		if(compras[i]['valor'] !== undefined)
			html += '<td>$ ' + new Intl.NumberFormat("es-CO").format(compras[i]['valor']) +'</td>';
		else
			html += '<td>$ 00</td>';
		html += "</tr>";
	}
	
	html += '</tbody></table>';
	jQuery('#visorFLujo').append(html);
	
	compras = data['materiaPrima'];
	
	if(compras !== undefined){
	
		html = '<table class="table table-bordered"><thead>';
		html += "<th>Lote</th>";
		html += "<th>Días para vencer</th>";
		html += "<th>cantidad</th>";
		html += "<th>Ingrediente</th>";
		html += "<th>Medida</th>";
		html += "</thead><tbody>";
		
		for(i in compras){
			
			// vence, lote, cantidad, I.nombre AS Ingrediente, UP.nombre AS medida
			if(compras[i]['lote'] !== undefined){
	//			html += "<tr>";
	//			html += '<td colspan="2" style="text-align:center;font-weight:bold;">' + compras[i]['Tipo'] +'</td>';
	//			html += "</tr>";
				html += "<tr>";
				html += '<td>' + compras[i]['lote'] +'</td>';
				html += '<td>' + compras[i]['vence'] +'</td>';
				html += '<td>' + compras[i]['cantidad'] +'</td>';
				html += '<td>' + compras[i]['Ingrediente'] +'</td>';
				html += '<td>' + compras[i]['medida'] +'</td>';
			}
		}
		html += '</tbody></table>';
	}
	
	
	jQuery('#visorFLujo').append(html);
	
	
}


