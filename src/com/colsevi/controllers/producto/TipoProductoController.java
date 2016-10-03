package com.colsevi.controllers.producto;

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
import com.colsevi.dao.producto.model.ProductoExample;
import com.colsevi.dao.producto.model.TipoProducto;
import com.colsevi.dao.producto.model.TipoProductoExample;

@Controller
public class TipoProductoController extends BaseConfigController {

	private static final long serialVersionUID = -8489159548975806696L;

	@RequestMapping("/Producto/Tipo")
	public ModelAndView Clasificar(HttpServletRequest request,ModelMap model){
		return new ModelAndView("producto/TipoProducto","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Producto/Tipo/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		
		TipoProductoExample tipoExample = new TipoProductoExample();
		tipoExample.setLimit(Inicio + ", " + Final);
		tipoExample.setOrderByClause("id_tipo_producto DESC");
		
		TipoProductoExample.Criteria criteria = (TipoProductoExample.Criteria) tipoExample.createCriteria();
		
		if(nombre != null && !nombre.trim().isEmpty()){
			criteria.andNombreLike("%" + nombre + "%");   
		}
		if(descripcion != null && !descripcion.trim().isEmpty()){
			criteria.andDescripcionLike("%" + descripcion + "%");   
		}
		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getTipoProductoMapper().selectByExample(tipoExample)));
		opciones.put("total", ColseviDao.getInstance().getTipoProductoMapper().countByExample(tipoExample));

		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<TipoProducto> listaTipoProd){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
	
		for (TipoProducto bean : listaTipoProd) {
			opciones = new JSONObject();
			opciones.put("id_tipo_producto", bean.getId_tipo_producto());
			opciones.put("nombre", bean.getNombre());
			opciones.put("descripcion", bean.getDescripcion());								
			resultado.add(opciones);
		}
			
		return resultado;
	}
	
	@RequestMapping("/Producto/Tipo/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo, TipoProducto bean){
		
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return Clasificar(request, modelo);
		}
		try{
			if(bean.getId_tipo_producto() != null){
				ColseviDao.getInstance().getTipoProductoMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Tipo de producto Actualizada");
			}else{
				ColseviDao.getInstance().getTipoProductoMapper().insert(bean);
				modelo.addAttribute("correcto", "Tipo de producto Insertada");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Clasificar(request, modelo);
	}
	
	public String validarGuardado(TipoProducto bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error = "Ingresar la descripción<br/>";
		}
		
		return error;
	}
	@RequestMapping("/Producto/Tipo/Eliminar")
	public ModelAndView Eliminar(HttpServletRequest request, ModelMap modelo){
		
		Integer id = Integer.parseInt(request.getParameter("id_tipo_producto"));
		if(id != null){
			
			ProductoExample ProdExample = new ProductoExample();
			ProdExample.createCriteria().andId_tipo_productoEqualTo(id);
			Integer countProd = ColseviDao.getInstance().getProductoMapper().countByExample(ProdExample);

			if(countProd != null && countProd > 0){
				modelo.addAttribute("error", "El tipo de producto no puede ser eliminado ya que se encuentra asociado a " + countProd + " producto(s)");
			}else{
				ColseviDao.getInstance().getTipoProductoMapper().deleteByPrimaryKey(id);
				modelo.addAttribute("correcto", "Tipo de producto Eliminada");
			}
		}
		return Clasificar(request, modelo);
	}
}
