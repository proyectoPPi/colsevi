package com.colsevi.controllers.general;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/General/CorreoCambioContrasenaController")
public class CorreoCambioContrasenaController extends BaseConfigController {

	private static final long serialVersionUID = -3538109285969027442L;
	private static Logger logger = Logger.getLogger(EstablecimientoController.class);
	
	@RequestMapping
	public String Establecimiento(HttpServletRequest request,ModelMap model){
		
		return "general/CorreoCambioContrasena";
	}

}
