package com.tau.commstudy.controllers;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    // @Autowired
    // private EmailService emailService;
    //
    // @RequestMapping(method = RequestMethod.GET, value = "/send")
    // public Message sendMail() throws Exception {
    // Message msg = emailService.sendEmail("royshavit21@gmail.com",
    // "hirshmanor@gmail.com", "**check**", "hello");
    // return msg;
    // }

    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping(method = RequestMethod.GET, value = "/send")
    public void send() {
	MimeMessage mail = javaMailSender.createMimeMessage();
	try {
	    MimeMessageHelper helper = new MimeMessageHelper(mail, true);
	    helper.setTo("royshavit21@gmail.com");
	    helper.setFrom("studybuddytau@gmail.com");
	    helper.setSubject("hiiii");
	    helper.setText("Lorem ipsum dolor sit amet [...]");
	} catch (MessagingException e) {
	    e.printStackTrace();
	} finally {
	}
	javaMailSender.send(mail);
	// return helper;
    }

}
