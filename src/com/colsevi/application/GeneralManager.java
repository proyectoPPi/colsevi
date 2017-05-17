package com.colsevi.application;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import com.colsevi.controllers.usuario.RolE;
import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.EstablecimientoExample;
import com.colsevi.dao.general.model.TipoTelefono;
import com.colsevi.dao.general.model.TipoTelefonoExample;
import com.colsevi.dao.usuario.model.Rol;
import com.colsevi.dao.usuario.model.RolExample;
import com.colsevi.dao.usuario.model.TipoDocumento;
import com.colsevi.dao.usuario.model.TipoDocumentoExample;

public class GeneralManager {

	public static List<Establecimiento> getEstablecimientos(){
		return ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(new EstablecimientoExample());
	}
	
	public static List<TipoTelefono> listaTipoTelefono(){
		return ColseviDao.getInstance().getTipoTelefonoMapper().selectByExample(new TipoTelefonoExample());
	}
	
	public static List<TipoDocumento> listaTipoDocumento(){
		return ColseviDao.getInstance().getTipoDocumentoMapper().selectByExample(new TipoDocumentoExample());
	}
	
	public static List<Rol> listaRolPorSesion(Integer rol){
		return ColseviDao.getInstance().getRolMapper().ListaPorSesionRol(rol);
	}
	
	public static String byteToHex(String clave){
		String result = null;
		try{
			MessageDigest encrypt = MessageDigest.getInstance("SHA-1");
			encrypt.reset();
			encrypt.update(clave.getBytes("UTF-8"));
			final byte[] hash = encrypt.digest();
			
		    Formatter formatter = new Formatter();
		    for (byte b : hash){
		        formatter.format("%02x", b);
		    }
		    result = formatter.toString();
		    formatter.close();
		}catch(Exception e){
			
		}
	    return result;
	}
	
}
