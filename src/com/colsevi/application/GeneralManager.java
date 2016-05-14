package com.colsevi.application;

import java.util.List;

import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.EstablecimientoExample;
import com.colsevi.dao.general.model.TipoTelefono;
import com.colsevi.dao.general.model.TipoTelefonoExample;

public class GeneralManager {

	public static List<Establecimiento> getEstablecimientos(){
		return ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(new EstablecimientoExample());
	}
	
	public static List<TipoTelefono> listaTipoTelefono(){
		return ColseviDao.getInstance().getTipoTelefonoMapper().selectByExample(new TipoTelefonoExample());
	}
}
