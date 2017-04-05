package com.colsevi.controllers.general;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.ColseviDaoTransaccion;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.catalogo.model.CatalogoExample;
import com.colsevi.dao.general.model.Correo;
import com.colsevi.dao.general.model.Direccion;
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.Telefono;
import com.colsevi.dao.general.model.TipoTelefono;
import com.colsevi.dao.general.model.TipoTelefonoExample;
import com.colsevi.dao.inventario.model.MateriaPrimaExample;

@Controller
@RequestMapping("/General/Establecimiento")
public class EstablecimientoController extends BaseConfigController {
	
	private static final long serialVersionUID = 1944372690226154900L;
	private static Logger logger = Logger.getLogger(EstablecimientoController.class);
	
	@RequestMapping
	public String Establecimiento(HttpServletRequest request,ModelMap model){
		model.addAttribute("tipoTel", ListaTipoTel());
		return "general/Establecimiento";
	}
	
	public static List<TipoTelefono> ListaTipoTel(){
		return ColseviDao.getInstance().getTipoTelefonoMapper().selectByExample(new TipoTelefonoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
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
			mapa.put("nombre","%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			mapa.put("desc","%" + descripcion + "%");   
		}
		if(direccion != null && !direccion.trim().isEmpty()){
			mapa.put("direccion","%" + direccion + "%");   
		}
		
		try{
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getEstablecimientoMapper().SelectDataView(mapa)));
			opciones.put("total", ColseviDao.getInstance().getEstablecimientoMapper().CountDataView(mapa));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		ResponseJson(request, response, opciones);
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String, Object>> listgeneral){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listgeneral != null && listgeneral.size() >0){
			for (Map<String, Object> map : listgeneral) {
				opciones = new JSONObject();
				try{
					opciones.put("id_establecimiento", map.get("id_establecimiento"));
					opciones.put("nombreEsta", map.get("nombreEsta"));
					opciones.put("descipEsta", map.get("descipEsta"));	
					opciones.put("hora_inicio", map.get("hora_inicio") != null ? map.get("hora_inicio") : "");
					opciones.put("hora_fin", map.get("hora_fin") != null ? map.get("hora_fin") : "");
					opciones.put("id_direccion", map.get("id_direccion") != null ? map.get("id_direccion") : "");	
					opciones.put("direccion", map.get("direccion") != null ? map.get("direccion") : "");	
					opciones.put("barrio", map.get("barrio") != null ? map.get("barrio") : "");
					opciones.put("descripDir", map.get("descripDir") != null ? map.get("descripDir") : "");
					opciones.put("id_telefono", map.get("id_telefono") != null ? map.get("id_telefono") : "");
					opciones.put("telTipo", map.get("telTipo") != null ? map.get("telTipo") : "0");
					opciones.put("telefono", map.get("telefono") != null ? map.get("telefono") : "");
					opciones.put("id_correo", map.get("id_correo") != null ? map.get("id_correo") : "");
					opciones.put("correo", map.get("correo") != null ? map.get("correo") : "");
					
					resultado.add(opciones);
				}catch(Exception e){
					continue;
				}
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/GuardarLocal")
	public void Guardar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		
		Object[] result = validarGuardado(request);
		if(!result[0].toString().isEmpty()){
			resultVista.put("error", result[0]);
			ResponseJson(request, response, resultVista);
			return;
		}
		
		Establecimiento bean= (Establecimiento) result[1];
		Direccion beanD = (Direccion) result[2];
		Telefono beanT = (Telefono) result[3];
		Correo beanC = (Correo) result[4];
		
		SqlSession sesion = ColseviDaoTransaccion.getInstance();

		try{
			if(beanD.getId_direccion() != null || beanD.getDireccion() != null){
				if(beanD.getId_direccion() != null){
					ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.general.map.DireccionMapper.updateByPrimaryKeySelective", beanD);
				}else{
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.DireccionMapper.insertSelective", beanD);
				}
			}
			bean.setId_direccion(beanD.getId_direccion());
			
			if(beanT.getId_telefono() != null || beanT.getTelefono() != null){
				if(beanT.getId_telefono() != null){
					ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.general.map.TelefonoMapper.updateByPrimaryKeySelective", beanT);
				}else{
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.TelefonoMapper.insertSelective", beanT);
				}
			}
			bean.setId_telefono(beanT.getId_telefono());
			
			if(beanC.getId_correo() != null || beanC.getCorreo() != null){
				if(beanC.getId_correo() != null){
					ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.general.map.CorreoMapper.updateByPrimaryKeySelective", beanC);
				}else{
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.CorreoMapper.insertSelective", beanC);
				}
			} 
			bean.setId_correo(beanC.getId_correo());
			
			if(bean.getId_establecimiento() != null){
				ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.general.map.EstablecimientoMapper.updateByPrimaryKeySelective", bean);
				resultVista.put("correcto", "Establecimiento Actualizado");
			}else{
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.general.map.EstablecimientoMapper.insertSelective", bean);
				resultVista.put("correcto", "Establecimiento insertado");
			}

			ColseviDaoTransaccion.RealizarCommit(sesion);
			
		}catch (Exception e) {
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
			ColseviDaoTransaccion.ErrorRollback(sesion);
		}
		
		ColseviDaoTransaccion.CerrarSesion(sesion);
		ResponseJson(request, response, resultVista);
	}
	
	public Object[] validarGuardado(HttpServletRequest request){
		Object[] obj = new Object[5];
		Establecimiento beanE = new Establecimiento();
		Direccion beanD = new Direccion();
		Telefono beanT = new Telefono();
		Correo beanC = new Correo();
		String error = "";
		
		if(request.getParameter("id_establecimiento") != null && !request.getParameter("id_establecimiento").trim().isEmpty())
			beanE.setId_establecimiento(Integer.parseInt(request.getParameter("id_establecimiento")));
		
		if(request.getParameter("nombreEsta") != null && !request.getParameter("nombreEsta").trim().isEmpty())
			beanE.setNombre(request.getParameter("nombreEsta"));
		else
			error += "Ingresar el nombre del establecimiento<br/>";
	
		if(request.getParameter("descipEsta") != null && !request.getParameter("descipEsta").trim().isEmpty())
			beanE.setDescripcion(request.getParameter("descipEsta"));
		else
			error += "Ingresar el descripcion del establecimiento<br/>";
		
		if(request.getParameter("hora_inicio") != null && !request.getParameter("hora_inicio").trim().isEmpty())
			beanE.setHora_inicio(request.getParameter("hora_inicio"));
		else
			error += "Ingresar la hora de Inicio del establecimiento<br/>";
		
		if(request.getParameter("hora_fin") != null && !request.getParameter("hora_fin").trim().isEmpty())
			beanE.setHora_fin(request.getParameter("hora_fin"));
		else
			error += "Ingrese la Hora de Cierre del establecimiento<br/>";
		
		//Direccion Establecimiento
		if(request.getParameter("id_direccion") != null && !request.getParameter("id_direccion").trim().isEmpty())
			beanD.setId_direccion(Integer.parseInt(request.getParameter("id_direccion")));
		
		if(request.getParameter("direccion") != null && !request.getParameter("direccion").trim().isEmpty())
			beanD.setDireccion(request.getParameter("direccion"));
		else
			error += "Ingresar la dirección del establecimiento<br/>";
		
		if(request.getParameter("barrio") != null)
			beanD.setBarrio(request.getParameter("barrio"));
		
		if(request.getParameter("descripDir") != null)
			beanD.setDescripcion(request.getParameter("descripDir"));

		//Telefono Establecimiento
		if(request.getParameter("id_telefono") != null && !request.getParameter("id_telefono").trim().isEmpty())
			beanT.setId_telefono(Integer.parseInt(request.getParameter("id_telefono")));
		
		if(request.getParameter("telefono") != null && !request.getParameter("telefono").trim().isEmpty())
			beanT.setTelefono(request.getParameter("telefono"));
		else
			error += "Ingresar el teléfono<br/>";
		
		if(request.getParameter("telTipo") != null && !request.getParameter("telTipo").trim().isEmpty() && !request.getParameter("telTipo").trim().equals("0"))
			beanT.setId_tipo_telefono(Integer.parseInt(request.getParameter("telTipo")));
		else
			error+= "Ingresar el tipo de Télefono<br/>";

		//Correo Establecimiento
		if(request.getParameter("id_correo") != null && !request.getParameter("id_correo").trim().isEmpty())
			beanC.setId_correo(Integer.parseInt(request.getParameter("id_correo")));
		
		if(request.getParameter("correo") != null && !request.getParameter("correo").trim().isEmpty())
			beanC.setCorreo(request.getParameter("correo"));

		obj[0] = error;
		obj[1] = beanE;
		obj[2] = beanD;
		obj[3] = beanT;
		obj[4] = beanC;
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/EliminarEstablecimiento")
	public void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		String id = request.getParameter("id_establecimiento");
		if(id != null){
			try {
				CatalogoExample example = new CatalogoExample();
				example.createCriteria().andId_establecimientoEqualTo(Integer.parseInt(id));
				Integer contador = ColseviDao.getInstance().getCatalogoMapper().countByExample(example);
				
				if(contador > 0){
					result.put("error", "El establecimiento no se pudo eliminar porqué tiene un catálogo asociado");
					ResponseJson(request, response, result);
					return;
				}
				
				MateriaPrimaExample MPE = new MateriaPrimaExample();
				MPE.createCriteria().andId_establecimientoEqualTo(Integer.parseInt(id));
				contador = ColseviDao.getInstance().getMateriaPrimaMapper().countByExample(MPE);
				if(contador > 0){
					result.put("error", "El establecimiento no se puede eliminar porqué tiene materia prima asociada");
				}else{
					ColseviDao.getInstance().getEstablecimientoMapper().deleteByPrimaryKey(Integer.parseInt(id));
					result.put("correcto", "Establecimiento Eliminado");
				}
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				result.put("error", "Ocurrió un error, contacte al administrador");
			}
		}
		ResponseJson(request, response, result);
	}
}