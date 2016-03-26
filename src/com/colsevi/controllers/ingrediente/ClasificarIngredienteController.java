package com.colsevi.controllers.ingrediente;

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
import com.colsevi.dao.ingrediente.model.ClasificarIngrediente;
import com.colsevi.dao.ingrediente.model.ClasificarIngredienteExample;
import com.colsevi.dao.ingrediente.model.Ingrediente;
import com.colsevi.dao.ingrediente.model.IngredienteExample;

@Controller
public class ClasificarIngredienteController {
	
	@RequestMapping("/Ingrediente/Clasificar")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		return new ModelAndView("ingrediente/Clasificar");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Ingrediente/Clasificar/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		
		ClasificarIngredienteExample clasificarExample = new ClasificarIngredienteExample();
		clasificarExample.setLimit(Inicio + ", " + Final);
		
		ClasificarIngredienteExample.Criteria criteria = (ClasificarIngredienteExample.Criteria) clasificarExample.createCriteria();
		
		if(nombre != null && !nombre.trim().isEmpty()){
			criteria.andNombreLike("%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			criteria.andDescripcionLike("%" + descripcion + "%");   
		}
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getClasificarIngredienteMapper().selectByExample(clasificarExample)));
		opciones.put("total", ColseviDao.getInstance().getClasificarIngredienteMapper().countByExample(clasificarExample));

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<ClasificarIngrediente> listaClasificacion){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listaClasificacion != null && listaClasificacion.size() >0){
			for (ClasificarIngrediente bean : listaClasificacion) {
				opciones = new JSONObject();
				opciones.put("id_clasificar_ingrediente", bean.getId_clasificar_ingrediente());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());								
				resultado.add(opciones);
			}
			
		}
		return resultado;
	}
	
	@RequestMapping("/Ingrediente/Clasificar/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo, ClasificarIngrediente bean){
		
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return administrador(request, modelo);
		}
		try{
			if(bean.getId_clasificar_ingrediente() != null){
				ColseviDao.getInstance().getClasificarIngredienteMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Clasificación Actualizada");
			}else{
				ColseviDao.getInstance().getClasificarIngredienteMapper().insert(bean);
				modelo.addAttribute("correcto", "Clasificación insertada");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return administrador(request, modelo);
	}
	
	public String validarGuardado(ClasificarIngrediente bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error = "Ingresar la descripción<br/>";
		}
		
		return error;
	}
	@RequestMapping("/Ingrediente/Clasificar/Eliminar")
	public ModelAndView EliminarEstablecimiento(HttpServletRequest request, ModelMap modelo){
		
		Integer id = Integer.parseInt(request.getParameter("id_clasificar_ingrediente"));
		if(id != null){
			
			IngredienteExample ingExample = new IngredienteExample();
			ingExample.createCriteria().andId_clasificar_ingredienteEqualTo(id);
			List<Ingrediente> listaIng = ColseviDao.getInstance().getIngredienteMapper().selectByExample(ingExample);

			if(listaIng != null && listaIng.size() > 0){
				modelo.addAttribute("error", "Clasificación No Eliminada, Asociada a ingredientes");
			}else{
				ColseviDao.getInstance().getClasificarIngredienteMapper().deleteByPrimaryKey(id);
				modelo.addAttribute("correcto", "Clasificación Eliminada");
			}
			
		}
		
		return administrador(request, modelo);
	}
}
