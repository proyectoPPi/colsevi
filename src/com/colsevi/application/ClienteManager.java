package com.colsevi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ClienteManager {

	@SuppressWarnings("unchecked")
	public static JSONObject AutocompletarCliente(String cliente){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("cliente", "%" + cliente + "%");
			result.put("labels", JsonAutocompletar(ColseviDao.getInstance().getUsuarioMapper().SelectAutocomplete(mapa)));
			
			return result;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray JsonAutocompletar(List<Map<String, Object>> listaCliente){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaCliente){
			opciones = new JSONObject();

			opciones.put("value", map.get("documento"));
			opciones.put("label", map.get("documento") + " -- " + map.get("nombre").toString() + " " + map.get("apellido"));
			opciones.put("nombreC",map.get("nombre").toString() + " " + map.get("apellido"));
			opciones.put("id_persona", map.get("id_persona"));
			opciones.put("documento", map.get("documento"));
			
			resultado.add(opciones);
		}
		return resultado;
	}

}
