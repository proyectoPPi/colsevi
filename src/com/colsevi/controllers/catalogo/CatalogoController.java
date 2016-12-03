package com.colsevi.controllers.catalogo;

import java.io.IOException;
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
import com.colsevi.dao.catalogo.model.Catalogo;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.catalogo.model.CatalogoExample;
import com.colsevi.dao.catalogo.model.CatalogoXProductoExample;
import com.colsevi.dao.catalogo.model.CatalogoXProductoKey;
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.EstablecimientoExample;
import com.colsevi.dao.producto.model.Producto;
import com.colsevi.dao.producto.model.ProductoExample;

@Controller
@RequestMapping("/Catalogo/Cat")
public class CatalogoController extends BaseConfigController{

	private static final long serialVersionUID = 4577401405301562904L;
	private static Logger logger = Logger.getLogger(CatalogoController.class);	
	
	@RequestMapping
	public ModelAndView Catalogo(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaEst", listaEstablecimiento());
		return new ModelAndView("catalogo/Catalogo","col",getValoresGenericos(request));
	}
	
	public static List<Establecimiento> listaEstablecimiento(){
		return ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(new EstablecimientoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();

		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			String nombre = request.getParameter("nombreF");
			String EstablecimientoF = request.getParameter("estF");
			
			CatalogoExample CatExample = new CatalogoExample();
			CatalogoExample.Criteria criteria = (CatalogoExample.Criteria) CatExample.createCriteria();
			CatExample.setOrderByClause("id_catalogo DESC");
			
			CatExample.setLimit(Inicio + ", " + Final);
			if(nombre != null && !nombre.trim().isEmpty()){
				criteria.andNombreLike("%" + nombre + "%");   
			}
			
			if(EstablecimientoF != null  && !EstablecimientoF.trim().isEmpty() && !EstablecimientoF.trim().equals("0")){
				criteria.andId_establecimientoEqualTo(Integer.parseInt(EstablecimientoF));
			}
			
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getCatalogoMapper().selectByExample(CatExample)));
			opciones.put("total", ColseviDao.getInstance().getCatalogoMapper().countByExample(CatExample));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Catalogo> listCat){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		JSONObject labels = new JSONObject();
		
		if(listCat != null && listCat.size() >0){
			for (Catalogo bean : listCat) {
				opciones = new JSONObject();
				labels = new JSONObject();
				opciones.put("id_catalogo", bean.getId_catalogo());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());
				if(bean.getId_establecimiento() != null){
					labels.put("label",ColseviDao.getInstance().getEstablecimientoMapper().selectByPrimaryKey(bean.getId_establecimiento()).getNombre());
					labels.put("value", bean.getId_establecimiento());
					opciones.put("est", labels);
				}
				resultado.add(opciones);
			}
		}
		return resultado;
	}
	
	public Object[] validarCatalogo(HttpServletRequest request){
		Object[] obj = new Object[2];
		String result = "";
		List<Integer> ListaC = new ArrayList<Integer>();
		try{
			String checked = request.getParameter("catalogActive");
			
			if(checked != null && !checked.isEmpty()){
				String[] c = checked.split(",");
				for(String ch: c){
					ListaC.add(Integer.parseInt(ch));
				}
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
			result = "Contactar al administrador";
		}
		
		obj[0] = result;
		obj[1] = ListaC;
		
		return obj;
	}
	
	public Object[] validarGuardar(HttpServletRequest request){
		Object[] obj = new Object[2];
		String error = "";
		Catalogo bean = new Catalogo();
		
		try{
			if(request.getParameter("id_catalogo") != null && !request.getParameter("id_catalogo").trim().isEmpty())
				bean.setId_catalogo(Integer.parseInt(request.getParameter("id_catalogo")));
			
			
			if(request.getParameter("est") == null || request.getParameter("est").trim().isEmpty() || request.getParameter("est").equals("0")){
				error += "Seleccionar el Establecimiento <br/>";
			}else{
				bean.setId_establecimiento(Integer.parseInt(request.getParameter("est")));
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
		}catch(Exception e){
			logger.error(e.getMessage());
			error = "Contacte al administrador";
		}
		
		obj[0] = error;
		obj[1] = bean;
		
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public ModelAndView GuardarCatalogo(HttpServletRequest request, ModelMap modelo, Catalogo bean){
		String error = "";
		List<Integer> ListaP = new ArrayList<Integer>();

		try{
			
			Object[] result = validarGuardar(request);
			if(!error.isEmpty()){
				modelo.addAttribute("error", error);
				return Catalogo(request, modelo);
			}
			
			bean = (Catalogo) result[1];
			
			result = validarCatalogo(request);
			if(!result[0].toString().isEmpty()){
				modelo.addAttribute("error", result[0]);
				return Catalogo(request, modelo);
			}
			
			ListaP = (List<Integer>) result[1];
			
			SqlSession sesion = ColseviDaoTransaccion.getInstance("/TransaccionCatalogo.xml");
			
			if(bean.getId_catalogo() != null){
				ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.catalogo.map.CatalogoMapper.updateByPrimaryKeySelective", bean);
				modelo.addAttribute("correcto", "Catalogo Actualizado");
			}else{
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.catalogo.map.CatalogoMapper.insertSelective", bean);
				modelo.addAttribute("correcto", "Catalogo insertado");
			}
			
			CatalogoXProductoExample CXPE = new CatalogoXProductoExample();
			CXPE.createCriteria().andId_productoEqualTo(bean.getId_catalogo());
			ColseviDaoTransaccion.Eliminar(sesion, "com.colsevi.dao.catalogo.map.CatalogoXProductoMapper.deleteByExample", CXPE);
			
			for(Integer c: ListaP){
				CatalogoXProductoKey cxp = new CatalogoXProductoKey();
				cxp.setId_catalogo(c);
				cxp.setId_producto(bean.getId_catalogo());
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.catalogo.map.CatalogoXProductoMapper.insert", cxp);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Catalogo(request, modelo);
	}
	
	
	@RequestMapping("/Eliminar")
	public ModelAndView EliminarCatalogo(HttpServletRequest request, ModelMap modelo){
		
		try{
			Integer id = Integer.parseInt(request.getParameter("id_catalogo"));
			if(id != null){
				
				CatalogoXProductoExample CXPE = new CatalogoXProductoExample();
				CXPE.createCriteria().andId_catalogoEqualTo(id);
				Integer count = ColseviDao.getInstance().getCatalogoXProductoMapper().countByExample(CXPE);
				if(!count.equals(0)){
					modelo.addAttribute("error", "No se puede eliminar, tiene productos asociados");	
				}else{
					ColseviDao.getInstance().getCatalogoMapper().deleteByPrimaryKey(id);
					modelo.addAttribute("correcto", "Catalogo Eliminado");
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			modelo.addAttribute("error", "Contacte al Administrador");
		}
		
		return Catalogo(request, modelo);
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
	@RequestMapping("/ListaCatalogoPosibleProducto")
	public void ListaCatalogoPosibleProducto(HttpServletRequest request, HttpServletResponse response) throws IOException{

		JSONArray catalogo = new JSONArray();
		JSONObject labels2 = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		List<Integer> prodL = new ArrayList<Integer>();
		
		try{
			if(request.getParameter("catalogo") != null){
				mapa.put("catalogo", request.getParameter("catalogo"));
				List<Map<String, Object>> listProdCatalog = ColseviDao.getInstance().getCatalogoMapper().ListaCatalogoPosibleProducto(mapa);
				for(Map<String, Object> map: listProdCatalog ){
					labels2 = new JSONObject();
					prodL.add(Integer.parseInt(map.get("id_producto").toString()));
					labels2.put("id", map.get("id_producto"));
					labels2.put("nombre", map.get("nombre"));
					labels2.put("select", "");
					catalogo.add(labels2);
				}
			}
			
			ProductoExample EX = new ProductoExample();
			ProductoExample.Criteria criteria = (ProductoExample.Criteria) EX.createCriteria();
			if(prodL.size() > 0)
				criteria.andId_productoNotIn(prodL);
			List<Producto> result = ColseviDao.getInstance().getProductoMapper().selectByExample(EX);
			for(Producto cat: result){
				labels2 = new JSONObject();
				labels2.put("id", cat.getId_producto());
				labels2.put("nombre", cat.getNombre());
				catalogo.add(labels2);
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		ResponseArray(request, response, catalogo);
	}
}
