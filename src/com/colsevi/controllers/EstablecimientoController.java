package com.colsevi.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Local;
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
public class EstablecimientoController {
	
	@RequestMapping("/General/Establecimiento")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		return new ModelAndView("Establecimiento");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/General/Establecimiento/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		EstablecimientoExample EstablecimientoExample = new EstablecimientoExample();
		EstablecimientoExample.setLimit(Inicio + ", " + Final);
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(EstablecimientoExample)));
		opciones.put("total", ColseviDao.getInstance().getEstablecimientoMapper().countByExample(new EstablecimientoExample()));

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
	
	@RequestMapping("/General/Establecimiento/GuardarLocal")
	public ModelAndView GuardarLocal(HttpServletRequest request, ModelMap modelo, Establecimiento bean){
		
		if(bean.getId_establecimiento() != null){
			ColseviDao.getInstance().getEstablecimientoMapper().updateByPrimaryKey(bean);
		}else{
			ColseviDao.getInstance().getEstablecimientoMapper().insert(bean);
			modelo.addAttribute("correcto", "OK");
		}
		
		return administrador(request, modelo);
	}
}
