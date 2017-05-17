jQuery(document).ready(function(){
	Tabla();
	$('#Formulario').validate({
		rules: {
			cantMov: {
				required : true,
				min: 1,
				number: true
			},
			unidadMov: {
				required: true
			},
			estaMov:{
				required: true
			}
		}  
     });
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Inventario/MateriaPrima/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function validarFormulario(){
	if(jQuery('#Formulario').valid())
		enviarFormulario();
}

jQuery('#adicion').click(function(){
	jQuery('#count').val(parseInt(jQuery('#count').val()) + 1);
	construirTbl();
});
function inicialTabla(){
	jQuery('#count').val(1);
	construirTbl();
}

function construirTbl(data){
	var medida = data !== undefined && data['medida'] !== undefined ? data['medida'] : ''; 
	var lote = data !== undefined && data['lote'] !== undefined ? data['lote'] : ''; 
	var count = jQuery('#count').val();
	var html ='<tr>';
	html += '<td>';
		html += '<input type="text" class="form-control" id="ingredienteText" sec="idIng' + count + '"/>';
		html += '<input type="hidden" name="idIng' + count + '" id="idIng' + count + '" />';
	html += '</td>';
	html += '<td>';
		html += '<input type="text" class="form-control" name="cant' + count + '" />';
		html += '<input type="hidden" name="lote' + count + '" id="lote' + count + '" value="' + lote + '" />';
	html += '</td>';
	html += '<td>';
		html += '<select name="tipo' + count + '" class="form-control">' + medida + '</select>';
	html += '</td>';
	html += '<td>';
		html += '<input type="text" id="fecha" name="fecha' + count + '" class="form-control"/>';
	html += '</td>';
	html += '<td>';
		html += '<a href="#" onclick="EliminarDet(this);"><i class="fa fa-times-circle fa-2x"></i></a>';
	html += '</td>';
	html += '</tr>';
	
	jQuery('#IngDynamic > section > table > tbody').append(html);
	
	if(data !== undefined){
		data['nombreIng'];
		jQuery( "input[sec*='idIng" + count+ "']" ).val(data['nombreIng']);
		jQuery( "input[name*='idIng" + count+ "']" ).val(data['id_ingrediente']);
		jQuery( "input[name*='cant" + count+ "']" ).val(data['cantidad']);
		jQuery( "input[name*='lote" + count+ "']" ).val(data['lote']);
		jQuery( "select[name*='tipo" + count+ "']" ).val(data['id_tipo_peso']);
		jQuery( "input[name*='fecha" + count+ "']" ).val(data['fecha_vencimiento']);
		jQuery( "input[name*='vunitario" + count+ "']" ).val(data['vunitario']);
	}
	
	HiniciarAutocompletar(contexto + '/Proveedor/Compra/autocompletar.html?', 'ingredienteText');
	HDatetimePicker('fecha','YYYY-MM-DD');
	
	jQuery( "input[id=ingredienteText]" ).autocomplete({
		  select: function(e, ui) {
		  this.value = ui.item.value;
		  jQuery('#' + this.getAttribute('sec')).val(ui.item.id_ingrediente);
		  var dit = this;
		  
		  jQuery.ajaxQueue({
				url: contexto + "/Proveedor/Compra/MedidaDetalle.html?",
				 data:{medida: ui.item.id_unidad_medida},
			}).done(function(result) {
				var data; 
		 		try{ 
		 			data = jQuery.parseJSON(result); 
		 		} catch(err){ 
		         	return; 
		 		} 
		 		var html = '<option value"0">Seleccione</option>';
		 		for(i in data){
		 			html += '<option value="' + data[i]['id'] + '" >' + data[i]['nombre'] + '</option>';
		 		}
		 		dit.parentNode.parentNode.childNodes[2].querySelector('select').innerHTML = html;
			});
		}
	});
}

function EliminarDet(option){
	option.parentNode.parentNode.remove();
}

