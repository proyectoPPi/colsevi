package com.colsevi.application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class IniciarDatos extends HttpServlet {
	
	private static final long serialVersionUID = 5284909550243984659L;
	
    public IniciarDatos() {
        super();
    }
   
    public void init() throws ServletException {
    	try{
	    	super.init();
	    	NavegacionUsuario usuario = new NavegacionUsuario();
	    	usuario.cargarPermisos();
	    	
			System.out.println("Iniciar");
    	}catch(Exception e){
    	}
    }
}