package com.colsevi.controllers.producto;

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
import com.colsevi.dao.producto.model.Producto;
import com.colsevi.dao.producto.model.Receta;
import com.colsevi.dao.producto.model.DificultadReceta;
import com.colsevi.dao.producto.model.DificultadRecetaExample;
import com.colsevi.dao.producto.model.PreparacionReceta;
import com.colsevi.dao.producto.model.PreparacionRecetaExample;

@Controller
public class RecetaController extends BaseConfigController{

	private static final long serialVersionUID = -5237605245293196719L;

	@RequestMapping("/Recetario")
	public ModelAndView Recetario(HttpServletRequest request, ModelMap model){
		model.addAttribute("ListaD", getDificultad());
		return new ModelAndView("producto/Recetario", "col", getValoresGenericos(request));
	}
	
	public static List<DificultadReceta> getDificultad(){
		return ColseviDao.getInstance().getDificultadRecetaMapper().selectByExample(new DificultadRecetaExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Recetario/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		mapa.put("limit",Inicio + ", " + Final);
		
		result.put("datos", ConstruirJson(ColseviDao.getInstance().getRecetaMapper().SelectDataView(mapa)));
		result.put("total", 0);
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String, Object>> listPedido){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listPedido != null && listPedido.size() >0){
			for (Map<String, Object> map : listPedido) {
				try{
					opciones = new JSONObject();
					opciones.put("id_receta", map.get("id_receta").toString());
					opciones.put("id_producto", map.get("id_producto"));
					opciones.put("nombreProd", map.get("nombreProd"));
					opciones.put("id_dificultad_receta", map.get("id_dificultad_receta"));
					opciones.put("nombreDif", map.get("nombreDif"));
					opciones.put("icono", map.get("icono"));
					opciones.put("tiempo", map.get("tiempo"));
					
					resultado.add(opciones);
				}catch(Exception e){
					continue;
				}
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Recetario/detalle")
	public void detalle(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Integer id = Integer.parseInt(request.getParameter("id"));

		result.put("detalle", ConstruirDetalle(id));
		result.put("producto", Producto(id));
		result.put("receta", receta(id));
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject receta(Integer id){
		JSONObject result = new JSONObject();
		Receta rec = ColseviDao.getInstance().getRecetaMapper().selectByPrimaryKey(id);
		result.put("tiempo", rec.getTiempo());
		result.put("difi", rec.getId_dificultad_receta());
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject Producto(Integer id){
		JSONObject result = new JSONObject();
		
		id = ColseviDao.getInstance().getRecetaMapper().selectByPrimaryKey(id).getId_producto();
		Producto bean = ColseviDao.getInstance().getProductoMapper().selectByPrimaryKey(id);
		result.put("id_prod", bean.getId_producto());
		result.put("nombreProd", bean.getNombre());
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirDetalle(Integer id){
		JSONArray result = new JSONArray();
		JSONObject options = new JSONObject();
		
		PreparacionRecetaExample PRE = new PreparacionRecetaExample();
		PRE.createCriteria().andId_recetaEqualTo(id);
		List<PreparacionReceta> ListaP = ColseviDao.getInstance().getPreparacionRecetaMapper().selectByExample(PRE);
		
		for(PreparacionReceta bean: ListaP){
			try{
				options = new JSONObject();
				options.put("id_preparacion", bean.getId_preparacion_receta());
				options.put("texto", bean.getPreparacion());
				result.add(options);
			}catch(Exception e){
				continue;
			}
		}
		
		return result;
	}
}
