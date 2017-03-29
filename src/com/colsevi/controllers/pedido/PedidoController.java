package com.colsevi.controllers.pedido;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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

import com.colsevi.application.ClienteManager;
import com.colsevi.application.ColseviDao;
import com.colsevi.application.PedidoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/Pedido/Visualizar")
public class PedidoController extends BaseConfigController {
	
	private static final long serialVersionUID = 2451593747890604373L;
	private static Logger logger = Logger.getLogger(PedidoController.class);

	@RequestMapping
	public ModelAndView Pedido(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaEstado", PedidoManager.listaEstadoP());
		if(request.getParameter("correcto") != null)
			model.addAttribute("correcto", "Pedido Creado con número: " + request.getParameter("correcto"));
		
		return new ModelAndView("pedido/visualizadorPedido","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			String estadoF = request.getParameter("estadoF");
			String clienteF = request.getParameter("clienteF");
	
			mapa.put("limit",Inicio + ", " + Final);
			
			if(!estadoF.trim().isEmpty())
				mapa.put("estado", estadoF);
			if(!clienteF.trim().isEmpty())
				mapa.put("cliente",  "%" + clienteF + "%");

			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getPedidoMapper().SelectDataView(mapa)));
			opciones.put("total", ColseviDao.getInstance().getPedidoMapper().CountDataView(mapa));
	
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		ResponseJson(request, response, opciones);
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String, Object>> listPedido){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listPedido != null && listPedido.size() >0){
			for (Map<String, Object> map : listPedido) {
				opciones = new JSONObject();
				try{
					opciones.put("id_pedido", map.get("id_pedido").toString());
					opciones.put("documento", map.get("documento"));
					opciones.put("nombreCompleto", map.get("nombre").toString() + " " + map.get("apellido"));
					opciones.put("estado", map.get("estado"));
					opciones.put("fecha_pedido", UtilidadManager.FechaDateConHora_Vista((Date)map.get("fecha_pedido")));
					opciones.put("total", UtilidadManager.MonedaVista((BigDecimal) map.get("total")));
					opciones.put("pagado", Boolean.parseBoolean(map.get("pagado").toString()) ? "SI" : "NO");
					
					resultado.add(opciones);
				}catch(Exception e){
					logger.error(e.getMessage());
					continue;
				}
			}
		}
		return resultado;
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
}
