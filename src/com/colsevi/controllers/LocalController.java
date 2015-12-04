package com.colsevi.controllers;

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
import com.colsevi.dao.general.model.GeneralLocal;
import com.colsevi.dao.general.model.GeneralLocalExample;

@Controller
public class LocalController {
	
	@RequestMapping("/local")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		return new ModelAndView("Local");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/local/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		GeneralLocalExample LocalExample = new GeneralLocalExample();
		LocalExample.setLimit(Inicio + ", " + Final);
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getGeneralLocalMapper().selectByExample(LocalExample)));
		opciones.put("total", ColseviDao.getInstance().getGeneralLocalMapper().countByExample(new GeneralLocalExample()));

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<GeneralLocal> listgeneral){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listgeneral != null && listgeneral.size() >0){
			for (GeneralLocal bean : listgeneral) {
				opciones = new JSONObject();
				opciones.put("id_local", bean.getId_local());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());								
				resultado.add(opciones);
			}
			
		}
		return resultado;
	}
	
	@RequestMapping("/local/GuardarLocal")
	public ModelAndView GuardarLocal(HttpServletRequest request, ModelMap modelo, GeneralLocal bean){
		
		if(bean.getId_local() != null){
			ColseviDao.getInstance().getGeneralLocalMapper().updateByPrimaryKey(bean);
		}else{
			ColseviDao.getInstance().getGeneralLocalMapper().insert(bean);
			modelo.addAttribute("correcto", "OK");
		}
		
		return administrador(request, modelo);
	}
}
