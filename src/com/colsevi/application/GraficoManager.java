package com.colsevi.application;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.colsevi.dao.general.model.Establecimiento;

public class GraficoManager {

	public static void armarGrafico(){
		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject CompraXEstablecimientoXDia(){
		JSONObject result = new JSONObject(), group = new JSONObject();
		JSONArray labels = new JSONArray(), dataset = new JSONArray(), data = new JSONArray(), color = new JSONArray();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		mapa.put("fecha", UtilidadManager.FechaDateConHora_Vista(new Date()) + "%");
		List<Map<String, Object>> listaM = ColseviDao.getInstance().getCompraProveedorMapper().compraEstablecimientoDiario(mapa);
		for(Map<String, Object> map: listaM){
			color = new JSONArray();
			data = new JSONArray();
			group = new JSONObject();
			
			BigDecimal total = new BigDecimal(map.get("total").toString());
			data.add(total.intValue());
			color.add("rgba(255, 99, 132, 0.2)");
		
			group.put("data", data);
			group.put("label", map.get("nombre"));
			group.put("backgroundColor", color);
			group.put("borderWidth", 1);
			
			dataset.add(group);
			
		}

		labels.add(UtilidadManager.FechaDateConHora_Vista(new Date()));
			
		result.put("datasets", dataset);
		result.put("labels", labels);
		
		return result;
	}

}