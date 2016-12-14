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
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.PedidoManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.pedido.model.Pedido;

@Controller
@RequestMapping("/Pedido/PedidoWizardStep2")
public class PedidoWizardStep2Controller extends BaseConfigController {

	private static final long serialVersionUID = 1898182992605286249L;
	private static Logger logger = Logger.getLogger(PedidoWizardStep2Controller.class);

	@RequestMapping
	public ModelAndView Step2(HttpServletRequest request,ModelMap model){
		model.addAttribute("consecutivo", request.getParameter("consecutivo"));
		return new ModelAndView("pedido/PedidoWizardStep2View", "col", getValoresGenericos(request));
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
	@RequestMapping("/listaProductos")
	public void listaProductos(HttpServletRequest request, HttpServletResponse response){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			Pedido ped = PedidoManager.obtenerPedido(Integer.parseInt(request.getParameter("consecutivo")));
			
//			mapa.put("tipo", request.getParameter("tipo"));
			mapa.put("esta", ped.getId_establecimiento());
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
