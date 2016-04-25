package com.colsevi.controllers.inventario;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;

@Controller
public class MateriaPrimaController extends BaseConfigController{

	private static final long serialVersionUID = 55977124238424730L;

	@RequestMapping("/Inventario/MateriaPrima")
	public ModelAndView MateriaPrima(HttpServletRequest request, ModelMap model){
		return new ModelAndView("/inventario/MateriaPrima", "col", getValoresGenericos(request));
	}
	
	@RequestMapping("/Inventario/MateriaPrima/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject options = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		mapa.put("limite", Inicio + "," + Final);
		
		options.put("datos", ConstruirJSON(ColseviDao.getInstance().getMateriaPrimaMapper().SelectDataView(mapa)));
		options.put("total", 0);
		
		options.writeJSONString(response.getWriter());
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJSON(List<Map<String, Object>> ListaMP){
		JSONArray result = new JSONArray();
		JSONObject options = new JSONObject();
		
		for(Map<String, Object> map: ListaMP){
			try{
				options = new JSONObject();
				options.put("lote", map.get("lote"));
				options.put("loteid", map.get("lote"));
				options.put("cantidad", map.get("cantidad"));
				options.put("fecha_vencimiento", map.get("fecha_vencimiento") != null ? UtilidadManager.FormatoFechaVistaO(map.get("fecha_vencimiento")) : "");
				options.put("nombreIng", map.get("nombreIng"));
				options.put("nombreEsta", map.get("nombreEsta"));
				options.put("nombreUp", map.get("nombreUp"));
				
				result.add(options);
			}catch(Exception e){
				continue;
			}
		}
		
		return result;
	}
}
