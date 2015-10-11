package com.colsevi.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.http11.Http11AprProcessor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

	@SuppressWarnings("unchecked")
	@RequestMapping("tabla")
	public void tabla(HttpServletRequest reqyest, HttpServletResponse response) throws IOException{
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		opciones.put("pedido", "123");
		opciones.put("nombre", "123");
		opciones.put("cliente", "123");
		resultado.add(opciones);
		
		opciones.put("pedido", "123");
		opciones.put("nombre", "123");
		opciones.put("cliente", "123");
		resultado.add(opciones);
		
		opciones.put("pedido", "123");
		opciones.put("nombre", "123");
		opciones.put("cliente", "123");
		resultado.add(opciones);
		
		opciones.put("pedido", "123");
		opciones.put("nombre", "123");
		opciones.put("cliente", "123");
		resultado.add(opciones);
		resultado.writeJSONString(response.getWriter());
	}
	
}
