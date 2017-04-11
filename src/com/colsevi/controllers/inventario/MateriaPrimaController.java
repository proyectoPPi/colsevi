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

import com.colsevi.application.ColseviDao;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.InventarioManager;
import com.colsevi.application.ProductoManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.dao.inventario.model.MateriaPrima;
import com.colsevi.controllers.BaseConfigController;

@Controller
@RequestMapping("/Inventario/MateriaPrima")
public class MateriaPrimaController extends BaseConfigController{

	private static final long serialVersionUID = 55977124238424730L;
	private static Logger logger = Logger.getLogger(MateriaPrimaController.class);

	@RequestMapping
	public String MateriaPrima(HttpServletRequest request, ModelMap model){
		model.addAttribute("ListaUM", ProductoManager.getTipoPeso());
		model.addAttribute("ListaE", GeneralManager.getEstablecimientos());
		return "/inventario/MateriaPrima";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject options = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		String loteF = request.getParameter("loteF");
		String cantidadF = request.getParameter("cantidadF");
		String unidadMF = request.getParameter("unidadMF");
		String compra = request.getParameter("compra");
		String establecimientoF = request.getParameter("establecimientoF");
		
		String Inicio = request.getParameter("Inicio");
		String Final = request.getParameter("Final");
		String vencimiento = request.getParameter("vencimiento");
		
		mapa.put("limite", Inicio + "," + Final);
		
		if(loteF != null && !loteF.trim().isEmpty())
			mapa.put("lote", loteF);
		if(compra != null && !compra.trim().isEmpty())
			mapa.put("compra", compra);
		if(cantidadF != null && !cantidadF.trim().isEmpty())
			mapa.put("cant", cantidadF);
		if(unidadMF != null && !unidadMF.trim().isEmpty() && !unidadMF.trim().equals("0"))
			mapa.put("um", unidadMF);
		if(vencimiento != null && !vencimiento.trim().isEmpty()){
			Object[] obj = UtilidadManager.FechaInicioFin(UtilidadManager.FechaStringConHora_BD(vencimiento));
			mapa.put("fechaI", obj[0]);
			mapa.put("fechaF", obj[1]);
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
				options.put("fecha_vencimiento", map.get("fecha_vencimiento") != null ? UtilidadManager.FechaDateConHora_Vista((Date) map.get("fecha_vencimiento")) : "");
				options.put("nombreIng", map.get("nombreIng"));
				options.put("nombreEsta", map.get("nombreEsta"));
				options.put("nombreUp", map.get("nombreUp") == null ? "" : map.get("nombreUp"));
				options.put("id_unidad_peso", map.get("id_unidad_peso"));
				options.put("id_establecimiento", map.get("id_establecimiento"));
				options.put("id_compra", map.get("id_compra"));
				
				result.add(options);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/GuardarMovimiento")
	public void GuardarMovimiento(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		try{
			Object[] result = validarGuardar(request);
			Double conv = 0d;
			
			if(!result[0].toString().isEmpty()){
				resultVista.put("error", result[0]);
				ResponseJson(request, response, resultVista);
				return;
			}
			MateriaPrima materiaV = (MateriaPrima)result[1];
			MateriaPrima MP = ColseviDao.getInstance().getMateriaPrimaMapper().selectByPrimaryKey(materiaV.getLote());
			Integer motivo = null;
			
			if(MP != null && MP.getLote() != null){
				if(materiaV.getId_establecimiento().equals(MP.getId_establecimiento())){
					resultVista.put("error", "Seleccionar un establecimiento distinto");
					ResponseJson(request, response, resultVista);
					return;
				}
				if(request.getParameter("motivoMov") != null && !request.getParameter("motivoMov").trim().isEmpty() && (request.getParameter("motivoMov").equals("4") || request.getParameter("motivoMov").equals("3")))
					motivo = Integer.parseInt(request.getParameter("motivoMov"));
				else{
					resultVista.put("error", "Seleccionar un motivo");
					ResponseJson(request, response, resultVista);
					return;
				}
				
				if(materiaV.getId_unidad_peso().equals(MP.getId_unidad_peso())){
					if(materiaV.getCantidad() > MP.getCantidad()){
						resultVista.put("error", "La cantidad ingresada no puede ser mayor a la del sistema.");
						ResponseJson(request, response, resultVista);
						return;
					}
					
					conv = MP.getCantidad() - materiaV.getCantidad();
					if(conv < 1){
						result = InventarioManager.conversionEncontrarMayorUnidad(MP.getId_unidad_peso(), conv);
						conv = (Double) result[0];
						MP.setId_unidad_peso((Integer) result[1]);
					}
				}else{
					result = InventarioManager.ConversionPMayorMenor(materiaV.getId_unidad_peso(), MP.getId_unidad_peso(), materiaV.getCantidad());
					conv = (Double) result[0];
					
					if((MP.getCantidad() - conv) < 0){
						resultVista.put("error", "La cantidad seleccionada supera la disponible");
						ResponseJson(request, response, resultVista);
						return;
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
				resultVista.put("correcto", "Movimiento realizado");
			}else{
				resultVista.put("error", "Seleccionar un ingrediente");
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			resultVista.put("error", "Contactar al administrador");
		}
		ResponseJson(request, response, resultVista);
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
			
			if(request.getParameter("cantMov") != null && !request.getParameter("cantMov").trim().isEmpty() && Double.valueOf(request.getParameter("cantMov")) > 0)
				MP.setCantidad(Double.valueOf(request.getParameter("cantMov")));
			else
				error += "Ingresar una cantidad válida</br>";
			
			if(request.getParameter("unidadMov") != null && !request.getParameter("unidadMov").trim().isEmpty())
				MP.setId_unidad_peso(Integer.valueOf(request.getParameter("unidadMov")));
			else
				error += "Seleccionar un tipo de peso</br>";
			
			if(request.getParameter("estaMov") != null && !request.getParameter("estaMov").trim().isEmpty())
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