package com.colsevi.controllers;

import java.io.Serializable;
import java.util.ArrayList;
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
		mapa.put("sesion", getUsuario(request) != null ? 'T' : 'F');

		return mapa;
	}
	
	public String getMenu(HttpServletRequest request){
		String menu = "";

		if(getUsuario(request) != null){
			NavegacionUsuario NU = new NavegacionUsuario();
			List<Pagina> listaPag = NU.getPaginasRol(getUsuario(request).getRol());
			
			for(Pagina pag: listaPag){
				if(pag.getMenu()){
					if(pag.getPadrePagina() != null){
						menu += "<li class=\"dropdown\">";
					}else{
						menu += "<li>";
					}
					menu += "<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\""+request.getContextPath()+pag.getUrl()+"\">"+
							pag.getNombre()+"</a>";
					
					if(pag.getPadrePagina() != null){
						String[] Padre = pag.getPadrePagina().split(",");
						List<Integer> list = new ArrayList<Integer>();
						for(int i = 0; i<Padre.length; i++){
							list.add(Integer.parseInt(Padre[i]));
						}
						PaginaExample PE = new PaginaExample();
						PE.createCriteria().andId_paginaIn(list);
						List<Pagina> listaPadre = ColseviDao.getInstance().getPaginaMapper().selectByExample(PE);
						menu += "<ul class=\"dropdown-menu\">";
						for(Pagina Ppag: listaPadre){
							menu += "<li>"+"<a href=\""+request.getContextPath()+Ppag.getUrl()+"\">+Ppag.getNombre()+"</a></li>";
						}
						menu += "</ul>";
					}
					
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
		
			PaginaExample pE = new PaginaExample();
			pE.createCriteria().andUrlLike(uri);
			Pagina pagina = null;
			try{
				pagina = ColseviDao.getInstance().getPaginaMapper().selectByExample(pE).get(0);
			}catch(Exception e){
				return menu;
			}
			
			try{
				if(pagina != null && pagina.getPadrePagina() != null){
					pE = new PaginaExample();
					String[] padre = pagina.getPadrePagina().split(",");
					List<Integer> ListaP = new ArrayList<Integer>();
					for(int i=0; i<padre.length; i++){
						ListaP.add(Integer.parseInt(padre[i]));
					}
					
					pE.createCriteria().andId_paginaIn(ListaP);
					List<Pagina> listaPagina = ColseviDao.getInstance().getPaginaMapper().selectByExample(pE);
					
					for(Pagina pag: listaPagina){
						menu += "<li>";
						menu += "<a href=\""+request.getContextPath()+pag.getUrl()+"\">";
						menu += pag.getNombre();
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
