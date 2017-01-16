package com.tau.commstudy.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

@Service
public class EmailService {

    @Value("${mailKeys.file}")
    private String mailKeyFile;

    public Boolean emailComment(String email, String title) throws AddressException, MessagingException {
	Properties props = System.getProperties();
	String[] keys = getcredentialsFromFile();
	String mail = keys[0];
	String password = keys[1];
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(mail));
	;
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
	msg.setSubject("התקבלה תגובה חדשה לשאלתך");
	msg.setText("הגיבו לשאלתך: " + title + " .");
	msg.setHeader("X-Mailer", "Tov Are's program");
	msg.setSentDate(new Date(0));
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", mail, password);
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }

    public Boolean emailNewUser(String email, String name) throws AddressException, MessagingException {
	Properties props = System.getProperties();
	String[] keys = getcredentialsFromFile();
	String mail = keys[0];
	String password = keys[1];
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(mail));
	;
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
	msg.setSubject("Welcome to Study buddy TAU!");
	msg.setText("שלום " + name + "\n\r"
		+ ".ברוכ/ה הבא/ה, כעת תוכל/י לשאול שאלות על מבחנים ולקבל תשובות במהירות ובקלות.");
	msg.setHeader("X-Mailer", "Tov Are's program");
	msg.setSentDate(new Date(0));
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", mail, password);
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }

    private String[] getcredentialsFromFile() {
	String[] keys = new String[2];
	BufferedReader fileReader = null;

	final String DELIMITER = ",";
	try {
	    String line = "";
	    fileReader = new BufferedReader(new FileReader(mailKeyFile));
	    int i = 0;
	    while ((line = fileReader.readLine()) != null) {
		String[] row = line.split(DELIMITER);
		keys[i] = row[1];
		i++;
	    }
	    try {
		fileReader.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return keys;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
