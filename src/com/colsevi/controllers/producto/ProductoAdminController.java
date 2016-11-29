package com.colsevi.controllers.producto;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.ColseviDaoTransaccion;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.application.ingredienteManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.catalogo.model.CatalogoXProductoExample;
import com.colsevi.dao.inventario.model.InventarioExample;
import com.colsevi.dao.pedido.model.DetallePedidoExample;
import com.colsevi.dao.producto.model.IngredienteXProducto;
import com.colsevi.dao.producto.model.IngredienteXProductoExample;
import com.colsevi.dao.producto.model.Producto;
import com.colsevi.dao.producto.model.ProductoExample;
import com.colsevi.dao.producto.model.RecetaExample;

@Controller
@RequestMapping("/Producto/Admin")
public class ProductoAdminController extends BaseConfigController {

	private static final long serialVersionUID = 4997906906136000223L;
	private static Logger logger = Logger.getLogger(ProductoAdminController.class);
	
	@RequestMapping
	public ModelAndView Producto(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaTipo", ProductoManager.tipoProducto());
		model.addAttribute("listaCatalogo", ProductoManager.catalogo());
		
		try{
			if(request.getParameter("producto") != null && !request.getParameter("producto").trim().isEmpty()){
				Producto prod = ColseviDao.getInstance().getProductoMapper().selectByPrimaryKey(Integer.parseInt(request.getParameter("producto")));
				if(prod != null && prod.getId_producto() != null){
					model.addAttribute("prod", prod.getId_producto());
					model.addAttribute("label", prod.getNombre() + " - " + prod.getReferencia());
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return new ModelAndView("producto/ProductoAdmin","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String prod = request.getParameter("prodV");
		BigDecimal venta = new BigDecimal(request.getParameter("ventaF") != null && !request.getParameter("ventaF").trim().isEmpty() ? request.getParameter("ventaF") : "0");
		Boolean mayorF = Boolean.valueOf(request.getParameter("mayorF") != null && request.getParameter("mayorF").trim().equals("true") ? "true" : "false");
		String clasificarF = request.getParameter("clasificarF");
		
		ProductoExample prodExample = new ProductoExample();
		prodExample.setLimit(Inicio + ", " + Final);
		prodExample.setOrderByClause("id_producto DESC");
		
		ProductoExample.Criteria criteria = (ProductoExample.Criteria) prodExample.createCriteria();
		try{
			if(prod != null && !prod.trim().isEmpty())
				criteria.andId_productoEqualTo(Integer.parseInt(prod));
			if(clasificarF != null && !clasificarF.trim().isEmpty() && !clasificarF.trim().equals("0"))
				criteria.andId_tipo_productoEqualTo(Integer.parseInt(clasificarF));
			if(mayorF && venta != null && venta.toBigInteger().intValue() > 0)
				criteria.andVentaGreaterThanOrEqualTo(venta);
			else if(venta != null && venta.toBigInteger().intValue() > 0)
				criteria.andVentaLessThanOrEqualTo(venta);
				
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getProductoMapper().selectByExample(prodExample)));
			opciones.put("total", ColseviDao.getInstance().getProductoMapper().countByExample(prodExample));
		}catch(Exception e){
			logger.error(e.getMessage());
			opciones.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, opciones);
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Producto> listaProd){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		JSONObject labels = new JSONObject();
		
		if(listaProd != null && listaProd.size() >0){
			for (Producto bean : listaProd) {
				try{
					
					opciones = new JSONObject();
					labels = new JSONObject();
					opciones.put("id_producto", bean.getId_producto());
					opciones.put("nombre", bean.getNombre());
					opciones.put("descripcion", bean.getDescripcion());
					opciones.put("referencia", bean.getReferencia());
					opciones.put("ventaf", UtilidadManager.MonedaVista(bean.getVenta()));
					opciones.put("venta", bean.getVenta());
					opciones.put("imagen", bean.getImagen());
					opciones.put("cantidadMin", bean.getCantidadMin());
					
					if(bean.getId_tipo_producto() != null){
						labels.put("label", ColseviDao.getInstance().getTipoProductoMapper().selectByPrimaryKey(bean.getId_tipo_producto()).getNombre());
						labels.put("value", bean.getId_tipo_producto());
					}else{
						labels.put("label", "");
						labels.put("value", "0");
					}
					opciones.put("tipoP", labels);
					
					resultado.add(opciones);
				
				}catch(Exception e){
					logger.error(e.getMessage());
					continue;
				}
			}
		}
		return resultado;
	}
	
	public Object[] validarGuardar(HttpServletRequest request){
		
		Object[] obj = new Object[3];
		List<IngredienteXProducto> ixp = new ArrayList<IngredienteXProducto>();
		IngredienteXProducto ixpB = null;
		Producto bean = new Producto();
		String error = "";
		
		try{
			if(request.getParameter("id_producto") != null && !request.getParameter("id_producto").trim().isEmpty()){
				bean.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
			}
			if(request.getParameter("nombre") != null && !request.getParameter("nombre").trim().isEmpty()){
				bean.setNombre(request.getParameter("nombre"));
			}else{
				error += "Ingresar el nombre<br/>";
			}
			if(request.getParameter("descripcion") != null && !request.getParameter("descripcion").trim().isEmpty()){
				bean.setDescripcion(request.getParameter("descripcion"));
			}else{
				error += "Ingresar el descripcion<br/>";
			}
			if(request.getParameter("referencia") != null && !request.getParameter("referencia").trim().isEmpty()){
				bean.setReferencia(request.getParameter("referencia"));
			}else{
				error += "Ingresar el referencia<br/>";
			}
			if(request.getParameter("tipoP") != null && !request.getParameter("tipoP").trim().isEmpty() && !request.getParameter("tipoP").equals("0")){
				bean.setId_tipo_producto(Integer.parseInt(request.getParameter("tipoP")));
			}else{
				error += "Seleccionar un tipo de producto<br/>";
			}
			if(request.getParameter("venta") != null && !request.getParameter("venta").trim().isEmpty()){
				bean.setVenta(UtilidadManager.MonedaBD(request.getParameter("venta")));
			}else{
				error += "Ingresar el venta<br/>";
			}
			if(!request.getParameter("cantidadMin").trim().isEmpty())
				bean.setCantidadMin(Integer.parseInt(request.getParameter("cantidadMin")));
			else
				error += "Ingresar la cantidad";
			
			String[] ingrediente = request.getParameterValues("idIng");
			String[] cantidad = request.getParameterValues("cant");
			String[] umedida = request.getParameterValues("tipo");
			
			
			if(ingrediente.length > 0){
				for(int i = 0; i < ingrediente.length; i++){
					ixpB = new IngredienteXProducto();
					ixpB.setId_ingrediente(Integer.parseInt(ingrediente[i]));
					ixpB.setCantidad(Integer.parseInt(cantidad[i]));
					ixpB.setId_unidad_peso(Integer.parseInt(umedida[i]));
					ixpB.setId_producto(bean.getId_producto());

					ixp.add(ixpB);
				}
			}else
				error += "No hay detalle seleccionado";
			
			if(ixp != null && ixp.size() < 1)
				error = "No hay detalle seleccionado";
				
		}catch(Exception e){
			logger.error(e.getMessage());
			error += "Contactar al administrador";
		}
		
		obj[0] = error;
		obj[1] = bean;
		obj[2] = ixp;
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		SqlSession sesion = ColseviDaoTransaccion.getInstance();
		Producto bean = null;
		List<IngredienteXProducto> listaIngProd = null;
		Object[] obj = validarGuardar(request);
		
		try{
			 
			if(obj[0] != null && !obj[0].toString().isEmpty()){
				modelo.addAttribute("error", obj[0]);
				return Producto(request, modelo);
			}
			bean = (Producto) obj[1];
			listaIngProd = (List<IngredienteXProducto>) obj[2];
			
			if(bean.getId_producto() != null){
				ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.producto.map.ProductoMapper.updateByPrimaryKeySelective", bean);
				modelo.addAttribute("correcto", "Producto Actualizado");
			}else{
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.producto.map.ProductoMapper.insertSelective", bean);
				modelo.addAttribute("correcto", "Producto Insertado");
			}
			
			IngredienteXProductoExample ingProdE = new IngredienteXProductoExample();
			ingProdE.createCriteria().andId_productoEqualTo(bean.getId_producto());
			ColseviDaoTransaccion.Eliminar(sesion, "com.colsevi.dao.producto.map.IngredienteXProductoMapper.deleteByExample", ingProdE);
			
			for(IngredienteXProducto ingProd : listaIngProd){
				ingProd.setId_producto(bean.getId_producto());
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.producto.map.IngredienteXProductoMapper.insertSelective", ingProd);
			}
		
			ColseviDaoTransaccion.RealizarCommit(sesion);
		}catch (Exception e) {
			logger.error(e.getMessage());
			modelo.addAttribute("error", "Contactar al administrador");
			ColseviDaoTransaccion.ErrorRollback(sesion);
		}
		
		ColseviDaoTransaccion.CerrarSesion(sesion);
		return Producto(request, modelo);
	}
	
	@RequestMapping("/Eliminar")
	public ModelAndView Eliminar(HttpServletRequest request, ModelMap modelo){
		
		try{
			Integer id_producto = Integer.parseInt(request.getParameter("id_producto"));
			
			IngredienteXProductoExample ixpE = new IngredienteXProductoExample();
			ixpE.createCriteria().andId_productoEqualTo(id_producto);
			if(ColseviDao.getInstance().getIngredienteXProductoMapper().countByExample(ixpE) > 0){
				modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a un Ingrediente");
				return Producto(request, modelo);
			}
			
			DetallePedidoExample dpE = new DetallePedidoExample();
			dpE.createCriteria().andId_productoEqualTo(id_producto);
			if(ColseviDao.getInstance().getDetallePedidoMapper().countByExample(dpE) > 0){
				modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a un Pedido");
				return Producto(request, modelo);
			}
			
			InventarioExample iE = new InventarioExample();
			iE.createCriteria().andId_productoEqualTo(id_producto);
			if(ColseviDao.getInstance().getInventarioMapper().countByExample(iE) > 0){
				modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a un Inventario");
				return Producto(request, modelo);
			}
			
			CatalogoXProductoExample cxpE = new CatalogoXProductoExample();
			cxpE.createCriteria().andId_productoEqualTo(id_producto);
			if(ColseviDao.getInstance().getCatalogoXProductoMapper().countByExample(cxpE) > 0){
				modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a un cat�logo");
				return Producto(request, modelo);
			}
			
			RecetaExample rE = new RecetaExample();
			rE.createCriteria().andId_productoEqualTo(id_producto);
			if(ColseviDao.getInstance().getRecetaMapper().countByExample(rE) > 0){
				modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a una Receta");
				return Producto(request, modelo);
			}
			
			ColseviDao.getInstance().getProductoMapper().deleteByPrimaryKey(id_producto);
			modelo.addAttribute("correcto", "Producto Eliminado");
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return Producto(request, modelo);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/cargarIng")
	public void cargarIng(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			mapa.put("producto", request.getParameter("producto"));
			result.put("dato", subIng(ColseviDao.getInstance().getIngredienteXProductoMapper().SelectDataView(mapa)));
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, result);
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray subIng(List<Map<String, Object>> listaCXI){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaCXI){
			try{
				opciones = new JSONObject();
				opciones.put("id_ingrediente", map.get("id_ingrediente"));
				opciones.put("id_tipo_peso", map.get("id_unidad_peso"));
				opciones.put("nombreIng", map.get("nombreIng"));
				opciones.put("nombreTp", map.get("nombreTp"));
				opciones.put("cantidad", map.get("cantidad"));
				
				resultado.add(opciones);
				
			}catch(Exception e){
				continue;
			}
		}
		return resultado;
	}
	
	@RequestMapping("/buscarProd")
	public void auto(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();

		try{
			String producto = request.getParameter("campo");
			result = ProductoManager.AutocompletarProducto(producto);

			if(result == null)
				result = new JSONObject();
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		ResponseJson(request, response, result);
	}
	
	@RequestMapping("/autocompletar")
	public void autoIng(HttpServletRequest request, HttpServletResponse response){
		try{
			JSONObject result = new JSONObject();
			
			String ing = request.getParameter("campo");
			result = ingredienteManager.AutocompletarIngrediente(ing);
			
			if(result != null){
				response.setContentType("text/html;charset=ISO-8859-1");
				request.setCharacterEncoding("UTF8");
				
				result.writeJSONString(response.getWriter());
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ProdInCatalog")
	public void ProdInCatalog(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		JSONArray options = new JSONArray();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			mapa.put("producto", request.getParameter("producto"));
			List<Map<String, Object>> listProdCatalog = ColseviDao.getInstance().getProductoMapper().ProdInCatalog(mapa);
			if(listProdCatalog != null && listProdCatalog.size() > 0){
				Map<String, Object> map = listProdCatalog.get(0);
				String[] Array = map.get("id_catalogo").toString().split(",");
				for(String sub: Array){
					options.add(sub);
				}
			}
			result.put("datos", options);			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		ResponseJson(request, response, result);
	}
	
}