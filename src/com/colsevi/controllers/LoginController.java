package com.colsevi.controllers;

import java.security.MessageDigest;
import java.util.Formatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.SesionUsuario;
import com.colsevi.dao.usuario.model.Usuario;
import com.colsevi.dao.usuario.model.UsuarioExample;

@Controller
public class LoginController {

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request, ModelMap model){
		return new ModelAndView("login");
	}
	
	@RequestMapping("login/Ingresar")
	public ModelAndView Ingresar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		
		String errores = validar(usuario, clave);
		if(!errores.isEmpty()){
			model.addAttribute("error", errores);
			return login(request, model);
		}
		
		SesionUsuario U = getUsuario(usuario, clave, model);
		if(U != null){
			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("sesion", U);
		}else{
			return login(request, model);
		}
		
		return new ModelAndView("redirect:/General/Establecimiento.html");
	}
	
	public String validar(String usuario, String clave){
		String error = "";
		if(usuario == null || usuario.trim().isEmpty()){
			error = "Ingrese el usuario<br/>";
		}
		if(clave == null || clave.trim().isEmpty()){
			error += "Ingrese la clave";
		}
		return error;
	}

	public SesionUsuario getUsuario(String usuario,String clave, ModelMap model){
		SesionUsuario U = new SesionUsuario();
		String sha1 = "";
		try{
			MessageDigest encrypt = MessageDigest.getInstance("SHA-1");
			encrypt.reset();
			encrypt.update(clave.getBytes("UTF-8"));
			sha1 = byteToHex(encrypt.digest());
		}catch(Exception e){
			e.printStackTrace();
		}
         
		UsuarioExample UsuarioExample = new UsuarioExample();
		UsuarioExample.createCriteria().andUsuarioEqualTo(usuario).andClaveEqualTo(sha1);
		Usuario usu = new Usuario();
		
		try{
			usu = ColseviDao.getInstance().getUsuarioMapper().selectByExample(UsuarioExample).get(0);
		}catch(Exception e){
			model.addAttribute("error", "No existe el usuario");
			return null;
		}
		
		if(!usu.getEstado().equals("T")){
			model.addAttribute("error", "Usuario inactivo");
			return null;
		}
		
		U.setUsuario(usu.getUsuario());
		
		return U;
	}
	
	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash){
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
}
