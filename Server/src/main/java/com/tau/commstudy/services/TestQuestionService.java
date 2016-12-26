package com.tau.commstudy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.entities.daos.TestQuestionDao;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionDao dao;

    // @RequestMapping(method = RequestMethod.GET, value = "/save")
    // public String saveQuestion(String title, String content, String userId) {
    // TestQuestion q = new TestQuestion();
    // dao.save(q);
    //
    // return "";
    // }

    public String saveByPost(TestQuestion q) {
	dao.save(q);

	return "";
    }

    public TestQuestion get(long id) {
	return dao.findOne(id);
    }

    public Iterable<TestQuestion> getAll() {
	return dao.findAll();
    }

    public TestQuestion getByTestAndNumber(Test test, Integer questionNumber) {
	return dao.findByTestAndQuestionNumber(test, questionNumber);
    }
}
