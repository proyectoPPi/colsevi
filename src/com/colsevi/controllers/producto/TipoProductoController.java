package com.colsevi.controllers.producto;

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
import com.colsevi.dao.producto.model.ProductoExample;
import com.colsevi.dao.producto.model.TipoProducto;
import com.colsevi.dao.producto.model.TipoProductoExample;

@Controller
@RequestMapping("/Producto/Tipo")
public class TipoProductoController extends BaseConfigController {

	private static final long serialVersionUID = -8489159548975806696L;
	private static Logger logger = Logger.getLogger(TipoProductoController.class);

	@RequestMapping
	public String Clasificar(HttpServletRequest request,ModelMap model){
		return "producto/TipoProducto";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		
		try{
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
		}catch(Exception e){
			logger.error(e.getMessage());
		}
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
			try{
				opciones.put("id_tipo_producto", bean.getId_tipo_producto());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());								
				resultado.add(opciones);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
			
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public void Guardar(HttpServletRequest request, HttpServletResponse response, TipoProducto bean) throws IOException{
		JSONObject resultVista = new JSONObject();
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			resultVista.put("error", error);
			ResponseJson(request, response, resultVista);
			return;
		}
		try{
			if(bean.getId_tipo_producto() != null){
				ColseviDao.getInstance().getTipoProductoMapper().updateByPrimaryKey(bean);
				resultVista.put("correcto", "Tipo de producto Actualizada");
			}else{
				ColseviDao.getInstance().getTipoProductoMapper().insert(bean);
				resultVista.put("correcto", "Tipo de producto Insertada");
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, resultVista);
	}
	
	public String validarGuardado(TipoProducto bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty())
			error += "Ingresar el Nombre<br/>";
		return error;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Eliminar")
	public void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		try{
			Integer id = Integer.parseInt(request.getParameter("id_tipo_producto"));
			if(id != null){
				
				ProductoExample ProdExample = new ProductoExample();
				ProdExample.createCriteria().andId_tipo_productoEqualTo(id);
				Long countProd = ColseviDao.getInstance().getProductoMapper().countByExample(ProdExample);
	
				if(countProd != null && countProd > 0){
					resultVista.put("error", "El tipo de producto no puede ser eliminado ya que se encuentra asociado a " + countProd + " producto(s)");
				}else{
					ColseviDao.getInstance().getTipoProductoMapper().deleteByPrimaryKey(id);
					resultVista.put("correcto", "Tipo de producto Eliminada");
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		ResponseJson(request, response, resultVista);
	}
}
