package com.tau.commstudy.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.UserPrefsBean;
import com.tau.commstudy.controllers.interfaces.UserControllerInterface;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerInterface {

    // Path should be "user/updateCourses?userTokenId={id}" method = POST
    // return true if updated successfully, throw exception if not

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/updateCourses")
    public Boolean updateUserCourses(@RequestBody Set<Course> courses, String userTokenId) {
	return userService.updateUserCourses(courses, userTokenId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getOrCreate")
    public User getOrCreateUser(String idTokenString) throws Exception {
	return userService.getOrCreate(idTokenString);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public User get(String idTokenString) throws Exception {
	return userService.login(idTokenString);
    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByUserCourses")
    public Set<User> getAllByCourse(Set<Course> courses) {
	return userService.getAllByCourse(courses);
    }

    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/updateEmailPref")
    public Boolean updateEmailPref(@RequestBody UserPrefsBean prefs, String userTokenId) {
	return userService.updateUserPrefs(userTokenId, prefs);
    }

}