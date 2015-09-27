package com.colsevi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("bienvenido")
	public ModelAndView hola(){
		ModelAndView model = new ModelAndView("index");
		model.addObject("prueba","holaaaa");
		return model;
	}
	
}
