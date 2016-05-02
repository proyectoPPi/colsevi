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
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.producto.model.Producto;
import com.colsevi.dao.producto.model.Receta;
import com.colsevi.dao.producto.model.RecetaExample;
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
	
	@RequestMapping("/Recetario/Guardar")
	public ModelAndView guardar(HttpServletRequest request, ModelMap model){
		
		try{
			Object[] result = validarGuardado(request);
			
			if(!result[0].toString().isEmpty()){
				model.addAttribute("error", result[0]);
				return Recetario(request, model);
			}
			
			Receta rec = (Receta) result[1];
			List<PreparacionReceta> listaP = (List<PreparacionReceta>) result[2];
			
			if(rec.getId_receta() == null){
				
				ColseviDao.getInstance().getRecetaMapper().insert(rec);
				
				RecetaExample RE = new RecetaExample();
				RE.setLimit("1");
				RE.setOrderByClause("id_receta DESC");
				rec.setId_receta(ColseviDao.getInstance().getRecetaMapper().selectByExample(RE).get(0).getId_receta());
				
				model.addAttribute("correcto", "Receta creada");
			}else{
				PreparacionRecetaExample PRE = new PreparacionRecetaExample();
				PRE.createCriteria().andId_recetaEqualTo(rec.getId_receta());
				ColseviDao.getInstance().getPreparacionRecetaMapper().deleteByExample(PRE);
				
				ColseviDao.getInstance().getRecetaMapper().updateByPrimaryKey(rec);
				
				model.addAttribute("correcto", "Receta actualizada");
			}

			for (PreparacionReceta bean : listaP) {
				
				bean.setId_receta(rec.getId_receta());
				ColseviDao.getInstance().getPreparacionRecetaMapper().insert(bean);
			}
			
		}catch(Exception e){
			model.addAttribute("error", "Contactar al administrador");
		}
		
		return Recetario(request, model);
	}
	
	public Object[] validarGuardado(HttpServletRequest request){
		
		Object[] obj = new Object[3];
		
		try{
			Receta rece = new Receta();
			PreparacionReceta preparacion = new PreparacionReceta();
			List<PreparacionReceta> ListaP = new ArrayList<PreparacionReceta>();
	
			String error = "";
			
			if(request.getParameter("id_receta") != null && !request.getParameter("id_receta").trim().isEmpty())
				rece.setId_receta(Integer.parseInt(request.getParameter("id_receta")));
			
			if(request.getParameter("id_producto") != null && !request.getParameter("id_producto").trim().isEmpty() && !request.getParameter("id_producto").trim().isEmpty())
				rece.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
			else
				error += "Seleccionar un producto<br/>";
		
			if(request.getParameter("dificultad") != null && !request.getParameter("dificultad").trim().isEmpty() && !request.getParameter("dificultad").trim().equals("0"))
				rece.setId_dificultad_receta(Integer.parseInt(request.getParameter("dificultad")));
			else
				error += "Seleccionar una difciltad de receta<br/>";
			
			if(request.getParameter("tiempo") != null && !request.getParameter("tiempo").trim().isEmpty())
				rece.setTiempo(request.getParameter("tiempo"));
			else
				error += "Ingresar tiempo de receta en minutos<br/>";
			
			Integer secuencia = Integer.parseInt(request.getParameter("secuencia")), sw = 0;
			
			for(int i=1;i<=secuencia && sw == 0; i++){
				preparacion = new PreparacionReceta();
				
				if(request.getParameter("texto" + i) != null && !request.getParameter("texto" + i).trim().isEmpty())
					preparacion.setPreparacion(request.getParameter("texto" + i));
				else
					sw = 1;
				ListaP.add(preparacion);
			}
			
			if(sw==1)
				error += "Ingresar los apartes de la preparación";
			
			obj[0] = error;
			obj[1] = rece;
			obj[2] = ListaP;
			
		}catch(Exception e){
			obj[0] = "Contactar al administrador";
			return null;
		}
		
		return obj;
	}

}
