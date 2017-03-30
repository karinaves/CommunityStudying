package com.tau.commstudy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.NewTestBean;
import com.tau.commstudy.beans.TestCriteria;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.services.CourseService;
import com.tau.commstudy.services.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService service;

    @Autowired
    private CourseService courseService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Test add(@RequestBody Test test) throws Exception {
	return service.add(test);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Test get(@PathVariable Long id) throws Exception {
	return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id) throws Exception {
	return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMoed")
    public Test getByMoed(Long courseId, Integer year, Character semester, Character moed) {
	Course course = courseService.get(courseId);
	return service.getByMoed(course, year, semester, moed);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public List<Test> search(@RequestBody TestCriteria criteria, int page, int size) {
	return service.search(criteria, page, size);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/count")
    public Long count(@RequestBody TestCriteria criteria) {
	return service.count(criteria);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Test addNewTest(@RequestBody NewTestBean test, String userTokenId) {
	return service.addNewTest(test, userTokenId);
    }

}
