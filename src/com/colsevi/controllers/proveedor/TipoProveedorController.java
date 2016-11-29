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
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.proveedor.model.ProveedorExample;
import com.colsevi.dao.proveedor.model.TipoProveedor;
import com.colsevi.dao.proveedor.model.TipoProveedorExample;


@Controller
public class TipoProveedorController extends BaseConfigController{
	

	private static final long serialVersionUID = -512007271081811336L;

	@RequestMapping("/TipoProveedor/TipoProv")
	public ModelAndView TipoProveedor(HttpServletRequest request,ModelMap model){
		
		return new ModelAndView("proveedor/TipoProveedor","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/TipoProveedor/TipoProv/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();

		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			String nombre = request.getParameter("nombreF");
			
			TipoProveedorExample TipoProvExample = new TipoProveedorExample();
			TipoProveedorExample.Criteria criteria = (TipoProveedorExample.Criteria) TipoProvExample.createCriteria();
			
			TipoProvExample.setLimit(Inicio + ", " + Final);
			if(nombre != null && !nombre.trim().isEmpty()){
				criteria.andNombreLike("%" + nombre + "%");   
			}
						
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getTipoProveedorMapper().selectByExample(TipoProvExample)));
			opciones.put("total", ColseviDao.getInstance().getTipoProveedorMapper().countByExample(TipoProvExample));

		}catch(Exception e){
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<TipoProveedor> listTipoProv){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listTipoProv != null && listTipoProv.size() >0){
			for (TipoProveedor bean : listTipoProv) {
				opciones = new JSONObject();
				opciones.put("id_tipo_proveedor", bean.getId_tipo_proveedor());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());

				resultado.add(opciones);
			}
		}
		return resultado;
	}
	
	@RequestMapping("/TipoProveedor/TipoProv/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo, TipoProveedor bean){
		
		try{
			String error = validarGuardado(bean);
			if(!error.isEmpty()){
				modelo.addAttribute("error", error);
				return TipoProveedor(request, modelo);
			}
			
			if(bean.getId_tipo_proveedor() != null){
				ColseviDao.getInstance().getTipoProveedorMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Tipo Proveedor Actualizado");
			}else{
				ColseviDao.getInstance().getTipoProveedorMapper().insert(bean);
				modelo.addAttribute("correcto", "Tipo Proveedor insertado");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return TipoProveedor(request, modelo);
	}
	
	public String validarGuardado(TipoProveedor bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		return error;
	}
	
	@RequestMapping("/TipoProveedor/TipoProv/Eliminar")
	public ModelAndView Eliminar(HttpServletRequest request, ModelMap modelo){
		
		try{
			Integer id = Integer.parseInt(request.getParameter("id_tipo_proveedor"));
			if(id != null){
				
				ProveedorExample Prov = new ProveedorExample();
				Prov.createCriteria().andId_tipo_proveedorEqualTo(id);
				Integer dataCruce = ColseviDao.getInstance().getProveedorMapper().countByExample(Prov);
				if(dataCruce != 0){
					modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a un proveedor");
				}else{
					ColseviDao.getInstance().getTipoProveedorMapper().deleteByPrimaryKey(id);
					modelo.addAttribute("correcto", "Tipo Proveedor Eliminado");
				}
			}
		}catch(Exception e){
			modelo.addAttribute("error", "Contacte al Administrador");
		}
		
		return TipoProveedor(request, modelo);
	}

}
