package com.tau.commstudy.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sun.mail.smtp.SMTPTransport;

@Service
public class EmailService {

    @Value("${mailKeys.file}")
    private String mailKeyFile;

    @Value("${clientDomain}")
    private String clientDomain;

    @Value("${url}")
    private String url;

    private String emailUsername = null;
    private String emailPassword = null;

    public Boolean emailCommentToPost(String email, String title, Long postId, String name)
	    throws AddressException, MessagingException {
	Properties props = System.getProperties();
	String[] keys = getCredentials();
	String mail = keys[0];
	String password = keys[1];
	String postPath = url + "/#/questions/view/" + postId;
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(mail));
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

	msg.setSubject("התקבלה תגובה חדשה לשאלתך");
	MimeMultipart multipart = new MimeMultipart("related");

	// first part (the html)
	BodyPart messageBodyPart = new MimeBodyPart();
	String htmlText = "<head>" + "<style type=\"text/css\">" + "  .blue { color: #00f; }" + "</style>" + "</head>"
		+ "<body dir='rtl'>" + "<h3 class=\"blue\">" + "שלום " + name + "," + "</h3>" + "<p>" + ""
		+ "<strong>התקבלה תגובה חדשה לשאלתך: </strong>" + title + "." + "</p>" + "<p>" + "לצפייה " + "<a href="
		+ postPath + " >לחץ כאן</a></p>" + "<img src=\'cid:image\'>" + "<p>"
		+ "<small>ניתן לשנות את  הגדרות קבלת המיילים בדף עריכת פרופיל באתר* </small>" + "</p>" + "</body>";
	messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
	multipart.addBodyPart(messageBodyPart);

	// second part (the image)
	messageBodyPart = new MimeBodyPart();
	DataSource fds = new FileDataSource("C:\\wamp\\www\\CommunityStudying-Client\\public_html\\img\\logo.png");
	messageBodyPart.setDataHandler(new DataHandler(fds));
	messageBodyPart.setHeader("Content-ID", "<image>");
	multipart.addBodyPart(messageBodyPart);
	msg.setContent(multipart);

	msg.setSentDate(new java.util.Date());
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", mail, password);
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }

    public Boolean emailCommentToComment(String email, String title, Long postId, String name)
	    throws AddressException, MessagingException {
	Properties props = System.getProperties();
	String[] keys = getCredentials();
	String mail = keys[0];
	String password = keys[1];
	String postPath = url + "/#/questions/view/" + postId;
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(mail));
	;
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

	msg.setSubject("התקבלה תגובה חדשה בדיון בו השתתפת");
	MimeMultipart multipart = new MimeMultipart("related");

	// first part (the html)
	BodyPart messageBodyPart = new MimeBodyPart();
	String htmlText = "<head>" + "<style type=\"text/css\">" + "  .blue { color: #00f; }" + "</style>" + "</head>"
		+ "<body dir='rtl'>" + "<h3 class=\"blue\">" + "שלום " + name + "," + "</h3>" + "<p>" + ""
		+ "<strong>התקבלה תגובה חדשה בדיון בו השתתפת: </strong>" + title + "." + "</p>" + "<p>" + "לצפייה "
		+ "<a href=" + postPath + " >לחץ כאן</a></p>" + "<img src=\'cid:image\'>" + "<p>"
		+ "<small>ניתן לשנות את  הגדרות קבלת המיילים בדף עריכת פרופיל באתר </small>" + "</p>" + "</body>";
	messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
	multipart.addBodyPart(messageBodyPart);

	// second part (the image)
	messageBodyPart = new MimeBodyPart();
	DataSource fds = new FileDataSource("C:\\wamp\\www\\CommunityStudying-Client\\public_html\\img\\logo.png");
	messageBodyPart.setDataHandler(new DataHandler(fds));
	messageBodyPart.setHeader("Content-ID", "<image>");
	multipart.addBodyPart(messageBodyPart);
	msg.setContent(multipart);

	msg.setSentDate(new java.util.Date());
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", mail, password);
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }

    public Boolean emailPostSameCourse(String email, String title, Long postId, String name, String courseName)
	    throws AddressException, MessagingException {
	Properties props = System.getProperties();
	String[] keys = getCredentials();
	String mail = keys[0];
	String password = keys[1];
	String postPath = url + "/#/questions/view/" + postId;
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(mail));
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

	msg.setSubject("פורסמה שאלה חדשה עבור קורס שאתה לוקח");
	MimeMultipart multipart = new MimeMultipart("related");

	// first part (the html)
	BodyPart messageBodyPart = new MimeBodyPart();
	String htmlText = "<head>" + "<style type=\"text/css\">" + "  .blue { color: #00f; }" + "</style>" + "</head>"
		+ "<body dir='rtl'>" + "<h3 class=\"blue\">" + "שלום " + name + "," + "</h3>" + "<p>"
		+ "<strong>פורסמה שאלה חדשה: </strong>" + title + ", " + "עבור הקורס: " + courseName + "." + "</p>"
		+ "<p>" + "לצפייה " + "<a href=" + postPath + " >לחץ כאן</a></p>" + "<img src=\'cid:image\'>" + "<p>"
		+ "<small>ניתן לשנות את  הגדרות קבלת המיילים בדף עריכת פרופיל באתר </small>" + "</p>" + "</body>";
	messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
	multipart.addBodyPart(messageBodyPart);

	// second part (the image)
	messageBodyPart = new MimeBodyPart();
	DataSource fds = new FileDataSource("C:\\wamp\\www\\CommunityStudying-Client\\public_html\\img\\logo.png");
	messageBodyPart.setDataHandler(new DataHandler(fds));
	messageBodyPart.setHeader("Content-ID", "<image>");
	multipart.addBodyPart(messageBodyPart);
	msg.setContent(multipart);

	msg.setSentDate(new java.util.Date());
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", mail, password);
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }

    public Boolean emailNewUser(String email, String name) throws AddressException, MessagingException {
	Properties props = System.getProperties();
	String[] keys = getCredentials();
	String mail = keys[0];
	String password = keys[1];
	props.put("mail.smtps.host", "smtp.gmail.com");
	props.put("mail.smtps.auth", "true");
	Session session = Session.getInstance(props, null);
	Message msg = new MimeMessage(session);
	msg.setFrom(new InternetAddress(mail));
	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
	msg.setSubject("ברוכים הבאים לסטדי-באדי!");

	MimeMultipart multipart = new MimeMultipart("related");

	// first part (the html)
	BodyPart messageBodyPart = new MimeBodyPart();
	String htmlText = "<head>" + "<style type=\"text/css\">" + "  .blue { color: #00f; }" + "</style>" + "</head>"
		+ "<body dir='rtl'>" + "<h3 class=\"blue\">" + "שלום " + name + "," + "</h3>" + "<p>" + "ברוך הבא, "
		+ "<strong>סטדי-באדי</strong>" + " זה המקום בו תוכל לשאול שאלות על מבחנים ולקבל תשובות בקלות ובמהירות."
		+ "</p>" + "<p>" + "לכניסה לאתר " + "<a href=" + url + ">לחץ כאן</a></p>" + "<img src=\'cid:image\'>"
		+ "</body>";
	messageBodyPart.setContent(htmlText, "text/html; charset=utf-8");
	multipart.addBodyPart(messageBodyPart);

	// second part (the image)
	messageBodyPart = new MimeBodyPart();
	DataSource fds = new FileDataSource("C:\\wamp\\www\\CommunityStudying-Client\\public_html\\img\\logo.png");
	messageBodyPart.setDataHandler(new DataHandler(fds));
	messageBodyPart.setHeader("Content-ID", "<image>");
	multipart.addBodyPart(messageBodyPart);
	msg.setContent(multipart);

	msg.setSentDate(new Date(0));
	SMTPTransport t = (SMTPTransport) session.getTransport("smtps");
	t.connect("smtp.gmail.com", mail, password);
	t.sendMessage(msg, msg.getAllRecipients());
	System.out.println("Response: " + t.getLastServerResponse());
	t.close();
	return true;
    }

    private String[] getCredentials() {
	String[] keys = new String[2];

	if (emailUsername != null && emailPassword != null) {
	    keys[0] = emailUsername;
	    keys[1] = emailPassword;
	    return keys;
	}

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

	    emailUsername = keys[0];
	    emailPassword = keys[1];

	    return keys;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }
}
