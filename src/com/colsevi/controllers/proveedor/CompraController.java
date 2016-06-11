package com.colsevi.controllers.proveedor;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.colsevi.application.GeneralManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.ProveedorManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.deuda.model.DeudaProveedorExample;
import com.colsevi.dao.inventario.model.InventarioXMateriaExample;
import com.colsevi.dao.inventario.model.MateriaPrima;
import com.colsevi.dao.inventario.model.MateriaPrimaExample;
import com.colsevi.dao.inventario.model.MovimientoMateriaExample;
import com.colsevi.dao.pago.model.PagoProveedorExample;
import com.colsevi.dao.producto.model.Ingrediente;
import com.colsevi.dao.producto.model.IngredienteExample;
import com.colsevi.dao.proveedor.model.Compra;
import com.colsevi.dao.proveedor.model.CompraExample;
import com.colsevi.dao.proveedor.model.CompraXIngrediente;
import com.colsevi.dao.proveedor.model.CompraXIngredienteExample;

@Controller
public class CompraController extends BaseConfigController {

	private static final long serialVersionUID = 7006733943938315447L;

	@RequestMapping("/Proveedor/Compra")
	public ModelAndView Compra(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaProveedores", ProveedorManager.getProveedores());
		model.addAttribute("listaClasificar", ProductoManager.getClasificar());
		model.addAttribute("listaTipoPeso", ProductoManager.getTipoPeso());
		model.addAttribute("listaEstablecimiento", GeneralManager.getEstablecimientos());
		
		if(request.getParameter("Compra") != null && !request.getParameter("Compra").trim().isEmpty()){
			model.addAttribute("com", request.getParameter("Compra"));
		}
		
		return new ModelAndView("proveedor/Compra","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String com = request.getParameter("com");
		String pagadoF = request.getParameter("pagadoF");
		String estadoF = request.getParameter("estadoF");
		String provF = request.getParameter("provF");
		String fechaF = request.getParameter("fechaF");
		String fechaMF = request.getParameter("fechaMF");
		String valorF = request.getParameter("valorF");
		String valorMF = request.getParameter("valorMF");
		String estaF = request.getParameter("estaF");
		
		CompraExample compraExample = new CompraExample();
		compraExample.setLimit(Inicio + ", " + Final);
		compraExample.setOrderByClause("id_compra DESC");
		
		CompraExample.Criteria criteria = (CompraExample.Criteria) compraExample.createCriteria();
		
		if(com != null && !com.trim().isEmpty()){
			criteria.andId_compraEqualTo(Integer.parseInt(com));
		}
		if(pagadoF != null && !pagadoF.trim().isEmpty() && !pagadoF.trim().equals("0")){
			if(pagadoF.trim().equals("1")){
				criteria.andPagadoEqualTo(true);
			}else{
				criteria.andPagadoEqualTo(false);
			}
		}
		if(estadoF != null && !estadoF.trim().isEmpty() && !estadoF.trim().equals("0")){
			if(estadoF.trim().equals("2")){
				criteria.andMotivoIsNotNull();
			}else{
				criteria.andMotivoIsNull();
			}
		}
		if(provF != null && !provF.trim().isEmpty() && !provF.trim().equals("0")){
			criteria.andId_proveedorEqualTo(Integer.parseInt(provF));
		}
		if(estaF != null && !estaF.trim().isEmpty() && !estaF.trim().equals("0")){
			criteria.andId_establecimientoEqualTo(Integer.parseInt(estaF));
		}
		if(valorF != null && !valorF.trim().isEmpty()){
			if(valorMF != null && valorMF.trim().equals("true")){
				criteria.andValorGreaterThanOrEqualTo(new BigDecimal(valorF));
			}else{
				criteria.andValorLessThanOrEqualTo(new BigDecimal(valorF));
			}
		}
		if(fechaF != null && !fechaF.trim().isEmpty()){
			if(fechaMF != null && fechaMF.trim().equals("true")){
				criteria.andFecha_compraGreaterThanOrEqualTo(UtilidadManager.FormatDateFormDB2(fechaF));
			}else{
				criteria.andFecha_compraLessThanOrEqualTo(UtilidadManager.FormatDateFormDB2(fechaF));
			}
		}
		opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getCompraMapper().selectByExample(compraExample)));
		opciones.put("total", ColseviDao.getInstance().getCompraMapper().countByExample(compraExample));

		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
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
				opciones.put("id_compraBoton", "");
				opciones.put("valor", UtilidadManager.Currency(bean.getValor()));
				opciones.put("valorsin", bean.getValor().intValue());
				opciones.put("fecha_compra", UtilidadManager.FormatDateDB(bean.getFecha_compra()));
				opciones.put("pagado", bean.getPagado() != null && bean.getPagado().equals(true) ? "SI" : "NO");
				opciones.put("motivo", bean.getMotivo());

				labels = new JSONObject();
				if(bean.getId_proveedor() != null){
					labels.put("label",ColseviDao.getInstance().getProveedorMapper().selectByPrimaryKey(bean.getId_proveedor()).getNombre());
					labels.put("value", bean.getId_proveedor());
					opciones.put("proveedor", labels);
				}
				
				labels = new JSONObject();
				if(bean.getId_establecimiento() != null){
					labels.put("label",ColseviDao.getInstance().getEstablecimientoMapper().selectByPrimaryKey(bean.getId_establecimiento()).getNombre());
					labels.put("value", bean.getId_establecimiento());
					opciones.put("establecimiento", labels);
				}
				
				if( bean.getMotivo() == null || bean.getMotivo().equals("")){
					opciones.put("Estado", "Alta");
				}else{
					opciones.put("Estado", "Baja");
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

		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
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
	@RequestMapping("/Proveedor/Compra/preprocesador")
	public void preprocesador(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		try{
			Object[] validacion = validarGuardar(request);
			
			if(!validacion[0].toString().isEmpty()){
				result.put("error", validacion[0]);
			}
		}catch(Exception e){
			result.put("error", "Contactar al administrador");
		}
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		List<CompraXIngrediente> listaCXI = null;
		CompraXIngredienteExample cie = new CompraXIngredienteExample();
		List<MateriaPrima> listaMP = null;
		List<Integer> loteList = null;
		List<Integer> countDelete = null;
		Compra bean = null;
		
		try{
			Object[] result = validarGuardar(request);
			
			if(result[0] != null && !result[0].toString().isEmpty()){
				modelo.addAttribute("error", result[0]);
				return Compra(request, modelo);
			}
	
			loteList = (List<Integer>) result[1];
			listaCXI = (List<CompraXIngrediente>) result[2];
			listaMP = (List<MateriaPrima>) result[3];
			bean = (Compra) result[4];
			
			bean.setMotivo("");
			
			if(loteList != null && loteList.size() > 0){
	
				MateriaPrimaExample mpe = new MateriaPrimaExample();
				
				cie.createCriteria().andLoteNotIn(loteList).andId_compraEqualTo(bean.getId_compra());
				List<CompraXIngrediente> listCXI = ColseviDao.getInstance().getCompraXIngredienteMapper().selectByExample(cie);
				
				countDelete = new ArrayList<Integer>();
				for(CompraXIngrediente ci: listCXI){
					loteList.add(ci.getLote());
					countDelete.add(ci.getLote());
				}
	
				if(countDelete.size() > 0){
					cie.clear();
					cie.createCriteria().andLoteIn(countDelete);
					mpe.createCriteria().andLoteIn(countDelete);
					ColseviDao.getInstance().getCompraXIngredienteMapper().deleteByExample(cie);
					ColseviDao.getInstance().getMateriaPrimaMapper().deleteByExample(mpe);
				}
			}

			if(bean.getId_compra() != null){
				ColseviDao.getInstance().getCompraMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Compra Actualizada");
			}else{
				ColseviDao.getInstance().getCompraMapper().insert(bean);
				
				CompraExample exampleC = new CompraExample();
				exampleC.setLimit("1");
				exampleC.setOrderByClause("id_compra DESC");
				bean.setId_compra(ColseviDao.getInstance().getCompraMapper().selectByExample(exampleC).get(0).getId_compra());
				modelo.addAttribute("correcto", "Compra insertada");
			}

			for(int i=0; i<listaMP.size(); i++){
				listaCXI.get(i).setId_compra(bean.getId_compra());
				listaMP.get(i).setId_establecimiento(bean.getId_establecimiento());
				
				if(listaCXI.get(i).getLote() != null){
					
					ColseviDao.getInstance().getMateriaPrimaMapper().updateByPrimaryKey(listaMP.get(i));
					CompraXIngredienteExample comIngE = new CompraXIngredienteExample();
					comIngE.createCriteria().andLoteEqualTo(listaCXI.get(i).getLote());
					ColseviDao.getInstance().getCompraXIngredienteMapper().updateByExampleSelective(listaCXI.get(i), comIngE);
				}else{
					ColseviDao.getInstance().getMateriaPrimaMapper().insertSelective(listaMP.get(i));
					
					MateriaPrimaExample mpe = new MateriaPrimaExample();
					mpe.setOrderByClause("lote DESC;");
					listaCXI.get(i).setLote(ColseviDao.getInstance().getMateriaPrimaMapper().selectByExample(mpe).get(0).getLote());
					ColseviDao.getInstance().getCompraXIngredienteMapper().insertSelective(listaCXI.get(i));
				}
			}
		}catch(Exception e){
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Compra(request, modelo);
	}
	
	public Object[] validarGuardar(HttpServletRequest request){
		Object[] result = new Object[5];
		String error = "";
		MovimientoMateriaExample MME = new MovimientoMateriaExample();
		Compra beanC = new Compra();
		beanC.setValor(new BigDecimal(0));
		List<CompraXIngrediente> listaCXI = new ArrayList<CompraXIngrediente>();
		List<MateriaPrima> listaMP = new ArrayList<MateriaPrima>();
		List<Integer> loteList = new ArrayList<Integer>();
		Integer count = Integer.parseInt(request.getParameter("count"));
		
		if(request.getParameter("id_compra") != null && !request.getParameter("id_compra").trim().isEmpty()){
			beanC.setId_compra(Integer.parseInt(request.getParameter("id_compra")));
			error += validarNoInv(beanC.getId_compra());
		}
		if(request.getParameter("proveedor") == null || request.getParameter("proveedor").trim().isEmpty() || request.getParameter("proveedor").trim().equals("0"))
			error += "Seleccionar el proveedor<br/>";
		else
			beanC.setId_proveedor(Integer.parseInt(request.getParameter("proveedor")));
		
		if(request.getParameter("establecimiento") == null || request.getParameter("establecimiento").trim().isEmpty() || request.getParameter("establecimiento").trim().equals("0"))
			error += "Seleccione un establecimiento <br/>";
		else
			beanC.setId_establecimiento(Integer.parseInt(request.getParameter("establecimiento")));
		
		if(request.getParameter("fecha_compra") == null || request.getParameter("fecha_compra").trim().isEmpty())
			error += "Ingresar la fecha de la compra<br/>";
		else{
			if(UtilidadManager.FormatDateFormDB(request.getParameter("fecha_compra")).getTime() > new Date().getTime() ){
				error += "La fecha no puede ser mayo a la actual<br/>";
			}else{
				beanC.setFecha_compra(UtilidadManager.FormatDateFormDB(request.getParameter("fecha_compra")));
			}
		}
		
		if(request.getParameter("pagado") != null)
			beanC.setPagado(request.getParameter("pagado").equals("SI") || request.getParameter("pagado").equals("on") ? true: false);
		
		
		if(count != null && count > 0){
			for(int i = 0; i < count; i++){
				if(request.getParameter("idIng" + (i +1)) != null && !request.getParameter("idIng" + (i +1)).trim().isEmpty()){
					CompraXIngrediente cxi = new CompraXIngrediente();
					MateriaPrima mp = new MateriaPrima();
					
					cxi.setId_ingrediente(Integer.parseInt(request.getParameter("idIng" + (i +1))));
					cxi.setCantidad(Double.valueOf(request.getParameter("cant" + (i +1))));
					cxi.setId_unidad_peso(Integer.parseInt(request.getParameter("tipo" + (i +1))));
					
					if(request.getParameter("vunitario" + (i +1)) != null && !request.getParameter("vunitario" + (i +1)).trim().isEmpty()){
						cxi.setVunitario(new BigDecimal(request.getParameter("vunitario" + (i +1))));
						beanC.setValor(beanC.getValor().add(cxi.getVunitario().multiply(new BigDecimal(cxi.getCantidad()))));
					}else
						error += "Ingresar el valor unitario</br/>";
					mp.setCantidad(cxi.getCantidad());
					mp.setId_unidad_peso(cxi.getId_unidad_peso());
					mp.setId_ingrediente(cxi.getId_ingrediente());
					
					if(request.getParameter("fecha" + (i +1)) != null && !request.getParameter("fecha" + (i +1)).trim().isEmpty()){
						Date dat = UtilidadManager.FormatDateFormDB2(request.getParameter("fecha" + (i +1)));
						if(dat.getTime() < new Date(System.currentTimeMillis()).getTime()){
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(dat);
							int year = calendar.get(Calendar.YEAR);
							int month = calendar.get(Calendar.MONTH);
							int day = calendar.get(Calendar.DATE);
							calendar.set(year, month, day, 23, 59, 59);
							
							mp.setFecha_vencimiento(calendar.getTime());
							cxi.setFecha_vencimiento(calendar.getTime());
						}else{
							error += "La fecha de la compra debe ser mayor a la actual<br/>";
						}
					}
					if(request.getParameter("lote" + (i +1)) != null && !request.getParameter("lote" + (i +1)).trim().isEmpty()){
						cxi.setLote(Integer.parseInt(request.getParameter("lote" + (i +1))));
						mp.setLote(Integer.parseInt(request.getParameter("lote" + (i +1))));
						loteList.add(mp.getLote());
					}
					
					listaMP.add(mp);
					listaCXI.add(cxi);
				}
			}
		}else{
			error += "No hay detalle seleccionado";
		}
		
		if((listaCXI == null || listaCXI.size() < 1) && (listaMP == null || listaMP.size() < 1)){
			if(beanC.getId_compra() == null){
				error += "No hay detalle seleccionado";
			}
		}

		if(loteList != null && loteList.size() > 0){
			MME.createCriteria().andLoteIn(loteList);
			Integer CountInvM = ColseviDao.getInstance().getMovimientoMateriaMapper().countByExample(MME);
			if(CountInvM != null && CountInvM > 0){
				error += "No se pueden eliminar ingredientes de las compras ya que est�n asociados a inventario";
			}
		}
		
		result[0] = error;
		result[1] = loteList;
		result[2] = listaCXI;
		result[3] = listaMP;
		result[4] = beanC;
		
		return result;
	}
	
	@RequestMapping("/Proveedor/Compra/GuardarMotivo")
	public ModelAndView GuardarMotivo(HttpServletRequest request, ModelMap modelo){
		
		Compra bean = new Compra();
		String error = "";
		
		if(request.getParameter("id_compraMotiv") != null && !request.getParameter("id_compraMotiv").trim().isEmpty())
			bean.setId_compra(Integer.parseInt(request.getParameter("id_compraMotiv")));
		else
			error += "Seleccionar una compra";
		
		if(request.getParameter("motivo") != null && !request.getParameter("motivo").trim().isEmpty())
			bean.setMotivo(request.getParameter("motivo"));
		else
			error += "Ingresar un motivo";
		
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return Compra(request, modelo);
		}
		
		error = validarNoInv(bean.getId_compra());
		
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return Compra(request, modelo);
		}
		
		try{
			ColseviDao.getInstance().getCompraMapper().updateByPrimaryKeySelective(bean);
			
			CompraXIngredienteExample CIE = new CompraXIngredienteExample();
			CIE.createCriteria().andId_compraEqualTo(bean.getId_compra());
			List<CompraXIngrediente> listaDetalle = ColseviDao.getInstance().getCompraXIngredienteMapper().selectByExample(CIE);
			Integer lote = null;
			for(CompraXIngrediente cxi: listaDetalle){
				lote = cxi.getLote();
				cxi.setLote(null);
				ColseviDao.getInstance().getCompraXIngredienteMapper().updateByPrimaryKey(cxi);
				ColseviDao.getInstance().getMateriaPrimaMapper().deleteByPrimaryKey(lote);
			}
			
			modelo.addAttribute("correcto", "Compra dada de Baja");
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Compra(request, modelo);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/ValidarModificacion")
	public void validarModificarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		
		if(request.getParameter("id_compra") != null && !request.getParameter("id_compra").trim().isEmpty()){
			String error = validarNoInv(Integer.parseInt(request.getParameter("id_compra")));
			if(!error.isEmpty())
				result.put("error", error);
		}else{
			result.put("error", "Seleccionar compra");
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	public String validarNoInv(Integer id){
		CompraXIngredienteExample cxiE = new CompraXIngredienteExample();
		MovimientoMateriaExample MME = new MovimientoMateriaExample();
		PagoProveedorExample PPE = new PagoProveedorExample();
		InventarioXMateriaExample IME = new InventarioXMateriaExample();
		
		cxiE.createCriteria().andId_compraEqualTo(id);
		List<CompraXIngrediente> listcxi = ColseviDao.getInstance().getCompraXIngredienteMapper().selectByExample(cxiE);
		
		
		PPE.createCriteria().andId_compraEqualTo(id);
		Integer total = ColseviDao.getInstance().getPagoProveedorMapper().countByExample(PPE);
		if(total != null && total > 0){
			return "La compra ya tiene asociada un pago<br/>";
		}
		
		DeudaProveedorExample DPE = new DeudaProveedorExample();
		DPE.createCriteria().andId_compraEqualTo(id);
		total = ColseviDao.getInstance().getDeudaProveedorMapper().countByExample(DPE);
		if(total != null && total > 0){
			return "La compra ya tiene asociada una deuda</br>";
		}
		
		if(listcxi != null && listcxi.size() > 0){
			for (CompraXIngrediente cxi : listcxi) {
				if(cxi.getLote() != null){
					IME.clear();
					IME.createCriteria().andLoteEqualTo(cxi.getLote());
					total = ColseviDao.getInstance().getInventarioXMateriaMapper().countByExample(IME);
					if(total != null && total > 0)
						return "No se puede modificarla compra, ya que algunos ingredientes se encuentran ya asignados en el inventario<br/>";
					
					MME.clear();
					MME.createCriteria().andLoteEqualTo(cxi.getLote());
					total = ColseviDao.getInstance().getMovimientoMateriaMapper().countByExample(MME);
					if(total != null && total > 0)
						return "No se pueden eliminar ingredientes de las compras ya que est�n asociados a inventario";
				}
				
			}
		}
		return "";
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
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
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
				opciones.put("fecha_vencimiento", map.get("fecha_vencimiento") != null ? UtilidadManager.FormatDateView(map.get("fecha_vencimiento").toString()) : "");
				opciones.put("cantidad", map.get("cantidad"));
				opciones.put("iva", map.get("iva") != null ? map.get("iva") : "");
				opciones.put("vunitario", map.get("vunitario") != null ? map.get("vunitario") : "");
				opciones.put("lote", map.get("lote"));
				
				resultado.add(opciones);
				
			}catch(Exception e){
				continue;
			}
		}
		return resultado;
	}
}
