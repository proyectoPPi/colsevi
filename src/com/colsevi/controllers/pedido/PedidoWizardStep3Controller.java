package com.colsevi.controllers.pedido;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.PedidoManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.pedido.model.DetallePedidoExample;
import com.colsevi.dao.pedido.model.Pedido;

@Controller
@RequestMapping("/Pedido/PedidoWizardStep3")
public class PedidoWizardStep3Controller extends BaseConfigController {

	private static final long serialVersionUID = 819819799992734776L;
	private static Logger logger = Logger.getLogger(PedidoWizardStep3Controller.class);

	@RequestMapping
	public ModelAndView Step2(HttpServletRequest request,ModelMap model){
		model.addAttribute("secuencia", request.getParameter("secuencia"));
		return new ModelAndView("pedido/PedidoWizardStep3View", "col", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/listaProductos")
	public void listaProductos(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			Pedido ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("consecutivo")));
			
			mapa.put("ped", ped.getId_pedido());
			List<Map<String, Object>> map = ColseviDao.getInstance().getDetallePedidoMapper().obtenerDetalle(mapa);
			result.put("records", ConsJsonDet(map));
			
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
				options.put("sub_total",UtilidadManager.MonedaVista((BigDecimal) map.get("sub_total")));
				options.put("venta",UtilidadManager.MonedaVista((BigDecimal) map.get("venta")));
				
				result.add(options);
			}catch(Exception e){
				continue;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Actualizar")
	public void Actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		Pedido ped = new Pedido();
		
		try{
			ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("consecutivo")));
			
			String[] cantidad = request.getParameterValues("cantidad");
			String[] producto = request.getParameterValues("producto");
			List<Integer> listP = new ArrayList<Integer>();
			
			for(int i=0; i<producto.length;i++){
				Integer p = Integer.parseInt(producto[i]);
				Integer c = Integer.parseInt(cantidad[i]);
				listP.add(p);
				
				if(ped != null && ped.getId_pedido() != null){
					PedidoManager.crearDetalle(ped.getId_pedido(), p, c);
				}else{
					result.put("error", "El pedido no se pudo crear");
				}
			}
			
			if(producto.length > 0){
				DetallePedidoExample DPE = new DetallePedidoExample();
				DPE.createCriteria().andId_productoNotIn(listP).andId_pedidoEqualTo(ped.getId_pedido());
				ColseviDao.getInstance().getDetallePedidoMapper().deleteByExample(DPE);
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
	
	@RequestMapping("/continuar")
	public ModelAndView continuar(HttpServletRequest request, ModelMap model){

		try{
			Pedido ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("secuencia")));
			
			PedidoManager.actualizarPedido(ped.getId_pedido(), null, PedidoE.NUEVO.getPedidoE(), null, null);
			model.addAttribute("correcto", "Pedido Creado con número: " + ped.getId_pedido());
		}catch(Exception e){
			model.addAttribute("error", "Contactar al administrador");
		}
		return new ModelAndView("redirect:/Pedido/Visualizar.html", model);
	}
	
	@RequestMapping("/autocompletar")
	public void auto(HttpServletRequest request, HttpServletResponse response){
		try{
			JSONObject result = new JSONObject();
			
			String producto = request.getParameter("campo");
			result = ProductoManager.AutocompletarProducto(producto);

			if(result != null){
				ResponseJson(request, response, result);
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Adicionar")
	public void Adicionar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		try{
			Pedido ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("consecutivo")));		
			PedidoManager.crearDetalle(ped.getId_pedido(), Integer.parseInt(request.getParameter("prod")), Integer.parseInt(request.getParameter("cantidad")));
			result.put("correcto", "Producto adicionado");

		}catch(Exception e){
			result.put("error", "Contactar al administrador");
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	


}
