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

import com.colsevi.application.ColseviDao;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.general.model.Configuracion;
import com.colsevi.dao.general.model.ConfiguracionExample;

@Controller
@RequestMapping("/general/Configuracion")
public class ConfiguracionController extends BaseConfigController{
	
	private static final long serialVersionUID = 4963696334014526524L;

	@RequestMapping
	public String Configuracion(HttpServletRequest request,ModelMap model){
		return "general/Configuracion";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String codigo = request.getParameter("codigoF");
		
		ConfiguracionExample configuracionExample = new ConfiguracionExample();
		configuracionExample.setLimit(Inicio + ", " + Final);
		configuracionExample.setOrderByClause("codigo DESC");
		
		ConfiguracionExample.Criteria criteria = (ConfiguracionExample.Criteria) configuracionExample.createCriteria();
		
		if(codigo != null && !codigo.trim().isEmpty()){
			criteria.andCodigoLike("%" + codigo + "%");   
		}
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getConfiguracionMapper().selectByExample(configuracionExample)));
		opciones.put("total", ColseviDao.getInstance().getConfiguracionMapper().countByExample(configuracionExample));

		ResponseJson(request, response, opciones);
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Configuracion> listaConfiguracion){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listaConfiguracion != null && listaConfiguracion.size() >0){
			for (Configuracion bean : listaConfiguracion) {
				opciones = new JSONObject();
				opciones.put("codigo", bean.getCodigo());
				opciones.put("valor", bean.getValor());
				opciones.put("descripcion", bean.getDescripcion());								
				resultado.add(opciones);
			}
			
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public void Guardar(HttpServletRequest request, HttpServletResponse response, Configuracion bean) throws IOException{
		JSONObject result = new JSONObject();
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			result.put("error", error);
		}else
			try{
				if(bean.getCodigo() != null){
					ColseviDao.getInstance().getConfiguracionMapper().updateByPrimaryKey(bean);
					result.put("correcto", "Clasificación Actualizada");
				}
			}catch (Exception e) {
				result.put("error", "Contactar al administrador");
			}
		ResponseJson(request, response, result);
	}
	
	public String validarGuardado(Configuracion bean){
		String error = "";
		if(bean.getValor() == null || bean.getValor().trim().isEmpty()){
			error = "Ingresar el Valor<br/>";
		}
		return error;
	}
	
}
