package com.colsevi.application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class IniciarDatos extends HttpServlet {
	
	private static final long serialVersionUID = 5284909550243984659L;
	
    public IniciarDatos() {
        super();
    }
   
    @SuppressWarnings("static-access")
	public void init() throws ServletException {
    	try{
	    	super.init();
	    	ColseviDao dao = new ColseviDao();
	    	dao.getInstance().getEstablecimientoMapper();
	    	
			System.out.println("Iniciar");
    	}catch(Exception e){
    	}
    }
   	
	
}
