package com.colsevi.controllers.producto;

import java.io.IOException;
import java.util.ArrayList;
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
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.producto.model.ProductoExample;
import com.colsevi.dao.producto.model.RecetaExample;
import com.colsevi.dao.catalogo.model.CatalogoXProductoExample;
import com.colsevi.dao.producto.model.ClasificarIngrediente;
import com.colsevi.dao.producto.model.ClasificarIngredienteExample;
import com.colsevi.dao.general.model.UnidadPeso;
import com.colsevi.dao.general.model.UnidadPesoExample;
import com.colsevi.dao.inventario.model.InventarioExample;
import com.colsevi.dao.pedido.model.DetallePedidoExample;
import com.colsevi.dao.producto.model.Ingrediente;
import com.colsevi.dao.producto.model.IngredienteExample;
import com.colsevi.dao.producto.model.IngredienteXProducto;
import com.colsevi.dao.producto.model.IngredienteXProductoExample;
import com.colsevi.dao.producto.model.Producto;

@Controller
public class ProductoAdminController extends BaseConfigController {

	private static final long serialVersionUID = 4997906906136000223L;

	@RequestMapping("/Producto/Admin")
	public ModelAndView Producto(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaTipo", UtilidadManager.tipoProducto());
		model.addAttribute("listaClasificar", getClasificar());
		model.addAttribute("listaTipoPeso", getTipoPeso());
		
		return new ModelAndView("producto/ProductoAdmin","col",getValoresGenericos(request));
	}
	
	public static List<ClasificarIngrediente> getClasificar(){
		return ColseviDao.getInstance().getClasificarIngredienteMapper().selectByExample(new ClasificarIngredienteExample());
	}
	
	public static List<UnidadPeso> getTipoPeso(){
		return ColseviDao.getInstance().getUnidadPesoMapper().selectByExample(new UnidadPesoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Producto/Admin/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		String clasificarF = request.getParameter("clasificarF");
		
		ProductoExample prodExample = new ProductoExample();
		prodExample.setLimit(Inicio + ", " + Final);
		prodExample.setOrderByClause("id_producto DESC");
		
		ProductoExample.Criteria criteria = (ProductoExample.Criteria) prodExample.createCriteria();
		try{
			if(nombre != null && !nombre.trim().isEmpty()){
				criteria.andNombreLike("%" + nombre + "%");   
			}
			if(descripcion != null && !descripcion.trim().isEmpty()){
				criteria.andDescripcionLike("%" + descripcion + "%");   
			}
			if(clasificarF != null && !clasificarF.trim().isEmpty() && !clasificarF.trim().equals("0")){
				criteria.andId_tipo_productoEqualTo(Integer.parseInt(clasificarF));
			}
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getProductoMapper().selectByExample(prodExample)));
			opciones.put("total", ColseviDao.getInstance().getProductoMapper().countByExample(prodExample));
		}catch(Exception e){
			//error
		}

		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
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
					opciones.put("id_producto", bean.getId_producto());
					opciones.put("nombre", bean.getNombre());
					opciones.put("descripcion", bean.getDescripcion());
					opciones.put("referencia", bean.getReferencia());
					opciones.put("venta", bean.getVenta());
					opciones.put("imagen", bean.getImagen());
					
					if(bean.getId_tipo_producto() != null){
						labels = new JSONObject();
						labels.put("label",ColseviDao.getInstance().getTipoProductoMapper().selectByPrimaryKey(bean.getId_tipo_producto()).getNombre());
						labels.put("value", bean.getId_tipo_producto());
						opciones.put("tipoP", labels);
					}
					
					resultado.add(opciones);
				
				}catch(Exception e){
					continue;
				}
			}
			
		}
		return resultado;
	}
	
	@RequestMapping("/Producto/Admin/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		Producto bean = new Producto();
		List<IngredienteXProducto> ixp = new ArrayList<IngredienteXProducto>();
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
				bean.setVenta(UtilidadManager.FormatStringBigDecimal(request.getParameter("venta")));
			}else{
				error += "Ingresar el venta<br/>";
			}
			
			if(!error.isEmpty()){
				modelo.addAttribute("error", error);
				return Producto(request, modelo);
			}
			
			Integer count = Integer.parseInt(request.getParameter("count"));
			
			if(count != null && count > 0){
				for(int i = 0; i < count; i++){
					if(request.getParameter("idIng" + (i +1)) != null && !request.getParameter("idIng" + (i +1)).trim().isEmpty()){
						IngredienteXProducto ixpB = new IngredienteXProducto();
						ixpB.setId_ingrediente(Integer.parseInt(request.getParameter("idIng" + (i +1))));
						ixpB.setCantidad(Integer.parseInt(request.getParameter("cant" + (i +1))));
						ixpB.setId_unidad_peso(Integer.parseInt(request.getParameter("tipo" + (i +1))));
						ixpB.setId_producto(bean.getId_producto());

						ixp.add(ixpB);
					}
				}
			}else{
				modelo.addAttribute("error", "No hay detalle seleccionado");
				return Producto(request, modelo);
			}
			
			if(bean.getId_producto() != null){
				ColseviDao.getInstance().getProductoMapper().updateByPrimaryKeySelective(bean);
				modelo.addAttribute("correcto", "Producto Actualizado");
			}else{
				ColseviDao.getInstance().getProductoMapper().insertSelective(bean);
				
				ProductoExample pExample = new ProductoExample();
				pExample.setOrderByClause("id_producto DESC");
				pExample.setLimit("1");
				bean.setId_producto(ColseviDao.getInstance().getProductoMapper().selectByExample(pExample).get(0).getId_producto());
				
				modelo.addAttribute("correcto", "Producto Insertado");
			}
			
			if(ixp == null || ixp.size() < 1){
				modelo.addAttribute("error", "No hay detalle seleccionado");
				return Producto(request, modelo);
			}else{
				IngredienteXProductoExample IPK = new IngredienteXProductoExample();
				IPK.createCriteria().andId_productoEqualTo(bean.getId_producto());
				ColseviDao.getInstance().getIngredienteXProductoMapper().deleteByExample(IPK);
				
				for(IngredienteXProducto ingP : ixp){
					ingP.setId_producto(bean.getId_producto());
					ColseviDao.getInstance().getIngredienteXProductoMapper().insertSelective(ingP);
				}
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Producto(request, modelo);
	}
	
	@RequestMapping("/Producto/Admin/Eliminar")
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
				modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociada a un catálogo");
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
			
		}

		return Producto(request, modelo);
	}
	
	@RequestMapping("/Producto/Admin/ClasificarIng")
	public void ClasificarIng(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONArray result = new JSONArray();
		Integer id = 0;
		try{
			id = Integer.parseInt(request.getParameter("clasificar"));
		}catch(Exception e){
			return;
		}
		IngredienteExample ingExample = new IngredienteExample();
		ingExample.createCriteria().andId_clasificar_ingredienteEqualTo(id);
		
		result = ConstruirIngrediente(ColseviDao.getInstance().getIngredienteMapper().selectByExample(ingExample));

		result.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirIngrediente(List<Ingrediente> listaIng){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listaIng != null && listaIng.size() >0){
			for (Ingrediente bean : listaIng) {
				opciones = new JSONObject();
				opciones.put("id", bean.getId_ingrediente());
				opciones.put("nombre", bean.getNombre());
				resultado.add(opciones);
			}
		}
		return resultado;
	}


	@SuppressWarnings("unchecked")
	@RequestMapping("/Producto/Admin/cargarIng")
	public void cargarIng(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			mapa.put("producto", request.getParameter("producto"));
			result.put("dato", subIng(ColseviDao.getInstance().getIngredienteXProductoMapper().SelectDataView(mapa)));
		}catch(Exception e){
			result.put("error", "Contactar al administrador");
		}
		
		result.writeJSONString(response.getWriter());
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
}