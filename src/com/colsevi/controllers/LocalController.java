package com.colsevi.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.dao.general.model.GeneralLocal;
import com.colsevi.dao.general.model.GeneralLocalExample;

@Controller
public class LocalController {
	
	@RequestMapping("local")
	public ModelAndView administrador(){
		ModelAndView model = new ModelAndView("Local");
		return model;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("local/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		List<GeneralLocal> listgeneral = ColseviDao.getInstance().getGeneralLocalMapper().selectByExample(new GeneralLocalExample());
		if(listgeneral != null && listgeneral.size() >0){
			for (GeneralLocal bean : listgeneral) {
				opciones = new JSONObject();
				opciones.put("ID", bean.getId_local());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());								
				resultado.add(opciones);
			}
			
		}
		resultado.writeJSONString(response.getWriter());
	}

}
