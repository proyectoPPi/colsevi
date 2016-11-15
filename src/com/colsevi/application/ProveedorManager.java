package com.colsevi.application;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.colsevi.dao.pago.model.PagoProveedor;
import com.colsevi.dao.proveedor.model.Proveedor;
import com.colsevi.dao.proveedor.model.ProveedorExample;
import com.colsevi.dao.proveedor.model.TipoProveedor;
import com.colsevi.dao.proveedor.model.TipoProveedorExample;

public class ProveedorManager {
	private static Logger logger = Logger.getLogger(ProveedorManager.class);
	
  public static List<Proveedor> getProveedores(){
		return ColseviDao.getInstance().getProveedorMapper().selectByExample(new ProveedorExample());
	}
  
  public static List<TipoProveedor> listaTipoProveedor(){
		return ColseviDao.getInstance().getTipoProveedorMapper().selectByExample(new TipoProveedorExample());
	}
  
  public static void InsertarPago(Integer compra, Date fechaPago, BigDecimal pendiente, BigDecimal valorPag, String obs){
	  
	PagoProveedor pp = new PagoProveedor();
	pp.setId_compra(compra);
	pp.setFecha_pago(fechaPago);
	pp.setPendiente(pendiente);
	pp.setValor_pagado(valorPag);
	pp.setObservacion(obs);
	
	ColseviDao.getInstance().getPagoProveedorMapper().insertSelective(pp);
  }
  
  @SuppressWarnings("unchecked")
	public static JSONObject AutocompletarProveedor(String proveedor){
		
		try{
			JSONObject result = new JSONObject();
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			if(!proveedor.isEmpty()){
				mapa.put("proveedor", "%" + proveedor + "%");
			}
			result.put("labels", JsonAutocompletar(ColseviDao.getInstance().getProveedorMapper().SelectAutocomplete(mapa)));
			
			return result;
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONArray JsonAutocompletar(List<Map<String, Object>> listaCliente){
		
		JSONArray resultado = new JSONArray();
		JSONObject opciones = new JSONObject();
		
		for(Map<String, Object> map: listaCliente){
			opciones = new JSONObject();
			try{
				opciones.put("label", map.get("nombre"));
				opciones.put("value", map.get("nombre"));
				opciones.put("id_proveedor", map.get("id_proveedor"));
				opciones.put("descripcion", map.get("descripcion"));
			}catch(Exception e){
				logger.error(e.getMessage());
				continue;
			}
			
			resultado.add(opciones);
		}
		return resultado;
	}

  
}
