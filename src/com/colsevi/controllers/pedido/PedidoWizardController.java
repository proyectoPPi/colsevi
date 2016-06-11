package com.colsevi.controllers.pedido;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ClienteManager;
import com.colsevi.application.ColseviDao;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.PedidoManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.pedido.model.DetallePedido;
import com.colsevi.dao.pedido.model.DetallePedidoExample;
import com.colsevi.dao.pedido.model.Pedido;
import com.colsevi.dao.producto.model.TipoProducto;
import com.colsevi.dao.producto.model.TipoProductoExample;

@Controller
public class PedidoWizardController extends BaseConfigController {

	private static final long serialVersionUID = -747040610997543849L;

	@RequestMapping("/Pedido/Flujo")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		model.addAttribute("tipoPadre", ProductoManager.tipoProducto());
		model.addAttribute("establecimiento", GeneralManager.getEstablecimientos());
		return new ModelAndView("pedido/WizardPedido", "col", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/listaProductos")
	public void listaProductos(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("tipo", request.getParameter("tipo"));
			mapa.put("esta", request.getParameter("esta"));
			List<Map<String, Object>> map = ColseviDao.getInstance().getDetallePedidoMapper().SelectDataView(mapa);
			result.put("records", construirJson(map));
			
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			result.writeJSONString(response.getWriter());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	@SuppressWarnings("unchecked")
	public JSONArray construirJson(List<Map<String, Object>> listaProd){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaProd){
			opciones = new JSONObject();
			opciones.put("id_producto", map.get("id_producto"));
			opciones.put("referencia", map.get("referencia"));
			opciones.put("nombre", map.get("nombre"));
			opciones.put("descripcion", map.get("descripcion"));
			opciones.put("venta", map.get("venta"));
			opciones.put("imagen", map.get("imagen"));
			
			resultado.add(opciones);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/crearPedido")
	public void CrearPedido(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Pedido ped = new Pedido();
		
		try{
			Integer interno = Integer.parseInt(request.getParameter("interno"));
			Integer persona = null;
			if(!interno.equals(0) && getUsuario(request) != null){
				persona = getUsuario(request).getPersona();
			}else
				persona = Integer.parseInt(request.getParameter("persona"));
			
			if(request.getParameter("pedido") != null && !request.getParameter("pedido").trim().isEmpty()){
				PedidoManager.actualizarPedido(Integer.parseInt(request.getParameter("pedido")), persona, PedidoE.BORRADOR.getPedidoE(), false, null);
			}else{
				if(!PedidoManager.crearPedido(persona, new Date(), new BigDecimal(0), false, PedidoE.BORRADOR.getPedidoE(),Integer.parseInt(request.getParameter("establecimiento")),interno)){
					result.put("error", "Ocurrió un error creando el pedido");
				}else{
					ped = PedidoManager.obtenerPedido(persona, null);
					result.put("pedsec", ped.getId_pedido());
					result.put("correcto", "OK");
				}
			}
		}catch(Exception e){
			result.put("error", "Ocurrió un error creando el pedido");
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/Adicionar")
	public void Adicionar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Pedido ped = new Pedido();

		try{
			Integer persona = Integer.parseInt(request.getParameter("persona"));
			
			ped = PedidoManager.obtenerPedido(persona, request.getParameter("pedido"));			
			if(ped != null && ped.getId_pedido() != null){
				PedidoManager.crearDetalle(ped.getId_pedido(), Integer.parseInt(request.getParameter("prod")), Integer.parseInt(request.getParameter("cantidad")));
				result.put("correcto", "Producto adicionado");
			}else{
				result.put("error", "Pedido no creado");
			}
		}catch(Exception e){
			result.put("error", "Contactar al administrador");
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	@RequestMapping("/Pedido/Flujo/autocompletar")
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/obtenerDet")
	public void obtenerDet(HttpServletRequest request, HttpServletResponse response){
		JSONObject result = new JSONObject();
		Pedido ped = new Pedido();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			Integer persona = Integer.parseInt(request.getParameter("CodigoPersona"));
			
			ped = PedidoManager.obtenerPedido(persona, request.getParameter("pedidosec"));			
			if(ped != null && ped.getId_pedido() != null){
				mapa.put("ped",ped.getId_pedido());
				result.put("datos", ConsJsonDet(ColseviDao.getInstance().getDetallePedidoMapper().obtenerDetalle(mapa)));
			}
			
			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
			result.writeJSONString(response.getWriter());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConsJsonDet(List<Map<String, Object>> detalle){
		JSONArray result = new JSONArray();
		JSONObject options = new JSONObject();
		
		for(Map<String, Object> map: detalle){
			try{
				options = new JSONObject();

				options.put("prod", map.get("id_producto"));
				options.put("prodId", map.get("id_producto"));
				options.put("ref", map.get("referencia"));
				options.put("nombre", map.get("referencia") + " " + map.get("nombre"));
				options.put("cantidad", map.get("cantidad"));
				options.put("sub_total",UtilidadManager.Currency(new BigDecimal(map.get("sub_total").toString())));
				
				result.add(options);
			}catch(Exception e){
				continue;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/Actualizar")
	public void Actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		Pedido ped = new Pedido();
		
		try{
			Integer cantTbl = Integer.parseInt(request.getParameter("cantTbl"));
			Integer persona = Integer.parseInt(request.getParameter("persona"));
			ped = PedidoManager.obtenerPedido(persona, request.getParameter("pedido"));
			
			for(int i=0; i<cantTbl;i++){
				Integer prod = Integer.parseInt(request.getParameter("prod" + i));
				Integer cant = Integer.parseInt(request.getParameter("cant" + i));

				if(ped != null && ped.getId_pedido() != null){
					PedidoManager.crearDetalle(ped.getId_pedido(), prod, cant);
				}else{
					result.put("Error", "El pedido no se pudo crear");
				}
			}
			result.put("correcto", "Productos actualizados");
		}catch(Exception e){
			e.printStackTrace();
			result.put("Error", "Contactar al administrador");
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	@RequestMapping("/Pedido/Flujo/terminar")
	public ModelAndView terminarPedido(HttpServletRequest request, ModelMap model){

		try{
			Boolean pago = Boolean.valueOf(request.getParameter("pago") != null && request.getParameter("pago").trim().equals("true") ? "true" : "false");
			Integer persona = Integer.parseInt(request.getParameter("CodigoPersona"));
			Pedido ped = PedidoManager.obtenerPedido(persona, request.getParameter("pedidosec"));	
			String comentario = request.getParameter("comentario");
			
			if(ped != null && ped.getId_pedido() != null){

				DetallePedidoExample DPE= new DetallePedidoExample();
				DPE.createCriteria().andId_pedidoEqualTo(ped.getId_pedido());
				List<DetallePedido> listaDet = ColseviDao.getInstance().getDetallePedidoMapper().selectByExample(DPE);
				
				for(DetallePedido det: listaDet){
					PedidoManager.descontarInventario(ped.getId_pedido(), det.getId_producto(), det.getCantidad(), null);
				}
				PedidoManager.actualizarPedido(ped.getId_pedido(), persona, PedidoE.NUEVO.getPedidoE(), pago, comentario);
			}
			model.addAttribute("correcto", "Pedido Creado con número: " + ped.getId_pedido());
		}catch(Exception e){
			model.addAttribute("error", "Contactar al administrador");
		}
		return new ModelAndView("redirect:/Pedido/Visualizar.html", model);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/cambioEstablecimiento")
	public void cambioEstablecimiento(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		
		try{
			Integer establecimiento = Integer.parseInt(request.getParameter("establecimiento"));
			Integer persona = Integer.parseInt(request.getParameter("persona"));
			String pedido = request.getParameter("pedido");
			
			if(pedido != null && !pedido.trim().isEmpty()){
				DetallePedidoExample DPE = new DetallePedidoExample();
				DPE.createCriteria().andId_pedidoEqualTo(Integer.parseInt(pedido));
				Integer total = ColseviDao.getInstance().getDetallePedidoMapper().countByExample(DPE);
				if(total > 0){
					Pedido ped = PedidoManager.obtenerPedido(persona, pedido);
					if(ped != null && ped.getId_pedido() != null){
						if(!ped.getId_establecimiento().equals(establecimiento)){
							result.put("warning", "Existen productos asociados a otro establecimiento, al hacer esto se eliminarán los productos previamente configurados.");
						}
					}
				}
			}
		}catch(Exception e){
			result.put("Error", "Contactar al administrador");
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
}