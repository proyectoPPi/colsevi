package com.colsevi.controllers.pedido;

import java.io.IOException;
import java.math.BigDecimal;
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

import com.colsevi.application.ColseviDao;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.InicialController;
import com.colsevi.dao.pedido.model.Pedido;
import com.colsevi.dao.producto.model.TipoProducto;

@Controller
public class PedidoWizardController extends InicialController {

	@RequestMapping("/Pedido/Flujo")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		model.addAttribute("tipoPadre", UtilidadManager.getTipoProductoPadre());
		return new ModelAndView("pedido/WizardPedido");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/hijos")
	public void hijos(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			
			Integer padre = Integer.parseInt(request.getParameter("padre"));
			result.put("records", jsonWrite(UtilidadManager.getTipoProductoHijo(padre)));
			
			result.writeJSONString(response.getWriter());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray jsonWrite(List<TipoProducto> listaProd){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(TipoProducto bean: listaProd){
			opciones = new JSONObject();
			opciones.put("id", bean.getId_tipo_producto());
			opciones.put("nombre", bean.getNombre());
			
			resultado.add(opciones);
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/listaProductos")
	public void listaProductos(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("tipo", request.getParameter("tipo"));
			List<Map<String, Object>> map = ColseviDao.getInstance().getDetallePedidoMapper().SelectDataView(mapa);
			result.put("records", construirJson(map));
			
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
			Integer persona = Integer.parseInt(request.getParameter("persona"));
			
			ped.setFecha_pedido(new Date());
			ped.setId_persona(persona);
			ped.setTotal(new BigDecimal(0));
			ped.setPagado(false);
			ped.setId_estado_pedido(5);
			
		}catch(Exception e){
			result.put("error", "");
		}
		
		try{
			ColseviDao.getInstance().getPedidoMapper().insertSelective(ped);
			result.put("correcto", "OK");
		}catch(Exception e){
			result.put("error", "");
		}
			
			
			
			result.writeJSONString(response.getWriter());
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Flujo/autocompletar")
	public void auto(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			String valor = request.getParameter("valor");
			mapa.put("cliente", "%" + valor + "%");
			
			result.put("labels", JsonAutocompletar(ColseviDao.getInstance().getUsuarioMapper().SelectAutocomplete(mapa)));
			
			result.writeJSONString(response.getWriter());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray JsonAutocompletar(List<Map<String, Object>> listaCliente){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaCliente){
			opciones = new JSONObject();

			opciones.put("label", map.get("documento"));
			opciones.put("value", map.get("documento") + " " + map.get("nombre").toString() + " " + map.get("apellido"));
			opciones.put("nombreC",map.get("nombre").toString() + " " + map.get("apellido"));
			opciones.put("id_persona", map.get("id_persona"));
			opciones.put("documento", map.get("documento"));
			
			resultado.add(opciones);
		}
		return resultado;
	}
	
}
