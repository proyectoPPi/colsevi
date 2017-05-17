jQuery(document).ready(function(){
	Tabla();
});

function Tabla(pagina){
	HTabla({
		url: contexto + "/Usuario/Persona/tabla.html?",
		Id: "#tabla",
		titulos: titulos,
		pagina:pagina
	});
}

function CargarFormulario(Id){
	HCargarFormulario(Id);
}

function CrearPersona(){
	HredireccionarVista(contexto + "/Usuario/RegistroPersona");
}

function EditarPersona(){
	HredireccionarVista(contexto + "/Usuario/RegistroPersona.html?editar=" + jQuery('#persona').val());
}