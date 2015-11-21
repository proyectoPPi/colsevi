package com.colsevi.application;
 
import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class FiltroAutenticacion implements Filter, Serializable{

	private static final long serialVersionUID = -130687285287276931L;
	public static final String LOGIN = "/login";

	public void destroy() { }

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
		try{
			HttpServletRequest httpServletRequest =  (HttpServletRequest)servletRequest ;
			String uri = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
			String url = uri;
			if (httpServletRequest.getQueryString() != null){
				url += "?"+httpServletRequest.getQueryString();
			}
			
			
			boolean ExcluirVerficacion= isExcluirVerficacion(uri);
			if(ExcluirVerficacion){
				chain.doFilter(servletRequest, servletResponse);
				return;
			}
			
			SesionUsuario userSession = (SesionUsuario) httpServletRequest.getSession().getAttribute("sesion");
			if(userSession != null){
				
			}else{
				httpServletRequest.getRequestDispatcher(LOGIN).forward(servletRequest, servletResponse);
			}
			
		}catch (Throwable e) {

			e.printStackTrace();

			((HttpServletRequest)servletRequest).setAttribute("ERROR", "ERROR EN EL SISTEMA");
			((HttpServletRequest)servletRequest).getRequestDispatcher(LOGIN).forward(servletRequest, servletResponse);
		}
	}

	/**
	 * 
	 * @return
	 */
	private boolean isExcluirVerficacion(String path) {

		if (path.endsWith("jpg")  || path.endsWith("png")  || path.endsWith("gif") ||
			path.endsWith("css") || path.endsWith("js")   ||  path.endsWith("pdf") ||
			
			path.equalsIgnoreCase("/indexMobile.jsp") ||
			path.startsWith(LOGIN)
			){
			
			return true;
		}else{		
			return false;
		}
	}


	@SuppressWarnings("unused")
//	private DsUserSession loadUser(String contextPath, DsUser user,int joomlaid) throws S4DSBusinessException{
//		DsApplicationSetupManager setupManager = DsApplicationSetupManager.getInstance();
//		DsSecurityPersistence securityPersistence = DsSecurityPersistence.getInstance();
//		DsUserSession userSession = new DsUserSession();
//		
//		if(user.getPersonid() != null){
//			UmPersons persons = S4dsDAOHelper.getInstance().getUmPersonsDAO().selectByPrimaryKey(user.getPersonid());
//			if(persons != null && persons.getLanguages_id() != null){
//				GnLanguages language = S4dsDAOHelper.getInstance().getGnLanguagesMapper().selectByPrimaryKey(persons.getLanguages_id());
//				userSession.setSelectedLanguage(language.getLanguage_code());
//			}
//		}
//		if(userSession.getSelectedLanguage()==null || userSession.getSelectedLanguage().trim().equals("")){
//			userSession.setSelectedLanguage(DsApplicationSetupManager.getInstance().getPropertyOrDefault(ApplicationSetupVariable.DEFAULT_LANGUAGE, "en"));
//		}
//		
//		
//		DsUserNavigation dsUserNavigation = securityPersistence.getUserNavigation(contextPath, user, Messages.getInstance(userSession.getSelectedLanguage()));
//		String joomlaPath = setupManager.getProperty(ApplicationSetupVariable.JOOMLA_PATH);
//		String avatarPath = null;
//		if(joomlaid != 0)
//			avatarPath = CommunityUserManager.getAvatarImagePath(joomlaid);
//		if(avatarPath != null){
//			userSession.setAvatarImage(joomlaPath + avatarPath);
//		} else {
//			//userSession.setAvatarImage(setupManager.getProperty( ApplicationSetupVariable.DEFAULT_AVATAR ));
//			userSession.setAvatarImage("defaultAvatar.png");
//		}
//		loggerS4ds.info("DsUserSession joomla "+joomlaid);
//		loggerS4ds.info("userSession.getAvatarImage "+userSession.getAvatarImage());
//		
//		securityPersistence.setActionsForUser(user);
//		if(userSession == null)
//			userSession = new DsUserSession();
//		//seteo los objetos
//		userSession.setUser(user);
//		userSession.setDsUserNavigation(dsUserNavigation);
//
//		return userSession;
//	}
	
//	private boolean validatePagePermissions(String uri, String url, DsUserSession user) {
//		if(uri.equals("/") || uri.equals(HOME)){
//			return true;
//		}
//		if(user.getDsUserNavigation()==null){
//			return false;
//		}
//		DsPage destPage  = user.getDsUserNavigation().getPageByUrlBinding(uri);
//		if (destPage != null)
//			return true;
//		// Let's try with the full URL We use this because user navigation may contain URLs including parameters, which are discarded by the URI
//		destPage  = user.getDsUserNavigation().getPageByUrlBinding(url);
//		if (destPage != null)
//			return true;
//		
//		return false;
//	}
	
	public void init(FilterConfig arg0) throws ServletException {
	}

}
