package com.colsevi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("login")
	public ModelAndView login(){
		ModelAndView model = new ModelAndView("login");
		return model;
	}
	
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

	
}
