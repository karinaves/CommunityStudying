package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.daos.CourseDao;
import com.tau.commstudy.exceptions.TableArgumentException;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDao dao;

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public Course add(Faculty faculty, String name, Long courseUniversityId) throws Exception {
	return courseService.add(faculty, name, courseUniversityId);
    }

    //temporarily without requestBody for testing
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Course add(Course course) throws Exception {
	return dao.save(course);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public void delete(Long id) throws Exception {
	courseService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public Course get(Long id) throws Exception {
	return courseService.get(id);
    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleException(TableArgumentException e) {
	return e.getMessage();
    }

}
