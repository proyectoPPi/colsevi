package com.colsevi.controllers.general;

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
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.catalogo.model.CatalogoExample;
import com.colsevi.dao.general.model.TipoTelefono;
import com.colsevi.dao.general.model.TipoTelefonoExample;
import com.colsevi.dao.usuario.model.Establecimiento;
import com.colsevi.dao.usuario.model.EstablecimientoExample;

@Controller
public class EstablecimientoController extends BaseConfigController {
	
	private static final long serialVersionUID = 1944372690226154900L;
	
	@RequestMapping("/General/Establecimiento")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		model.addAttribute("tipoTel", ListaTipoTel());
		return new ModelAndView("general/Establecimiento", "col" ,getValoresGenericos(request));
	}
	
	public static List<TipoTelefono> ListaTipoTel(){
		return ColseviDao.getInstance().getTipoTelefonoMapper().selectByExample(new TipoTelefonoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/General/Establecimiento/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		String direccion = request.getParameter("direccionF");
		
		mapa.put("limit",Inicio + ", " + Final);
		
		if(nombre != null && !nombre.trim().isEmpty()){
			mapa.put("","%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			mapa.put("","%" + descripcion + "%");   
		}
		if(direccion != null && !direccion.trim().isEmpty()){
			mapa.put("","%" + descripcion + "%");   
		}
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getEstablecimientoMapper().SelectDataView(mapa)));
		opciones.put("total", ColseviDao.getInstance().getEstablecimientoMapper().CountDataView(mapa));

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String, Object>> listgeneral){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listgeneral != null && listgeneral.size() >0){
			for (Map<String, Object> map : listgeneral) {
				opciones = new JSONObject();
				opciones.put("id_establecimiento", map.get("id_establecimiento"));
				opciones.put("nombreEsta", map.get("nombreEsta"));
				opciones.put("descipEsta", map.get("descipEsta"));	
				opciones.put("hora_inicio", map.get("hora_inicio"));
				opciones.put("hora_fin", map.get("hora_fin"));
				opciones.put("id_direccion", map.get("id_direccion"));	
				opciones.put("hora_fin", map.get("hora_fin"));
				opciones.put("id_direccion", map.get("id_direccion"));
				opciones.put("direccion", map.get("direccion"));	
				opciones.put("barrio", map.get("barrio"));
				opciones.put("descripDir", map.get("descripDir"));
				opciones.put("id_telefono", map.get("id_telefono"));
				opciones.put("telTipo", map.get("telTipo"));
				opciones.put("telefono", map.get("telefono"));
				opciones.put("id_correo", map.get("id_correo"));
				opciones.put("correo", map.get("correo"));
				
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
	@SuppressWarnings("unused")
	@RequestMapping("/General/Establecimiento/EliminarEstablecimiento")
	public ModelAndView Eliminar(HttpServletRequest request, ModelMap modelo){
		
		String id = request.getParameter("id_establecimiento");
		if(id != null){
			
			try {
				
				CatalogoExample example = new CatalogoExample();
				example.createCriteria().andId_establecimientoEqualTo(Integer.parseInt(id));
				Integer contCatalogo = ColseviDao.getInstance().getCatalogoMapper().countByExample(example);
				
				if(contCatalogo == null && contCatalogo > 0){
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
