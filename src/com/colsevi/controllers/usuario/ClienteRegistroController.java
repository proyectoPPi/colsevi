package com.colsevi.controllers.usuario;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.controllers.BaseConfigController;

@Controller
public class ClienteRegistroController extends BaseConfigController{

	private static final long serialVersionUID = 5049120499336624560L;

	@RequestMapping("/Usuario/ClienteRegistro")
	public ModelAndView Registro(HttpServletRequest request, ModelMap model){
		return new ModelAndView("usuario/RegistroCliente","col", getValoresGenericos(request));
	}
}
