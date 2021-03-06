package com.colsevi.controllers.proveedor;

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
import com.colsevi.application.GeneralManager;
import com.colsevi.application.ProveedorManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.general.model.Direccion;
import com.colsevi.dao.general.model.DireccionExample;
import com.colsevi.dao.general.model.Telefono;
import com.colsevi.dao.general.model.TelefonoExample;
import com.colsevi.dao.proveedor.model.CompraProveedorExample;
import com.colsevi.dao.proveedor.model.Proveedor;
import com.colsevi.dao.proveedor.model.ProveedorExample;

@Controller
public class ProveedorController extends BaseConfigController {
	
	private static final long serialVersionUID = 6171705625439131732L;

	@RequestMapping("/Proveedor/Prov")
	public ModelAndView Proveedor(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaTipoProv", ProveedorManager.listaTipoProveedor());
		model.addAttribute("listaTipoTel", GeneralManager.listaTipoTelefono());
		return new ModelAndView("proveedor/Proveedor","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Prov/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();

		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			String nombre = request.getParameter("nombreF");
			String tipoProvF = request.getParameter("tipoProvF");
			
			ProveedorExample ProvExample = new ProveedorExample();
			ProveedorExample.Criteria criteria = (ProveedorExample.Criteria) ProvExample.createCriteria();
			ProvExample.setOrderByClause("id_proveedor DESC");
			
			ProvExample.setLimit(Inicio + ", " + Final);
			if(nombre != null && !nombre.trim().isEmpty()){
				criteria.andNombreLike("%" + nombre + "%");   
			}
			
			if(tipoProvF != null  && !tipoProvF.trim().isEmpty() && !tipoProvF.trim().equals("0")){
				criteria.andId_tipo_proveedorEqualTo(Integer.parseInt(tipoProvF));
			}
			
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getProveedorMapper().selectByExample(ProvExample)));
			opciones.put("total", ColseviDao.getInstance().getProveedorMapper().countByExample(ProvExample));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Proveedor> listProv){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		JSONObject labels = new JSONObject();
		
		if(listProv != null && listProv.size() >0){
			for (Proveedor bean : listProv) {
				opciones = new JSONObject();
				labels = new JSONObject();
				opciones.put("id_proveedor", bean.getId_proveedor());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());
				opciones.put("Idtelefono", bean.getId_telefono());
				if(bean.getId_telefono() != null){
					opciones.put("telefono", ColseviDao.getInstance().getTelefonoMapper().selectByPrimaryKey(bean.getId_telefono()).getTelefono());
				}
				opciones.put("Iddireccion", bean.getId_direccion());
				if(bean.getId_direccion() != null){
					opciones.put("direccion", ColseviDao.getInstance().getDireccionMapper().selectByPrimaryKey(bean.getId_direccion()).getDireccion());
				}

				if(bean.getId_tipo_proveedor() != null){
					labels.put("label",ColseviDao.getInstance().getTipoProveedorMapper().selectByPrimaryKey(bean.getId_tipo_proveedor()).getNombre());
					labels.put("value", bean.getId_tipo_proveedor());
					opciones.put("tipoProv", labels);
				}
				resultado.add(opciones);
			}
		}
		return resultado;
	}
	
	@RequestMapping("/Proveedor/Prov/Guardar")
	public ModelAndView GuardarProveedor(HttpServletRequest request, ModelMap modelo, Proveedor bean){

		try{
			Direccion beanD = new Direccion();
			Telefono beanT = new Telefono();
			Object[] result = validarGuardado(bean,request);
			
			if(result[0] != null && !result[0].toString().isEmpty()){
				modelo.addAttribute("error", result[0]);
				return Proveedor(request, modelo);
			}
			
			bean = (Proveedor) result[1];
			beanD = (Direccion) result[2];
			beanT = (Telefono) result[3];
			
			if(beanD.getId_direccion() == null){
				ColseviDao.getInstance().getDireccionMapper().insertSelective(beanD);
				DireccionExample dirE = new DireccionExample();
				dirE.setOrderByClause("id_direccion DESC");
				dirE.setLimit("1");
				beanD.setId_direccion(ColseviDao.getInstance().getDireccionMapper().selectByExample(dirE).get(0).getId_direccion());
			}else{
				ColseviDao.getInstance().getDireccionMapper().updateByPrimaryKeySelective(beanD);
			}
			if(beanT.getId_telefono() == null){
				ColseviDao.getInstance().getTelefonoMapper().insertSelective(beanT);
				TelefonoExample telE = new TelefonoExample();
				telE.setOrderByClause("id_telefono DESC");
				telE.setLimit("1");
				beanT.setId_telefono(ColseviDao.getInstance().getTelefonoMapper().selectByExample(telE).get(0).getId_telefono());
			}else{
				ColseviDao.getInstance().getTelefonoMapper().updateByPrimaryKeySelective(beanT);
			}
			
			bean.setId_direccion(beanD.getId_direccion());
			bean.setId_telefono(beanT.getId_telefono());
			
			if(bean.getId_proveedor() != null){
				ColseviDao.getInstance().getProveedorMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Proveedor Actualizado");
			}else{
				ColseviDao.getInstance().getProveedorMapper().insert(bean);
				modelo.addAttribute("correcto", "Proveedor insertado");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Proveedor(request, modelo);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Prov/preprocesador")
	public void preprocesador(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		try{
			Object[] validacion = validarGuardado(new Proveedor(),request);
			
			if(!validacion[0].toString().isEmpty()){
				result.put("error", validacion[0]);
			}
		}catch(Exception e){
			result.put("error", "Contactar al administrador");
		}
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	
	public Object[] validarGuardado(Proveedor bean, HttpServletRequest request){
		Object[] result = new Object[4];
		String error = "";
		Direccion beanD = new Direccion();
		Telefono beanT = new Telefono();
		
		if(request.getParameter("id_proveedor") != null && !request.getParameter("id_proveedor").trim().isEmpty())
			bean.setId_proveedor(Integer.parseInt(request.getParameter("id_proveedor")));
		
		if(request.getParameter("tipoProv") == null || request.getParameter("tipoProv").trim().isEmpty() || request.getParameter("tipoProv").equals("0")){
			error += "Seleccionar el tipo de proveedor<br/>";
		}else{
			bean.setId_tipo_proveedor(Integer.parseInt(request.getParameter("tipoProv")));
		}
		
		if(request.getParameter("nombre") == null || request.getParameter("nombre").trim().isEmpty()){
			error += "Agregar nombre<br/>";
		}else{
			bean.setNombre(request.getParameter("nombre"));
		}
		
		if(request.getParameter("descripcion") == null || request.getParameter("descripcion").trim().isEmpty()){
			error += "Agregar descripcion<br/>";
		}else{
			bean.setDescripcion(request.getParameter("descripcion"));
		}
		
		//Direccion proveedor
		
		if(request.getParameter("id_direccion") != null && !request.getParameter("id_direccion").trim().isEmpty())
			beanD.setId_direccion(Integer.parseInt(request.getParameter("id_direccion")));
		
		if(request.getParameter("direccion") != null && !request.getParameter("direccion").trim().isEmpty())
			beanD.setDireccion(request.getParameter("direccion"));
		else
			error += "Ingresar la direcci�n del establecimiento<br/>";
		
		if(request.getParameter("barrio") != null)
			beanD.setBarrio(request.getParameter("barrio"));
		
		if(request.getParameter("descripDir") != null)
			beanD.setDescripcion(request.getParameter("descripDir"));

		//Telefono proveedor
		
		if(request.getParameter("id_telefono") != null && !request.getParameter("id_telefono").trim().isEmpty())
			beanT.setId_telefono(Integer.parseInt(request.getParameter("id_telefono")));
		
		if(request.getParameter("telefono") != null && !request.getParameter("telefono").trim().isEmpty())
			beanT.setTelefono(request.getParameter("telefono"));
		else
			error += "Ingresar el tel�fono<br/>";
		
		if(request.getParameter("telTipo") != null && !request.getParameter("telTipo").trim().isEmpty() && !request.getParameter("telTipo").trim().equals("0"))
			beanT.setId_tipo_telefono(Integer.parseInt(request.getParameter("telTipo")));
		else
			error+= "Ingresar el tipo de T�lefono<br/>";

		result[0] = error;
		result[1] = bean;
		result[2] = beanD;
		result[3] = beanT;
		
		return result;
	}
	
	@RequestMapping("/Proveedor/Prov/Eliminar")
	public ModelAndView EliminarProveedor(HttpServletRequest request, ModelMap modelo){
		
		try{
			Integer id = Integer.parseInt(request.getParameter("id_proveedor"));
			if(id != null){
				
				CompraProveedorExample compra = new CompraProveedorExample();
				compra.createCriteria().andId_proveedorEqualTo(id);
				Integer dataCruce = ColseviDao.getInstance().getCompraProveedorMapper().countByExample(compra);
				if(dataCruce != null && dataCruce > 0){
					modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociado a una compra");
				}else{
					ColseviDao.getInstance().getProveedorMapper().deleteByPrimaryKey(id);
					modelo.addAttribute("correcto", "Proveedor Eliminado");
				}
			}
		}catch(Exception e){
			modelo.addAttribute("error", "Contacte al Administrador");
		}
		
		return Proveedor(request, modelo);
	}
}