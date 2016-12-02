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
import com.colsevi.application.GeneralManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.application.ingredienteManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.catalogo.model.Catalogo;
import com.colsevi.dao.catalogo.model.CatalogoExample;
import com.colsevi.dao.catalogo.model.CatalogoXProductoExample;
import com.colsevi.dao.catalogo.model.CatalogoXProductoKey;
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.UnidadPeso;
import com.colsevi.dao.general.model.UnidadPesoExample;
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
			error = "Contactar al administrador";
		}
		
		obj[0] = error;
		obj[1] = bean;
		obj[2] = ixp;
		
		return obj;
	}
	
	public Object[] validarCatalogo(HttpServletRequest request){
		Object[] obj = new Object[3];
		String result = "";
		List<Integer> ListaC = new ArrayList<Integer>(), ListaN = new ArrayList<Integer>();
		try{
			String checked = request.getParameter("catalogActive");
			String Nochecked = request.getParameter("catalogNoActive");
			
			if(checked != null && !checked.isEmpty()){
				String[] c = checked.split(",");
				for(String ch: c){
					ListaC.add(Integer.parseInt(ch));
				}
			}
			
			if(Nochecked != null && !Nochecked.isEmpty()){
				String[] n = Nochecked.split(",");
				for(String ch: n){
					ListaN.add(Integer.parseInt(ch));
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			result = "Contactar al administrador";
		}
		
		obj[0] = result;
		obj[1] = ListaC;
		obj[2] = ListaN;
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/preprocesador")
	public void preprocesador(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		try{
			Object[] validacion = validarGuardar(request);
			
			if(!validacion[0].toString().isEmpty()){
				result.put("error", validacion[0]);
			}
			validacion = validarCatalogo(request);
			if(!validacion[0].toString().isEmpty()){
				result.put("error", validacion[0]);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("error", "Contactar al administrador");
		}
		
		ResponseJson(request, response, result);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		validarCatalogo(request);
		SqlSession sesion = ColseviDaoTransaccion.getInstance();
		Producto bean = null;
		List<IngredienteXProducto> listaIngProd = null;
		List<Integer> ListaC = new ArrayList<Integer>(), ListaN = new ArrayList<Integer>();
		Object[] obj = validarGuardar(request);
		
		try{
			 
			if(obj[0] != null && !obj[0].toString().isEmpty()){
				modelo.addAttribute("error", obj[0]);
				return Producto(request, modelo);
			}
			bean = (Producto) obj[1];
			listaIngProd = (List<IngredienteXProducto>) obj[2];
			
			obj = validarCatalogo(request);
			if(obj[0] != null && !obj[0].toString().isEmpty()){
				modelo.addAttribute("error", obj[0]);
				return Producto(request, modelo);
			}
			ListaC = (List<Integer>) obj[1];
			ListaN = (List<Integer>) obj[2];
			
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
			
			CatalogoXProductoExample CXPE = new CatalogoXProductoExample();
			CXPE.createCriteria().andId_productoEqualTo(bean.getId_producto());
			ColseviDaoTransaccion.Eliminar(sesion, "com.colsevi.dao.catalogo.map.CatalogoXProductoMapper.deleteByExample", CXPE);
			
			for(Integer c: ListaC){
				CatalogoXProductoKey cxp = new CatalogoXProductoKey();
				cxp.setId_catalogo(c);
				cxp.setId_producto(bean.getId_producto());
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.catalogo.map.CatalogoXProductoMapper.insert", bean);
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
				opciones.put("id_unidad_medida", map.get("id_unidad_medida"));
				opciones.put("nombreIng", map.get("nombreIng"));
				opciones.put("nombreTp", map.get("nombreTp"));
				opciones.put("cantidad", map.get("cantidad"));

				String html = "<option value='0'>Seleccione</option>";
				if(map.get("id_unidad_medida") != null){
					UnidadPesoExample UPX = new UnidadPesoExample();
					UPX.createCriteria().andId_unidad_medidaEqualTo(Integer.parseInt(map.get("id_unidad_medida").toString()));
					List<UnidadPeso> listaPeso = ColseviDao.getInstance().getUnidadPesoMapper().selectByExample(UPX);
					for(UnidadPeso bean: listaPeso){
						if(bean.getId_unidad_peso().equals(map.get("id_unidad_peso"))){
							html += "<option value='" + bean.getId_unidad_peso() + "' selected>" + bean.getNombre() + "</option>";
						}else{
							html += "<option value='" + bean.getId_unidad_peso() + "'>" + bean.getNombre() + "</option>";
						}
					}
				}
				opciones.put("medida", html);
				
				resultado.add(opciones);
				
			}catch(Exception e){
				continue;
			}
		}
		return resultado;
	}
	
	@RequestMapping("/autocompletar")
	public void autoIng(HttpServletRequest request, HttpServletResponse response){
		try{
			JSONObject result = new JSONObject();
			String ing = request.getParameter("campo");
			result = ingredienteManager.AutocompletarIngrediente(ing);
			
			if(result != null)
				ResponseJson(request, response, result);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/MedidaDetalle")
	public void MedidaDetalle(HttpServletRequest request, HttpServletResponse response){
		try{
			JSONObject opt = new JSONObject();
			JSONArray result = new JSONArray();
			Integer medida = Integer.parseInt(request.getParameter("medida"));

			UnidadPesoExample UPX = new UnidadPesoExample();
			UPX.createCriteria().andId_unidad_medidaEqualTo(medida);
			List<UnidadPeso> listaPeso = ColseviDao.getInstance().getUnidadPesoMapper().selectByExample(UPX);
			for(UnidadPeso bean: listaPeso){
				opt = new JSONObject();
				opt.put("id", bean.getId_unidad_peso());
				opt.put("nombre", bean.getNombre());
				
				result.add(opt);
			}
			
			ResponseArray(request, response, result);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/ListaCatalogoPosibleProducto")
	public void ListaCatalogoPosibleProducto(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONArray catalogo = new JSONArray();
		JSONObject labels2 = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Integer> catList = new ArrayList<Integer>();
		
		try{
			mapa.put("producto", request.getParameter("producto"));
			List<Map<String, Object>> listProdCatalog = ColseviDao.getInstance().getProductoMapper().ListaCatalogoPosibleProducto(mapa);
			for(Map<String, Object> map: listProdCatalog ){
				labels2 = new JSONObject();
				catList.add(Integer.parseInt(map.get("id_catalogo").toString()));
				labels2.put("id", map.get("id_catalogo"));
				labels2.put("nombreC", map.get("nombre"));
				labels2.put("select", "");
				catalogo.add(labels2);
			}
			
			CatalogoExample EX = new CatalogoExample();
			CatalogoExample.Criteria criteria = (CatalogoExample.Criteria) EX.createCriteria();
			if(catList.size() > 0)
				criteria.andId_catalogoNotIn(catList);
			List<Catalogo> listaCat = ColseviDao.getInstance().getCatalogoMapper().selectByExample(EX);
			for(Catalogo cat: listaCat){
				labels2 = new JSONObject();
				labels2.put("id", cat.getId_catalogo());
				labels2.put("nombreC", cat.getNombre());
				catalogo.add(labels2);
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		ResponseArray(request, response, catalogo);
	}
	
}