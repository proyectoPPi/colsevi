package com.colsevi.controllers.catalogo;

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
import com.colsevi.dao.catalogo.model.Catalogo;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.catalogo.model.CatalogoExample;
import com.colsevi.dao.general.model.Direccion;
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.EstablecimientoExample;
import com.colsevi.dao.general.model.Telefono;
import com.colsevi.dao.general.model.TipoTelefono;
import com.colsevi.dao.general.model.TipoTelefonoExample;
import com.colsevi.dao.proveedor.model.CompraExample;
import com.colsevi.dao.proveedor.model.Proveedor;
import com.colsevi.dao.proveedor.model.ProveedorExample;
import com.sun.org.apache.xml.internal.resolver.Catalog;
@Controller
public class CatalogoController extends BaseConfigController{

	private static final long serialVersionUID = 4577401405301562904L;
	
	@RequestMapping("/Catalogo/Cat")
	public ModelAndView Catalogo(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaEst", listaEstablecimiento());
		return new ModelAndView("catalogo/Catalogo","col",getValoresGenericos(request));
	}
	
	public static List<Establecimiento> listaEstablecimiento(){
		return ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(new EstablecimientoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Catalogo/Cat/tabla")
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
			e.printStackTrace();
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
	
	@RequestMapping("/Catalogo/Cat/Guardar")
	public ModelAndView GuardarCatalogo(HttpServletRequest request, ModelMap modelo, Catalogo bean){
		String error = "";
		Catalogo beanC = new Catalogo();
		
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
			
			
			if(!error.isEmpty()){
				modelo.addAttribute("error", error);
				return Catalogo(request, modelo);
			}

			
			if(bean.getId_catalogo() != null){
				ColseviDao.getInstance().getCatalogoMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Proveedor Actualizado");
			}else{
				ColseviDao.getInstance().getCatalogoMapper().insert(bean);
				modelo.addAttribute("correcto", "Proveedor insertado");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Catalogo(request, modelo);
	}
	
	
	@RequestMapping("/Catalogo/Cat/Eliminar")
	public ModelAndView EliminarCatalogo(HttpServletRequest request, ModelMap modelo){
		
		try{
			Integer id = Integer.parseInt(request.getParameter("id_catalogo"));
			if(id != null){
				
//				CompraExample compra = new CompraExample();
//				compra.createCriteria().andId_compraEqualTo(id);
//				Integer dataCruce = ColseviDao.getInstance().getCompraMapper().countByExample(compra);
//				if(dataCruce != null && dataCruce > 0){
//					modelo.addAttribute("error", "No se puede eliminar, ya que se encuentra asociado a una compra");
//				}else{
					ColseviDao.getInstance().getCatalogoMapper().deleteByPrimaryKey(id);
					modelo.addAttribute("correcto", "Catalogo Eliminado");
//				}
			}
		}catch(Exception e){
			modelo.addAttribute("error", "Contacte al Administrador");
		}
		
		return Catalogo(request, modelo);
	}
	

}
