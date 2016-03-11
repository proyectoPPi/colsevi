package com.colsevi.controllers.pedido;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.dao.usuario.model.Establecimiento;
import com.colsevi.dao.usuario.model.EstablecimientoExample;

@Controller
public class PedidoController {
	
	@RequestMapping("/Pedido/Visualizar")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		return new ModelAndView("pedido/visualizadorPedido");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Pedido/Visualizar/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		EstablecimientoExample EstablecimientoExample = new EstablecimientoExample();
		EstablecimientoExample.setLimit(Inicio + ", " + Final);
		EstablecimientoExample.createCriteria().andEstadovisibleEqualTo("T");
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(EstablecimientoExample)));
		opciones.put("total", ColseviDao.getInstance().getEstablecimientoMapper().countByExample(EstablecimientoExample));

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Establecimiento> listgeneral){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listgeneral != null && listgeneral.size() >0){
			for (Establecimiento bean : listgeneral) {
				opciones = new JSONObject();
				opciones.put("id_establecimiento", bean.getId_establecimiento());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());								
				resultado.add(opciones);
			}
			
		}
		return resultado;
	}
	
}
