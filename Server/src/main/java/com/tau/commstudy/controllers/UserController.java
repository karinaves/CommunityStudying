package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.UsersService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/getOrCreateUser")
    public User getOrCreateUser(String idTokenString) throws Exception {
	User user = userService.getOrCreateUser(idTokenString);
	if (user == null) 
	{
	    throw new UnauthorizesException();
	}
	return user;
    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

}