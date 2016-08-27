package com.colsevi.controllers.producto;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.controllers.BaseConfigController;

@Controller
public class PlatoController extends BaseConfigController{

	private static final long serialVersionUID = 8661656967139033340L;

	public ModelAndView Plato(HttpServletRequest request, ModelMap model){
		return new ModelAndView("producto/Plato","col", getValoresGenericos(request));
	}
	
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		
		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		result.writeJSONString(response.getWriter());
	}
}
