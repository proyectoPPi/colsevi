package com.colsevi.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController extends BaseConfigController{

	private static final long serialVersionUID = -9194726703842278303L;

	@RequestMapping("/")
	public ModelAndView profile(HttpServletRequest request){
		ModelAndView model = null;
		
		if(getUsuario(request) != null && getUsuario(request).getPersona() != null){
			model = new ModelAndView("producto/ProductoAdmin");
		}else{
			return new ModelAndView("redirect:/login.html");
		}
		
		return model;
	}


}
