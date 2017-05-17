package com.colsevi.application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.colsevi.dao.general.model.Configuracion;
import com.colsevi.dao.general.model.ConfiguracionExample;

public class ConfiguracionGeneral implements java.io.Serializable {

	private static final long serialVersionUID = -7085558475246737852L;

	private static Map<String, Object> paramsConfig = null;
	
	private synchronized static void createInstance() {
		if (paramsConfig == null) {
			paramsConfig = new HashMap<String, Object>();
			cargarData();
		}
	}
	
	public static Map<String, Object> getInstance() {
		if (paramsConfig == null)
			createInstance();
		return paramsConfig;
	}
	
	public static void cargarData(){
		List<Configuracion> LC = ColseviDao.getInstance().getConfiguracionMapper().selectByExample(new ConfiguracionExample());
		
		for(Configuracion con: LC){
			paramsConfig.put(con.getCodigo(), con.getValor());
		}
	}
	 
	public Map<String, Object> getConfiguracion(){
    	if(getInstance() == null || getInstance().isEmpty())
    		cargarData();

    	return (Map<String, Object>) getInstance();
    }
}