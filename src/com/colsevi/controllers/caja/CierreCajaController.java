package com.colsevi.controllers.caja;

import java.io.IOException;
import java.util.List;

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
import com.colsevi.dao.caja.model.CierreCaja;
import com.colsevi.dao.caja.model.CierreCajaExample;
import com.colsevi.dao.usuario.model.Persona;

@Controller
public class CierreCajaController extends BaseConfigController{

	private static final long serialVersionUID = 2407344590292431117L;

	@RequestMapping("/Caja/CierreCaja")
	public ModelAndView cierre(HttpServletRequest request, ModelMap model){
		return new ModelAndView("caja/Cierre", "co", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Caja/CierreCaja/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		
		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");

			result.put("datos", ConstruirJson(ColseviDao.getInstance().getCierreCajaMapper().selectByExample(new CierreCajaExample())));
			result.put("total", 0);
		
		}catch(Exception e){
			
		}
		result.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<CierreCaja> listaCierre){
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(CierreCaja bean: listaCierre){
			try{
				opciones = new JSONObject();
				opciones.put("id_cierre_caja", bean.getId_cierre_caja());
				opciones.put("id_persona", bean.getId_persona());
				Persona per = ColseviDao.getInstance().getPersonaMapper().selectByPrimaryKey(bean.getId_persona());
				opciones.put("nombre", per.getNombre() + " " + per.getApellido());
				opciones.put("mensaje", bean.getMensaje());
				opciones.put("fecha_ejecucion", UtilidadManager.FormatDateDB(bean.getFecha_ejecucion()));
				opciones.put("fecha_cierre", UtilidadManager.FormatDateDB(bean.getFecha_cierre()));
				
				resultado.add(opciones);
			}catch(Exception e){
				continue;
			}
		}
		
		return resultado;
	}
}