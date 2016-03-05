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
				opciones.put("telefono", bean.getId_telefono());
				opciones.put("correo", bean.getId_correo());
				opciones.put("hora_inicio", bean.getHora_inicio());
				opciones.put("hora_fin", bean.getHora_fin());
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
			bean.setEstadovisible("T");
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
		if(bean.getId_telefono() == null || bean.getId_telefono().equals("")){
			error = "Ingresar la número de teléfono<br/>";
		}
		if(bean.getId_correo() == null || bean.getId_correo().equals("")){
			error = "Ingresar correo<br/>";
		}
		if(bean.getHora_inicio() == null || bean.getHora_inicio().trim().isEmpty()){
			error = "Ingresar la hora de inicio<br/>";
		}
		if(bean.getHora_fin() == null || bean.getHora_fin().trim().isEmpty()){
			error = "Ingresar la hora de cierre<br/>";
		}
		
		return error;
	}
	@RequestMapping("/General/Establecimiento/EliminarEstablecimiento")
	public ModelAndView EliminarEstablecimiento(HttpServletRequest request, ModelMap modelo){
		
		String id = request.getParameter("id_establecimiento");
		if(id != null){
			
			Establecimiento establecimiento = new Establecimiento();
			establecimiento.setEstadovisible("F");
			establecimiento.setId_establecimiento(Integer.parseInt(id));
			ColseviDao.getInstance().getEstablecimientoMapper().updateByPrimaryKeySelective(establecimiento);
			modelo.addAttribute("correcto", "Establecimiento Eliminado");
		}
		
		return administrador(request, modelo);
	}
}
