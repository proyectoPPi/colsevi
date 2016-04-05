package com.colsevi.controllers.usuario;

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
import com.colsevi.dao.usuario.model.TipoDocumento;
import com.colsevi.dao.usuario.model.TipoDocumentoExample;

@Controller
public class TipoDocumentoController extends BaseConfigController {
	
	private static final long serialVersionUID = 4256773623052938383L;
	
	@RequestMapping("/Usuario/TipoDocumento")
	public ModelAndView tipoDocumento(HttpServletRequest request,ModelMap model){
		return new ModelAndView("usuario/TipoDocumento","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Usuario/TipoDocumento/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		TipoDocumentoExample tipoDocExample = new TipoDocumentoExample();
		tipoDocExample.setLimit(Inicio + ", " + Final);
//		tipoDocExample.createCriteria().andEstadovisibleEqualTo("T");
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getTipoDocumentoMapper().selectByExample(tipoDocExample)));
		opciones.put("total", ColseviDao.getInstance().getTipoDocumentoMapper().countByExample(tipoDocExample));

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<TipoDocumento> listgeneral){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listgeneral != null && listgeneral.size() >0){
			for (TipoDocumento bean : listgeneral) {
				opciones = new JSONObject();
				opciones.put("id_tipo_documento", bean.getId_tipo_documento());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());								
				resultado.add(opciones);
			}
			
		}
		return resultado;
	}
	
	@RequestMapping("/Usuario/TipoDocumento/GuardarTipo")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo, TipoDocumento bean){
		
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return tipoDocumento(request, modelo);
		}
		try{
			if(bean.getId_tipo_documento() != null){
				ColseviDao.getInstance().getTipoDocumentoMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Tipo de Documento Actualizado");
			}else{
				ColseviDao.getInstance().getTipoDocumentoMapper().insert(bean);
				modelo.addAttribute("correcto", "Tipo de Documento insertado");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return tipoDocumento(request, modelo);
	}
	
	public String validarGuardado(TipoDocumento bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error = "Ingresar la descripción<br/>";
		}
		
		return error;
	}
	@RequestMapping("/Usuario/TipoDocumento/EliminarTipoDocumento")
	public ModelAndView EliminarEstablecimiento(HttpServletRequest request, ModelMap modelo){
		
		String id = request.getParameter("id_tipo_documento");
		if(id != null){
			
			TipoDocumento tipoDoc = new TipoDocumento();
//			establecimiento.setEstadovisible("F");
			tipoDoc.setId_tipo_documento(Integer.parseInt(id));
			ColseviDao.getInstance().getTipoDocumentoMapper().updateByPrimaryKeySelective(tipoDoc);
			modelo.addAttribute("correcto", "Tipo de Documento Eliminado");
		}
		
		return tipoDocumento(request, modelo);
	}
}
