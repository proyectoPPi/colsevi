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
import com.colsevi.dao.general.model.Correo;
import com.colsevi.dao.general.model.CorreoExample;
import com.colsevi.dao.general.model.Direccion;
import com.colsevi.dao.general.model.DireccionExample;
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.Telefono;
import com.colsevi.dao.general.model.TelefonoExample;
import com.colsevi.dao.general.model.TipoTelefono;
import com.colsevi.dao.general.model.TipoTelefonoExample;

@Controller
public class EstablecimientoController extends BaseConfigController {
	
	private static final long serialVersionUID = 1944372690226154900L;
	
	@RequestMapping("/General/Establecimiento")
	public ModelAndView Establecimiento(HttpServletRequest request,ModelMap model){
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
			mapa.put("nombre","%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			mapa.put("desc","%" + descripcion + "%");   
		}
		if(direccion != null && !direccion.trim().isEmpty()){
			mapa.put("direccion","%" + descripcion + "%");   
		}
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getEstablecimientoMapper().SelectDataView(mapa)));
		opciones.put("total", ColseviDao.getInstance().getEstablecimientoMapper().CountDataView(mapa));

		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
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
					opciones.put("hora_inicio", map.get("hora_inicio"));
					opciones.put("hora_fin", map.get("hora_fin"));
					opciones.put("id_direccion", map.get("id_direccion") != null ? map.get("id_direccion") : "");	
					opciones.put("direccion", map.get("direccion") != null ? map.get("direccion") : "");	
					opciones.put("barrio", map.get("barrio") != null ? map.get("barrio") : "");
					opciones.put("descripDir", map.get("descripDir") != null ? map.get("descripDir") : "");
					opciones.put("id_telefono", map.get("id_telefono") != null ? map.get("id_telefono") : "");
					opciones.put("telTipo", map.get("telTipo") != null ? map.get("telTipo") : "");
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
	
	@RequestMapping("/General/Establecimiento/GuardarLocal")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		Object[] result = validarGuardado(request);
				
		if(!result[0].toString().isEmpty()){
			modelo.addAttribute("error", result[0]);
			return Establecimiento(request, modelo);
		}
		
		Establecimiento bean= (Establecimiento) result[1];
		Direccion beanD = (Direccion) result[2];
		Telefono beanT = (Telefono) result[3];
		Correo beanC = (Correo) result[4];
		
		try{
			if(beanD.getId_direccion() != null){
				ColseviDao.getInstance().getDireccionMapper().updateByPrimaryKeySelective(beanD);
			}else{
				ColseviDao.getInstance().getDireccionMapper().insertSelective(beanD);
				
				DireccionExample DE = new DireccionExample();
				DE.setOrderByClause("id_direccion DESC");
				beanD.setId_direccion(ColseviDao.getInstance().getDireccionMapper().selectByExample(DE).get(0).getId_direccion());
			}
			bean.setId_direccion(beanD.getId_direccion());
			
			if(beanT.getId_telefono() != null){
				ColseviDao.getInstance().getTelefonoMapper().updateByPrimaryKeySelective(beanT);
			}else{
				ColseviDao.getInstance().getTelefonoMapper().insertSelective(beanT);
				
				TelefonoExample TE = new TelefonoExample();
				TE.setOrderByClause("id_telefono DESC");
				beanT.setId_telefono(ColseviDao.getInstance().getTelefonoMapper().selectByExample(TE).get(0).getId_telefono());
			}
			bean.setId_telefono(beanT.getId_telefono());
			
			if(beanC.getId_correo() != null){
				ColseviDao.getInstance().getCorreoMapper().updateByPrimaryKeySelective(beanC);
			}else{
				ColseviDao.getInstance().getCorreoMapper().insertSelective(beanC);
				
				CorreoExample CE = new CorreoExample();
				CE.setOrderByClause("id_correo DESC");
				beanC.setId_correo(ColseviDao.getInstance().getCorreoMapper().selectByExample(CE).get(0).getId_correo());
			}
			
			bean.setId_correo(beanC.getId_correo());
			
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
		return Establecimiento(request, modelo);
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
		
		return Establecimiento(request, modelo);
	}
}