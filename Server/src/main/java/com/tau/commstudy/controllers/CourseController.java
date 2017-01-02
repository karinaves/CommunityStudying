package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.UserAllData;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.services.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Course add(@RequestBody Course course) throws Exception {
	return courseService.add(course);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Course get(@PathVariable Long id) throws Exception {
	return courseService.get(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id) throws Exception {
	return courseService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addFaculty")
    public boolean addFaculty(Long id, Long facultyId) throws Exception {
	return courseService.addFaculty(id, facultyId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserAllData")
    public UserAllData<Course> getUserAndAllData(String idTokenString, Long facultyId) throws Exception {
	UserAllData<Course> courses = courseService.getUserAndAllData(idTokenString, facultyId);
	return courses;

    }

}
