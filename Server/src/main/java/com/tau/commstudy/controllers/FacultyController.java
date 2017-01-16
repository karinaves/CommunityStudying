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
import com.tau.commstudy.services.UserService;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Faculty add(@RequestBody Faculty faculty, String userTokenId) throws Exception {
	userService.assertAdminUser(userTokenId);
	return facultyService.add(faculty);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Faculty get(@PathVariable Long id) throws Exception {
	return facultyService.get(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id, String userTokenId) throws Exception {
	userService.assertAdminUser(userTokenId);
	return facultyService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addUniversity")
    public boolean addUniversity(Long id, Long universityId, String userTokenId) throws Exception {
	userService.assertAdminUser(userTokenId);
	return facultyService.addUniversity(id, universityId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCourse")
    public boolean addCourse(Long id, Long courseId, String userTokenId) throws Exception {
	userService.assertAdminUser(userTokenId);
	return courseService.addFaculty(courseId, id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserAllData")
    public UserAllData<Faculty> getUserAndAllData(String userTokenId) throws Exception {
	UserAllData<Faculty> faculties = facultyService.getUserAndAllData(userTokenId);
	return faculties;

    }

}
