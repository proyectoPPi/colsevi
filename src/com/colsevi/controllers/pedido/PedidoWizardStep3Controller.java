package com.colsevi.controllers.pedido;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.application.ClienteManager;
import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/Pedido/PedidoWizardStep3")
public class PedidoWizardStep3Controller extends BaseConfigController {

	private static final long serialVersionUID = 819819799992734776L;
	private static Logger logger = Logger.getLogger(PedidoWizardStep3Controller.class);

	@RequestMapping
	public String Step3(HttpServletRequest request,ModelMap model){
		model.addAttribute("secuencia", request.getParameter("secuencia"));
		return "pedido/PedidoWizardStep3View";
	}
	
	@RequestMapping("/autocompletar")
	public void auto(HttpServletRequest request, HttpServletResponse response){
		try{
			String cliente = request.getParameter("campo");
			JSONObject result = ClienteManager.AutocompletarCliente(cliente);
			if(result != null)
				ResponseJson(request, response, result);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
//	
//	@RequestMapping("/continuar")
//	public String continuar(HttpServletRequest request, ModelMap model){
//
//		try{
//			Integer persona = request.getParameter("persona") != null && !request.getParameter("persona").trim().isEmpty() ? 
//					Integer.parseInt(request.getParameter("persona")) : null;
//			Pedido ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("secuencia")));
//			
//			PedidoManager.actualizarPedido(ped.getId_pedido(), persona, PedidoE.NUEVO.getPedidoE(), null, null);
//			model.addAttribute("correcto", ped.getId_pedido());
//			return new ModelAndView("redirect:/Pedido/Visualizar.html", model);
//		}catch(Exception e){
//			logger.error(e.getMessage());
//			model.addAttribute("error", "Contactar al administrador");
//			return Step3(request, model);
//		}
//	}
	
}
