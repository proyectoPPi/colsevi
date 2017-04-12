package com.colsevi.application;

import java.util.Date;

import com.colsevi.dao.caja.model.CajaExample;

public class CajaManager {

	public static Boolean ExistenciaCaja(Date ejecucion){
		Boolean result = false;
		CajaExample CE = new CajaExample();

		try{
			CE.createCriteria().andEstadoEqualTo("3").andFecha_ejecucionEqualTo(ejecucion);
			Integer count = ColseviDao.getInstance().getCajaMapper().countByExample(CE);
			if(count > 0)
				result = true;
		}catch(Exception e){
			
		}
		return result;
	}
	
}
