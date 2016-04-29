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
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.EstablecimientoExample;
import com.colsevi.dao.general.model.UnidadPeso;
import com.colsevi.dao.general.model.UnidadPesoExample;

@Controller
public class MateriaPrimaController extends BaseConfigController{

	private static final long serialVersionUID = 55977124238424730L;

	@RequestMapping("/Inventario/MateriaPrima")
	public ModelAndView MateriaPrima(HttpServletRequest request, ModelMap model){
		model.addAttribute("ListaUM", ListaUM());
		model.addAttribute("ListaE", ListaE());
		return new ModelAndView("/inventario/MateriaPrima", "col", getValoresGenericos(request));
	}
	
	public static List<UnidadPeso> ListaUM(){
		return ColseviDao.getInstance().getUnidadPesoMapper().selectByExample(new UnidadPesoExample());
	}
	
	public static List<Establecimiento> ListaE(){
		return ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(new EstablecimientoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/MateriaPrima/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject options = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String loteF = request.getParameter("loteF");
		String cantidadF = request.getParameter("cantidadF");
		String unidadMF = request.getParameter("unidadMF");
		String establecimientoF = request.getParameter("establecimientoF");
		mapa.put("limite", Inicio + "," + Final);
		
		if(loteF != null && !loteF.trim().isEmpty()){
			mapa.put("lote", loteF);
		}
		if(cantidadF != null && !cantidadF.trim().isEmpty()){
			mapa.put("cant", cantidadF);
		}
		if(unidadMF != null && !unidadMF.trim().isEmpty() && !unidadMF.trim().equals("0")){
			mapa.put("um", unidadMF);
		}
		if(establecimientoF != null && !establecimientoF.trim().isEmpty() && !establecimientoF.trim().equals("0")){
			mapa.put("esta", establecimientoF);
		}
		
		try{
			options.put("datos", ConstruirJSON(ColseviDao.getInstance().getMateriaPrimaMapper().SelectDataView(mapa)));
			options.put("total", ColseviDao.getInstance().getMateriaPrimaMapper().CountDataView(mapa));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
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
