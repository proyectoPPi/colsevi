package com.colsevi.controllers.inventario;

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
import org.springframework.web.portlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.controllers.BaseConfigController;

@Controller
public class InventarioController extends BaseConfigController {

	private static final long serialVersionUID = 4663892967154593514L;

	@RequestMapping("/Inventario/Vista")
	public ModelAndView inv(HttpServletRequest request, ModelMap model){
		return new ModelAndView("producto/Ingrediente", "col", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/Vista/tabla")
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
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getInventarioMapper().SelectDataView(mapa)));
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
				
				try{
					opciones.put("id_inventario", map.get("id_inventario").toString());
					opciones.put("id_producto", map.get("id_producto"));
					opciones.put("nombre", map.get("nombre").toString());
					opciones.put("referencia", map.get("referencia"));
					opciones.put("id_establecimiento", map.get("id_establecimiento"));
					opciones.put("nombreEsta", map.get("nombreEsta"));
					opciones.put("disponible", map.get("disponible") == null ? "0" : map.get("disponible"));
					opciones.put("compromiso", map.get("compromiso") == null ? "0" : map.get("compromiso"));
				
					resultado.add(opciones);
				
				}catch(Exception e){
					continue;
				}
			}
		}
		return resultado;
	}

}
