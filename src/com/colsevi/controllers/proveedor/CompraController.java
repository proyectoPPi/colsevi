package com.colsevi.controllers.proveedor;

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
import com.colsevi.application.UtilidadManager;
import com.colsevi.dao.ingrediente.model.ClasificarIngrediente;
import com.colsevi.dao.ingrediente.model.ClasificarIngredienteExample;
import com.colsevi.dao.ingrediente.model.Ingrediente;
import com.colsevi.dao.ingrediente.model.IngredienteExample;
import com.colsevi.dao.proveedor.model.Compra;
import com.colsevi.dao.proveedor.model.CompraExample;
import com.colsevi.dao.proveedor.model.Proveedor;
import com.colsevi.dao.proveedor.model.ProveedorExample;

@Controller
public class CompraController {

	@RequestMapping("/Proveedor/Compra")
	public ModelAndView administrador(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaProveedores", getProveedores());
		model.addAttribute("listaClasificar", getClasificar());
		
		return new ModelAndView("proveedor/Compra");
	}
	
	public static List<Proveedor> getProveedores(){
		return ColseviDao.getInstance().getProveedorMapper().selectByExample(new ProveedorExample());
	}
	
	public static List<ClasificarIngrediente> getClasificar(){
		return ColseviDao.getInstance().getClasificarIngredienteMapper().selectByExample(new ClasificarIngredienteExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String nombre = request.getParameter("nombreF");
		String descripcion = request.getParameter("descripcionF");
		
		CompraExample compraExample = new CompraExample();
		compraExample.setLimit(Inicio + ", " + Final);
		
		CompraExample.Criteria criteria = (CompraExample.Criteria) compraExample.createCriteria();
		
//		if(nombre != null && !nombre.trim().isEmpty()){
//			criteria.andNombreLike("%" + nombre + "%");   
//		}
//		if(descripcion != null && !descripcion.trim().isEmpty()){
//			criteria.andDescripcionLike("%" + descripcion + "%");   
//		}
//		
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getCompraMapper().selectByExample(compraExample)));
		opciones.put("total", ColseviDao.getInstance().getCompraMapper().countByExample(compraExample));

		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Compra> listaCompra){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listaCompra != null && listaCompra.size() >0){
			for (Compra bean : listaCompra) {
				opciones = new JSONObject();
				opciones.put("id_compra", bean.getId_compra());
				opciones.put("valor", UtilidadManager.Currency(bean.getValor()));
				opciones.put("fecha_compra", UtilidadManager.FormatDateComplete(bean.getFecha_compra().toString()));
				opciones.put("id_proveedor", bean.getId_proveedor());
				resultado.add(opciones);
			}
		}
		return resultado;
	}
	
	@RequestMapping("/Proveedor/Compra/ClasificarIng")
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
	
	@RequestMapping("/Proveedor/Compra/Guardar")
	public ModelAndView GuardarLocal(HttpServletRequest request, ModelMap modelo, Compra bean){
		
		String error = validarGuardado(bean);
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return administrador(request, modelo);
		}
		try{
			if(bean.getId_compra() != null){
				ColseviDao.getInstance().getCompraMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Compra Actualizada");
			}else{
				ColseviDao.getInstance().getCompraMapper().insert(bean);
				modelo.addAttribute("correcto", "Compra insertada");
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return administrador(request, modelo);
	}
	
	public String validarGuardado(Compra bean){
		String error = "";
		if(bean.getId_proveedor() == null){
			error = "Ingresar el Nombre<br/>";
		}
		if(bean.getValor() == null){
			error = "Ingresar la descripción<br/>";
		}
		
		return error;
	}
}
