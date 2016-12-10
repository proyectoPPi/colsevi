package com.colsevi.application;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.colsevi.dao.general.model.Establecimiento;

public class GraficoManager {

	public static Boolean buscarId(List<Map<String, Object>> listaM, String fecha){
		for(Map<String, Object> map: listaM){
			if(map.get("fechaDia").toString().equals(fecha)){
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject CompraXEstablecimientoXDia(){
		JSONObject result = new JSONObject(), group = new JSONObject();
		JSONArray labels = new JSONArray(), dataset = new JSONArray(), data = new JSONArray(), color = new JSONArray();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Establecimiento> listaE = GeneralManager.getEstablecimientos();
		Date day = new Date();
		
		for(Establecimiento bean : listaE){
			color = new JSONArray();
			data = new JSONArray();
			group = new JSONObject();
			
			mapa.put("estab", bean.getId_establecimiento());
			List<Map<String, Object>> listaM = ColseviDao.getInstance().getCompraProveedorMapper().compraEstablecimientoDiario(mapa);
			
			for(int i = 0; listaM.size() < 6; i++){
					String fecha = UtilidadManager.FechaDateConHora_Vista(day);
					if(i != 0)
						fecha = UtilidadManager.FechaDateConHora_Vista(days(day, i));
					if(!buscarId(listaM, fecha)){
						Map<String, Object> M = new HashMap<String, Object>();
						M.put("total", 0);
						M.put("fechaDia", fecha);
						listaM.add(M);
					}
			}

			 int j;
			 Map<String, Object> mapaOrdernar = new HashMap<String, Object>();
	         for(int i=0;i<6;i++)
	              for(j= i+1;j<6;j++){
	            	  
	                   if(UtilidadManager.FechaStringConHora_BD(listaM.get(i).get("fechaDia").toString()).getTime() < 
	                		   UtilidadManager.FechaStringConHora_BD(listaM.get(j).get("fechaDia").toString()).getTime()){
	                	   mapaOrdernar.put("total", listaM.get(i).get("total"));
	                	   mapaOrdernar.put("fechaDia", listaM.get(i).get("fechaDia"));
	                	   listaM.get(i).put("total", listaM.get(j).get("total"));
	                	   listaM.get(i).put("fechaDia", listaM.get(j).get("fechaDia"));
	                	   listaM.get(j).put("total", mapaOrdernar.get("total"));
	                	   listaM.get(j).put("fechaDia", mapaOrdernar.get("fechaDia"));
	                   }
	              }
	for(int i = 0; i < 6; i++){
				BigDecimal total = new BigDecimal(listaM.get(i).get("total").toString());
				data.add(total.intValue());
				color.add("rgba(255, 99, 132, 0.2)");				
			}
			group.put("data", data);
			group.put("label", bean.getNombre());
			group.put("backgroundColor", color);
			group.put("borderWidth", 1);
			dataset.add(group);
			
		}

		labels.add(UtilidadManager.FechaDateConHora_Vista(day));
		labels.add(UtilidadManager.FechaDateConHora_Vista(days(day, 1)));
		labels.add(UtilidadManager.FechaDateConHora_Vista(days(day, 2)));
		labels.add(UtilidadManager.FechaDateConHora_Vista(days(day, 3)));
		labels.add(UtilidadManager.FechaDateConHora_Vista(days(day, 4)));
		labels.add(UtilidadManager.FechaDateConHora_Vista(days(day, 5)));
		
		result.put("datasets", dataset);
		result.put("labels", labels);
		
		return result;
	}
	
	

	public static Date days(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days); //minus number would decrement the days
        return cal.getTime();
    }
	
}