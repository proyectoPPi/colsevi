package com.colsevi.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.colsevi.dao.usuario.model.Pagina;
import com.colsevi.dao.usuario.model.PaginaExample;
import com.colsevi.dao.usuario.model.PaginaXRolExample;
import com.colsevi.dao.usuario.model.PaginaXRolKey;
import com.colsevi.dao.usuario.model.Rol;
import com.colsevi.dao.usuario.model.RolExample;

public class NavegacionUsuario implements java.io.Serializable {


	private static final long serialVersionUID = -7085558475246737852L;

	private HashMap  params = new HashMap();
	
	 public void cargarPermisos(){
	    	List<Rol> listRol = ColseviDao.getInstance().getRolMapper().selectByExample(new RolExample());
	    	List<Integer> paginasId = new ArrayList<Integer>();
	    	for (Rol bean : listRol) {
	    		
	    		PaginaXRolExample paginaRolExample = new PaginaXRolExample();
	    		paginaRolExample.createCriteria().andId_rolEqualTo(bean.getId_rol());
	    		List<PaginaXRolKey> listPaginaRol = ColseviDao.getInstance().getPaginaXRolMapper().selectByExample(paginaRolExample);
	    		for (PaginaXRolKey paginaXRolKey : listPaginaRol) {
	    			paginasId.add(paginaXRolKey.getId_pagina());
				}
				
	    		PaginaExample PaginasExample = new PaginaExample();
	    		PaginasExample.createCriteria().andId_paginaIn(paginasId);
	    		List<Pagina> ListaPaginas = ColseviDao.getInstance().getPaginaMapper().selectByExample(PaginasExample);
	    		
	    		if(ListaPaginas != null && ListaPaginas.size() > 0){
	    			params.put(bean.getId_rol(), ListaPaginas);
	    		}
			}
	    }
	    
	    public List<Pagina> getPaginasRol(Integer rol){
	    	if(params == null || params.isEmpty()){
	    		cargarPermisos();
	    	}
	    	System.out.println(params.size());
	    	List<Pagina> ListaPaginas = (List<Pagina>) params.get(rol);
	    	
	    	return ListaPaginas;
	    }
		
}