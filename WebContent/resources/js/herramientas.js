var dataMap = {};
var registrosPagina = 10;
/*
 * Método para pintar la tabla
 */
function HTabla(opciones){
	var titulos = opciones.titulos;
	var Id = opciones.Id;
	var id = null;
	dataMap['url'] = opciones.url;
	dataMap['clase'] = opciones.clase;
	dataMap['boton'] = opciones.boton;
	dataMap['color'] = opciones.color;
	
	if(dataMap['clase'] == undefined) dataMap['clase'] = new Array();
	if(dataMap['boton'] == undefined) dataMap['boton'] = new Array();
	if(dataMap['color'] == undefined) dataMap['color'] = new Array();
	
	Setlimite(opciones.pagina);
	
	SetFiltros();
	
	jQuery.ajaxQueue({
		  url: dataMap['url'],
		}).done(function(result) {
			data = jQuery.parseJSON( result );
			var html = ""; 
			if(data == "" || data['datos'][0] == undefined ){
				 jQuery(Id).html("<br/>No hay datos");
				 return;
			 }
			dataMap["datos"] = data["datos"];
			dataMap["titulos"] = titulos;
			dataMap["total"] = data["total"];
			
			html='<table class="display table table-bordered table-striped dataTable"><thead><tr>';
			for(k in titulos){
				var className = '';
				if(titulos[k] != "ID"){
					if(dataMap['clase'][k] != undefined){
						className = 'class="' + dataMap['clase'][k] + '" ';
					}
					html += "<th " + className + ">"+opciones.titulos[k]+"</th>";
				}else{
					dataMap['id'] = k;
				}
			 }
			html += "</tr></thead><tbody>";
		 for(i in data["datos"]){
			 html += "<tr>";
			 var sw = true;
			 for(k in titulos){
				 var className = '';
				 if(titulos[k]=="ID"){
					 id = data["datos"][i][k];
				 }else{
					 if(sw){
						 html += '<td><span><a onclick="CargarFormulario('+id+');" data-toggle="modal" href="#ModalFormulario">'+data["datos"][i][k]+'</a></span></td>';
						 sw = false;
					 }else{
						 if(dataMap['clase'][k] != undefined){
							className = 'class="' + dataMap['clase'][k] + '" ';
						 }
						 
						 if(dataMap['color'] != undefined && dataMap['color'][k] != undefined){
							 html += '<td style="background-color: ' + data["datos"][i][k] +'" ' + className + ">";
						 }else{
							 html += "<td "  + className + ">";
						 }
						 
						 if(data["datos"][i][k] != undefined && data["datos"][i][k]['label'] != undefined){
							 html += data["datos"][i][k]['label'];
						 }else{
							 if(dataMap['color'] != undefined && dataMap['color'][k] == undefined){
								 html += data["datos"][i][k];
							 }
							 
							 if(dataMap['boton'][k] != undefined){
								for(var key in dataMap['boton'][k]){
								var opcion = dataMap['boton'][k][key];
								var metodo = "metodo";
								if(opcion.metodo!=undefined){
									metodo = opcion.metodo;
								}
									html += '<span><a href="#" onclick="'+metodo+'(\''+id+'\');" class="btn btn-xs '+opcion.color+'"><i class="'+opcion.img+'"></i></a></span>';
								}
							}
						 }
						 
						 html += "</td>";
					 }
				 }
			 }
			 html += "</tr>";
		 }
		 html += "<tbody></table>";
		 
		 jQuery(Id).html(html);
		 
		 if(opciones.pagina == undefined){
			 opciones.pagina = 1;
		 }
		 organizarPaginacion(opciones.pagina);
		 
		 dataMap['keys'] = [];
			var registro = dataMap["datos"][0];
			if(registro != undefined){
				for(key in registro){
					dataMap['keys'].push(key);
				}
			}
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
			html += i==pagina ? '<li class="active"><a href="javascript:void(Tabla('+i+'));">'+i+'</a></li>':'<li class="hidden-xs"><a href="javascript:void(Tabla('+i+'));">'+i+'</a></li>';
		}
		if(pagina<NPaginas){
			html += '<li><a href="javascript:void(Tabla('+parseInt(pagina+1)+'));">&rsaquo;</a></li>';
			html += '<li><a href="javascript:void(Tabla('+NPaginas+'));">&raquo;</a></li>';
		}
		jQuery("#paginacion").append('<ul class="dataTables_paginate paging_bootstrap pagination">'+html+'</ul>');
}

function HLimpliar(){
	
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
	jQuery('#' + div).attr('action', url);
	jQuery('#' + div).submit();
}

/*
 @Id id del campo de texto que servirá para desplegar el selector
 @Popup Si es true se muestra en un popup, de lo contrario se mostrará como un Dropdown
 */
function HDatetimePicker(Id, Popup){
	if(Popup == undefined){
		Popup = true;
	}
	
	jQuery('#'+ Id).DateTimePicker({
		language: "es",
		isPopup: Popup
	});
}

function HColorPicker(Id){
	jQuery('#' + Id).colorpickerplus();
}

function HValidador(Id){
	jQuery('#'+Id).validate({
		submitHandler: function(form) {
		    form.submit();
		  }
	});
}