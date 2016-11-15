package com.colsevi.controllers.inventario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.InventarioManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.controllers.general.MotivoE;
import com.colsevi.dao.inventario.model.Inventario;
import com.colsevi.dao.inventario.model.InventarioExample;
import com.colsevi.dao.inventario.model.InventarioXMateria;
import com.colsevi.dao.inventario.model.InventarioXMateriaExample;
import com.colsevi.dao.inventario.model.MateriaPrima;
import com.colsevi.dao.inventario.model.MateriaPrimaExample;
import com.colsevi.dao.inventario.model.MovimientoMateria;
import com.colsevi.dao.producto.model.Ingrediente;
import com.colsevi.dao.producto.model.IngredienteXProducto;
import com.colsevi.dao.producto.model.IngredienteXProductoKey;

@Controller
public class InventarioController extends BaseConfigController {
/*
 * Cambiar carga de ingredientes a que sea por una sola consulta que devuelva toda la data, JSON ingrediente, SUBJSON MATERIA PRIMA
 * */

	private static final long serialVersionUID = -1900570445397410663L;
	private static Logger logger = Logger.getLogger(InventarioController.class);

	@RequestMapping("/Inventario/Inv")
	public ModelAndView Inventario(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaEsta", GeneralManager.getEstablecimientos());
		model.addAttribute("listaUnidad", ProductoManager.getTipoPeso());
		return new ModelAndView("inventario/inventarioVista","col",getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/Inv/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			String prodV = request.getParameter("prodV");
			String estaF = request.getParameter("estaF");
			
			mapa.put("limit",Inicio + ", " + Final);
			if(!prodV.trim().isEmpty())
				mapa.put("prodV", prodV);
			if(!estaF.trim().equals("0"))
				mapa.put("estaF", estaF);

			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getInventarioMapper().SelectDataView(mapa)));
			opciones.put("total", ColseviDao.getInstance().getInventarioMapper().CountDataView(mapa));

		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		opciones.writeJSONString(response.getWriter());
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<Map<String, Object>> listData){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		JSONObject labels = new JSONObject();
		
		if(listData != null && listData.size() >0){
			for (Map<String, Object> map : listData) {
				labels = new JSONObject();
				opciones = new JSONObject();
				try{
					opciones.put("id_inventario", map.get("id_inventario"));
					opciones.put("id_producto", map.get("id_producto"));
					opciones.put("nombreProd", map.get("nombreProd"));

					if(map.get("id_establecimiento") != null){
						labels.put("label", map.get("nombreEsta"));
						labels.put("value", map.get("id_establecimiento"));
					}else{
						labels.put("label", "");
						labels.put("value", "0");
					}
					opciones.put("establecimiento", labels);
					
					opciones.put("disponible", map.get("disponible") == null ? "0" : map.get("disponible"));
					opciones.put("compromiso", map.get("compromiso") == null ? "0" : map.get("compromiso"));
				
					resultado.add(opciones);
				
				}catch(Exception e){
					logger.error(e.getMessage());
					continue;
				}
			}
			
		}
		return resultado;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/Inv/cargarInv")
	public void cargarInv(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));
		
		mapa.put("producto", request.getParameter("prod"));
		mapa.put("cantidad", cantidad);
		
		try{
			opciones.put("datos", Construir(ColseviDao.getInstance().getInventarioMapper().CargarIngProd(mapa), request.getParameter("establecimiento")));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");

		opciones.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray Construir(List<Map<String, Object>> listData, String establecimiento){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listData != null && listData.size() >0){
			for (Map<String, Object> map : listData) {
				try{
					opciones = new JSONObject();
					opciones.put("id_ingrediente", map.get("id_ingrediente"));
					opciones.put("nombreIng", map.get("nombreIng"));
					opciones.put("cantidadProd", map.get("cantidadProd"));
					opciones.put("nombreIngU", map.get("nombreIngU"));
					opciones.put("codUM", map.get("codUM"));
					opciones.put("id_unidad_peso", map.get("id_unidad_peso"));
					
					JSONArray detalle = cargarIng((Long) map.get("cantidadProd"), (Integer) map.get("id_unidad_peso"), map.get("id_ingrediente").toString(), establecimiento);
					if(detalle != null){
						opciones.put("detalle", detalle);
					}
					
					resultado.add(opciones);
				}catch(Exception e){
					logger.error(e.getMessage());
					continue;
				}
			}
		}
		return resultado;
	}

	public JSONArray cargarIng(Long cantidad, Integer um, String ing, String establecimiento) throws IOException{
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("ing", ing);
		mapa.put("esta", establecimiento);
		try{
			return ConstruirInv(ColseviDao.getInstance().getInventarioMapper().CargarInv(mapa), cantidad, um);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirInv(List<Map<String, Object>> listData, Long cantidad, Integer um){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		Object[] result = new Object[2];
		Double cant = 0d;
		Integer medida = 0;
		double op = 0;
		if(listData != null && listData.size() >0){
			for (Map<String, Object> map : listData) {
				try{
					opciones = new JSONObject();
					
					opciones.put("nombre", map.get("nombre") != null ? map.get("nombre") : "");
					opciones.put("fecha_vencimiento", map.get("fecha_vencimiento") != null ? UtilidadManager.FechaDateConHora_Vista((Date) map.get("fecha_vencimiento")) : "");
					opciones.put("lote", map.get("lote"));
					opciones.put("color", true);
					opciones.put("codUM", map.get("codUM") != null ? map.get("codUM") : "0");
					opciones.put("id_ingrediente", map.get("id_ingrediente"));
					opciones.put("cantAsig", map.get("cantAsig") == null ? "" : map.get("cantAsig"));
					opciones.put("umAsig", map.get("umAsig") == null ? "0" : map.get("umAsig"));
					
					medida = (Integer) map.get("um");
					cant = (Double) map.get("cantidad");
					opciones.put("cantidad", cant);
					if(medida != null && cant != null){
						op = cant;
					
						result = InventarioManager.ConversionPMenorMayor(um, medida, cant);
						op = (Double) result[0];
						
						if(op < cantidad){
							opciones.put("color", false);
						}
					}else{
						opciones.put("color", false);
					}
					resultado.add(opciones);
				}catch(Exception e){
					logger.error(e.getMessage());
					continue;
				}
			}
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/Inv/Guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap modelo){
		
		try{
			Inventario bean = new Inventario();
			List<InventarioXMateria> listaIXM = null;
			List<MateriaPrima> listaMP = null;
			List<MovimientoMateria> listamov = null;
			Object[] result = validarGuardar(request);
			
			if(!result[0].toString().isEmpty()){
				modelo.addAttribute("error", result[0]);
				return Inventario(request, modelo);
			}

			bean.setId_inventario(request.getParameter("id_inventario") == null || request.getParameter("id_inventario").trim().isEmpty() ? null : Integer.parseInt(request.getParameter("id_inventario")));
			bean.setCompromiso(0);
			bean.setDisponible(Integer.parseInt(request.getParameter("cantSolicitada")));
			bean.setId_establecimiento(Integer.parseInt(request.getParameter("establecimiento")));
			bean.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
			
			listaIXM = (List<InventarioXMateria>) result[1];
			listaMP = (List<MateriaPrima>) result[2];
			listamov = (List<MovimientoMateria>) result[3];
			
			if(bean.getId_inventario() != null){
				ColseviDao.getInstance().getInventarioMapper().updateByPrimaryKey(bean);
				registrarInventario(request, listaMP, listaIXM,listamov, bean);
				modelo.addAttribute("correcto", "Inventario actualizado");
			}else{
				ColseviDao.getInstance().getInventarioMapper().insert(bean);
				
				InventarioExample invE = new InventarioExample();
				invE.setOrderByClause("id_inventario DESC");
				bean.setId_inventario(ColseviDao.getInstance().getInventarioMapper().selectByExample(invE).get(0).getId_inventario());
				
				registrarInventario(request, listaMP, listaIXM,listamov, bean);
				modelo.addAttribute("correcto", "Inventario insertado");
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Inventario(request, modelo);
	}
	
	public void registrarInventario(HttpServletRequest request, List<MateriaPrima> listaMP, List<InventarioXMateria> listaInv, List<MovimientoMateria> listaMov, Inventario bean){
		if(bean.getId_inventario() != null || (request.getParameter("detalle") != null && !request.getParameter("detalle").trim().isEmpty())){
			if(listaMP != null && listaMP.size() > 0){
				if(listaInv != null && listaInv.size() > 0){
					InventarioXMateriaExample ixm = new InventarioXMateriaExample();
					ixm.createCriteria().andId_inventarioEqualTo(bean.getId_inventario());
					ColseviDao.getInstance().getInventarioXMateriaMapper().deleteByExample(ixm);
				}
	
				for(MateriaPrima beanMP: listaMP){
					beanMP.setId_establecimiento(bean.getId_establecimiento());
					InventarioManager.ActualizarMateriaPrima(beanMP);
				}
				
				for(InventarioXMateria beanMateria: listaInv){
					beanMateria.setId_inventario(bean.getId_inventario());
					ColseviDao.getInstance().getInventarioXMateriaMapper().insertSelective(beanMateria);
				}
				
				for(MovimientoMateria mov: listaMov){
					if(mov.getId_motivo() != null)
						InventarioManager.RegistrarMovimientoMateria(mov.getLote(), mov.getId_unidad_peso(), mov.getCantidad(), bean.getId_establecimiento(), mov.getFecha_movimiento(), mov.getId_motivo());
				}
			}
		}
	}
	
	public Object[] validarGuardar(HttpServletRequest request){
		
		Object[] result = new Object[5];
		Object[] managerconversion = new Object[2];
		Integer id_inventario = null;
		Integer ingrediente = null;
		Double totalAsignado = 0d;
		InventarioXMateria invxmatv = new InventarioXMateria();//vista
		InventarioXMateria invxmatb = new InventarioXMateria();//base de datos
		IngredienteXProducto ingProd = new IngredienteXProducto();
		Ingrediente ing = new Ingrediente();
		IngredienteXProductoKey keyIXP = new IngredienteXProductoKey();
		MateriaPrima mp = new MateriaPrima();
		MateriaPrimaExample cxiE = new MateriaPrimaExample();
		InventarioXMateriaExample IXME = new InventarioXMateriaExample(); 
		List<InventarioXMateria> listaIXME = new ArrayList<InventarioXMateria>();
		List<InventarioXMateria> listaInv = new ArrayList<InventarioXMateria>();
		List<MateriaPrima> listaMP = new ArrayList<MateriaPrima>();
		List<MovimientoMateria> listamov = new ArrayList<MovimientoMateria>();
		MovimientoMateria movmat = new MovimientoMateria();
		
		String error = "";
		String[] sec = request.getParameterValues("lote");
		String[] ingArray = request.getParameterValues("ing");
		String[] umArray = request.getParameterValues("um");
		String[] cantArray = request.getParameterValues("cant");
		Integer producto = Integer.parseInt(request.getParameter("id_producto"));
		Integer cantSolicitada = Integer.parseInt(request.getParameter("cantSolicitada"));
		ingrediente = Integer.parseInt(ingArray[0]);
		
		for(int i = 0; i < sec.length ; i ++){
			movmat = new MovimientoMateria();
			invxmatv = new InventarioXMateria();
			mp = null;
			cxiE.clear();
			IXME.clear();

			invxmatv.setId_ingrediente(Integer.parseInt(ingArray[i]));
			keyIXP.setId_ingrediente(invxmatv.getId_ingrediente());
			keyIXP.setId_producto(producto);
			ingProd = ColseviDao.getInstance().getIngredienteXProductoMapper().selectByPrimaryKey(keyIXP);
			ingProd.setCantidad(ingProd.getCantidad() * cantSolicitada);
			
			if(cantArray[i] != null && !umArray[i].trim().equals("0")){
				invxmatv.setCantidad(Double.valueOf(cantArray[i]));
				invxmatv.setId_unidad_peso(Integer.parseInt(umArray[i]));
				invxmatv.setLote(Integer.parseInt(sec[i]));
				
				if(invxmatv.getCantidad() != null && !invxmatv.getCantidad().equals(0d)){
					
					cxiE.createCriteria().andLoteEqualTo(invxmatv.getLote()).andId_ingredienteEqualTo(invxmatv.getId_ingrediente());
					mp = ColseviDao.getInstance().getMateriaPrimaMapper().selectByExample(cxiE).get(0);
					
					managerconversion = InventarioManager.ConversionPMayorMenor(invxmatv.getId_unidad_peso(), ingProd.getId_unidad_peso(), invxmatv.getCantidad());
					invxmatv.setCantidad((Double) managerconversion[0]);
					invxmatv.setId_unidad_peso((Integer) managerconversion[1]);
					
					if(mp.getCantidad() != null && mp.getCantidad() > 0d){
						managerconversion = InventarioManager.ConversionPMayorMenor(mp.getId_unidad_peso(), invxmatv.getId_unidad_peso(), mp.getCantidad());
						mp.setCantidad((Double) managerconversion[0]);
						mp.setId_unidad_peso((Integer) managerconversion[1]);
					}else{
						mp.setCantidad(0d);
					}
					
					if(request.getParameter("id_inventario") != null && !request.getParameter("id_inventario").trim().isEmpty()){
						id_inventario = Integer.parseInt(request.getParameter("id_inventario"));
						IXME.createCriteria().andId_inventarioEqualTo(id_inventario).andId_ingredienteEqualTo(invxmatv.getId_ingrediente()).andLoteEqualTo(invxmatv.getLote());
						listaIXME = ColseviDao.getInstance().getInventarioXMateriaMapper().selectByExample(IXME);
						
						if(listaIXME != null && listaIXME.size() > 0){
							invxmatb = listaIXME.get(0);
							managerconversion = InventarioManager.ConversionPMayorMenor(invxmatb.getId_unidad_peso(), invxmatv.getId_unidad_peso(), invxmatb.getCantidad());
							invxmatb.setCantidad((Double) managerconversion[0]);
							invxmatb.setId_unidad_peso((Integer) managerconversion[1]);
							
							if(!invxmatb.getCantidad().equals(invxmatv.getCantidad()) || (!invxmatb.getId_unidad_peso().equals(invxmatv.getId_unidad_peso()))){
								managerconversion = InventarioManager.ConversionPMayorMenor(invxmatb.getId_unidad_peso(), invxmatv.getId_unidad_peso(), invxmatb.getCantidad());
								invxmatb.setCantidad((Double) managerconversion[0]);
								invxmatb.setId_unidad_peso((Integer) managerconversion[1]);
								
								if(mp.getCantidad().equals(0d)){
									if(invxmatv.getCantidad() > invxmatb.getCantidad()){
										error += "No hay materia prima disponible<br/>";
									}else{
										movmat.setId_motivo(MotivoE.DEVOLUCION.getMotivoE());
										movmat.setCantidad(invxmatb.getCantidad() - invxmatv.getCantidad());
										mp.setCantidad(invxmatb.getCantidad() - invxmatv.getCantidad());
										movmat.setId_unidad_peso(invxmatb.getId_unidad_peso());
										mp.setId_unidad_peso(invxmatb.getId_unidad_peso());
									}
								}else 
								if((invxmatv.getCantidad() > invxmatb.getCantidad()) || (invxmatv.getId_unidad_peso() > invxmatb.getId_unidad_peso())){
									if((invxmatv.getCantidad() <= mp.getCantidad()) || (invxmatv.getId_unidad_peso() <= mp.getId_unidad_peso())){
										//asignar
										if((invxmatv.getCantidad() - mp.getCantidad()) < 1){
											mp.setCantidad(mp.getCantidad() - invxmatv.getCantidad() + invxmatb.getCantidad());
											if((mp.getCantidad() - invxmatv.getCantidad()) < 1)
												movmat.setCantidad(invxmatv.getCantidad() - mp.getCantidad());
											else
											movmat.setCantidad(mp.getCantidad() - invxmatv.getCantidad());
										}else{
											mp.setCantidad(invxmatv.getCantidad() - mp.getCantidad() - invxmatb.getCantidad());
											movmat.setCantidad(invxmatv.getCantidad() - mp.getCantidad());
										}
										movmat.setId_motivo(MotivoE.ASIGNACION.getMotivoE());
										movmat.setId_unidad_peso(invxmatv.getId_unidad_peso());
									}else{
										error +="No hay materia prima disponible </br>";
									}
								}else if((invxmatv.getCantidad() < invxmatb.getCantidad()) || (invxmatv.getId_unidad_peso() < invxmatb.getId_unidad_peso())){
									if((invxmatv.getCantidad() <= mp.getCantidad()) || (invxmatv.getId_unidad_peso() <= mp.getId_unidad_peso())){
										//desasignar
										mp.setCantidad(mp.getCantidad() + invxmatv.getCantidad());
										movmat.setId_motivo(MotivoE.DEVOLUCION.getMotivoE());
										if((invxmatv.getCantidad() - mp.getCantidad()) < 1){
											movmat.setCantidad(mp.getCantidad() - invxmatv.getCantidad());
										}else{
											movmat.setCantidad(invxmatv.getCantidad() - mp.getCantidad());
										}
										movmat.setId_unidad_peso(mp.getId_unidad_peso());
									}else{
										error +="No hay materia prima disponible </br>";
									}
								}	
							}
						}else{
							if((invxmatv.getCantidad() - mp.getCantidad()) < 1){
								mp.setCantidad(mp.getCantidad() - invxmatv.getCantidad());
								movmat.setCantidad(mp.getCantidad() - invxmatv.getCantidad());
							}else{
								mp.setCantidad(invxmatv.getCantidad() - mp.getCantidad());
								movmat.setCantidad(invxmatv.getCantidad() - mp.getCantidad());
							}
							movmat.setId_motivo(MotivoE.ASIGNACION.getMotivoE());
							
						}
					}else{
						movmat.setId_motivo(MotivoE.ASIGNACION.getMotivoE());
						movmat.setCantidad(invxmatv.getCantidad());
						movmat.setId_unidad_peso(invxmatv.getId_unidad_peso());
						
						if((invxmatv.getCantidad() - mp.getCantidad()) < 1)
							mp.setCantidad(mp.getCantidad() - invxmatv.getCantidad());
						else
							mp.setCantidad(invxmatv.getCantidad() - mp.getCantidad());
					}
					
					totalAsignado += invxmatv.getCantidad();
					
					if(ingrediente != null && (!ingrediente.equals(invxmatv.getId_ingrediente()) || i == (sec.length - 1))){
						if(totalAsignado < Double.valueOf(ingProd.getCantidad())){
							error += "La cantidad ingresada es inferior a la cantidad solicitada <br/>";
						}else if(totalAsignado > Double.valueOf(ingProd.getCantidad())){
							error += "La cantidad ingresada supera la cantidad solicitada <br/>";
						}else{
							totalAsignado = 0d;					
						}
					}
					
					if(totalAsignado <= ingProd.getCantidad()){
						movmat.setFecha_movimiento(new Date());
						movmat.setLote(invxmatv.getLote());
						listamov.add(movmat);
						if(invxmatv.getCantidad() < 1){
							managerconversion = InventarioManager.conversionEncontrarMayorUnidad(invxmatv.getId_unidad_peso(), invxmatv.getCantidad());
						}else{
							managerconversion = InventarioManager.conversionMOptima(invxmatv.getId_unidad_peso(), invxmatv.getCantidad());
						}
						invxmatv.setCantidad((Double) managerconversion[0]);
						invxmatv.setId_unidad_peso((Integer) managerconversion[1]);
						listaInv.add(invxmatv);
						
						if(mp.getCantidad() < 1){
							if(mp.getId_unidad_peso() != null)
							managerconversion = InventarioManager.conversionEncontrarMayorUnidad(mp.getId_unidad_peso(), mp.getCantidad());
						}else{
							managerconversion = InventarioManager.conversionMOptima(mp.getId_unidad_peso(), mp.getCantidad());
						}
						if(mp.getId_unidad_peso() != null){
							mp.setCantidad((Double) managerconversion[0]);
							mp.setId_unidad_peso((Integer) managerconversion[1]);
						}
						listaMP.add(mp);
					}else{
						if(totalAsignado > ingProd.getCantidad()){
							error += "La cantidad seleccionada supera el numero requerido<br/>";
						}else if(totalAsignado > mp.getCantidad()){
							ing = ColseviDao.getInstance().getIngredienteMapper().selectByPrimaryKey(invxmatv.getId_ingrediente());
							error += "La cantidad seleccionada del ingrediente " + ing.getNombre() + " supera la capacidad de la materia prima<br/>";
						}
					}
				}
			}else{
				//consultar 
				if(request.getParameter("id_inventario") != null && !request.getParameter("id_inventario").trim().isEmpty()){

					invxmatv.setLote(Integer.parseInt(sec[i]));
					invxmatv.setId_ingrediente(Integer.parseInt(ingArray[i]));
					
					cxiE.createCriteria().andLoteEqualTo(invxmatv.getLote()).andId_ingredienteEqualTo(invxmatv.getId_ingrediente());
					mp = ColseviDao.getInstance().getMateriaPrimaMapper().selectByExample(cxiE).get(0);
					
					id_inventario = Integer.parseInt(request.getParameter("id_inventario"));
					IXME.createCriteria().andId_inventarioEqualTo(id_inventario).andId_ingredienteEqualTo(invxmatv.getId_ingrediente()).andLoteEqualTo(invxmatv.getLote());
					listaIXME = ColseviDao.getInstance().getInventarioXMateriaMapper().selectByExample(IXME);
					
					if(listaIXME != null && listaIXME.size() > 0){
						invxmatb = listaIXME.get(0);
						if(mp.getCantidad() == null || mp.getCantidad().equals(0d)){
							mp.setCantidad(invxmatb.getCantidad());
							mp.setId_unidad_peso(invxmatb.getId_unidad_peso());
							listaMP.add(mp);
							
							movmat.setCantidad(mp.getCantidad());
							movmat.setId_unidad_peso(mp.getId_unidad_peso());
							movmat.setId_motivo(MotivoE.DEVOLUCION.getMotivoE());
							movmat.setFecha_movimiento(new Date());
							movmat.setLote(invxmatv.getLote());
							listamov.add(movmat);
						}else{
							managerconversion = InventarioManager.ConversionPMayorMenor(invxmatb.getId_unidad_peso(), mp.getId_unidad_peso(), invxmatb.getCantidad());
							invxmatb.setCantidad((Double) managerconversion[0]);
							invxmatb.setId_unidad_peso((Integer) managerconversion[1]);
							
							mp.setCantidad(invxmatb.getCantidad() + mp.getCantidad());
							mp.setId_unidad_peso(invxmatb.getId_unidad_peso());
							listaMP.add(mp);
							
							movmat.setCantidad(mp.getCantidad());
							movmat.setId_unidad_peso(mp.getId_unidad_peso());
							movmat.setId_motivo(MotivoE.DEVOLUCION.getMotivoE());
							movmat.setFecha_movimiento(new Date());
							movmat.setLote(invxmatv.getLote());
							listamov.add(movmat);
						}
						
						if(ingrediente != null && (!ingrediente.equals(invxmatv.getId_ingrediente()) || i == (sec.length - 1))){
							if(totalAsignado < Double.valueOf(ingProd.getCantidad())){
								error += "La cantidad ingresada es inferior a la cantidad solicitada <br/>";
							}else if(totalAsignado > Double.valueOf(ingProd.getCantidad())){
								error += "La cantidad ingresada supera la cantidad solicitada <br/>";
							}else{
								totalAsignado = 0d;					
							}
						}
					}
				}
			}
		}
		
		result[0] = error;
		result[1] = listaInv;
		result[2] = listaMP;
		result[3] = listamov;
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/Inv/preprocesador")
	public void preprocesador(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		try{
			Object[] validacion = validarGuardar(request);
			
			if(!validacion[0].toString().isEmpty()){
				result.put("error", validacion[0]);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			result.put("error", "Contactar al administrador");
		}
		
		result.writeJSONString(response.getWriter());
	}
	
	@RequestMapping("/Inventario/Inv/buscarProd")
	public void auto(HttpServletRequest request, HttpServletResponse response){
		try{
			JSONObject result = new JSONObject();
			
			String producto = request.getParameter("campo");
			result = ProductoManager.AutocompletarProducto(producto);

			if(result != null){
				result.writeJSONString(response.getWriter());
			}
			
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
}