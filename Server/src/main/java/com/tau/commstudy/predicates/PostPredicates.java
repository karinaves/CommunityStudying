package com.tau.commstudy.predicates;

import java.util.Set;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.tau.commstudy.entities.QPost;
import com.tau.commstudy.entities.Tag;

public final class PostPredicates {
    private PostPredicates() {
    }

    public static BooleanExpression byYear(Integer year) {
	QPost post = QPost.post;

	// get all posts
	if (year == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	return post.testQuestion.test.year.eq(year);

    }

    public static BooleanExpression bySemester(Character semester) {
	QPost post = QPost.post;

	// get all posts
	if (semester == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	return post.testQuestion.test.semester.eq(semester);
    }

    public static BooleanExpression byMoed(Character moed) {
	QPost post = QPost.post;

	// get all posts
	if (moed == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	return post.testQuestion.test.moed.eq(moed);
    }

    public static BooleanBuilder byTags(Set<Tag> tags) {
	QPost post = QPost.post;
	BooleanBuilder searchCriteria = new BooleanBuilder();

	// get all posts
	if (tags == null || tags.isEmpty()) {
	    searchCriteria.and(post.isNotNull());
	    return searchCriteria;
	}

	for (Tag tag : tags)
	    searchCriteria.or(post.tags.contains(tag));

	return searchCriteria;
    }

    public static BooleanExpression byUserId(Long id) {
	QPost post = QPost.post;

	// get all posts
	if (id == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	return post.user.id.eq(id);
    }

    public static BooleanExpression byContentOrTitleLike(String content) {
	QPost post = QPost.post;

	// get all posts
	if (content == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	return post.content.like("%" + content + "%").or(post.title.like("%" + content + "%"));
    }

    public static BooleanExpression byQuestionNumber(Integer questionNumber) {
	QPost post = QPost.post;

	// get all posts
	if (questionNumber == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	return post.testQuestion.questionNumber.eq(questionNumber);
    }

    public static BooleanExpression byCourse(Long courseId) {
	QPost post = QPost.post;

	// get all posts
	if (courseId == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	return post.testQuestion.test.course.id.eq(courseId);

    }

    public static Predicate byFaculty(Long facultyId) {
	QPost post = QPost.post;

	if (facultyId == null)
	    return post.isNotNull();

	// if parameter is OK return usual predicate
	try {
	    return post.testQuestion.test.course.faculty.id.eq(facultyId);
	    // return post.testQuestion.test.id.eq((long) 1);
	} catch (Exception ex) {
	    System.out.println("Error creating the predicate:" + ex.toString());
	    return null;
	}
    }
}
