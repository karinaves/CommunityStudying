package com.tau.commstudy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.exceptions.TableArgumentException;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.FacultyService;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public Faculty add(@RequestBody Faculty faculty) throws Exception {
	// return facultyService.add(faculty);
	return faculty;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Faculty get(Long id) throws Exception {
	return facultyService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public void delete(Long id) throws Exception {
	facultyService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addUniversity")
    public Faculty addUniversity(Long id, Long universityId) throws Exception {
	return facultyService.addUniversity(id, universityId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllFaculties")
    public List<Faculty> getOrCreateUser(String idTokenString) throws Exception {
	List<Faculty> faculties = facultyService.getAllFaculties(idTokenString);
	return faculties;

    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

    @ExceptionHandler(TableArgumentException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleException(TableArgumentException e) {
	return e.getMessage();
    }

}
