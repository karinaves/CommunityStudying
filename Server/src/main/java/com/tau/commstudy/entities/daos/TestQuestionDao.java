package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;

@Transactional
public interface TestQuestionDao extends CrudRepository<TestQuestion, Long>, QueryDslPredicateExecutor<TestQuestion> {
    TestQuestion findByTestAndQuestionNumber(Test test, Integer questionNumber);

}
