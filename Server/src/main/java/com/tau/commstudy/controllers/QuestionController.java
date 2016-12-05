package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Question;
import com.tau.commstudy.entities.QuestionDao;

@RestController
@RequestMapping("/question")
public class QuestionController {
   
    @Autowired
    private QuestionDao dao;
    
    @RequestMapping(method = RequestMethod.GET, value = "/save")
    public String saveQuestion(String title,String content)
    {
	Question q = new Question();
	q.setTitle(title);
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
}
