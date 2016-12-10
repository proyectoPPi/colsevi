package com.colsevi.application;

import java.math.BigDecimal;
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
		List<Establecimiento> listaE = GeneralManager.getEstablecimientos();
		
		for(Establecimiento bean: listaE){
			group = new JSONObject();
			mapa.put("estab", bean.getId_establecimiento());
			List<Map<String, Object>> listaM = ColseviDao.getInstance().getCompraProveedorMapper().compraEstablecimientoDiario(mapa);
			for(Map<String, Object> map: listaM){
//				group = new JSONObject();
				
				labels.add(map.get("fechaDia").toString());
				BigDecimal total = new BigDecimal(map.get("total").toString());
				data.add(total.intValue());
				color.add("rgba(255, 99, 132, 0.2)");
			}
			if(listaM.size() < 1){
				data.add(0);
				color.add("rgba(255, 99, 132, 0.2)");
			}
			group.put("data", data);
			group.put("label", bean.getNombre());
			group.put("backgroundColor", color);
			group.put("borderWidth", 1);
			
			dataset.add(group);
			
			color = new JSONArray();
			data = new JSONArray();
		}
		
		result.put("datasets", dataset);
		result.put("labels", labels);
		
		return result;
	}
}
