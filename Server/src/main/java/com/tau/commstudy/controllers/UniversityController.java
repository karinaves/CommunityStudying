package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.University;
import com.tau.commstudy.services.FacultyService;
import com.tau.commstudy.services.UniversityService;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private FacultyService facultyService;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public University add(@RequestBody University university) throws Exception {
	return universityService.add(university);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public University get(@PathVariable Long id) throws Exception {
	return universityService.get(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id) throws Exception {
	return universityService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addFaculty")
    public boolean addFaculty(Long id, Long facultyId) throws Exception {
	return facultyService.addUniversity(facultyId, id);
    }

}
