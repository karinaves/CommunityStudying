package com.tau.commstudy.services;

import java.sql.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

@Service
public class EmailService {
    public Boolean emailComment(String email, String title) throws AddressException, MessagingException {
	Properties props = System.getProperties();
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress("studybuddytau@gmail.com"));
	;
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
	msg.setSubject("New comment to your question");
	msg.setText("Someone has commented to your question: " + title + " on Studdy Buddy TAU.");
	msg.setHeader("X-Mailer", "Tov Are's program");
	msg.setSentDate(new Date(0));
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", "studybuddytau@gmail.com", "ilovepeti");
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }

    public Boolean emailNewUser(String email, String name) throws AddressException, MessagingException {
	Properties props = System.getProperties();
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress("studybuddytau@gmail.com"));
	;
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
	msg.setSubject("Welcome to Study buddy TAU!");
	msg.setText("Dear " + name
		+ ", welcome to study buddy TAU. Here you can get all the information you need for your coming tests.");
	msg.setHeader("X-Mailer", "Tov Are's program");
	msg.setSentDate(new Date(0));
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", "studybuddytau@gmail.com", "ilovepeti");
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }
}
