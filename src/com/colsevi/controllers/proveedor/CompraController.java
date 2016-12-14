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
import com.colsevi.application.ProveedorManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.application.ingredienteManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.general.model.UnidadPeso;
import com.colsevi.dao.general.model.UnidadPesoExample;
import com.colsevi.dao.inventario.model.MateriaPrima;
import com.colsevi.dao.inventario.model.MateriaPrimaExample;
import com.colsevi.dao.inventario.model.MovimientoMateriaExample;
import com.colsevi.dao.proveedor.model.CompraProveedor;
import com.colsevi.dao.proveedor.model.CompraXIngrediente;
import com.colsevi.dao.proveedor.model.CompraXIngredienteExample;

@Controller
public class CompraController extends BaseConfigController {

	private static final long serialVersionUID = 7006733943938315447L;
	private static Logger logger = Logger.getLogger(CompraController.class);

	@RequestMapping("/Proveedor/Compra")
	public ModelAndView Compra(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaProveedores", ProveedorManager.getProveedores());
		model.addAttribute("listaEstablecimiento", GeneralManager.getEstablecimientos());
		
		if(request.getParameter("Compra") != null){
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
		String valorF = request.getParameter("valorF");
		String valorMF = request.getParameter("valorMF");
		String estaF = request.getParameter("estaF");
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("limit", Inicio + ", " + Final);
		
		try{
			if(com != null && !com.trim().isEmpty()){
				mapa.put("compra", com);
			}
			if(pagadoF != null && !pagadoF.trim().isEmpty() && !pagadoF.trim().equals("0")){
				if(pagadoF.trim().equals("1")){
					mapa.put("pagadoT", "");
				}else{
					mapa.put("pagadoF", "");
				}
			}
			if(provF != null && !provF.trim().isEmpty() && !provF.trim().equals("0")){
				mapa.put("prov", provF);
			}
			if(estaF != null && !estaF.trim().isEmpty() && !estaF.trim().equals("0")){
				mapa.put("estab", estaF);
			}
			if(valorF != null && !valorF.trim().isEmpty()){
				if(valorMF != null && valorMF.trim().equals("true")){
					mapa.put("valorMayor", valorF);
				}else{
					mapa.put("valorMenor", valorF);
				}
			}
			if(fechaF != null && !fechaF.trim().isEmpty()){
				Object[] obj = UtilidadManager.FechaInicioFin(UtilidadManager.FechaStringConHora_BD(fechaF));
				mapa.put("fechaI", obj[0]);
				mapa.put("fechaF", obj[1]);
			}
			if(estadoF != null && !estadoF.trim().isEmpty() && !estadoF.trim().equals("0")){
				if(estadoF.trim().equals("2")){
					mapa.put("estadoBaja", "");
				}else{
					mapa.put("estadoAlta", "");
				}
			}
			
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getCompraProveedorMapper().TablaCompras(mapa)));
			opciones.put("total", ColseviDao.getInstance().getCompraProveedorMapper().CountTablaCompras(mapa));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		ResponseJson(request, response, opciones);
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String,Object>> listaCompra){
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject(), labels = new JSONObject();
		
		if(listaCompra != null && listaCompra.size() >0){
			for (Map<String,Object> bean : listaCompra) {
				opciones = new JSONObject();
				labels = new JSONObject();
				opciones.put("id_compra", bean.get("id_compra"));
				opciones.put("compra", bean.get("id_compra"));
				opciones.put("valor", bean.get("valor"));
				opciones.put("valorsin", bean.get("valor"));
				opciones.put("fecha_compra", UtilidadManager.FechaStringConHora_Vista(bean.get("fecha_compra").toString(), true));
				opciones.put("pagado", bean.get("pagado"));
				opciones.put("motivo", bean.get("motivo"));
				opciones.put("Estado", bean.get("estado"));

				labels = new JSONObject();
				labels.put("label", bean.get("nombreProv"));
				labels.put("value", bean.get("id_proveedor"));
				opciones.put("proveedor", labels);
					
				labels = new JSONObject();
				labels.put("label", bean.get("nombreEstab"));
				labels.put("value", bean.get("id_establecimiento"));
				opciones.put("establecimiento", labels);
				
				mapa.put("compra", bean.get("id_compra"));
				opciones.put("detalle", subIng(ColseviDao.getInstance().getCompraXIngredienteMapper().SelectDataView(mapa)));
				
				resultado.add(opciones);
			}
		}
		return resultado;
	}
	
	public void detalle(List<CompraXIngrediente> LCompra){
		
		
	}
	
	@RequestMapping("/Proveedor/Compra/autocompletar")
	public void auto(HttpServletRequest request, HttpServletResponse response){
		try{
			JSONObject result = new JSONObject();
			
			String ing = request.getParameter("campo");
			result = ingredienteManager.AutocompletarIngrediente(ing);
			
			if(result != null){
				response.setContentType("text/html;charset=ISO-8859-1");
				request.setCharacterEncoding("UTF8");
				
				result.writeJSONString(response.getWriter());
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
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
			logger.error(e.getMessage());
		}
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		List<CompraXIngrediente> listaCXI = null;
		SqlSession sesion = ColseviDaoTransaccion.getInstance("/TransaccionCompra.xml");
		CompraXIngredienteExample cie = new CompraXIngredienteExample();
		List<MateriaPrima> listaMP = null;
		List<Integer> loteList = null;
		List<Integer> countDelete = null;
		CompraProveedor bean = null;
		
		try{
			Object[] result = validarGuardar(request);
			
			if(result[0] != null && !result[0].toString().isEmpty()){
				modelo.addAttribute("error", result[0]);
				return Compra(request, modelo);
			}
	
			loteList = (List<Integer>) result[1];
			listaCXI = (List<CompraXIngrediente>) result[2];
			listaMP = (List<MateriaPrima>) result[3];
			bean = (CompraProveedor) result[4];
			
			bean.setMotivo("");
			
			if(loteList != null && loteList.size() > 0){
	
				MateriaPrimaExample mpe = new MateriaPrimaExample();
				
				cie.createCriteria().andLoteNotIn(loteList).andId_compraEqualTo(bean.getId_compra_proveedor());
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
					ColseviDaoTransaccion.Eliminar(sesion, "com.colsevi.dao.proveedor.map.CompraXIngredienteMapper.deleteByExample", cie);
					ColseviDaoTransaccion.Eliminar(sesion, "com.colsevi.dao.inventario.map.MateriaPrimaMapper.deleteByExample", mpe);
				}
			}

			if(bean.getId_compra_proveedor() != null){
				ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.proveedor.map.CompraProveedorMapper.updateByPrimaryKey", bean);
				modelo.addAttribute("correcto", "Compra Actualizada");
			}else{
				ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.proveedor.map.CompraProveedorMapper.insertSelective", bean);
				modelo.addAttribute("correcto", "Compra insertada");
			}

			for(int i=0; i<listaMP.size(); i++){
				listaCXI.get(i).setId_compra(bean.getId_compra_proveedor());
				listaMP.get(i).setId_establecimiento(bean.getId_establecimiento());
				
				if(listaCXI.get(i).getLote() != null){
					ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.inventario.map.MateriaPrimaMapper.updateByPrimaryKey", listaMP.get(i));
					ColseviDaoTransaccion.Actualizar(sesion, "com.colsevi.dao.proveedor.map.CompraXIngredienteMapper.updateByExampleSelective", listaCXI.get(i));
				}else{
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.inventario.map.MateriaPrimaMapper.insertSelective", listaMP.get(i));
					listaCXI.get(i).setLote(listaMP.get(i).getLote());
					ColseviDaoTransaccion.Insertar(sesion, "com.colsevi.dao.proveedor.map.CompraXIngredienteMapper.insertSelective", listaCXI.get(i));
				}
			}
			ColseviDaoTransaccion.RealizarCommit(sesion);
		}catch(Exception e){
			modelo.addAttribute("error", "Contactar al administrador");
			logger.error(e.getMessage());
			ColseviDaoTransaccion.ErrorRollback(sesion);
		}
		
		ColseviDaoTransaccion.CerrarSesion(sesion);
		return Compra(request, modelo);
	}
	
	public Object[] validarGuardar(HttpServletRequest request){
		Object[] result = new Object[5];
		String error = "";
		MovimientoMateriaExample MME = new MovimientoMateriaExample();
		CompraProveedor beanC = new CompraProveedor();
		beanC.setValor(new BigDecimal(0));
		List<CompraXIngrediente> listaCXI = new ArrayList<CompraXIngrediente>();
		List<MateriaPrima> listaMP = new ArrayList<MateriaPrima>();
		List<Integer> loteList = new ArrayList<Integer>();
		Integer count = Integer.parseInt(request.getParameter("count"));
		
		if(request.getParameter("id_compra") != null && !request.getParameter("id_compra").trim().isEmpty()){
			beanC.setId_compra_proveedor(Integer.parseInt(request.getParameter("id_compra")));
			error += validarNoInv(beanC.getId_compra_proveedor());
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
			if(UtilidadManager.FechaStringConHora_BD(request.getParameter("fecha_compra"), true).getTime() >= new Date().getTime() ){
				error += "La fecha de la compra no puede ser menor a la actual<br/>";
			}else{
				beanC.setFecha_compra(UtilidadManager.FechaStringConHora_BD(request.getParameter("fecha_compra"), true));
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
						Date dat = UtilidadManager.FechaStringConHora_BD(request.getParameter("fecha" + (i +1)), true);
						if(dat.getTime() > new Date(System.currentTimeMillis()).getTime()){
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(dat);
							int year = calendar.get(Calendar.YEAR);
							int month = calendar.get(Calendar.MONTH);
							int day = calendar.get(Calendar.DATE);
							calendar.set(year, month, day, 23, 59, 59);
							
							mp.setFecha_vencimiento(calendar.getTime());
							cxi.setFecha_vencimiento(calendar.getTime());
						}else{
							error += "La fecha de vencimiento debe ser mayor a la actual<br/>";
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
			if(beanC.getId_compra_proveedor() == null){
				error += "No hay detalle seleccionado";
			}
		}

		if(loteList != null && loteList.size() > 0){
			MME.createCriteria().andLoteIn(loteList);
			Integer CountInvM = ColseviDao.getInstance().getMovimientoMateriaMapper().countByExample(MME);
			if(CountInvM != null && CountInvM > 0){
				error += "No se pueden eliminar ingredientes de las compras ya que están asociados a inventario";
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
		
		CompraProveedor bean = new CompraProveedor();
		String error = "";
		
		if(request.getParameter("id_compraMotiv") != null && !request.getParameter("id_compraMotiv").trim().isEmpty())
			bean.setId_compra_proveedor(Integer.parseInt(request.getParameter("id_compraMotiv")));
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
		
		error = validarNoInv(bean.getId_compra_proveedor());
		
		if(!error.isEmpty()){
			modelo.addAttribute("error", error);
			return Compra(request, modelo);
		}
		
		try{
			ColseviDao.getInstance().getCompraProveedorMapper().updateByPrimaryKeySelective(bean);
			
			CompraXIngredienteExample CIE = new CompraXIngredienteExample();
			CIE.createCriteria().andId_compraEqualTo(bean.getId_compra_proveedor());
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
			logger.error(e.getMessage());
		}
		return Compra(request, modelo);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/ValidarModificacion")
	public void validarModificarCompra(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		
		try{
			if(request.getParameter("id_compra") != null && !request.getParameter("id_compra").trim().isEmpty()){
				String error = validarNoInv(Integer.parseInt(request.getParameter("id_compra")));
				if(!error.isEmpty())
					result.put("error", error);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	public String validarNoInv(Integer id){
		Map<String, Object> mapa = new HashMap<String, Object>();
		CompraProveedor compraProv = ColseviDao.getInstance().getCompraProveedorMapper().selectByPrimaryKey(id);
		if(compraProv != null && compraProv.getPendiente() != null){
			return "La compra ya tiene asociado un pago y/o una deuda<br/>";
		}
		
		mapa.put("compra", id);
		if(ColseviDao.getInstance().getCompraProveedorMapper().countCambiosDetalleCompra(mapa) > 0){
			return "La compra ya tiene movimientos asociados<br/>";
		}
		
		return "";
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
				opciones.put("fecha_vencimiento", map.get("fecha_vencimiento") != null ? UtilidadManager.FechaStringConHora_Vista(map.get("fecha_vencimiento").toString()) : "");
				opciones.put("cantidad", map.get("cantidad"));
				opciones.put("iva", map.get("iva") != null ? map.get("iva") : "");
				opciones.put("vunitario", map.get("vunitario") != null ? map.get("vunitario") : "");
				opciones.put("lote", map.get("lote"));
				
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
				logger.error(e.getMessage());
				continue;
			}
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Proveedor/Compra/MedidaDetalle")
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

}
