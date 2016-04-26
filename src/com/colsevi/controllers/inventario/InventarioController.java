package com.colsevi.controllers.inventario;

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
import com.colsevi.controllers.general.MotivoE;
import com.colsevi.controllers.general.UnidadMedidaE;
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.EstablecimientoExample;
import com.colsevi.dao.general.model.UnidadPeso;
import com.colsevi.dao.general.model.UnidadPesoExample;
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


	private static final long serialVersionUID = -1900570445397410663L;

	@RequestMapping("/Inventario/Inv")
	public ModelAndView Ingrediente(HttpServletRequest request,ModelMap model){
		model.addAttribute("listaEsta", listaEstablecimiento());
		model.addAttribute("listaUnidad", listaUnidad());
		return new ModelAndView("inventario/inventarioVista","col",getValoresGenericos(request));
	}
	
	public static List<Establecimiento> listaEstablecimiento(){
		return ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(new EstablecimientoExample());
	}
	
	public List<UnidadPeso> listaUnidad(){
		return ColseviDao.getInstance().getUnidadPesoMapper().selectByExample(new UnidadPesoExample());
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/Inv/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		try{
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			
			mapa.put("limit",Inicio + ", " + Final);
			
			opciones.put("datos", ConstruirJson(ColseviDao.getInstance().getInventarioMapper().SelectDataView(mapa)));
			opciones.put("total", ColseviDao.getInstance().getInventarioMapper().CountDataView(mapa));

		}catch(Exception e){
			e.printStackTrace();
		}
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
		
		mapa.put("producto",request.getParameter("prod"));
		opciones.put("datos", Construir(ColseviDao.getInstance().getInventarioMapper().CargarIngProd(mapa), cantidad));
		
		opciones.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray Construir(List<Map<String, Object>> listData, Integer cantidad){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		if(listData != null && listData.size() >0){
			for (Map<String, Object> map : listData) {
				try{
					opciones = new JSONObject();
					opciones.put("id_ingrediente", map.get("id_ingrediente"));
					opciones.put("nombreIng", map.get("nombreIng"));
					opciones.put("cantidadProd", (Integer) map.get("cantidadProd")  * cantidad);
					opciones.put("nombreIngU", map.get("nombreIngU"));
					opciones.put("codUM", map.get("codUM"));
					opciones.put("id_unidad_peso", map.get("id_unidad_peso"));
					
					resultado.add(opciones);
				}catch(Exception e){
					continue;
				}
			}
		}
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/Inv/cargarIng")
	public void cargarIng(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject opciones = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		Integer cantidad = Integer.parseInt(request.getParameter("cantidad"));

		Integer um = Integer.parseInt(request.getParameter("um"));
		
		mapa.put("ing",request.getParameter("ing"));
		mapa.put("esta", request.getParameter("establecimiento"));
		opciones.put("datos", ConstruirInv(ColseviDao.getInstance().getInventarioMapper().CargarInv(mapa), cantidad, um));
		
		opciones.writeJSONString(response.getWriter());
		
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirInv(List<Map<String, Object>> listData, Integer cantidad, Integer um){

		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		Double cant = 0d;
		Integer medida = 0;
		double op = 0;
		if(listData != null && listData.size() >0){
			for (Map<String, Object> map : listData) {
				try{
					opciones = new JSONObject();
					
					opciones.put("nombre", map.get("nombre"));
					opciones.put("fecha_vencimiento", UtilidadManager.FormatoFechaVista((Date) map.get("fecha_vencimiento")));
					opciones.put("lote", map.get("lote"));
					opciones.put("color", true);
					opciones.put("codUM", map.get("codUM"));
					opciones.put("id_ingrediente", map.get("id_ingrediente"));
					opciones.put("cantAsig", map.get("cantAsig"));
					opciones.put("umAsig", map.get("umAsig"));
					
					medida = (Integer) map.get("um");
					cant = (Double) map.get("cantidad");
					opciones.put("cantidad", cant);
					op = cant;
					
					if(um.equals(UnidadMedidaE.KILO.getUnidadM())){
						if(medida.equals(UnidadMedidaE.LIBRA.getUnidadM())){
							op = cant * 0.45359237;
						}else if(medida.equals(UnidadMedidaE.GRAMO.getUnidadM())){
							op = cant / 100;
						}
					}else if(um.equals(UnidadMedidaE.LIBRA.getUnidadM())){
						if(medida.equals(UnidadMedidaE.KILO.getUnidadM())){
							op = cant *  2.20462262;
						}else if(medida.equals(UnidadMedidaE.GRAMO.getUnidadM())){
							op = cant * 0.00220462262;
						}
					}else if(um.equals(UnidadMedidaE.GRAMO.getUnidadM())){
						if(medida.equals(UnidadMedidaE.LIBRA.getUnidadM())){
							op = cant * 453.59237;
						}else if(medida.equals(UnidadMedidaE.KILO.getUnidadM())){
							op = cant * 1000;
						}
					}
					
					if(op < cantidad){
						opciones.put("color", false);
					}
					resultado.add(opciones);
				}catch(Exception e){
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
			Object[] result = validarGuardado(request);
			
			if(!result[0].toString().isEmpty()){
				modelo.addAttribute("error", result[0]);
				return Ingrediente(request, modelo);
			}

			bean.setId_inventario(request.getParameter("id_inventario") == null || request.getParameter("id_inventario").trim().isEmpty() ? 0 : Integer.parseInt(request.getParameter("id_inventario")));
			bean.setCompromiso(0);
			bean.setDisponible(Integer.parseInt(request.getParameter("cantSolicitada")));
			bean.setId_establecimiento(Integer.parseInt(request.getParameter("establecimiento")));
			bean.setId_producto(Integer.parseInt(request.getParameter("id_producto")));
			
			List<InventarioXMateria> listaInv = (List<InventarioXMateria>) result[1];
			List<MateriaPrima> listaMP = (List<MateriaPrima>) result[2];
			
			if(!bean.getId_inventario().equals(0)){
			
				ColseviDao.getInstance().getInventarioMapper().updateByPrimaryKey(bean);
				modelo.addAttribute("correcto", "Ingrediente Actualizado");
			}else{
				ColseviDao.getInstance().getInventarioMapper().insert(bean);
				
				InventarioExample invE = new InventarioExample();
				invE.setOrderByClause("id_inventario DESC");
				bean.setId_inventario(ColseviDao.getInstance().getInventarioMapper().selectByExample(invE).get(0).getId_inventario());
				
				modelo.addAttribute("correcto", "Inventario insertado");
			}

			if(request.getParameter("detalle") != null && !request.getParameter("detalle").trim().isEmpty() && request.getParameter("detalle").trim().equals("1")){

				InventarioXMateriaExample ixm = new InventarioXMateriaExample();
				ixm.createCriteria().andId_inventarioEqualTo(bean.getId_inventario());
				ColseviDao.getInstance().getInventarioXMateriaMapper().deleteByExample(ixm);
				
				for(InventarioXMateria beanMateria: listaInv){
					beanMateria.setId_inventario(bean.getId_inventario());
					ColseviDao.getInstance().getInventarioXMateriaMapper().insertSelective(beanMateria);
					
					MovimientoMateria mm = new MovimientoMateria();
					mm.setLote(beanMateria.getLote());
					mm.setId_unidad_peso(beanMateria.getId_unidad_peso());
					mm.setId_establecimiento(bean.getId_establecimiento());
					mm.setCantidad(beanMateria.getCantidad());
					mm.setFecha_movimiento(new Date());
					mm.setId_motivo(MotivoE.ASIGNACION.getMotivoE());
					
					ColseviDao.getInstance().getMovimientoMateriaMapper().insertSelective(mm);
				}
				
				for(MateriaPrima beanMP: listaMP){
					MateriaPrimaExample MPE = new MateriaPrimaExample();
					MPE.createCriteria().andLoteEqualTo(beanMP.getLote());
					beanMP.setId_establecimiento(bean.getId_establecimiento());
					ColseviDao.getInstance().getMateriaPrimaMapper().updateByExampleSelective(beanMP, MPE);
				}
			}
		}catch (Exception e) {
			modelo.addAttribute("error", "Contactar al administrador");
		}
		return Ingrediente(request, modelo);
	}
	
	public Object[] validarGuardado(HttpServletRequest request){

		Object[] obj = new Object[5];
		List<InventarioXMateria> ListaIinv = new ArrayList<InventarioXMateria>();
		List<MateriaPrima> listaMP = new ArrayList<MateriaPrima>();
		String error = "";
		String[] sec = request.getParameter("secuencia").split(",");
		Integer producto = Integer.parseInt(request.getParameter("id_producto"));
		Integer cantSolicitada = Integer.parseInt(request.getParameter("cantSolicitada"));
		Integer umVista = 0;
		Double opcompra = 0d,op = 0d,cantidadVista = 0d, totalAsignado = 0d;
		
		for(int i = 0; i < sec.length ; i ++){
			if(request.getParameter("cant" + sec[i]) != null && !request.getParameter("cant" + sec[i]).trim().isEmpty() &&
					!request.getParameter("um" + sec[i]).trim().equals("0")){
				cantidadVista = Double.valueOf(UtilidadManager.retirarCaracteresEspeciales(request.getParameter("cant" + sec[i])));	
				umVista = Integer.parseInt(request.getParameter("um" + sec[i]));
				
				if(cantidadVista != null && !cantidadVista.equals(0)){
					
					MateriaPrimaExample cxiE = new MateriaPrimaExample();
					cxiE.createCriteria().andLoteEqualTo(Integer.parseInt(sec[i])).andId_ingredienteEqualTo(Integer.parseInt(request.getParameter("ing" + sec[i])));
					MateriaPrima MP = ColseviDao.getInstance().getMateriaPrimaMapper().selectByExample(cxiE).get(0);
					
					IngredienteXProductoKey keyIXP = new IngredienteXProductoKey();
					keyIXP.setId_ingrediente(Integer.parseInt(request.getParameter("ing" + sec[i])));
					keyIXP.setId_producto(producto);
					IngredienteXProducto ingProd = ColseviDao.getInstance().getIngredienteXProductoMapper().selectByPrimaryKey(keyIXP);
					
					Ingrediente ing = ColseviDao.getInstance().getIngredienteMapper().selectByPrimaryKey(Integer.parseInt(request.getParameter("ing" + sec[i])));
					
					op = cantidadVista;
					opcompra = Double.valueOf(MP.getCantidad());
					
					ingProd.setCantidad(ingProd.getCantidad() * cantSolicitada);
			
					if(umVista.equals(UnidadMedidaE.KILO.getUnidadM())){
						if(ingProd.getId_unidad_peso().equals(UnidadMedidaE.LIBRA.getUnidadM())){
							op = cantidadVista *  2.20462262;
							umVista = ingProd.getId_unidad_peso();
						}else if(ingProd.getId_unidad_peso().equals(UnidadMedidaE.GRAMO.getUnidadM())){
							op = (double) (cantidadVista *  1000);
							umVista = ingProd.getId_unidad_peso();
						}
					}else if(umVista.equals(UnidadMedidaE.LIBRA.getUnidadM())){
						if(ingProd.getId_unidad_peso().equals(UnidadMedidaE.KILO.getUnidadM())){
							op = cantidadVista * 0.45359237;
							umVista = ingProd.getId_unidad_peso();
						}else if(ingProd.getId_unidad_peso().equals(UnidadMedidaE.GRAMO.getUnidadM())){
							op = cantidadVista * 453.59237;
							umVista = ingProd.getId_unidad_peso();
						}
					}else if(umVista.equals(UnidadMedidaE.GRAMO.getUnidadM())){
						if(ingProd.getId_unidad_peso().equals(UnidadMedidaE.LIBRA.getUnidadM())){
							op = cantidadVista * 0.00220462262;
							umVista = ingProd.getId_unidad_peso();
						}else if(ingProd.getId_unidad_peso().equals(UnidadMedidaE.KILO.getUnidadM())){
							op = (double) (cantidadVista / 1000);
							umVista = ingProd.getId_unidad_peso();
						}
					}
					
					if(MP.getId_unidad_peso().equals(UnidadMedidaE.KILO.getUnidadM())){
						if(umVista.equals(UnidadMedidaE.LIBRA.getUnidadM())){
							opcompra = MP.getCantidad() *  2.20462262;
						}else if(umVista.equals(UnidadMedidaE.GRAMO.getUnidadM())){
							opcompra = (double) (MP.getCantidad() *  1000);
						}
					}else if(MP.getId_unidad_peso().equals(UnidadMedidaE.LIBRA.getUnidadM())){
						if(umVista.equals(UnidadMedidaE.KILO.getUnidadM())){
							opcompra = MP.getCantidad() * 0.45359237;
						}else if(umVista.equals(UnidadMedidaE.GRAMO.getUnidadM())){
							opcompra = MP.getCantidad() * 453.59237;
						}
					}else if(MP.getId_unidad_peso().equals(UnidadMedidaE.GRAMO.getUnidadM())){
						if(umVista.equals(UnidadMedidaE.LIBRA.getUnidadM())){
							opcompra = MP.getCantidad() * 0.00220462262;
						}else if(umVista.equals(UnidadMedidaE.KILO.getUnidadM())){
							opcompra = (double) (MP.getCantidad() / 1000);
						}
					}
					
					totalAsignado =+ op;
					if(op <= opcompra && op <= ingProd.getCantidad() && totalAsignado <= ingProd.getCantidad()){
						InventarioXMateria ixm = new InventarioXMateria();
						ixm.setId_ingrediente(Integer.parseInt(request.getParameter("ing" + sec[i])));
						ixm.setCantidad(Double.valueOf(request.getParameter("cant" + sec[i])));
						ixm.setId_unidad_peso(Integer.parseInt(request.getParameter("um" + sec[i])));
						ixm.setLote(Integer.parseInt(sec[i]));
						ListaIinv.add(ixm);
						
						opcompra -= op;
						if(opcompra < 1){
							
							if(umVista.equals(UnidadMedidaE.KILO.getUnidadM())){
								opcompra *= 2.20462262;
								umVista = UnidadMedidaE.LIBRA.getUnidadM();
								if(opcompra < 1){
									opcompra *= 1000;
									umVista = UnidadMedidaE.GRAMO.getUnidadM();
								}
							}else if(umVista.equals(UnidadMedidaE.LIBRA.getUnidadM())){
								opcompra *= 453.59237;
								umVista = UnidadMedidaE.GRAMO.getUnidadM();
							}
						}else{
							if(umVista.equals(UnidadMedidaE.LIBRA.getUnidadM()) && (opcompra  * 0.45359237) > 1){
								opcompra *= 0.45359237;
								umVista = UnidadMedidaE.KILO.getUnidadM();
							}else if(umVista.equals(UnidadMedidaE.GRAMO.getUnidadM())){
								if((opcompra  * 0.00220462262) > 1){
									if((opcompra  / 1000) > 1){
										opcompra /= 1000;
										umVista = UnidadMedidaE.KILO.getUnidadM();
									}else{
										opcompra *= 0.00220462262;
										umVista = UnidadMedidaE.LIBRA.getUnidadM();
									}
								}
							}
						}
						
						MateriaPrima mp = new MateriaPrima();
						mp.setCantidad(opcompra);
						mp.setId_unidad_peso(umVista);
						mp.setLote(Integer.parseInt(sec[i]));
						listaMP.add(mp);
						
					}else{
						if(totalAsignado > ingProd.getCantidad()){
							error += "La cantidad seleccionada supera el numero requerido<br/>";
						}
						if(op > opcompra){
							error += "La cantidad seleccionada del lote" + sec[i] +  " supera la capacidad disponible<br/>";
						}
						if(op > ingProd.getCantidad()){
							error += "La cantidad seleccionada del ingrediente " + ing.getNombre() + " supera la capacidad configurada para el producto<br/>";
						}
					}
				}
			}
		}
		obj[0] = error;
		obj[1] = ListaIinv;
		obj[2] = listaMP;
		
		return obj;
	}
	
}