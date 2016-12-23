package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.services.TestQuestionService;

@RestController
@RequestMapping("/testQuestion")
@CrossOrigin
public class TestQuestionController {

    @Autowired
    private TestQuestionService service;

    // @RequestMapping(method = RequestMethod.GET, value = "/save")
    // public String saveQuestion(String title, String content, String userId) {
    // TestQuestion q = new TestQuestion();
    // dao.save(q);
    //
    // return "";
    // }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String saveByPost(@RequestBody TestQuestion q) {
	service.saveByPost(q);

	return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public TestQuestion get(long id) {
	return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<TestQuestion> getAll() {
	return service.getAll();
    }
}
