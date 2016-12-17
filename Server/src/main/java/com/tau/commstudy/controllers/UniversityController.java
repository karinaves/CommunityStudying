package com.tau.commstudy.controllers;


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
import com.tau.commstudy.exceptions.TableArgumentException;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.UniversityService;

@RestController
@RequestMapping("/university")
public class UniversityController {
    
    @Autowired
    private UniversityService universityService;

   
    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public University add( String name, Set<Faculty> faculties, Long universityNum, String address) throws Exception {
	return universityService.add(name, faculties, universityNum, address);
}
    
    @RequestMapping(method = RequestMethod.GET, value = "/addFaculty")
    public University addUniversity( Long id, Faculty faculty) throws Exception {
	return universityService.addFaculty(id, faculty);
    }  
    
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public void delete(Long id) throws Exception {
	universityService.delete(id);
    }  
    
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public void get(Long id) throws Exception {
	universityService.get(id);
    }  
    
    
    
    
    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }
    
    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code=HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleException(TableArgumentException e) {
	return e.getMessage();
    }

}
