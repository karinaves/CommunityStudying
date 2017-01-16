package com.tau.commstudy.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Service;

import com.mysema.query.BooleanBuilder;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.QTestQuestion;
import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.entities.daos.TestQuestionDao;
import com.tau.commstudy.predicates.QuestionPredicates;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionDao dao;

    @Autowired
    private PostService postService;

    // @RequestMapping(method = RequestMethod.GET, value = "/save")
    // public String saveQuestion(String title, String content, String userId) {
    // TestQuestion q = new TestQuestion();
    // dao.save(q);
    //
    // return "";
    // }

    public TestQuestion add(TestQuestion q) {
	try {
	    return dao.save(q);
	} catch (Exception ex) {
	    System.out.println("Error creating the question:" + ex.toString());
	    return null; // "Error creating the post:" + ex.toString();
	}
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

    public List<TestQuestion> getSimilarQuestions(Long testQuestionId, int page, int size) {

	TestQuestion question = dao.findOne(testQuestionId);
	Set<Post> postsOfQuestions = question.getPosts();
	Set<Tag> tags = new HashSet<Tag>();
	for (Post post : postsOfQuestions)
	    tags.addAll(post.getTags());

	BooleanBuilder searchCriteria = QuestionPredicates.byTags(tags);
	return searchBy(searchCriteria, page, size);
    }

    private List<TestQuestion> searchBy(BooleanBuilder searchCriteria, int page, int size) {
	return dao.findAll(searchCriteria, new PageRequest(page, size, orderByTime())).getContent();
    }

    private QSort orderByTime() {
	QSort sort = new QSort(QTestQuestion.testQuestion.test.year.desc());
	sort = sort.and(QTestQuestion.testQuestion.test.semester.desc());
	sort = sort.and(QTestQuestion.testQuestion.test.moed.desc());
	sort = sort.and(QTestQuestion.testQuestion.questionNumber.asc());
	return sort;
    }

}
