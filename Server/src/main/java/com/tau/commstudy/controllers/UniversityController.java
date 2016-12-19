package com.tau.commstudy.controllers;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.University;
import com.tau.commstudy.exceptions.NotHandeledException;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.UniversityService;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public University add(@RequestBody University university) throws Exception {
	return universityService.add(university);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public University get(@PathVariable Long id) throws Exception {
	return universityService.get(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id) throws Exception {
	universityService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addFaculty")
    public University addFaculty(Long id, Long facultyId) throws Exception {
	return universityService.addFaculty(id, facultyId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(IllegalArgumentException e) {
	return e.getMessage();
    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleException(ValidationException e) {
	return e.getMessage();
    }

    @ExceptionHandler(NotHandeledException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
	return e.getMessage();
    }

}
