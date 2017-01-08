// package com.tau.commstudy.controllers;
//
// import javax.mail.MessagingException;
// import javax.mail.internet.AddressException;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.tau.commstudy.services.EmailService;
//
// @RestController
// @RequestMapping("/email")
// @CrossOrigin
// public class EmailController {
//
// @Autowired
// private EmailService emailService;
//
// @RequestMapping(method = RequestMethod.GET, value = "/send")
// public Boolean send() throws AddressException, MessagingException {
// return emailService.send();
// }
// }
