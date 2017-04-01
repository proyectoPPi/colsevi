package com.colsevi.controllers.usuario;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.application.ColseviDao;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.controllers.producto.ProductoAdminController;
import com.colsevi.dao.usuario.model.PersonaExample;
import com.colsevi.dao.usuario.model.TipoDocumento;
import com.colsevi.dao.usuario.model.TipoDocumentoExample;

@Controller
@RequestMapping("/Usuario/TipoDocumento")
public class TipoDocumentoController extends BaseConfigController {
	
	private static final long serialVersionUID = 4256773623052938383L;
	private static Logger logger = Logger.getLogger(ProductoAdminController.class);
	
	@RequestMapping
	public String tipoDocumento(HttpServletRequest request,ModelMap model){
		return "usuario/TipoDocumento";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		
		TipoDocumentoExample tipoExample = new TipoDocumentoExample();
		tipoExample.setLimit(Inicio + ", " + Final);
		tipoExample.setOrderByClause("id_tipo_documento DESC");
		
		TipoDocumentoExample.Criteria criteria = (TipoDocumentoExample.Criteria) tipoExample.createCriteria();
		
		if(nombre != null && !nombre.trim().isEmpty()){
			criteria.andNombreLike("%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			criteria.andDescripcionLike("%" + descripcion + "%");   
		}
		
		try{
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getTipoDocumentoMapper().selectByExample(tipoExample)));
		opciones.put("total", ColseviDao.getInstance().getTipoDocumentoMapper().countByExample(tipoExample));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		ResponseJson(request, response, opciones);
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<TipoDocumento> listgeneral){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listgeneral != null && listgeneral.size() >0){
			for (TipoDocumento bean : listgeneral) {
				opciones = new JSONObject();
				try{
					opciones.put("id_tipo_documento", bean.getId_tipo_documento());
					opciones.put("nombre", bean.getNombre());
					opciones.put("descripcion", bean.getDescripcion());								
					resultado.add(opciones);
				}catch(Exception e){
					logger.error(e.getMessage());
					continue;
				}
			}
			
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/GuardarTipo")
	public void Guardar(HttpServletRequest request, HttpServletResponse response, TipoDocumento bean) throws IOException{
		JSONObject resultVista = new JSONObject();
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			resultVista.put("error", error);
			ResponseJson(request, response, resultVista);
			return;
		}
		try{
			if(bean.getId_tipo_documento() != null){
				ColseviDao.getInstance().getTipoDocumentoMapper().updateByPrimaryKey(bean);
				resultVista.put("correcto", "Tipo de Documento Actualizado");
			}else{
				ColseviDao.getInstance().getTipoDocumentoMapper().insert(bean);
				resultVista.put("correcto", "Tipo de Documento insertado");
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, resultVista);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/preprocesador")
	public void preprocesador(HttpServletRequest request, HttpServletResponse response, TipoDocumento bean) throws IOException{
		JSONObject result = new JSONObject();
		try{
			String error = validarGuardado(bean);
			if(!error.isEmpty()){
				result.put("error", error);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, result);
	}
	
	public String validarGuardado(TipoDocumento bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error += "Ingresar la descripción<br/>";
		}
		
		return error;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/EliminarTipoDocumento")
	public void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		try{
		
			Integer id = Integer.parseInt(request.getParameter("id_tipo_documento"));
			if(id != null){
				
				PersonaExample perExample = new PersonaExample();
				perExample.createCriteria().andTipo_docEqualTo(id);
				Integer count = ColseviDao.getInstance().getPersonaMapper().countByExample(perExample);
				
				if(count != null && count < 0){
					ColseviDao.getInstance().getTipoDocumentoMapper().deleteByPrimaryKey(id);
					resultVista.put("correcto", "Tipo de Documento Eliminado");
				}else{
					resultVista.put("error", "No se puede eliminar el tipo de documento ya que se encuentra en " + count + " personas");
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al Administrador");
		}
		ResponseJson(request, response, resultVista);
	}
}
