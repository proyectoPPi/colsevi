package com.colsevi.controllers.pago;

import java.io.IOException;	
import java.math.BigDecimal;
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
import com.colsevi.application.ProveedorManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.pago.model.PagoProveedor;
import com.colsevi.dao.pago.model.PagoProveedorExample;
import com.colsevi.dao.proveedor.model.CompraProveedor;

@Controller
@RequestMapping("/pago/Proveedor")
public class PagoProveedorController extends BaseConfigController{

	private static final long serialVersionUID = -3396874236142724754L;
	private static Logger logger = Logger.getLogger(PagoProveedorController.class);
	
	@RequestMapping
	public String PagoProv (HttpServletRequest request, ModelMap model){
		return "/pago/ProveedorPago";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		
		PagoProveedorExample PPE = new PagoProveedorExample();
		
		try{		
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			PPE.setLimit(Inicio + ", " + Final);
			
			result.put("datos", ConstruirJson(ColseviDao.getInstance().getPagoProveedorMapper().selectByExample(PPE)));
			result.put("total", ColseviDao.getInstance().getPagoProveedorMapper().countByExample(PPE));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirJson(List<PagoProveedor> listaPago){
		JSONArray result = new JSONArray();
		JSONObject options = new JSONObject();
		
		for(PagoProveedor bean: listaPago){
			try{
				options = new JSONObject();
				
				options.put("id_pago_proveedor", bean.getId_pago_proveedor());
				options.put("id_compra", bean.getId_compra());
				options.put("compra", "");
				options.put("fecha_pago", UtilidadManager.FechaDateConHora_Vista(bean.getFecha_pago()));
				options.put("observacion", bean.getObservacion());
				options.put("pendiente", UtilidadManager.MonedaVista(bean.getPendiente()));
				options.put("valor_pagado", UtilidadManager.MonedaVista(bean.getValor_pagado()));
				
				result.add(options);
			}catch(Exception e ){
				logger.error(e.getMessage());
				continue;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/cargarDeuda")
	public void cargarDeuda(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		try{
			Integer prov = Integer.parseInt(request.getParameter("prov"));
			if(prov != null && !prov.equals(0)){
				mapa.put("compra", prov);
				result.put("datos", ConstruirDeuda(ColseviDao.getInstance().getPagoProveedorMapper().deudaCom(mapa)));
			}else{
				result.put("datos", "");
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}

		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");	
		
		result.writeJSONString(response.getWriter());
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConstruirDeuda(List<Map<String, Object>> deudaLista){
		JSONArray result = new JSONArray();
		JSONObject options = new JSONObject();
		
		for(Map<String, Object> map: deudaLista){
			try{
				options = new JSONObject();
				
				options.put("id_compra_proveedor", map.get("id_compra_proveedor"));
				options.put("pendiente", new BigDecimal(map.get("pendiente").toString()).intValue());
				options.put("valor", new BigDecimal(map.get("valor").toString()).intValue());
				options.put("diferencia", new BigDecimal(map.get("diferencia").toString()).intValue());
				
				result.add(options);
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/guardar")
	public void Guardar(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject resultVista = new JSONObject();
		Integer compra = 0;
		BigDecimal pendiente = new BigDecimal(0);
		BigDecimal valorP = new BigDecimal(0);
		String obs = request.getParameter("observacion") != null && !request.getParameter("observacion").trim().isEmpty() ? request.getParameter("observacion") : "";
		String error = "";
		
		if(obs.isEmpty())
			error += "Ingresar una observación</br>";
		
		try{
			compra = Integer.parseInt(request.getParameter("compra"));
		}catch(Exception e){
			error += "Seleccionar una compra válida<br/>";
		}
		
		try{
			valorP = new BigDecimal(request.getParameter("valorP"));
			if(valorP.doubleValue() <0)
				error += "Ingresar un valor mayor a cero<br/>";
		}catch(Exception e){
			error += "Ingresar un valor a pagar<br/>";
		}
		
		try{
			pendiente = new BigDecimal(request.getParameter("ValPend"));
		}catch(Exception e){
			error += "Ingresar un valor a pagar<br/>";
		}
		try{
			
			if((pendiente.doubleValue() - valorP.doubleValue()) < 0)
				error += "El valor pagado no puede superar el valor pendiente<br/>";
			else
				pendiente = new BigDecimal(pendiente.doubleValue() - valorP.doubleValue());
		}catch(Exception e){
			error += "Ingresar una compra válida<br/>";
		}
		if(!error.isEmpty())
			resultVista.put("error", error);
		else{
			try{
				CompraProveedor compraProv = new CompraProveedor();
				compraProv.setId_compra_proveedor(compra);
				compraProv.setPendiente(pendiente);
				ColseviDao.getInstance().getCompraProveedorMapper().updateByPrimaryKeySelective(compraProv);
				
				ProveedorManager.InsertarPago(compra, new Date(), pendiente, valorP, obs);
				
				resultVista.put("correcto", "Pago creado");
			}catch(Exception e){
				logger.error(e.getMessage());
				resultVista.put("error", "Contactar al administrador<br/>");
			}
		}
		ResponseJson(request, response, resultVista);
	}
	
	@RequestMapping("/autocompletar")
	public void auto(HttpServletRequest request, HttpServletResponse response){
		try{
			JSONObject result = new JSONObject();
			
			String proveedor = request.getParameter("campo");
			result = ProveedorManager.AutocompletarProveedor(proveedor);
			
			if(result != null){
				response.setContentType("text/html;charset=ISO-8859-1");
				request.setCharacterEncoding("UTF8");
				
				result.writeJSONString(response.getWriter());
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
	}
	
}