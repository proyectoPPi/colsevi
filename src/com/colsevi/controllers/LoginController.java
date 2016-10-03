package com.colsevi.controllers;

import java.security.MessageDigest;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.EnviarCorreo;
import com.colsevi.application.SesionUsuario;
import com.colsevi.dao.general.model.Correo;
import com.colsevi.dao.general.model.CorreoExample;
import com.colsevi.dao.usuario.model.Persona;
import com.colsevi.dao.usuario.model.Usuario;
import com.colsevi.dao.usuario.model.UsuarioExample;

@Controller
public class LoginController {

	@RequestMapping("login")
	public ModelAndView login(HttpServletRequest request, ModelMap model){
		puerto(request);
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
	
	@RequestMapping("login/Cerrar")
	public ModelAndView Logouth(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		try{
			HttpSession session = request.getSession(false);
			if (session != null) {
			    session.invalidate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return login(request, model);
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
			model.addAttribute("error", "Usuario y/o contraseña incorrecta");
			return null;
		}
		
		if(!usu.getEstado().equals("T")){
			model.addAttribute("error", "Usuario inactivo");
			return null;
		}
		if(usu.getId_rol() == null){
			model.addAttribute("error", "Usuario sin perfil asignado");
			return null;
		}
		
		U.setUsuario(usu.getUsuario());
		U.setRol(usu.getId_rol());
		
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
	
	public ModelAndView recuperar(HttpServletRequest request, ModelMap model){
		
		Correo cor = new Correo();
		Persona per = new Persona();
		String correo = request.getParameter("email");
		if(correo != null && !correo.trim().isEmpty()){

			CorreoExample CE = new CorreoExample();
			CE.createCriteria().andCorreoEqualTo(correo);
			
			try{
				cor = ColseviDao.getInstance().getCorreoMapper().selectByExample(CE).get(0);
				per = ColseviDao.getInstance().getPersonaMapper().selectByPrimaryKey(cor.getId_persona());
			}catch(Exception e){
				cor = null;
				model.addAttribute("error", "No existe un correo en el sistema");
			}
			
			try{
				if(cor != null){
					StringBuffer mensaje = new StringBuffer("Hola " + per.getNombre() + "<br/>");
					mensaje.append("Hemos recibido un pedido para restablecer tu contraseña. <br/>");
					mensaje.append("Si no has iniciado este pedido, puedes simplemente ignorar este mensaje y ninguna acción será tomada. <br/>");
					mensaje.append("Para restablecer tu contraseña, haz click en el link abajo: <br/> <br/>");
					EnviarCorreo.enviar("Resetear tu contraseña", mensaje.toString(), cor.getCorreo());
				}
			}catch(Exception e){
				cor = null;
				model.addAttribute("error", "Error enviando el correo");
			}
		}
		
		return login(request, model);
	}
	
	public void puerto(HttpServletRequest request){
		String url = request.getScheme() + ":"+request.getServerName()+ request.getServerPort() + " "+ request.getContextPath();
		System.out.println(url);
	}
}
