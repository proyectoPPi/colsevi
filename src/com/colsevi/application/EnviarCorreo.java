package com.colsevi.application;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviarCorreo {

	 public static void enviar(String subject, String mensaje, String to) {

	      // Sender's email ID needs to be mentioned
	      String from = "brayan_cardona23141@elpoli.edu.co";//change accordingly
	      final String username = "brayan_cardona23141@elpoli.edu.co";//change accordingly
	      final String password = "yuranyta";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

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
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(subject);

//	         message.setText(mensaje);
	         
	         
//	         MimeMultipart alternative = new MimeMultipart("alternative");
//	         MimeBodyPart text = new MimeBodyPart();
//	         MimeBodyPart html = new MimeBodyPart();
//	         text.setText(mensaje);
//	         html.setContent("html content", "text/html");
//	         alternative.addBodyPart(text);
//	         alternative.addBodyPart(html);
//	         Message msg = new MimeMessage(session);
	         message.setContent(mensaje, "text/html; charset=utf-8");

	         // Send message
	         Transport.send(message);

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	   }
}