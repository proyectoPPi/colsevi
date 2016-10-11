package com.colsevi.controllers.inventario;

import java.io.IOException;
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
import com.colsevi.dao.inventario.model.MateriaPrima;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.controllers.producto.ProductoAdminController;

@Controller
public class MateriaPrimaController extends BaseConfigController{

	private static final long serialVersionUID = 55977124238424730L;
	private static Logger logger = Logger.getLogger(MateriaPrimaController.class);

	@RequestMapping("/Inventario/MateriaPrima")
	public ModelAndView MateriaPrima(HttpServletRequest request, ModelMap model){
		model.addAttribute("ListaUM", ProductoManager.getTipoPeso());
		model.addAttribute("ListaE", GeneralManager.getEstablecimientos());
		return new ModelAndView("/inventario/MateriaPrima", "col", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/Inventario/MateriaPrima/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject options = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String loteF = request.getParameter("loteF");
		String cantidadF = request.getParameter("cantidadF");
		String unidadMF = request.getParameter("unidadMF");
		Boolean mayorF = Boolean.valueOf(request.getParameter("mayorF") != null && request.getParameter("mayorF").trim().equals("true") ? "true" : "false");
		String establecimientoF = request.getParameter("establecimientoF");
		mapa.put("limite", Inicio + "," + Final);
		
		if(loteF != null && !loteF.trim().isEmpty()){
			mapa.put("lote", loteF);
		}
		if(mayorF && cantidadF != null && !cantidadF.trim().isEmpty()){
			mapa.put("cant", ">" + cantidadF);
		}else if(cantidadF != null && !cantidadF.trim().isEmpty()){
			mapa.put("cant", "<" + cantidadF);
		}
		if(unidadMF != null && !unidadMF.trim().isEmpty() && !unidadMF.trim().equals("0")){
			mapa.put("um", unidadMF);
		}
		if(establecimientoF != null && !establecimientoF.trim().isEmpty() && !establecimientoF.trim().equals("0")){
			mapa.put("esta", establecimientoF);
		}
		
		try{
			options.put("datos", ConstruirJSON(ColseviDao.getInstance().getMateriaPrimaMapper().SelectDataView(mapa)));
			options.put("total", ColseviDao.getInstance().getMateriaPrimaMapper().CountDataView(mapa));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		options.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJSON(List<Map<String, Object>> ListaMP){
		JSONArray result = new JSONArray();
		JSONObject options = new JSONObject();
		
		for(Map<String, Object> map: ListaMP){
			try{
				options = new JSONObject();
				options.put("lote", map.get("lote"));
				options.put("loteid", map.get("lote"));
				options.put("cantidad", map.get("cantidad") == null ? "0" : map.get("cantidad"));
				options.put("fecha_vencimiento", map.get("fecha_vencimiento") != null ? UtilidadManager.FormatoFechaVistaO(map.get("fecha_vencimiento")) : "");
				options.put("nombreIng", map.get("nombreIng"));
				options.put("nombreEsta", map.get("nombreEsta"));
				options.put("nombreUp", map.get("nombreUp") == null ? "" : map.get("nombreUp"));
				options.put("id_unidad_peso", map.get("id_unidad_peso"));
				options.put("id_establecimiento", map.get("id_establecimiento"));
				
				result.add(options);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
		return result;
	}
	
	@RequestMapping("/Inventario/MateriaPrima/GuardarMovimiento")
	public ModelAndView GuardarMovimiento(HttpServletRequest request, ModelMap model){
		try{
			Object[] result = validarGuardar(request);
			Double conv = 0d;
			
			if(!result[0].toString().isEmpty()){
				model.addAttribute("error", result[0]);
				return MateriaPrima(request, model);
			}
			MateriaPrima materiaV = (MateriaPrima)result[1];
			MateriaPrima MP = ColseviDao.getInstance().getMateriaPrimaMapper().selectByPrimaryKey(materiaV.getLote());
			Integer motivo = null;
			
			if(MP != null && MP.getLote() != null){
				if(materiaV.getId_establecimiento().equals(MP.getId_establecimiento())){
					model.addAttribute("error", "Seleccionar un establecimiento distinto");
					return MateriaPrima(request, model);
				}
				if(request.getParameter("motivoMov") != null && !request.getParameter("motivoMov").trim().isEmpty() && (request.getParameter("motivoMov").equals("4") || request.getParameter("motivoMov").equals("3")))
					motivo = Integer.parseInt(request.getParameter("motivoMov"));
				else{
					model.addAttribute("error", "Seleccionar un motivo");
					return MateriaPrima(request, model);
				}
				
				if(materiaV.getId_unidad_peso().equals(MP.getId_unidad_peso())){
					conv = MP.getCantidad() - materiaV.getCantidad();
					
					if(conv < 1){
						result = InventarioManager.conversionEncontrarMayorUnidad(MP.getId_unidad_peso(), conv);
						conv = (Double) result[0];
						MP.setId_unidad_peso((Integer) result[1]);
					}
					
					if(materiaV.getCantidad() > MP.getCantidad())
						model.addAttribute("error", "La cantidad seleccionada supera la disponible");
				}else{
					result = InventarioManager.ConversionPMayorMenor(materiaV.getId_unidad_peso(), MP.getId_unidad_peso(), materiaV.getCantidad());
					conv = (Double) result[0];
					
					if((MP.getCantidad() - conv) < 0){
						model.addAttribute("error", "La cantidad seleccionada supera la disponible");
						return MateriaPrima(request, model);
					}
					conv = MP.getCantidad() - conv;
					
					if(conv < 1){
						result = InventarioManager.conversionEncontrarMayorUnidad(MP.getId_unidad_peso(), conv);
						conv = (Double) result[0];
						MP.setId_unidad_peso((Integer) result[1]);
					}else{
						result = InventarioManager.conversionMOptima(MP.getId_unidad_peso(), conv);
						conv = (Double) result[0];
						MP.setId_unidad_peso((Integer) result[1]);
					}
				}

				materiaV.setCantidad(materiaV.getCantidad());
				
				materiaV.setLote(null);
				materiaV.setId_ingrediente(MP.getId_ingrediente());
				materiaV.setFecha_vencimiento(MP.getFecha_vencimiento());
				ColseviDao.getInstance().getMateriaPrimaMapper().insertSelective(materiaV);
				
				materiaV.setCantidad(conv);
				materiaV.setLote(MP.getLote());
				materiaV.setId_unidad_peso(MP.getId_unidad_peso());
				materiaV.setId_establecimiento(MP.getId_establecimiento());
				InventarioManager.ActualizarMateriaPrima(materiaV);
				
				InventarioManager.RegistrarMovimientoMateria(MP.getLote(), MP.getId_unidad_peso(), conv, materiaV.getId_establecimiento(), new Date(), motivo);
				model.addAttribute("correcto", "Movimiento realizado");
			}else{
				model.addAttribute("error", "Seleccionar un ingrediente");
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			model.addAttribute("error", "Contactar al administrador");
		}
		return MateriaPrima(request, model);
	}
	
	public Object[] validarGuardar(HttpServletRequest request){
		
		Object[] obj = new Object[3];
		String error = "";
		MateriaPrima MP = new MateriaPrima();
		
		try{
			if(request.getParameter("lote") != null && !request.getParameter("lote").trim().isEmpty())
				MP.setLote(Integer.parseInt(request.getParameter("lote")));
			else
				error += "Seleccionar un ingrediente</br>";
			
			if(request.getParameter("cantMov") != null && !request.getParameter("cantMov").trim().isEmpty())
				MP.setCantidad(Double.valueOf(request.getParameter("cantMov")));
			else
				error += "Ingresar una cantidad</br>";
			
			if(request.getParameter("unidadMov") != null && !request.getParameter("unidadMov").trim().isEmpty() && !request.getParameter("unidadMov").trim().equals("0"))
				MP.setId_unidad_peso(Integer.valueOf(request.getParameter("unidadMov")));
			else
				error += "Seleccionar un tipo de peso</br>";
			
			if(request.getParameter("estaMov") != null && !request.getParameter("estaMov").trim().isEmpty() && !request.getParameter("estaMov").trim().equals("0"))
				MP.setId_establecimiento(Integer.valueOf(request.getParameter("estaMov")));
			else
				error += "Seleccionar un establecimiento</br>";
		}catch(Exception e){
			logger.error(e.getMessage());
			error = "Contactar al administrador";
		}
		
		obj[0] = error;
		obj[1] = MP;
		
		return obj;
	}
}