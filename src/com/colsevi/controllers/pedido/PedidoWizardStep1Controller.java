package com.colsevi.controllers.pedido;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.PedidoManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.pedido.model.Pedido;

@Controller
@RequestMapping("/Pedido/PedidoWizardStep1")
public class PedidoWizardStep1Controller extends BaseConfigController {

	private static final long serialVersionUID = -747040610997543849L;
	private static Logger logger = Logger.getLogger(PedidoWizardStep1Controller.class);

	@RequestMapping
	public String administrador(HttpServletRequest request,ModelMap model){
		model.addAttribute("establecimiento", GeneralManager.getEstablecimientos());
		return "pedido/PedidoWizardStep1View";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/crearPedido")
	public void CrearPedido(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		String error = "";
		Integer pedido = null;
		
		try{
			pedido = PedidoManager.crearPedido(getUsuario(request).getPersona(), Integer.parseInt(request.getParameter("establecimiento")), null);
		}catch(Exception e){
			error = "Contactar al administrador";
			logger.error(e.getMessage());
		}
		resultVista.put("consecutivo", pedido);
		
		if(!error.isEmpty())
			resultVista.put("error", error);
		
		ResponseJson(request, response, resultVista);
	}

	
	@RequestMapping("/completarProducto")
	public void completarProducto(HttpServletRequest request, HttpServletResponse response){
		try{
			String producto = request.getParameter("campo");
			JSONObject result = ProductoManager.AutocompletarProducto(producto);
			if(result != null)
				ResponseJson(request, response, result);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/listaProductos")
	public void listaProductos(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			if(request.getParameter("prod") != null && !request.getParameter("prod").trim().isEmpty())
				mapa.put("prod", request.getParameter("prod"));
			
			Pedido ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("consecutivo")));
			mapa.put("esta", ped.getId_establecimiento());
			List<Map<String, Object>> map = ColseviDao.getInstance().getDetallePedidoMapper().SelectDataView(mapa);
			result.put("records", construirJson(map));
			
			ResponseJson(request, response, result);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray construirJson(List<Map<String, Object>> listaProd){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaProd){
			try{
				opciones = new JSONObject();
				opciones.put("id_producto", map.get("id_producto"));
				opciones.put("referencia", map.get("referencia"));
				opciones.put("nombre", map.get("nombre"));
				opciones.put("descripcion", map.get("descripcion"));
				opciones.put("venta", map.get("venta"));
				opciones.put("imagen", map.get("imagen"));
				
				resultado.add(opciones);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Adicionar")
	public void Adicionar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		try{
			Pedido ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("consecutivo")));		
			PedidoManager.crearDetalle(ped.getId_pedido(), Integer.parseInt(request.getParameter("prod")), 
					Integer.parseInt(request.getParameter("cantidad")));
			resultVista.put("correcto", "Producto adicionado");
		}catch(Exception e){
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, resultVista);
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