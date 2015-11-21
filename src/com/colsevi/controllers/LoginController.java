package com.colsevi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request, ModelMap model){
		return new ModelAndView("login", model);
	}
	
	@RequestMapping("login/Ingresar")
	public ModelAndView Ingresar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		
		String errores = validar(usuario, clave);
		if(!errores.isEmpty()){
			model.addAttribute("error", errores);
			return login(request, model);
		}
		
		
		
		return login(request, model);
	}
	
	public String validar(String usuario, String clave){
		String error = "";
		if(usuario != null && !usuario.trim().isEmpty()){
			error = "Ingrese el usuario<br/>";
		}
		if(clave != null && !clave.trim().isEmpty()){
			error += "Ingrese la clave";
		}
		
		return error;
	}
	
}
