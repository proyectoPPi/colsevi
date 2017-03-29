package com.colsevi.application;
 
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.colsevi.dao.usuario.model.Pagina;

public class FiltroAutenticacion implements Filter, Serializable{

	private static final long serialVersionUID = -130687285287276931L;
	public static final String INDEX = "/front/index";
	
	public void destroy() { }

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		try{
			HttpServletRequest httpServletRequest =  (HttpServletRequest)servletRequest;
			String uri = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
			String url = uri;
			if (httpServletRequest.getQueryString() != null){
				url += "?" + httpServletRequest.getQueryString();
			}
			
			boolean ExcluirVerficacion= isExcluirVerficacion(uri);
			if(ExcluirVerficacion){
				chain.doFilter(servletRequest, servletResponse);
				return;
			}
			
			SesionUsuario userSession = (SesionUsuario) httpServletRequest.getSession().getAttribute("sesion");
			if(userSession != null){
				boolean validar = validarPermisos(uri, url, userSession);
				if(validar){
					chain.doFilter(servletRequest, servletResponse);
					return;
				}else{
					httpServletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);//Ajustar pagina error
				}
			}else{
				httpServletRequest.getRequestDispatcher("/").forward(servletRequest, servletResponse);
			}
			
		}catch (Throwable e) {
			e.printStackTrace();
			((HttpServletRequest)servletRequest).setAttribute("ERROR", "ERROR EN EL SISTEMA");
			((HttpServletRequest)servletRequest).getRequestDispatcher("/").forward(servletRequest, servletResponse);
		}
	}

	private boolean isExcluirVerficacion(String path) {

		if (path.endsWith("jpg")  || path.endsWith("png")  || path.endsWith("gif") ||
			path.endsWith("css") || path.endsWith("js")   ||  path.endsWith("pdf") || 
			path.startsWith("/login") ||
			path.startsWith("/Usuario/ClienteRegistro") ||
			path.startsWith("/Pedido/PedidoWizardStep1") ||
			path.startsWith("/Pedido/PedidoWizardStep2") ||
			path.startsWith("/Pedido/PedidoWizardStep3") ||
			path.startsWith("/Pedido/Visualizar") ||
			path.startsWith(INDEX)
			){
			return true;
		}else{		
			return false;
		}
	}

	public boolean validarPermisos(String uri, String url, SesionUsuario user) {
		if(uri.equals("/")){
			return true;
		}
		
		NavegacionUsuario iniciar = new NavegacionUsuario();
		List<Pagina> ListaPaginas = iniciar.getPaginasRol(user.getRol());
		for (Pagina bean : ListaPaginas) {
			if(bean.getUrl().endsWith(".html")){
				String[] urlSplit = bean.getUrl().split(".html");
				if(url.replace(".html", "").startsWith(urlSplit[0])){
					return true;
				}
			}
		}
		return false;
	}
	
	public void init(FilterConfig arg0) throws ServletException {}

}