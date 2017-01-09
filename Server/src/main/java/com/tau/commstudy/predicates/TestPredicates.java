package com.tau.commstudy.predicates;

import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import com.tau.commstudy.entities.QTest;

public class TestPredicates {
    private TestPredicates() {
    }

    public static BooleanExpression byYear(Integer year) {
	QTest test = QTest.test;

	// get all posts
	if (year == null)
	    return test.isNotNull();

	// if parameter is OK return usual predicate
	return test.year.eq(year);

    }

    public static BooleanExpression bySemester(Character semester) {
	QTest test = QTest.test;

	// get all posts
	if (semester == null)
	    return test.isNotNull();

	// if parameter is OK return usual predicate
	return test.semester.eq(semester);
    }

    public static BooleanExpression byMoed(Character moed) {
	QTest test = QTest.test;

	// get all posts
	if (moed == null)
	    return test.isNotNull();

	// if parameter is OK return usual predicate
	return test.moed.eq(moed);
    }

    public static BooleanExpression byCourse(Long courseId) {
	QTest test = QTest.test;

	// get all posts
	if (courseId == null)
	    return test.isNotNull();

	// if parameter is OK return usual predicate
	return test.course.id.eq(courseId);

    }

    public static Predicate byFaculty(Long facultyId) {
	QTest test = QTest.test;

	if (facultyId == null)
	    return test.isNotNull();

	// if parameter is OK return usual predicate
	try {
	    return test.course.faculty.id.eq(facultyId);
	    // return post.testQuestion.test.id.eq((long) 1);
	} catch (Exception ex) {
	    System.out.println("Error creating the predicate:" + ex.toString());
	    return null;
	}
    }
}
