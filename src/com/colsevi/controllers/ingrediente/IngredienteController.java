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
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.ingrediente.model.ClasificarIngrediente;
import com.colsevi.dao.ingrediente.model.ClasificarIngredienteExample;
import com.colsevi.dao.ingrediente.model.Ingrediente;
import com.colsevi.dao.ingrediente.model.IngredienteExample;
import com.colsevi.dao.producto.model.IngredienteXProductoExample;

@Controller
public class IngredienteController extends BaseConfigController {

	private static final long serialVersionUID = 8349230539753648934L;

	@RequestMapping("/Ingrediente/Ing")
	public ModelAndView Ingrediente(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaClasificar", listaClasificacion());
		return new ModelAndView("ingrediente/Ingrediente","col",getValoresGenericos(request));
	}
	
	public static List<ClasificarIngrediente> listaClasificacion(){
		return ColseviDao.getInstance().getClasificarIngredienteMapper().selectByExample(new ClasificarIngredienteExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Ingrediente/Ing/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();

		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			String nombre = request.getParameter("nombreF");
			String descripcion = request.getParameter("descripcionF");
			String clasificarF = request.getParameter("clasificarF");
			
			IngredienteExample IngExample = new IngredienteExample();
			IngredienteExample.Criteria criteria = (IngredienteExample.Criteria) IngExample.createCriteria();
			IngExample.setOrderByClause("id_ingrediente DESC");
			
			IngExample.setLimit(Inicio + ", " + Final);
			if(nombre != null && !nombre.trim().isEmpty()){
				criteria.andNombreLike("%" + nombre + "%");   
			}
			if(descripcion != null && !descripcion.trim().isEmpty()){
				criteria.andDescripcionLike("%" + descripcion + "%");   
			}
			if(clasificarF != null  && !clasificarF.trim().isEmpty() && !clasificarF.trim().equals("0")){
				criteria.andId_clasificar_ingredienteEqualTo(Integer.parseInt(clasificarF));
			}
			
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getIngredienteMapper().selectByExample(IngExample)));
			opciones.put("total", ColseviDao.getInstance().getIngredienteMapper().countByExample(IngExample));

		}catch(Exception e){
			e.printStackTrace();
		}
		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Ingrediente> listIng){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		JSONObject labels = new JSONObject();
		
		if(listIng != null && listIng.size() >0){
			for (Ingrediente bean : listIng) {
				opciones = new JSONObject();
				labels = new JSONObject();
				opciones.put("id_ingrediente", bean.getId_ingrediente());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());

				if(bean.getId_clasificar_ingrediente() != null){
					labels.put("label",ColseviDao.getInstance().getClasificarIngredienteMapper().selectByPrimaryKey(bean.getId_clasificar_ingrediente()).getNombre());
					labels.put("value", bean.getId_clasificar_ingrediente());
					opciones.put("clasificar", labels);
				}
				resultado.add(opciones);
			}
			
		}
		return resultado;
	}
	
	@RequestMapping("/Ingrediente/Ing/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo, Ingrediente bean){
		
		try{
			bean.setId_clasificar_ingrediente(Integer.parseInt(request.getParameter("clasificar")));
			
			String error = validarGuardado(bean);
			if(!error.isEmpty()){
				modelo.addAttribute("error", error);
				return Ingrediente(request, modelo);
			}

			
			if(bean.getId_ingrediente() != null){
				ColseviDao.getInstance().getIngredienteMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Ingrediente Actualizado");
			}else{
				ColseviDao.getInstance().getIngredienteMapper().insert(bean);
				modelo.addAttribute("correcto", "Ingrediente insertado");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Ingrediente(request, modelo);
	}
	
	public String validarGuardado(Ingrediente bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error += "Ingresar la descripción<br/>";
		}
		if(bean.getId_clasificar_ingrediente() == null || bean.getId_clasificar_ingrediente().equals(0)){
			error += "Seleccionar una clasificación<br/>";
		}
		
		return error;
	}
	
	@RequestMapping("/Ingrediente/Ing/Eliminar")
	public ModelAndView Eliminar(HttpServletRequest request, ModelMap modelo){
		
		try{
			Integer id = Integer.parseInt(request.getParameter("id_ingrediente"));
			if(id != null){
				
				IngredienteXProductoExample IngProd = new IngredienteXProductoExample();
				IngProd.createCriteria().andId_ingredienteEqualTo(id);
				Integer dataCruce = ColseviDao.getInstance().getIngredienteXProductoMapper().countByExample(IngProd);
				if(dataCruce != null && dataCruce > 0){
					modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a un producto");
				}else{
					ColseviDao.getInstance().getIngredienteMapper().deleteByPrimaryKey(id);
					modelo.addAttribute("correcto", "Establecimiento Eliminado");
				}
			}
		}catch(Exception e){
			modelo.addAttribute("error", "Contacte al Administrador");
		}
		
		return Ingrediente(request, modelo);
	}
}
