package com.tau.commstudy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.BooleanBuilder;
import com.tau.commstudy.beans.TestCriteria;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.daos.TestDao;
import com.tau.commstudy.predicates.TestPredicates;

@Service
public class TestService {

    @Autowired
    private TestDao dao;

    public boolean delete(Long id) throws IllegalArgumentException {
	dao.delete(id);
	return true;
    }

    public Test add(Test test) {
	return dao.save(test);
    }

    public Test getById(Long id) throws IllegalArgumentException {
	return dao.findOne(id);
    }

    public Test getByMoed(Course course, Integer year, Character semester, Character moed) {
	return dao.findByCourseAndYearAndSemesterAndMoed(course, year, semester, moed);
    }

    public List<Test> search(TestCriteria criteria) {
	BooleanBuilder searchCriteria = new BooleanBuilder();

	if (criteria.getFacultyId() == null)
	    // return all
	    return searchBy(searchCriteria);

	// add faculty parameter to search
	searchCriteria.and(TestPredicates.byFaculty(criteria.getFacultyId()));
	if (criteria.getCourseId() == null)
	    // return by faculty
	    return searchBy(searchCriteria);

	// add course parameter to search
	searchCriteria.and(TestPredicates.byCourse(criteria.getCourseId()));
	if (criteria.getYear() == null)
	    // return by course
	    return searchBy(searchCriteria);

	// add year parameter to search
	searchCriteria.and(TestPredicates.byYear(criteria.getYear()));
	if (criteria.getSemester() == null)
	    // return by year
	    return searchBy(searchCriteria);

	// add semester parameter to search
	searchCriteria.and(TestPredicates.bySemester(criteria.getSemester()));
	if (criteria.getMoed() == null)
	    // return by semester
	    return searchBy(searchCriteria);

	// add moed parameter to search
	searchCriteria.and(TestPredicates.byMoed(criteria.getMoed()));

	// return by moed
	return searchBy(searchCriteria);
    }

    // ------------Aid Functions---------------------------

    private List<Test> searchBy(BooleanBuilder searchCriteria) {
	if (dao.count(searchCriteria) == 0)
	    return null;
	return (List<Test>) dao.findAll(searchCriteria, orderByTime());
    }

    private Sort orderByTime() {
	return new Sort(Sort.Direction.DESC, "year", "semester", "moed");
    }

}
