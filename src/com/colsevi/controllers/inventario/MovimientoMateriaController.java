package com.colsevi.controllers.inventario;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;

@Controller
public class MovimientoMateriaController extends BaseConfigController{

	private static final long serialVersionUID = 8419735704984053475L;
	private static Logger logger = Logger.getLogger(MovimientoMateriaController.class);

	@RequestMapping("/Inventario/MovimientoMateria")
	public ModelAndView MovimientoMateria(HttpServletRequest request, ModelMap model){
		model.addAttribute("ListaUM", ProductoManager.getTipoPeso());
		model.addAttribute("ListaE", GeneralManager.getEstablecimientos());
		return new ModelAndView("/inventario/MovimientoMateria", "col", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/MovimientoMateria/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject options = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String loteF = request.getParameter("loteF");
		String cantidadF = request.getParameter("cantidadF");
		String unidadMF = request.getParameter("unidadMF");
		String establecimientoF = request.getParameter("establecimientoF");
		String compra = request.getParameter("compra");
		String motivoMov = request.getParameter("motivoMov");
		String fechaMov = request.getParameter("fechaMov");
		
		mapa.put("limite", Inicio + "," + Final);
		
		if(!loteF.trim().isEmpty())
			mapa.put("lote", loteF);
		if(!cantidadF.trim().isEmpty())
			mapa.put("cant", cantidadF);
		if(!unidadMF.trim().equals("0"))
			mapa.put("um", unidadMF);
		if(!establecimientoF.trim().equals("0"))
			mapa.put("esta", establecimientoF);
		if(!compra.trim().isEmpty())
			mapa.put("compra", compra);
		if(!motivoMov.trim().equals("0"))
			mapa.put("motivo", motivoMov);
		if(!fechaMov.trim().isEmpty()){
			Object[] obj = UtilidadManager.FechaInicioFin(UtilidadManager.FechaStringConHora_BD(fechaMov));
			mapa.put("fechaI", obj[0]);
			mapa.put("fechaF", obj[1]);
		}
		
		try{
			options.put("datos", ConstruirJSON(ColseviDao.getInstance().getMovimientoMateriaMapper().SelectDataView(mapa)));
			options.put("total", ColseviDao.getInstance().getMovimientoMateriaMapper().CountDataView(mapa));
		}catch(Exception e){
			logger.error(e.getMessage());
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
				options.put("id_movimiento_materia", map.get("id_movimiento_materia"));
				options.put("lote", map.get("lote"));
				options.put("motivo", map.get("motivo"));
				options.put("fecha_movimiento", map.get("fecha_movimiento") != null ? UtilidadManager.FechaDateConHora_Vista((Date) map.get("fecha_movimiento")) : "");
				options.put("um", map.get("um"));
				options.put("cantidad", map.get("cantidad"));
				options.put("esta", map.get("esta"));
				
				result.add(options);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
		
		return result;
	}

}
