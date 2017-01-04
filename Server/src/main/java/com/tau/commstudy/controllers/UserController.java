package com.tau.commstudy.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.controllers.interfaces.UserControllerInterface;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController implements UserControllerInterface {

    // Path should be "user/updateCourses?userTokenId={id}" method = POST
    // return true if updated successfully, throw exception if not

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/updateCourses")
    public Boolean updateUserCourses(@RequestBody Set<Long> coursesIds, String userTokenId) {
	return userService.updateUserCourses(coursesIds, userTokenId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOrCreate")
    public User getOrCreateUser(String idTokenString) throws Exception {
	// if an exception is thrown that it is caught in runtime by
	// @ExceptionHandler
	User user = userService.getOrCreate(idTokenString);
	return user;

    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

}