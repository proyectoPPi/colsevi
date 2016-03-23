package com.colsevi.application;

import java.math.BigDecimal;
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
	
	public static String FormatDateDB(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			return sdf.format(date); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String Currency(BigDecimal valor){
		Locale locale = new Locale("es","CO"); // elegimos Argentina
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		return nf.format(valor);
	}
}
