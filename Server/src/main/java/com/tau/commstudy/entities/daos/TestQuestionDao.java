package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.TestQuestion;

@Transactional
public interface TestQuestionDao extends CrudRepository<TestQuestion, Long> {

}
