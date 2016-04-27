package com.colsevi.controllers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.NavegacionUsuario;
import com.colsevi.application.SesionUsuario;
import com.colsevi.dao.usuario.model.Pagina;
import com.colsevi.dao.usuario.model.PaginaExample;

public class BaseConfigController implements Serializable {

	private static final long serialVersionUID = -5638128544265729391L;
	
	public Map<String, Object> getValoresGenericos(HttpServletRequest request){
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("menu", getMenu(request));
		mapa.put("SubMenu", SubMenu(request));
		
		return mapa;
	}
	
	public String getMenu(HttpServletRequest request){
		String menu = "";

		if(getUsuario(request) != null){
			NavegacionUsuario NU = new NavegacionUsuario();
			List<Pagina> listaPag = NU.getPaginasRol(getUsuario(request).getRol());
			
			for(Pagina pag: listaPag){
				if(pag.getPadrePagina() == null){
					menu += "<li>";
					menu += "<a href=\""+request.getContextPath()+pag.getUrl()+"\">";
					menu += "<i class=\""+pag.getIcono()+"\"></i>";
					menu += "<span>"+pag.getNombre()+"</span>";
					menu += "</a>";
					menu += "</li>";
				}
			}
		}
		return menu;
	}
	
	public String SubMenu(HttpServletRequest request){

		String menu = "";
		if(getUsuario(request) != null){
			String uri = request.getRequestURI().substring(request.getContextPath().length());
		
			try{
			
				PaginaExample pE = new PaginaExample();
				pE.createCriteria().andUrlLike(uri);
				Integer id = ColseviDao.getInstance().getPaginaMapper().selectByExample(pE).get(0).getId_pagina();
				
				if(id != null){
					pE = new PaginaExample();
					pE.createCriteria().andPadrePaginaLike(id.toString());
					List<Pagina> listaPagina = ColseviDao.getInstance().getPaginaMapper().selectByExample(pE);
					
					for(Pagina pag: listaPagina){
						menu += "<li>";
						menu += "<a href=\""+request.getContextPath()+pag.getUrl()+"\">";
						menu += "<span>"+pag.getNombre()+"</span>";
						menu += "</a>";
						menu += "</li>";
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			}
				
			}
		return menu;
	}
	
	public SesionUsuario getUsuario(HttpServletRequest request){
		if(request.getSession() != null && request.getSession().getAttribute("sesion") != null){
			return (SesionUsuario) request.getSession().getAttribute("sesion");
		}
		return null;
	}

}
