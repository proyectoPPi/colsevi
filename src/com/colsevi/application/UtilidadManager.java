package com.colsevi.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.colsevi.dao.producto.model.TipoProducto;
import com.colsevi.dao.producto.model.TipoProductoExample;

public class UtilidadManager {

	public static String FormatDateComplete(String date){
		//retorna Sat Mar 12 13:16:15 COT 2016
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.format(formatoDelTexto.parse(date)); 
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static List<TipoProducto> getTipoProductoPadre(){
		TipoProductoExample tipoExample = new TipoProductoExample();
		tipoExample.createCriteria().andPadreIsNull();
		return ColseviDao.getInstance().getTipoProductoMapper().selectByExample(tipoExample);
	}
	
	public static List<TipoProducto> getTipoProductoHijo(Integer padre){
		TipoProductoExample tipoExample = new TipoProductoExample();
		tipoExample.createCriteria().andPadreEqualTo(padre);
		return ColseviDao.getInstance().getTipoProductoMapper().selectByExample(tipoExample);
	}
}
