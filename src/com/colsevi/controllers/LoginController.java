package com.colsevi.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.ColseviDao;
import com.colsevi.application.EnviarCorreo;
import com.colsevi.application.GeneralManager;
import com.colsevi.application.SesionUsuario;
import com.colsevi.dao.general.model.Correo;
import com.colsevi.dao.general.model.CorreoExample;
import com.colsevi.dao.general.model.Mensajeria;
import com.colsevi.dao.usuario.model.Persona;
import com.colsevi.dao.usuario.model.Usuario;
import com.colsevi.dao.usuario.model.UsuarioExample;

@Controller
@RequestMapping("login")
public class LoginController extends BaseConfigController {
	
	private static final long serialVersionUID = 1759665983139848003L;
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping
	public ModelAndView login(HttpServletRequest request, ModelMap model){
		return new ModelAndView("/");
	}
	
	@RequestMapping("/Ingresar")
	public ModelAndView Ingresar(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		
		String errores = validar(usuario, clave);
		if(!errores.isEmpty()){
			model.addAttribute("error", errores);
			return new ModelAndView("front/index");
		}
		
		SesionUsuario U = getUsuario(usuario, clave, model);
		if(U != null){
			HttpSession sesion = request.getSession(true);
			sesion.setAttribute("sesion", U);
			Usuario bean = new Usuario();
			bean.setUsuario(U.getUsuario());
			bean.setPrimer_login("T");
			ColseviDao.getInstance().getUsuarioMapper().updateByPrimaryKeySelective(bean);
		}else{
			return new ModelAndView("front/index");
		}
		//principalBase
		return new ModelAndView("redireccionInicial","col",getValoresGenericos(request));
	}
	
	@RequestMapping("/Cerrar")
	public ModelAndView Logouth(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		
		try{
			HttpSession session = request.getSession(false);
			if (session != null) {
			    session.invalidate();
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		
		return new ModelAndView("front/index");
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
		String sha = GeneralManager.byteToHex(clave);
		if(sha == null){
			model.addAttribute("error", "Contactar al administrador");
			return null;
		}
		UsuarioExample UE = new UsuarioExample();
		UE.createCriteria().andUsuarioEqualTo(usuario).andClaveEqualTo(sha);
		Usuario usuarioBean = new Usuario();
		
		try{
			usuarioBean = ColseviDao.getInstance().getUsuarioMapper().selectByExample(UE).get(0);
		}catch(Exception e){
			logger.error(e.getMessage());
			model.addAttribute("error", "Usuario y/o contraseña incorrecta");
			return null;
		}
		
		if(!usuarioBean.getEstado().equals("T")){
			model.addAttribute("error", "Usuario inactivo");
			return null;
		}
		if(usuarioBean.getId_rol() == null){
			model.addAttribute("error", "Usuario sin perfil asignado");
			return null;
		}
		
		U.setUsuario(usuarioBean.getUsuario());
		U.setRol(usuarioBean.getId_rol());
		U.setPersona(usuarioBean.getId_persona());
		
		return U;
	}
	
	@RequestMapping("/recuperar")
	public ModelAndView recuperar(HttpServletRequest request, ModelMap model){
		
		Correo cor = new Correo();
		Persona per = new Persona();
		Usuario usuarioBean = new Usuario();
		String usuario = request.getParameter("usuarioRecuperar");
		if(usuario != null && !usuario.trim().isEmpty()){

			CorreoExample CE = new CorreoExample();
			UsuarioExample UE = new UsuarioExample();
			UE.createCriteria().andUsuarioEqualTo(usuario);
			
			try{
				usuarioBean = ColseviDao.getInstance().getUsuarioMapper().selectByExample(UE).get(0);
				CE.createCriteria().andId_personaEqualTo(usuarioBean.getId_persona());
				cor = ColseviDao.getInstance().getCorreoMapper().selectByExample(CE).get(0);
				per = ColseviDao.getInstance().getPersonaMapper().selectByPrimaryKey(usuarioBean.getId_persona());
			}catch(Exception e){
				cor = null;
				model.addAttribute("error", "No existe un correo en el sistema");
			}
			
			try{
				if(cor != null){
					EnviarCorreo.RecuperarContraseña(cor.getCorreo());
				}
			}catch(Exception e){
				cor = null;
				model.addAttribute("error", "Error enviando el correo");
			}
		}
		
		return new ModelAndView("front/index");
	}
}
