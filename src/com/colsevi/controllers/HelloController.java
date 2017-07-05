package com.colsevi.controllers;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.colsevi.application.FTPManager;
import com.colsevi.application.validadorGeneral;

@Controller
public class HelloController extends BaseConfigController{

	private static final long serialVersionUID = -9194726703842278303L;
	private static Logger logger = Logger.getLogger(HelloController.class);

	@RequestMapping("/")
	public ModelAndView profile(HttpServletRequest request){
		if(getUsuario(request) != null && getUsuario(request).getPersona() != null){
			return new ModelAndView("inicialApp","col",getValoresGenericos(request));
		}else{
			return new ModelAndView("front/index");
		}
	}

	@RequestMapping("/subirArchivos")
	public @ResponseBody String  cargarArchivo(@RequestParam("file") MultipartFile file, HttpServletRequest request){
//		boolean esXSSAttack = !checkXSSAttack(file.getName(), request);
//		if(!esXSSAttack) {
//			return "";
//		}
		
		if(!file.isEmpty() && file.getSize() > 3) {
			try {
				if(file.getSize() <= 7000000) {
				    return FTPManager.cargarArchivos(file.getInputStream(), ".png", "I");
				}else {
					logger.error("Error en la carga del arhivo -- Supera el limite esperado");
					return "";
				}
			}catch (Exception e) {
				// TODO: handle exception
				return "";
			}
		}else {
			return "";
		}
	}
	
}
