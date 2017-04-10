var xhr = null;
var dataMap = {};
var registrosPagina = 10;
var cantPagina = 16;


function HvalidateXHR() {
	if( xhr !== null ) {
    	xhr.abort();
    	xhr = null;
	}
}

function HredireccionarVista(accion, data) {
	HvalidateXHR();
	
	xhr = $.ajax({
		url : accion,
		type : 'POST',
		data : data,
		success : HMostrarDiv,
		error : HValidarErrorGeneralAjax
	});
}

function HMostrarDiv(response) {
	dataMap = {};
	$("#contenidoHTML").html(response);
}

function HValidarErrorGeneralAjax(jqXHR, textStatus, errorThrown) {
	console.log("Fallo general al cargar la pantalla");
	if (jqXHR.status === '403' || jqXHR.status === '500') {
		HcheckSession();
		if (jqXHR.status === '403') {
			console.log('Access Denied (Permissions)');
		} else {
			console.log('Internal Error Encountered: '+ jqXHR.responseText);
		}
	} else if(jqXHR.status === '900'){
			console.log('******INTENTO ATAQUE CSFR - URL****** OR ******INTENTO ATAQUE XSS******');
			inFormOrLink = true;
			window.location = '/logout/logout.jsp';
	} else if(jqXHR.status === '800'){
		console.log('Se detecto diferencias al comprar los atributos ingresados en step1 con step2');
		inFormOrLink = true;
		window.location = '/logout/logout.jsp';
	}else {
		console.log(jqXHR, 'xhr response');
		console.log('Falló General al cargar MostrarDiv: HValidarErrorGeneralAjax');
	}	
}

function Hformulario(id){
	id === undefined ? id = 'Formulario': '';
	$("#" + id).submit(function(e) {
		var form = $(this);
		e.preventDefault(); 
	
		enviarFormulario(form);
	});
}

function enviarFormulario(form){
	form === undefined ? form = 'Formulario': '';
	form = jQuery('#' + form);
	$.ajax({
        type: "POST",
        url: form.attr('action'),
        data: form.serialize(),
        success: function(respFormulario){
     	   try{
     		   respFormulario = jQuery.parseJSON(respFormulario);
	       			modal = form.attr('data-modal') !== undefined ? form.attr('data-modal') : id;
    				tabla = form.attr('data-tabla') !== undefined ? form.attr('data-tabla') : 'Tabla';
    				resp = form.attr('data-resp');
	       			MostrarMensaje(respFormulario, modal, tabla, resp);
	       		} catch(err){
	       			console.log("Error ejecutando tabla" + err);
	               	return;
	       		}
        }
  });
}

function MostrarMensaje(respFormulario, modal, tabla, resp){
	if(respFormulario["error"]  != undefined && respFormulario["error"] != ""){
   		HMensaje(respFormulario["error"], 'danger');
   	}else if(respFormulario["correcto"]  != undefined && respFormulario["correcto"] != ""){
   		jQuery("#" + modal).modal('hide');
   		HMensaje(respFormulario["correcto"], 'success');
   		eval(tabla + '();');
   	}else if(respFormulario["peligro"]  != undefined && respFormulario["peligro"] != ""){
   		HMensaje(respFormulario["peligro"], 'warning');
   	}else if(respFormulario["info"]  != undefined && respFormulario["info"] != ""){
   		HMensaje(respFormulario["info"], 'info');
   	}else{
   		jQuery("#" + modal).modal('hide');
   		eval(tabla + '(' + respFormulario[resp] + ');');
   	}
}

function HcheckSession() {
	$.get('/app/authentication/check_session', function(data) {
		// inactive session so redirect to login page
		if (data !== '1') {
			inFormOrLink = true;
			window.location = '/logout/logout.jsp';
		}
		// likely does not have perimissions
	});
}

/*
	@RequestMapping(method = RequestMethod.GET, value = "/check_session")
	@ResponseBody
	public String checkSession(Model model, HttpServletRequest request) {
	    HttpSession session = request.getSession(false);
	    if(session.getAttribute("nombreUsuario") == null){
		return "-1";
	    }
	    return "1";
	}
*/

function moveTopScroll(){
    	 window.scrollTo(0,0);
}

$(document).ajaxStart(function(){
	xhr = null;
	jQuery("#ModalCargando").modal('show');
	$(window).trigger('resize');
});

$(document).ajaxStop(function(){
	xhr = null;
	jQuery("#ModalCargando").modal('hide');
	$(window).trigger('resize');
});

$( document ).ajaxError(function( event, jqxhr, settings, thrownError ){
	xhr = null;
	jQuery("#ModalCargando").modal('hide');
	HValidarErrorGeneralAjax(jqxhr, event, thrownError);
	$(window).trigger('resize');
});

function HAjax(opciones){
	jQuery.ajaxQueue({
		url: opciones.url,
		data:opciones.data,
		async:opciones.async,
	}).done(function(result) {
 		try{ 
 			eval(opciones.method + '(' + result + ');');
 		} catch(err){ 
 			console.log("Error ejecutando HAjax" + err); 
 			eval(opciones.method + '();');
 		} 
	});
}


/*
 * Método para pintar la tabla
 */
function HTabla(opciones){
	var titulos = opciones.titulos;
	var Id = opciones.Id;
	var id = null;
	dataMap['url'] = opciones.url;
	dataMap['boton'] = opciones.boton;
	dataMap['color'] = opciones.color;
	dataMap['accion'] = opciones.accion;
	dataMap['campo'] = opciones.campo;
	dataMap['link'] = opciones.link;
	
	if(dataMap['boton'] == undefined) dataMap['boton'] = new Array();
	if(dataMap['color'] == undefined) dataMap['color'] = new Array();
	if(dataMap['accion'] == undefined) dataMap['accion'] = new Array();
	if(dataMap['campo'] == undefined) dataMap['campo'] = new Array();
	
	Setlimite(opciones.pagina);
	SetFiltros();
	
	jQuery.ajaxQueue({
	  url: dataMap['url'],
	}).done(function(result) {
		try{
			data = jQuery.parseJSON(result);
		} catch(err){
			console.log("Error ejecutando tabla" + err);
        	return;
		}
		var html = ""; 
		html = '<section id="flip-scroll">';
		html += '<table class="table table-bordered table-striped cf"><thead class="cf"><tr>';
		var count = 0;
		for(k in titulos){
			count ++;
			var className = '';
			if(titulos[k] != "ID"){
				html += "<th>"+opciones.titulos[k]+"</th>";
			}else{
				dataMap['id'] = k;
			}
		 }
		html += "</tr></thead><tbody>";
		dataMap["total"] = data["total"];
		
		if(data == "" || data['datos'] === undefined || data['datos'][0] == undefined ){
			html += '<tr>';
			html += '<td colspan="'+count+'" style="text-align:center;font-weight: bold;">NO HAY DATOS</td>';
			html += '</tr>';
		 }else{
			dataMap["datos"] = data["datos"];
			dataMap["titulos"] = titulos;

			 for(i in data["datos"]){
				 html += "<tr>";
				 var sw = true;
				 for(k in titulos){
					 if(titulos[k]=="ID"){
						 id = data["datos"][i][k];
					 }else{
						 if(sw){
							 if(dataMap['link'] !== undefined)
								 html += '<td><span>'+data["datos"][i][k]+'</span></td>';
							 else
								 html += '<td><span><a onclick="CargarFormulario('+id+');" data-toggle="modal" href="#ModalFormulario">'+data["datos"][i][k]+'</a></span></td>';
							 sw = false;
						 }else{
							 if(dataMap['color'] != undefined && dataMap['color'][k] != undefined){
								 html += '<td style="background-color: ' + data["datos"][i][k] +'" ' + className + ">";
							 }else{
								 html += "<td>";
							 }		 
							 if(dataMap['campo'] != undefined && dataMap['campo'][k] != undefined){
								 html += '<input type="number" value="'+data['datos'][i][k]+'" class="form-control" id="campo'+id+'" name="cantTbl"/>';
							 }
							 
							 if(data["datos"][i][k] != undefined && data["datos"][i][k]['label'] != undefined){
								 html += data["datos"][i][k]['label'];
							 }else{
								 if(dataMap['color'][k] == undefined && dataMap['campo'][k] == undefined && dataMap['boton'][k] == undefined && dataMap['accion'][k] == undefined){
									 html += data["datos"][i][k];
								 }
								 
								 if(dataMap['boton'][k] != undefined){
									for(var key in dataMap['boton'][k]){
									var opcion = dataMap['boton'][k][key];
									var metodo = "metodo";
									if(opcion.metodo!=undefined){
										metodo = opcion.metodo;
									}
										html += '<a href="#" onclick="'+metodo+'(\''+id+'\', this);"><i class="'+opcion.img+'"></i></a>';
									}
								}
								 if(dataMap['accion'][k] != undefined){
									 html +='<div class="btn-group">';
									 html +='<button data-toggle="dropdown" class="btn btn-white" aria-pressed="false"><i class="fa fa-ellipsis-v"></i></button>';
									 html +='<ul role="menu" class="dropdown-menu">';
									 
									 for(var key in dataMap['accion'][k]){
										var opcion = dataMap['accion'][k][key];
										var metodo = "metodo";
										if(opcion.metodo != undefined){
											metodo = opcion.metodo;
										}
										html +='<li><a href="#" onclick="'+metodo+'(\''+id+'\');" href="#">'+opcion.label+'</a></li>';
									}
								 }
							 }
							 html += "</td>";
						 }
					 }
				 }
				 html += "</tr>";
			 }
			 
			dataMap['keys'] = [];
			var registro = dataMap["datos"][0];
			if(registro != undefined){
				for(key in registro){
					dataMap['keys'].push(key);
				}
			}
		 }
		 html += "<tbody></table></section>";
		 
		 jQuery(Id).html(html);
		 
		 if(opciones.pagina == undefined)
			 opciones.pagina = 1;

		 organizarPaginacion(opciones.pagina);
	});
}

function BuscarRegistro(Id){
	if(dataMap["datos"] != undefined){
		var data = dataMap["datos"];
		var ID = dataMap['id'];
		for(i in data){
			if(data[i][ID] != undefined && data[i][ID] == Id){
				return data[i];
			}
		}
	}
	return null;
}

function HCargarFormulario(Id){
	if(dataMap['URLInicial'] !== undefined)
		jQuery('#Formulario').attr('action', dataMap['URLInicial']);

	var Fila = BuscarRegistro(Id);
	if(Fila != null){
		for(key in dataMap['keys']){
			var valor = dataMap['keys'][key];
			if(Fila[valor] != null ){
				if(Fila[valor]['value'] != undefined){
					jQuery("#" + valor).val(Fila[valor]['value']);
				}else{
					jQuery("#" + valor).val(Fila[valor]);
				}
			}else{
				jQuery("#" + valor).val("");
			}
		}
	}
}

function Setlimite(pagina){
	if(pagina==undefined) pagina = 1;
	
	dataMap['Inicio'] = (pagina-1)*registrosPagina;
	dataMap['Final'] = registrosPagina;
	
	dataMap['url'] += "&Inicio=" + dataMap['Inicio'];
	dataMap['url'] += "&Final=" + dataMap['Final'];
}

function SetFiltros(){
	var filtro = "&";
	jQuery("[name*='filtro']").each(function() {
		filtro += $(this).attr("id") +"="+ $(this).val() + "&";
	});
	dataMap['url'] += filtro;
}

function organizarPaginacion(pagina){
	jQuery("#paginacion").html('');
	var n = dataMap["total"];
	var NPaginas = Math.ceil(n/registrosPagina);
	if(NPaginas == 0){
		NPaginas = 1;
	}
	
	var Inicio = (pagina-4) <= 0 ? 1 : pagina - 4;
	var Final = (pagina+4) >= NPaginas ? NPaginas : pagina + 4;
	
	var html = '';
	if(pagina>1){
		html += '<li><a href="javascript:void(Tabla(1));">&laquo;</a></li>';
		html += '<li><a href="javascript:void(Tabla('+parseInt(pagina-1)+'));">&lsaquo;</a></li>';
	}
	for(var i=Inicio; i<=Final; i++){
		html += i==pagina ? '<li class="active"><a href="javascript:void(Tabla('+i+'));">'+i+'</a></li>':
			'<li class="hidden-xs"><a href="javascript:void(Tabla('+i+'));">'+i+'</a></li>';
	}
	if(pagina<NPaginas){
		html += '<li><a href="javascript:void(Tabla('+parseInt(pagina+1)+'));">&rsaquo;</a></li>';
		html += '<li><a href="javascript:void(Tabla('+NPaginas+'));">&raquo;</a></li>';
	}
	jQuery("#paginacion").append('<ul class="dataTables_paginate paging_bootstrap pagination">'+html+'</ul>');
}

function HLimpiar(){
	if(dataMap['URLInicial'] !== undefined)
		jQuery('#Formulario').attr('action', dataMap['URLInicial']);

	for(key in dataMap['keys']){
		var valor = dataMap['keys'][key];
		if(valor['value'] != undefined){
			jQuery("#" + valor).val('0');
		}else{
			jQuery("#" + valor).val('');
		}
	}
}

function HEliminar(div, url){
	dataMap['URLInicial'] = jQuery('#' + div).attr('action');
	jQuery('#' + div).attr('action', url);
	jQuery('#' + div).submit();
}

function HiniciarAutocompletar(url,input){

	var value = "campo="+jQuery("#"+input).val();
	dataMap['AutocompletarUrl'] = url;
	
	jQuery.ajaxQueue({
		url: dataMap['AutocompletarUrl'] + value,
	}).done(function(result) {
		var data;
		try{
			data = jQuery.parseJSON(result);
		} catch(err){
			console.log("Error ejecutando HiniciarAutocompletar" + err);
        	return;
		}
		dataMap['autocompletar'] = data['labels'];
		
		AuxiliarAutocompletar(input);
		
		jQuery( "input[id="+ input +"]" ).keyup(function(e) {
			if((e.which<37 || e.which>40) && e.which!=13){
				ActualizarAutocompletar(this);
			}
		});
	});
}

function AuxiliarAutocompletar(input){
	jQuery( "input[id="+ input +"]" ).autocomplete({
		source: dataMap['autocompletar'],
		minLength: 0,
		open: function( event, ui ) {}
	});

	jQuery( "input[id="+ input +"]" ).focus(function() {
		$(this).autocomplete("search");
	});
}

function ActualizarAutocompletar(input){
	jQuery.ajaxQueue({
	  	url: dataMap['AutocompletarUrl'],
	  	data: {campo: input.value},
	  	success: function(o) {
			var data;
			try{
				data = jQuery.parseJSON(o);
			} catch(err){
				console.log("Error ejecutando ajaxQueue en ActualizarAutocompletar " + err);
	        	return;
			}
			if(data.labels==undefined) data.labels = [];
			jQuery(input).autocomplete( "option", "source", data.labels );
			jQuery(input).autocomplete("search", "");
	  	}
	});
}

function HPreprocesar(opcion){
	jQuery("#errorDivF").hide();
	jQuery.ajax({
		url: opcion.url + jQuery("#" + opcion.formulario).serialize(),
		async:false,
	}).done(function(result) {
		var data; 
 		try{ 
 			data = jQuery.parseJSON(result); 
 		} catch(err){ 
 			console.log("Error" + err); 
         	return; 
 		} 
 		
 		if(data['error'] != undefined){
 			jQuery("#mensajeEr").html(data['error']);
	    	jQuery("#errorDivF").show();
 		}else{
 			jQuery("#" + opcion.formulario).submit();
 		}
	});
}

function HValidador(Id){
	jQuery('#'+Id).validate({
		submitHandler: function(form) {
		    form.submit();
		  }
	});
}

/*
@Id id del campo de texto que servirá para desplegar el selector
@format Formato de la fecha
Se utiliza la acción focus para cuando se ingrese al campo
$(this) se utiliza para obtener el contexto del campo
*/
function HDatetimePicker(Id, format){
	jQuery( "input[id="+ Id +"]" ).focus(function(e) {
		$(this).datetimepicker({
			format: format
		});
	});
}

function HColorPicker(Id){
	jQuery('#' + Id).colorpickerplus();
}

function HTipoPeso(value, opt){
	if(opt !== undefined){
		return HTipoPesoVal(value, opt);
	}else{
		var html = '<option value="0" selected>Seleccione</option>';
		
		if(value === 1){
			html += '<option value="1">Kilogramos</option>';
			html += '<option value="2">Libras</option>';
			html += '<option value="3">Gramos</option>';
		}else{
			html += '<option value="4" selected>Litros</option>';
		}
		return html;
	}
}

function HTipoPesoVal(value, opt){
	var html = '<option value="0" selected>Seleccione</option>';
	
	if(value === 1){
		
		html += '<option value="1" ';
		if(opt === 1)
			html += 'selected';
		html += '>Kilogramos</option>';
		html += '<option value="2" ';
		if(opt === 2)
			html += 'selected';
		html += '>Libras</option>';
		html += '<option value="3" ';
		if(opt === 3)
			html += 'selected';
		html +='>Gramos</option>';
	}else{
		html += '<option value="4" selected>Litros</option>';
	}
	return html;
}

function HMensaje(mensaje, tipo){
	$.notify({
		message: mensaje
	},{
		type: tipo,
		delay: 5000,
		offset : {
			y: 150,
			x: 20
		}
	});
}

function HGrafico(opciones){
	var data = undefined;
	jQuery.ajaxQueue({
		url: opciones.url,
	}).done(function(result) {
 		try{ 
 			data = jQuery.parseJSON(result);
 			var ctx = document.getElementById(opciones.id);
 			var scatterChart = new Chart(ctx, {
 			    type: opciones.tipo,
 			    data: data,
 			    options: opciones.opt
 			});
 		} catch(err){ 
 			console.log("Error ejecutando HGrafico" + err); 
 		} 
	});
}
