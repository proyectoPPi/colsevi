package com.colsevi.application;

import java.util.List;

import com.colsevi.dao.general.model.Establecimiento;
import com.colsevi.dao.general.model.EstablecimientoExample;

public class GeneralManager {

	public static List<Establecimiento> getEstablecimientos(){
		return ColseviDao.getInstance().getEstablecimientoMapper().selectByExample(new EstablecimientoExample());
	}
}
