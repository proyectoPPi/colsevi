package com.colsevi.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.ConfiguracionGeneral;
import com.colsevi.application.SesionUsuario;
import com.colsevi.dao.usuario.model.Pagina;
import com.colsevi.dao.usuario.model.PaginaExample;

public class BaseConfigController implements Serializable {

	private static final long serialVersionUID = -5638128544265729391L;
	
	public Map<String, Object> getValoresGenericos(HttpServletRequest request){
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("menu", getMenu(request));
		mapa.put("sesion", getUsuario(request));
		mapa.put("configuracion", VariablesConfiguracion());

		return mapa;
	}
	
	public String getMenu(HttpServletRequest request){
		String menu = "";
		Map<String, Object> mapa = new HashMap<String, Object>();
		mapa.put("rol", getUsuario(request).getRol());
		
		if(getUsuario(request) != null){
			List<Pagina> listaPag = ColseviDao.getInstance().getPaginaMapper().ListaMenuPadre(mapa);
			
			for(Pagina pag: listaPag){
				if(pag.getMenu()){
					
					if(pag.getPadrePagina() != null && !pag.getPadrePagina().trim().isEmpty()){
						menu += "<li class=\"dropdown\">";
					}else{
						menu += "<li>";
					}
					menu += "<a class=\"dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\">"+pag.getNombre()+"</a>";
					
					if(pag.getPadrePagina() != null && !pag.getPadrePagina().trim().isEmpty()){
						String[] Padre = pag.getPadrePagina().split(",");
						List<Integer> list = new ArrayList<Integer>();
						for(int i = 0; i<Padre.length; i++){
							list.add(Integer.parseInt(Padre[i]));
						}
						PaginaExample PE = new PaginaExample();
						PE.createCriteria().andId_paginaIn(list);
						List<Pagina> listaHijo = ColseviDao.getInstance().getPaginaMapper().selectByExample(PE);
						
						menu += "<ul class=\"dropdown-menu\">";
						for(Pagina hijo: listaHijo){
							menu += "<li>"+"<a onclick=\"HredireccionarVista('" + request.getContextPath()+hijo.getUrl() + "')\" >"+hijo.getNombre()+"</a></li>";
						}
						menu += "</ul>";
					}
					
					menu += "</li>";
				}
			}
		}
		return menu;
	}
	
	public static Map<String, Object> VariablesConfiguracion() {
		return ConfiguracionGeneral.getInstance();
	}
	
	public SesionUsuario getUsuario(HttpServletRequest request){
		if(request.getSession() != null && request.getSession().getAttribute("sesion") != null){
			return (SesionUsuario) request.getSession().getAttribute("sesion");
		}
		return null;
	}
	
	public void ResponseJson(HttpServletRequest request, HttpServletResponse response, JSONObject result) throws IOException{
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}
	
	public void ResponseArray(HttpServletRequest request, HttpServletResponse response, JSONArray result) throws IOException{
		response.setContentType("text/html;charset=ISO-8859-1");
		request.setCharacterEncoding("UTF8");
		
		result.writeJSONString(response.getWriter());
	}

}
