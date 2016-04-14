package com.colsevi.controllers.pedido;

import java.io.IOException;
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
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.pedido.model.EstadoPedido;
import com.colsevi.dao.pedido.model.EstadoPedidoExample;

@Controller
public class PedidoController extends BaseConfigController {
	
	private static final long serialVersionUID = 2451593747890604373L;

	@RequestMapping("/Pedido/Visualizar")
	public ModelAndView Pedido(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaEstado", listaEstadoP());
		return new ModelAndView("pedido/visualizadorPedido","col",getValoresGenericos(request));
	}
	
	public static List<EstadoPedido> listaEstadoP(){
		return ColseviDao.getInstance().getEstadoPedidoMapper().selectByExample(new EstadoPedidoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Visualizar/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String documentoF = request.getParameter("documentoF");
		String estadoF = request.getParameter("estadoF");
		String clienteF = request.getParameter("clienteF");

		mapa.put("limit",Inicio + ", " + Final);
		
		if(documentoF != null && !documentoF.trim().isEmpty()){
			mapa.put("documento", "%" + documentoF + "%");
		}
		if(estadoF != null && !estadoF.trim().isEmpty()){
			mapa.put("estado", estadoF);
		}
		if(clienteF != null && !clienteF.trim().isEmpty()){
			mapa.put("cliente",  "%" + clienteF + "%");
		}
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getPedidoMapper().SelectDataView(mapa)));
		opciones.put("total", 0);

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String, Object>> listPedido){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listPedido != null && listPedido.size() >0){
			for (Map<String, Object> map : listPedido) {
				opciones = new JSONObject();

				opciones.put("id_pedido", map.get("id_pedido").toString());
				opciones.put("documento", map.get("documento"));
				opciones.put("nombreCompleto", map.get("nombre").toString() + " " + map.get("apellido"));
				opciones.put("estado", map.get("estado"));
				opciones.put("fecha_pedido", UtilidadManager.FormatDateComplete(map.get("fecha_pedido").toString()));
				opciones.put("total", map.get("total"));
				opciones.put("pagado", Boolean.parseBoolean(map.get("pagado").toString()) ? "SI" : "NO");
				
				resultado.add(opciones);
			}
		}
		return resultado;
	}
}
