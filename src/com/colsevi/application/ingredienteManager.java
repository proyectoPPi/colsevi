package com.colsevi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ingredienteManager {
	private static Logger logger = Logger.getLogger(ingredienteManager.class);
	
	@SuppressWarnings("unchecked")
	public static JSONObject AutocompletarIngrediente(String ingrediente){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			if(!ingrediente.isEmpty()){
				mapa.put("ingrediente", "%" + ingrediente + "%");
			}
			result.put("labels", JsonAutocompletar(ColseviDao.getInstance().getIngredienteMapper().SelectAutocomplete(mapa)));
			
			return result;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray JsonAutocompletar(List<Map<String, Object>> listaCliente){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaCliente){
			opciones = new JSONObject();
			try{
				opciones.put("label", map.get("nombreIng"));
				opciones.put("value", map.get("nombreIng"));
				opciones.put("id_ingrediente", map.get("id_ingrediente"));
				opciones.put("id_unidad_medida", map.get("id_unidad_medida"));
				opciones.put("descripIng", map.get("descripIng"));
				opciones.put("id_clasificar_ingrediente", map.get("id_clasificar_ingrediente"));
				opciones.put("nombreClas", map.get("nombreClas"));
				opciones.put("descripClas", map.get("descripClas"));
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
			
			resultado.add(opciones);
		}
		return resultado;
	}

}
