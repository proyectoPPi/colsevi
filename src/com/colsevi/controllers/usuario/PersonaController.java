package com.colsevi.controllers.usuario;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.application.ColseviDao;
import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/Usuario/Persona")
public class PersonaController extends BaseConfigController{

	private static final long serialVersionUID = 1731727865245215951L;
	private static Logger logger = Logger.getLogger(PersonaController.class);

	@RequestMapping
	public String Persona(HttpServletRequest request, ModelMap model){
		return "usuario/Persona";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		
		try{
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getPersonaMapper().ListaPersona(mapa)));
//			opciones.put("total", ColseviDao.getInstance().getTipoProductoMapper().countByExample(tipoExample));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String, Object>> listaPersona){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
	
		for (Map<String, Object> map:listaPersona) {
			opciones = new JSONObject();
			try{
				opciones.put("persona", map.get("id_persona"));
				opciones.put("nombre", map.get("nombre"));
				opciones.put("apellido", map.get("apellido"));								
				resultado.add(opciones);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
			
		return resultado;
	}

	
}
