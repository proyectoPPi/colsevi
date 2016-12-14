package com.colsevi.controllers.pedido;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ClienteManager;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.PedidoManager;
import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/Pedido/PedidoWizardStep1")
public class PedidoWizardStep1Controller extends BaseConfigController {

	private static final long serialVersionUID = -747040610997543849L;
	private static Logger logger = Logger.getLogger(PedidoWizardStep1Controller.class);

	@RequestMapping
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		model.addAttribute("establecimiento", GeneralManager.getEstablecimientos());
		return new ModelAndView("pedido/PedidoWizardStep1View", "col", getValoresGenericos(request));
	}

	@RequestMapping("/autocompletar")
	public void auto(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			
			String cliente = request.getParameter("campo");
			result = ClienteManager.AutocompletarCliente(cliente);
			
			if(result != null){
				response.setContentType("text/html;charset=ISO-8859-1");
				request.setCharacterEncoding("UTF8");
				
				result.writeJSONString(response.getWriter());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/crearPedido")
	public ModelAndView CrearPedido(HttpServletRequest request, ModelMap model) throws IOException{
		String error = "";
		Integer pedido = null;
		
		try{
//			Integer interno = Integer.parseInt(request.getParameter("interno"));
			Integer persona = null;
//			if(!interno.equals(0) && getUsuario(request) != null){
//				persona = getUsuario(request).getPersona();
//			}else
			if(!request.getParameter("CodigoPersona").trim().isEmpty()){
				persona = Integer.parseInt(request.getParameter("CodigoPersona"));
			}else{
				error += "Seleccione una persona<br/>";
			}
				
			if(!request.getParameter("consecutivo").trim().isEmpty()){
				pedido = Integer.parseInt(request.getParameter("consecutivo"));
			}
			
			if(pedido != null){
				PedidoManager.actualizarPedido(pedido, persona, PedidoE.BORRADOR.getPedidoE(), false, null);
			}else{
				pedido = PedidoManager.crearPedido(persona, Integer.parseInt(request.getParameter("establecimiento")), null);
				if(pedido == null)
					error += "Ocurrió un error creando el pedido";
			}
		}catch(Exception e){
			error += "Contactar al administrador";
			logger.error(e.getMessage());
		}
		
		if(error.isEmpty()){
			return new ModelAndView("redirect:/Pedido/PedidoWizardStep2.html?consecutivo=" + pedido, "col", getValoresGenericos(request));
		}else
			model.addAttribute("error", error);
		
		return administrador(request, model);
	}

//	
//	@SuppressWarnings("unchecked")
//	@RequestMapping("/obtenerDet")
//	public void obtenerDet(HttpServletRequest request, HttpServletResponse response){
//		JSONObject result = new JSONObject();
//		Pedido ped = new Pedido();
//		Map<String, Object> mapa = new HashMap<String, Object>();
//		
//		try{
//			Integer persona = Integer.parseInt(request.getParameter("CodigoPersona"));
//			
////			ped = PedidoManager.obtenerPedido(persona, request.getParameter("pedidosec"));			
//			if(ped != null && ped.getId_pedido() != null){
//				mapa.put("ped",ped.getId_pedido());
//				result.put("datos", ConsJsonDet(ColseviDao.getInstance().getDetallePedidoMapper().obtenerDetalle(mapa)));
//			}
//			
//			response.setContentType("text/html;charset=ISO-8859-1");
//			request.setCharacterEncoding("UTF8");
//			
//			result.writeJSONString(response.getWriter());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@SuppressWarnings("unchecked")
//	public JSONArray ConsJsonDet(List<Map<String, Object>> detalle){
//		JSONArray result = new JSONArray();
//		JSONObject options = new JSONObject();
//		
//		for(Map<String, Object> map: detalle){
//			try{
//				options = new JSONObject();
//
//				options.put("prod", map.get("id_producto"));
//				options.put("prodId", map.get("id_producto"));
//				options.put("ref", map.get("referencia"));
//				options.put("nombre", map.get("referencia") + " " + map.get("nombre"));
//				options.put("cantidad", map.get("cantidad"));
//				options.put("sub_total",UtilidadManager.MonedaVista((BigDecimal) map.get("sub_total")));
//				
//				result.add(options);
//			}catch(Exception e){
//				continue;
//			}
//		}
//		return result;
//	}
//	

//	
//	
//	@SuppressWarnings("unchecked")
//	@RequestMapping("/cambioEstablecimiento")
//	public void cambioEstablecimiento(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		JSONObject result = new JSONObject();
//		
//		try{
//			Integer establecimiento = Integer.parseInt(request.getParameter("establecimiento"));
//			Integer persona = Integer.parseInt(request.getParameter("persona"));
//			String pedido = request.getParameter("pedido");
//			
//			if(pedido != null && !pedido.trim().isEmpty()){
//				DetallePedidoExample DPE = new DetallePedidoExample();
//				DPE.createCriteria().andId_pedidoEqualTo(Integer.parseInt(pedido));
//				Integer total = ColseviDao.getInstance().getDetallePedidoMapper().countByExample(DPE);
//				if(total > 0){
//					Pedido ped = null;
//					if(ped != null && ped.getId_pedido() != null){
//						if(!ped.getId_establecimiento().equals(establecimiento)){
//							result.put("warning", "Existen productos asociados a otro establecimiento, al hacer esto se eliminarán los productos previamente configurados.");
//						}
//					}
//				}
//			}
//		}catch(Exception e){
//			result.put("Error", "Contactar al administrador");
//		}
//		
//		response.setContentType("text/html;charset=ISO-8859-1");
//		request.setCharacterEncoding("UTF8");
//		
//		result.writeJSONString(response.getWriter());
//	}
	
}