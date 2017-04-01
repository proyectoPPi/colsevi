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
import com.colsevi.application.ProductoManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.producto.model.Ingrediente;
import com.colsevi.dao.producto.model.IngredienteExample;
import com.colsevi.dao.producto.model.IngredienteXProductoExample;

@Controller
@RequestMapping("/Ingrediente/Ing")
public class IngredienteController extends BaseConfigController {

	private static final long serialVersionUID = 8349230539753648934L;
	private static Logger logger = Logger.getLogger(IngredienteController.class);

	@RequestMapping
	public String Ingrediente(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaClasificar", ProductoManager.getClasificar());
		model.addAttribute("listaMedida", ProductoManager.getMedida());
		return "producto/Ingrediente";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();

		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			String nombre = request.getParameter("nombreF");
			String descripcion = request.getParameter("descripcionF");
			String clasificarF = request.getParameter("clasificarF");
			String medidaF = request.getParameter("medidaF");
			
			IngredienteExample IngExample = new IngredienteExample();
			IngredienteExample.Criteria criteria = (IngredienteExample.Criteria) IngExample.createCriteria();
			IngExample.setOrderByClause("id_ingrediente DESC");
			IngExample.setLimit(Inicio + ", " + Final);

			if(!nombre.trim().isEmpty()){
				criteria.andNombreLike("%" + nombre + "%");   
			}
			if(!descripcion.trim().isEmpty()){
				criteria.andDescripcionLike("%" + descripcion + "%");   
			}
			if(!clasificarF.trim().isEmpty() && !clasificarF.trim().equals("0")){
				criteria.andId_clasificar_ingredienteEqualTo(Integer.parseInt(clasificarF));
			}
			if(!medidaF.trim().isEmpty() && !medidaF.trim().equals("0")){
				criteria.andId_unidad_medidaEqualTo(Integer.parseInt(medidaF));
			}
			
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getIngredienteMapper().selectByExample(IngExample)));
			opciones.put("total", ColseviDao.getInstance().getIngredienteMapper().countByExample(IngExample));

			response.setContentType("text/html;charset=ISO-8859-1");
			request.setCharacterEncoding("UTF8");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Ingrediente> listIng){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		JSONObject labels = new JSONObject();
		JSONObject labels2 = new JSONObject();
		
		for (Ingrediente bean : listIng) {
			try{
				opciones = new JSONObject();
				labels = new JSONObject();
				labels2 = new JSONObject();
				opciones.put("id_ingrediente", bean.getId_ingrediente());
				opciones.put("nombre", bean.getNombre());
				opciones.put("descripcion", bean.getDescripcion());
	
				if(bean.getId_clasificar_ingrediente() != null){
					labels.put("label",ColseviDao.getInstance().getClasificarIngredienteMapper().selectByPrimaryKey(bean.getId_clasificar_ingrediente()).getNombre());
					labels.put("value", bean.getId_clasificar_ingrediente());
					opciones.put("clasificar", labels);
				}else{
					labels.put("label", "");
					labels.put("value", "0");
					opciones.put("clasificar", labels);
				}
				
				if(bean.getId_unidad_medida() != null){
					labels2.put("label",ColseviDao.getInstance().getUnidadMedidaMapper().selectByPrimaryKey(bean.getId_unidad_medida()).getNombre());
					labels2.put("value", bean.getId_unidad_medida());
					opciones.put("medida", labels2);
				}else{
					labels2.put("label", "");
					labels2.put("value", "0");
					opciones.put("medida", labels2);
				}
				
				resultado.add(opciones);
			}catch(Exception e){
				logger.error(e.getMessage());
			}
		}
			
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Guardar")
	public void Guardar(HttpServletRequest request, HttpServletResponse response, Ingrediente bean) throws IOException{
		JSONObject resultVista = new JSONObject();
		try{
			bean.setId_clasificar_ingrediente(Integer.parseInt(request.getParameter("clasificar")));
			bean.setId_unidad_medida(Integer.parseInt(request.getParameter("medida")));
			
			String error = validarGuardado(bean);
			if(!error.isEmpty()){
				resultVista.put("error", error);
				ResponseJson(request, response, resultVista);
				return;
			}
			
			if(bean.getId_ingrediente() != null){
				ColseviDao.getInstance().getIngredienteMapper().updateByPrimaryKey(bean);
				resultVista.put("correcto", "Ingrediente Actualizado");
			}else{
				ColseviDao.getInstance().getIngredienteMapper().insert(bean);
				resultVista.put("correcto", "Ingrediente insertado");
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, resultVista);
	}
	
	public String validarGuardado(Ingrediente bean){
		String error = "";
		if(bean.getNombre() == null || bean.getNombre().trim().isEmpty()){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getDescripcion() == null || bean.getDescripcion().trim().isEmpty()){
			error += "Ingresar la descripción<br/>";
		}
		if(bean.getId_clasificar_ingrediente() == null || bean.getId_clasificar_ingrediente().equals(0)){
			error += "Seleccionar una clasificación<br/>";
		}
		if(bean.getId_unidad_medida() == null || bean.getId_unidad_medida().equals(0)){
			error += "Seleccionar unidad de Medida<br/>";
		}
		
		return error;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Eliminar")
	public void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		try{
			Integer id = Integer.parseInt(request.getParameter("id_ingrediente"));
			if(id != null){
				
				IngredienteXProductoExample IngProd = new IngredienteXProductoExample();
				IngProd.createCriteria().andId_ingredienteEqualTo(id);
				Integer dataCruce = ColseviDao.getInstance().getIngredienteXProductoMapper().countByExample(IngProd);
				if(dataCruce != null && dataCruce > 0){
					resultVista.put("error", "No se puede eliminar, ya que se encuentra asociada a un producto");
				}else{
					ColseviDao.getInstance().getIngredienteMapper().deleteByPrimaryKey(id);
					resultVista.put("correcto", "Establecimiento Eliminado");
				}
			}
		}catch(Exception e){
			resultVista.put("error", "Contacte al Administrador");
		}
		ResponseJson(request, response, resultVista);
	}
}
