package com.colsevi.controllers.general;

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
import com.colsevi.dao.catalogo.model.Catalogo;
import com.colsevi.dao.catalogo.model.CatalogoExample;
import com.colsevi.dao.ingrediente.model.IngredienteExample;
import com.colsevi.dao.usuario.model.Establecimiento;
import com.colsevi.dao.usuario.model.EstablecimientoExample;

@Controller
public class EstablecimientoController {
	
	@RequestMapping("/General/Establecimiento")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		return new ModelAndView("general/Establecimiento");
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/General/Establecimiento/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		String direccion = request.getParameter("direccionF");
		
		EstablecimientoExample EstablecimientoExample = new EstablecimientoExample();
		EstablecimientoExample.Criteria criteria = (EstablecimientoExample.Criteria) EstablecimientoExample.createCriteria();
		EstablecimientoExample.setLimit(Inicio + ", " + Final);
		
		
		
		if(nombre != null && !nombre.trim().isEmpty()){
			criteria.andNombreLike("%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			criteria.andDescripcionLike("%" + descripcion + "%");   
		}
		if(direccion != null && !direccion.trim().isEmpty()){
			criteria.andDescripcionLike("%" + descripcion + "%");   
		}
		
		
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
	
	@RequestMapping("/General/Establecimiento/GuardarLocal")
	public ModelAndView GuardarLocal(HttpServletRequest request, ModelMap modelo, Establecimiento bean){
		
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return administrador(request, modelo);
		}
		try{
			if(bean.getId_establecimiento() != null){
				ColseviDao.getInstance().getEstablecimientoMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Establecimiento Actualizado");
			}else{
				ColseviDao.getInstance().getEstablecimientoMapper().insert(bean);
				modelo.addAttribute("correcto", "Establecimiento insertado");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return administrador(request, modelo);
	}
	
	public String validarGuardado(Establecimiento bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error = "Ingresar la descripción<br/>";
		}
		
		return error;
	}
	@RequestMapping("/General/Establecimiento/EliminarEstablecimiento")
	public ModelAndView EliminarEstablecimiento(HttpServletRequest request, ModelMap modelo){
		
		String id = request.getParameter("id_establecimiento");
		if(id != null){
			
			try {
				
				CatalogoExample example = new CatalogoExample();
				example.createCriteria().andId_establecimientoEqualTo(Integer.parseInt(id));
				List<Catalogo> ListaCatalogo = ColseviDao.getInstance().getCatalogoMapper().selectByExample(example);
				
				if(ListaCatalogo == null && ListaCatalogo.size() == 0)
				{
					ColseviDao.getInstance().getEstablecimientoMapper().deleteByPrimaryKey(Integer.parseInt(id));
					modelo.addAttribute("correcto", "Establecimiento Eliminado");
				}else{
					modelo.addAttribute("error", "El establecimiento no se pudo eliminar porqué tiene un catálogo asociado");
				}
				
				
			} catch (Exception e) {
				modelo.addAttribute("error", "Ocurrió un error, contacte al administrador");
			}
			
			
			
		}
		
		return administrador(request, modelo);
	}
}
