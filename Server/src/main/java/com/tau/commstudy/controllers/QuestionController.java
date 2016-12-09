package com.tau.commstudy.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.tau.commstudy.entities.Question;
import com.tau.commstudy.entities.daos.QuestionDao;

@RestController
@RequestMapping("/question")
public class QuestionController {
   
    @Autowired
    private QuestionDao dao;
    
    @RequestMapping(method = RequestMethod.GET, value = "/save")
    public String saveQuestion(String title,String content,String userId)
    {
	Question q = new Question();
	q.setTitle(title);
	q.setUserId(userId);
	q.setContent(content);
	dao.save(q);
	
	return "";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String saveByPost(@RequestBody Question q)
    {
	dao.save(q);
	
	return "";
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public Question get(long id)
    {
	return dao.findOne(id);
    }
    
    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<Question> getAll()
    {
	return dao.findAll();
    }
}
