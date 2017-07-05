package com.colsevi.application;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.simplejavamail.mailer.config.TransportStrategy;

import com.colsevi.dao.general.model.Mensajeria;

public class EnviarCorreo {

	 public static void enviar(String subject, String mensaje, String to) {

	      // Sender's email ID needs to be mentioned
	      String from = "brayan_cardona23141@elpoli.edu.co";//change accordingly
	      final String username = "colsevirestaurantes@gmail.com";//change accordingly
	      final String password = "restaurantes123*";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", false);
	      props.put("mail.smtp.ssl.enable", false);
	      props.put("mail.smtp.starttls.enable", true);
	      props.put("mail.smtp.port", "587");
	      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.socketFactory.port", "587");
	      props.put("mail.smtp.socketFactory.fallback", false);
	      props.put("mail.smtp.ssl.socketFactory", false);
	      
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
	            throw new RuntimeException(e);
	      }
	   }
	 
	 public static void RecuperarContraseña(String para) {
		 Mensajeria mensaje = ColseviDao.getInstance().getMensajeriaMapper().selectByPrimaryKey("RECUPERAR_CONTRASEÑA");
		 
		 enviar(mensaje.getAsunto(),mensaje.getMensaje(),para);
	 }
}