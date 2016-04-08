package com.colsevi.controllers.proveedor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import com.colsevi.dao.ingrediente.model.ClasificarIngrediente;
import com.colsevi.dao.ingrediente.model.ClasificarIngredienteExample;
import com.colsevi.dao.ingrediente.model.CompraXIngrediente;
import com.colsevi.dao.ingrediente.model.CompraXIngredienteExample;
import com.colsevi.dao.ingrediente.model.Ingrediente;
import com.colsevi.dao.ingrediente.model.IngredienteExample;
import com.colsevi.dao.ingrediente.model.TipoPeso;
import com.colsevi.dao.ingrediente.model.TipoPesoExample;
import com.colsevi.dao.proveedor.model.Compra;
import com.colsevi.dao.proveedor.model.CompraExample;
import com.colsevi.dao.proveedor.model.Proveedor;
import com.colsevi.dao.proveedor.model.ProveedorExample;

@Controller
public class CompraController extends BaseConfigController {

	private static final long serialVersionUID = 7006733943938315447L;

	@RequestMapping("/Proveedor/Compra")
	public ModelAndView Compra(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaProveedores", getProveedores());
		model.addAttribute("listaClasificar", getClasificar());
		model.addAttribute("listaTipoPeso", getTipoPeso());
		
		return new ModelAndView("proveedor/Compra","col",getValoresGenericos(request));
	}
	
	public static List<Proveedor> getProveedores(){
		return ColseviDao.getInstance().getProveedorMapper().selectByExample(new ProveedorExample());
	}
	
	public static List<ClasificarIngrediente> getClasificar(){
		return ColseviDao.getInstance().getClasificarIngredienteMapper().selectByExample(new ClasificarIngredienteExample());
	}
	
	public static List<TipoPeso> getTipoPeso(){
		return ColseviDao.getInstance().getTipoPesoMapper().selectByExample(new TipoPesoExample());
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
		JSONObject opciones = new JSONObject(), labels = new JSONObject();
		
		if(listaCompra != null && listaCompra.size() >0){
			for (Compra bean : listaCompra) {
				opciones = new JSONObject();
				labels = new JSONObject();
				opciones.put("id_compra", bean.getId_compra().toString());
				opciones.put("valor", UtilidadManager.Currency(bean.getValor()));
				opciones.put("valorsin", bean.getValor().intValue());
				opciones.put("fecha_compra", UtilidadManager.FormatDateDB(bean.getFecha_compra()));
				opciones.put("pagado", bean.getPagado() != null && bean.getPagado().equals(true) ? "SI" : "NO");
				if(bean.getId_proveedor() != null){
					labels.put("label",ColseviDao.getInstance().getProveedorMapper().selectByPrimaryKey(bean.getId_proveedor()).getNombre());
					labels.put("value", bean.getId_proveedor());
					opciones.put("proveedor", labels);
				}
				if( bean.getMotivo() == null || bean.getMotivo().equals(""))
				{
					opciones.put("Estado", "Disponible");
				}else{
					opciones.put("Estado", "Cancelado");
				}
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
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		List<CompraXIngrediente> listaCXI = new ArrayList<CompraXIngrediente>();
		Compra bean = new Compra();
		String error = "";
		
		if(request.getParameter("id_compra") != null && !request.getParameter("id_compra").trim().isEmpty())
			bean.setId_compra(Integer.parseInt(request.getParameter("id_compra")));
		
		if(request.getParameter("proveedor") == null || request.getParameter("proveedor").trim().isEmpty() || request.getParameter("proveedor").equals("0"))
			error += "Seleccionar el proveedor<br/>";
		else
			bean.setId_proveedor(Integer.parseInt(request.getParameter("proveedor")));
		
		if(request.getParameter("valorsin") == null || request.getParameter("valorsin").trim().isEmpty())
			error += "Ingresar el valor de la compra<br/>";
		else
			bean.setValor(UtilidadManager.FormatStringBigDecimal(request.getParameter("valorsin")));
		
		if(request.getParameter("fecha_compra") == null || request.getParameter("fecha_compra").trim().isEmpty())
			error += "Ingresar la fecha de la compra<br/>";
		
		else{
			Date fec= new Date();
			if(UtilidadManager.FormatDateFormDB(request.getParameter("fecha_compra")).getTime() > fec.getTime() ){
				error += "La fecha no puede ser mayo a la actual<br/>";
			}else{
				bean.setFecha_compra(UtilidadManager.FormatDateFormDB(request.getParameter("fecha_compra")));
			}
		}
		
		if(request.getParameter("pagado") != null)
			bean.setPagado(request.getParameter("pagado").equals("SI") || request.getParameter("pagado").equals("on") ? true: false);
		
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return Compra(request, modelo);
		}

		Integer count = Integer.parseInt(request.getParameter("count"));
		if(count != null && count > 0){
			for(int i = 0; i < count; i++){
				if(request.getParameter("idIng" + (i +1)) != null && !request.getParameter("idIng" + (i +1)).trim().isEmpty()){
					CompraXIngrediente cxi = new CompraXIngrediente();
					cxi.setId_ingrediente(Integer.parseInt(request.getParameter("idIng" + (i +1))));
					cxi.setCantidad(Integer.parseInt(request.getParameter("cant" + (i +1))));
					cxi.setId_tipo_peso(Integer.parseInt(request.getParameter("tipo" + (i +1))));

					if(request.getParameter("fecha" + (i +1)) != null && !request.getParameter("fecha" + (i +1)).trim().isEmpty()){
						cxi.setFecha_vencimiento(UtilidadManager.FormatDateFormDB2(request.getParameter("fecha" + (i +1))));
					}
					if(request.getParameter("lote" + (i +1)) != null && !request.getParameter("lote" + (i +1)).trim().isEmpty()){
						cxi.setLote(Integer.parseInt(request.getParameter("lote" + (i +1))));
					}
					
					listaCXI.add(cxi);
				}
			}
		}else{
			modelo.addAttribute("error", "No hay detalle seleccionado");
			return Compra(request, modelo);
		}
		
		if(listaCXI == null || listaCXI.size() < 1){
			modelo.addAttribute("error", "No hay detalle seleccionado");
			return Compra(request, modelo);
		}
		
		try{
			if(bean.getId_compra() != null){
				ColseviDao.getInstance().getCompraMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Compra Actualizada");
				
				CompraXIngredienteExample cxiE = new CompraXIngredienteExample();
				cxiE.createCriteria().andId_compraEqualTo(bean.getId_compra());
				ColseviDao.getInstance().getCompraXIngredienteMapper().deleteByExample(cxiE);
				
			}else{
				ColseviDao.getInstance().getCompraMapper().insert(bean);
				
				CompraExample exampleC = new CompraExample();
				exampleC.setLimit("1");
				exampleC.setOrderByClause("id_compra DESC");
				bean.setId_compra(ColseviDao.getInstance().getCompraMapper().selectByExample(exampleC).get(0).getId_compra());
				modelo.addAttribute("correcto", "Compra insertada");
			}
			
			for(CompraXIngrediente cxi: listaCXI){
				cxi.setId_compra(bean.getId_compra());
				ColseviDao.getInstance().getCompraXIngredienteMapper().insertSelective(cxi);
			}
			
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Compra(request, modelo);
	}
	
	
	
	@RequestMapping("/Proveedor/Compra/GuardarMotivo")
	public ModelAndView GuardarMotivo(HttpServletRequest request, ModelMap modelo){
		
		Compra bean = new Compra();
		String error = "";
		
		if(request.getParameter("id_compraMotiv") != null && !request.getParameter("id_compraMotiv").trim().isEmpty())
			bean.setId_compra(Integer.parseInt(request.getParameter("id_compraMotiv")));
		
		if(request.getParameter("motivo") != null && !request.getParameter("motivo").trim().isEmpty())
			bean.setMotivo(request.getParameter("motivo"));
		try{
			
				
			ColseviDao.getInstance().getCompraMapper().updateByPrimaryKey(bean);
			
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Compra(request, modelo);
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/cargarIng")
	public void cargarIng(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			mapa.put("compra", request.getParameter("compra"));
			result.put("dato", subIng(ColseviDao.getInstance().getCompraXIngredienteMapper().SelectDataView(mapa)));
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
				opciones.put("id_tipo_peso", map.get("id_tipo_peso"));
				opciones.put("nombreIng", map.get("nombreIng"));
				opciones.put("nombreTp", map.get("nombreTp"));
				opciones.put("fecha_vencimiento", map.get("fecha_vencimiento") != null ? UtilidadManager.FormatDateComplete(map.get("fecha_vencimiento").toString()) : "");
				opciones.put("cantidad", map.get("cantidad"));
				opciones.put("lote", map.get("lote"));
				
				resultado.add(opciones);
				
			}catch(Exception e){
				continue;
			}
		}
		return resultado;
	}
}
