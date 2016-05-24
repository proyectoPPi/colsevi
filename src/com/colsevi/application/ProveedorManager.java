package com.colsevi.application;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.colsevi.dao.pago.model.PagoProveedor;
import com.colsevi.dao.proveedor.model.Proveedor;
import com.colsevi.dao.proveedor.model.ProveedorExample;
import com.colsevi.dao.proveedor.model.TipoProveedor;
import com.colsevi.dao.proveedor.model.TipoProveedorExample;

public class ProveedorManager {
  
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
}
