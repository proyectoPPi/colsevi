package com.colsevi.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.Http11AprProcessor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.dao.general.model.GeneralLocal;
import com.colsevi.dao.general.model.GeneralLocalExample;

@Controller
public class HelloController {

	@RequestMapping("administrador")
	public ModelAndView administrador(){
		ModelAndView model = new ModelAndView("Administrador");
		return model;
	}
	
	@RequestMapping("profile")
	public ModelAndView profile(){
		ModelAndView model = new ModelAndView("Profile");
		return model;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("tabla")
	public void tabla(HttpServletRequest reqyest, HttpServletResponse response) throws IOException{
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		List<GeneralLocal> listgeneral = ColseviDao.getInstance().getGeneralLocalMapper().selectByExample(new GeneralLocalExample());
		if(listgeneral != null && listgeneral.size() >0){
			for (GeneralLocal bean : listgeneral) {
				opciones = new JSONObject();
				opciones.put("pedido", bean.getDescripcion());
				opciones.put("nombre", bean.getNombre());
				opciones.put("cliente", "<span class=\"label label-success label-mini\">Nuevo</span>");
				resultado.add(opciones);
			}
			
		}
		resultado.writeJSONString(response.getWriter());
	}
}
