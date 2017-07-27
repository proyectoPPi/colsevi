package com.colsevi.application;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.colsevi.dao.general.model.Mensajeria;

public class EnviarCorreo {

	private static Logger logger = Logger.getLogger(EnviarCorreo.class);

	 public static void enviar(String subject, String mensaje, String to) {

	      // Sender's email ID needs to be mentioned
	      String from = "brayan_cardona23141@elpoli.edu.co";//change accordingly
	      final String username = "colsevirestaurantes@gmail.com";//change accordingly
	      final String password = "restaurantes123*";//change accordingly

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", true);
	      props.put("mail.smtp.starttls.enable", true);
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.debug", "true");
	      
	      
	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(from));

	         message.setSubject(subject);
	         message.setContent(mensaje, "text/html; charset=utf-8");

	         // Send message
	         Transport.send(message);
	         
	      } catch (MessagingException e) {
	    	  logger.error(e.getMessage());
	            throw new RuntimeException(e);
	      }
	   }
	 
	 public static void RecuperarContraseña(String para) {
		 Mensajeria mensaje = ColseviDao.getInstance().getMensajeriaMapper().selectByPrimaryKey("RECUPERAR_CONTRASEÑA");
		 enviar("12","12",para);
	 }
}