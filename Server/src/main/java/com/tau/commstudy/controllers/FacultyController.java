package com.tau.commstudy.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.University;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.FacultyDao;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.FacultyService;
import com.tau.commstudy.services.UserService;


@RestController
@RequestMapping("/faculty")
public class FacultyController {
    
    @Autowired
    private FacultyService facultyService;

   
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public Faculty add( String name, Set<University> universities, Integer facultyUniversityId) throws Exception {
	return facultyService.add(name, universities, facultyUniversityId);
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/addUniversity")
    public Faculty addUniversity( Long id, University university) throws Exception {
	return facultyService.addUniversity(id, university);
    }  
    
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public void add(Long id) throws Exception {
	facultyService.delete(id);
    }  
    
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public void get(Long id) throws Exception {
	facultyService.get(id);
    }  
    
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/getAllFaculties")
    public List<Faculty> getOrCreateUser(String idTokenString) throws Exception {
	List<Faculty> faculties = facultyService.getAllFaculties(idTokenString);
	return faculties;
	
    }

    
    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

}
