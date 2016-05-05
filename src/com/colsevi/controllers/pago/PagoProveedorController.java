package com.colsevi.controllers.pago;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.colsevi.application.ProveedorManager;
import com.colsevi.application.UtilidadManager;
import com.colsevi.controllers.BaseConfigController;
import com.colsevi.dao.deuda.model.DeudaProveedor;
import com.colsevi.dao.deuda.model.DeudaProveedorExample;
import com.colsevi.dao.pago.model.PagoProveedor;
import com.colsevi.dao.pago.model.PagoProveedorExample;

@Controller
public class PagoProveedorController extends BaseConfigController{

	private static final long serialVersionUID = -3396874236142724754L;

	@RequestMapping("/pago/Proveedor")
	public ModelAndView PagoProv (HttpServletRequest request, ModelMap model){
		model.addAttribute("proveedorLista", ProveedorManager.getProveedores());
		return new ModelAndView("/pago/ProveedorPago", "col", getValoresGenericos(request));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/pago/Proveedor/tabla")
	public void tabla(HttpServletRequest request, HttpServletResponse response) throws IOException{
		JSONObject result = new JSONObject();
		
		PagoProveedorExample PPE = new PagoProveedorExample();
		PagoProveedorExample.Criteria criteria = (PagoProveedorExample.Criteria) PPE.createCriteria();
		
		try{		
			String Inicio = request.getParameter("Inicio");
			String Final = request.getParameter("Final");
			PPE.setLimit(Inicio + ", " + Final);
			
			result.put("datos", ConstruirJson(ColseviDao.getInstance().getPagoProveedorMapper().selectByExample(PPE)));
			result.put("total", ColseviDao.getInstance().getPagoProveedorMapper().countByExample(PPE));
		}catch(Exception e){
			e.printStackTrace();
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
				options.put("fecha_pago", UtilidadManager.FormatoFechaVistaO(bean.getFecha_pago()));
				options.put("observacion", bean.getObservacion());
				options.put("pendiente", UtilidadManager.Currency(bean.getPendiente()));
				options.put("valor_pagado", UtilidadManager.Currency(bean.getValor_pagado()));
				
				result.add(options);
			}catch(Exception e ){
				continue;
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/pago/Proveedor/cargarDeuda")
	public void cargarDeuda(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		JSONObject result = new JSONObject();
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		Integer prov = Integer.parseInt(request.getParameter("prov"));
		if(prov != null && !prov.equals(0)){
			mapa.put("compra", prov);
			result.put("datos", ConstruirDeuda(ColseviDao.getInstance().getPagoProveedorMapper().deudaCom(mapa)));
		}else{
			result.put("datos", "");
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
				
				options.put("id", map.get("id_compra"));
				options.put("pendiente", new BigDecimal(map.get("pendiente").toString()).intValue());
				result.add(options);
			}catch(Exception e){
				continue;
			}
		}
		return result;
	}
	
	@RequestMapping("/pago/Proveedor/guardar")
	public ModelAndView Guardar(HttpServletRequest request, ModelMap model){
		
		Integer compra = 0;
		BigDecimal pendiente = new BigDecimal(0);
		BigDecimal valorP = new BigDecimal(0);
		DeudaProveedorExample DPE = new DeudaProveedorExample();
		DeudaProveedor dp = new DeudaProveedor();
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
			DPE.createCriteria().andId_compraEqualTo(compra);
			dp = ColseviDao.getInstance().getDeudaProveedorMapper().selectByExample(DPE).get(0);
			pendiente = dp.getPendiente();
			
			if((pendiente.doubleValue() - valorP.doubleValue()) < 0)
				error += "El valor pagado no puede superar el valor pendiente<br/>";
			else
				pendiente = new BigDecimal(pendiente.doubleValue() - valorP.doubleValue());
		}catch(Exception e){
			error += "Ingresar una compra válida<br/>";
		}
		if(error.isEmpty())
			model.addAttribute("error", error);
		else{
			try{
				DeudaProveedor deudaprov = new DeudaProveedor();
				deudaprov.setPendiente(pendiente);
				deudaprov.setId_deuda_proveedor(dp.getId_deuda_proveedor());
				deudaprov.setId_compra(dp.getId_compra());
				ColseviDao.getInstance().getDeudaProveedorMapper().updateByPrimaryKeySelective(deudaprov);
				
				ProveedorManager.InsertarPago(compra, new Date(), pendiente, valorP, obs);
				
				model.addAttribute("correcto", "Pago creado");
			}catch(Exception e){
				error += "Contactar al administrador<br/>";
			}
		}
		
		return PagoProv(request, model);
	}
	
}