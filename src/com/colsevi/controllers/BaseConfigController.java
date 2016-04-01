package com.colsevi.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.colsevi.application.NavegacionUsuario;
import com.colsevi.application.SesionUsuario;
import com.colsevi.dao.usuario.model.Pagina;

public class BaseConfigController implements Serializable {

	private static final long serialVersionUID = -5638128544265729391L;
	
	public Map<String, Object> getValoresGenericos(HttpServletRequest request){
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("menu", getMenu(request));
		return mapa;
	}
	
	public String getMenu(HttpServletRequest request){
		String menu = "";
		NavegacionUsuario NU = new NavegacionUsuario();
		List<Pagina> listaPag = NU.getPaginasRol(getUsuario(request).getRol());
		
		for(Pagina pag: listaPag){
			menu += "<li>";
			menu += "<a href=\""+request.getContextPath()+pag.getUrl()+"\">";
			menu += "<i class=\"fa fa-remove\"></i>";
			menu += "<span>"+pag.getNombre()+"</span>";
			menu += "</a>";
			menu += "</li>";
		}
		
		return menu;
	}
	
	public SesionUsuario getUsuario(HttpServletRequest request){
		return (SesionUsuario) request.getSession().getAttribute("sesion");
		
	}

}
