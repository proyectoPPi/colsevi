package com.colsevi.application;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.colsevi.controllers.BaseConfigController;


public class FTPManager extends BaseConfigController{
	
	private static final long serialVersionUID = -7044971865185095283L;
	private static Logger logger = Logger.getLogger(FTPManager.class);
	
	private static FTPClient crearConexionFTP(){
		FTPClient ftp = new FTPClient();
		String host = VariablesConfiguracion().get("FTP_HOST").toString();
		Integer port = Integer.parseInt(VariablesConfiguracion().get("FPT_PORT").toString());
		String usuario = VariablesConfiguracion().get("FTP_USUARIO").toString();
		String pwd = VariablesConfiguracion().get("FTP_PWD").toString();
		
		try {
			ftp.connect(host,port);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		int replyCode = ftp.getReplyCode();
		 if (!FTPReply.isPositiveCompletion(replyCode)) {
             System.out.println("Operation failed. Server reply code: " + replyCode);
             return ftp;
         }
         try {
			boolean success = ftp.login(usuario, pwd);
			if (!success) {
                System.out.println("Could not login to the server");
                return ftp;
            } else {
                System.out.println("LOGGED IN SERVER");
            }
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
         
		return ftp;
	}

	private static void desconectarFTP(FTPClient ftp) {
		try {
			ftp.logout();
			ftp.disconnect();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	public static String ruta(String tipo) {
		String result ="";
		
		switch (tipo) {
		 
	        case "I":
	        result = "img/";
	        break;
	 
	        default:
	        result = "";
	        break;
 
		}
		
		return result;
	}
	
	public static String cargarArchivos(InputStream file, String extension, String ruta) {
		String result = "-1";
		FTPClient ftp = crearConexionFTP();
		
		try {
			 BufferedInputStream buffIn = null;
	            buffIn = new BufferedInputStream(file);
            ftp.changeWorkingDirectory(ruta(ruta));//Cambiar directorio de trabajo
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			
			Date nombre = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			result = sdf.format(nombre);
			ftp.storeFile(result + extension, buffIn);
			buffIn.close();
		} catch (IOException e) {
			result = "-1";
			logger.error(e.getMessage());
		}
		
		desconectarFTP(ftp);
		
		return result + extension;
		
	}

}
