package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.lang.RuntimeException;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.services.UsersService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UsersService userService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/getOrCreateUser")
    public User getOrCreateUser(String idTokenString) throws Exception{
	User user =userService.getOrCreateUser(idTokenString);
	if (user == null){
	    throw new Exception();
	}
	return user;	
    }
}