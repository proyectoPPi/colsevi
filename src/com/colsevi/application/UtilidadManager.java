package com.colsevi.application;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
	
	public static Date FormatDateFormDB(String date){
		//retorna Sat Mar 12 13:16:15 COT 2016
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return formatoDelTexto.parse(date); 
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static Date FormatDateFormDB2(String date){
		//retorna Sat Mar 12 13:16:15 COT 2016
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatoDelTexto.parse(date); 
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String FormatDateDB(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			return sdf.format(date); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String Currency(BigDecimal valor){
		Locale locale = new Locale("es","CO"); // elegimos COLOMBIA
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		return nf.format(valor);
	}
	
	public static BigDecimal FormatStringBigDecimal(String valor){
		Locale locale = new Locale("es","CO"); // elegimos COLOMBIA
		BigDecimal decimal = null;
		
		DecimalFormat df = (DecimalFormat)	NumberFormat.getInstance(locale);
		df.setParseBigDecimal(true);

		valor = valor.replace('.', ',');
		try {
			decimal = (BigDecimal) df.parseObject(valor);
		} catch(ParseException e) {
		    // TODO: What ever you desire
		}
		return decimal;
	}
}
