package com.tau.commstudy.predicates;

import java.util.Set;

import com.mysema.query.BooleanBuilder;
import com.tau.commstudy.entities.QTestQuestion;
import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.entities.TestQuestion;

public final class QuestionPredicates {
    private QuestionPredicates() {
    }

    public static BooleanBuilder byTags(Set<Tag> tags, TestQuestion sourceQuestion) {
	QTestQuestion question = QTestQuestion.testQuestion;
	BooleanBuilder searchCriteria = new BooleanBuilder();

	// get no questions
	if (tags == null || tags.isEmpty()) {
	    searchCriteria.and(question.isNull());
	    return searchCriteria;
	}

	for (Tag tag : tags)
	    searchCriteria.or(question.questionNumber.goe(1).and(question.posts.any().tags.contains(tag))
		    .and(question.ne(sourceQuestion)));

	return searchCriteria;
    }

}
