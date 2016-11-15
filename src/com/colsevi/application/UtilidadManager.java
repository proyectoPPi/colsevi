package com.colsevi.application;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UtilidadManager {

	public static String FechaStringConHora_Vista(String fecha){
		return FechaStringConHora_Vista(fecha, false);
	}
	
	public static String FechaStringConHora_Vista(String date, Boolean hora){
		//retorna Sat Mar 12 13:16:15 COT 2016
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(hora)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			return sdf.format(formatoDelTexto.parse(date)); 
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date FechaStringConHora_BD (String date){
		return FechaStringConHora_BD(date, false);
	}
	
	public static Date FechaStringConHora_BD(String date, Boolean hora){
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd"); 
		if(hora)
			formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			return formatoDelTexto.parse(date); 
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String FechaDateConHora_Vista(Date date){
		return FechaDateConHora_Vista(date, false);
	}
	
	public static String FechaDateConHora_Vista(Date date, Boolean hora){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(hora)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			return sdf.format(date); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Object[] FechaInicioFin(Date date){
		Object[] result = new Object[2];
		result[0] = FechaInicio(date);
		result[1] = FechaFin(date);
		
		return result;
	}
	
	private static Date FechaInicio(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 0, 0, 0);
	    return calendar.getTime();
	}

	private static Date FechaFin(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DATE);
	    calendar.set(year, month, day, 23, 59, 59);
	    return calendar.getTime();
	}

	public static String MonedaVista(BigDecimal valor){
		Locale locale = new Locale("es","CO");
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		return nf.format(valor);
	}
	
	public static BigDecimal MonedaBD(String valor){
		Locale locale = new Locale("es","CO"); // elegimos COLOMBIA
		BigDecimal decimal = null;
		
		DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(locale);
		df.setParseBigDecimal(true);
		valor = valor.replace('.', ',');
		
		try {
			decimal = (BigDecimal) df.parseObject(valor);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		
		return decimal;
	}
	
	public static String retirarCaracteresEspeciales(String valor){
		valor.replace(",", ".");
		valor.replace("-", "");
		valor.replace("+", "");
		valor.replace("*", "");
		valor.replace("/", "");
		valor.replace(" ", "");
		
		return valor;
	}
}