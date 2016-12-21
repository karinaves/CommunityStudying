package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.UserAllData;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.services.CourseService;
import com.tau.commstudy.services.FacultyService;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Faculty add(@RequestBody Faculty faculty) throws Exception {
	return facultyService.add(faculty);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Faculty get(@PathVariable Long id) throws Exception {
	return facultyService.get(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id) throws Exception {
	return facultyService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addUniversity")
    public boolean addUniversity(Long id, Long universityId) throws Exception {
	return facultyService.addUniversity(id, universityId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCourse")
    public boolean addCourse(Long id, Long courseId) throws Exception {
	return courseService.addFaculty(courseId, id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserAllData")
    public UserAllData<Faculty> getUserAndAllData(String idTokenString) throws Exception {
	UserAllData<Faculty> faculties = facultyService.getUserAndAllData(idTokenString);
	return faculties;

    }

}
