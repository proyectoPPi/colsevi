package com.colsevi.controllers.producto;

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
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.producto.model.DificultadReceta;
import com.colsevi.dao.producto.model.DificultadRecetaExample;
import com.colsevi.dao.producto.model.Receta;
import com.colsevi.dao.producto.model.RecetaExample;

@Controller
public class DificultadRecetaController extends BaseConfigController {

	private static final long serialVersionUID = -2538142015739043306L;

	@RequestMapping("/Receta/Nivel")
	public ModelAndView Nivel(HttpServletRequest request,ModelMap model){
		
		return new ModelAndView("producto/Nivel","col" , getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Receta/Nivel/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		
		DificultadRecetaExample DRE = new DificultadRecetaExample();
		DRE.setLimit(Inicio + ", " + Final);
		DRE.setOrderByClause("id_dificultad_receta DESC");
		
		DificultadRecetaExample.Criteria criteria = (DificultadRecetaExample.Criteria) DRE.createCriteria();
		
		if(nombre != null && !nombre.trim().isEmpty()){
			criteria.andNombreLike("%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			criteria.andDescripcionLike("%" + descripcion + "%");   
		}
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getDificultadRecetaMapper().selectByExample(DRE)));
		opciones.put("total", ColseviDao.getInstance().getDificultadRecetaMapper().countByExample(DRE));
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<DificultadReceta> listaD){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listaD != null && listaD.size() >0){
			for (DificultadReceta bean : listaD) {
				try{
					opciones = new JSONObject();
					opciones.put("id_dificultad_receta", bean.getId_dificultad_receta());
					opciones.put("nombre", bean.getNombre());
					opciones.put("descripcion", bean.getDescripcion());		
					opciones.put("icono", bean.getIcono());		
					resultado.add(opciones);
				}catch(Exception e){
					continue;
				}
			}
			
		}
		return resultado;
	}
	
	@RequestMapping("/Receta/Nivel/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo, DificultadReceta bean){
		
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return Nivel(request, modelo);
		}
		try{
			if(bean.getId_dificultad_receta() != null){
				ColseviDao.getInstance().getDificultadRecetaMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Dificultad Actualizada");
			}else{
				ColseviDao.getInstance().getDificultadRecetaMapper().insert(bean);
				modelo.addAttribute("correcto", "Dificultad insertada");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Nivel(request, modelo);
	}
	
	public String validarGuardado(DificultadReceta bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error = "Ingresar la descripción<br/>";
		}
		
		return error;
	}

	@SuppressWarnings("null")
	@RequestMapping("/Receta/Nivel/Eliminar")
	public ModelAndView Eliminar(HttpServletRequest request, ModelMap modelo){
		
		Integer id = Integer.parseInt(request.getParameter("id_clasificar_ingrediente"));
		if(id != null){
			
			RecetaExample RExample = new RecetaExample();
			RExample.createCriteria().andId_dificultad_recetaEqualTo(id);
			List<Receta> listaReceta = ColseviDao.getInstance().getRecetaMapper().selectByExample(RExample);

			if(listaReceta != null || listaReceta.size() > 0){
				modelo.addAttribute("error", "Dificultad No Eliminada, Asociada a receta");
			}else{
				ColseviDao.getInstance().getDificultadRecetaMapper().deleteByPrimaryKey(id);
				modelo.addAttribute("correcto", "Dificultad Eliminada");
			}
		}
		return Nivel(request, modelo);
	}
}
