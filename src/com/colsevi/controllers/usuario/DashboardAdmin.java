package com.colsevi.controllers.usuario;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.GraficoManager;
import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/Dashboard/Admin")
public class DashboardAdmin extends BaseConfigController {
	
	private static final long serialVersionUID = 4997906906136000223L;
	private static Logger logger = Logger.getLogger(DashboardAdmin.class);
	
	@RequestMapping
	public ModelAndView Producto(HttpServletRequest request,ModelMap model){
		return new ModelAndView("dashboard/AdminView", "col", getValoresGenericos(request));
	}
	
	@RequestMapping("/CompraXEstablecimientoXDia")
	public void Compra(HttpServletRequest request, HttpServletResponse response) throws IOException{
		ResponseJson(request, response, GraficoManager.CompraXEstablecimientoXDia());
	}

}
