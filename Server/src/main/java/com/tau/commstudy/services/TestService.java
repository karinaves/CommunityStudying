package com.tau.commstudy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public List<Test> search(TestCriteria criteria, int page, int size) {

	BooleanBuilder searchCriteria = getSearchCriteria(criteria);
	return searchBy(searchCriteria, page, size);
    }

    private BooleanBuilder getSearchCriteria(TestCriteria criteria) {
	BooleanBuilder searchCriteria = new BooleanBuilder();

	if (criteria.getFacultyId() == null)
	    // return all
	    return searchCriteria;

	// add faculty parameter to search
	searchCriteria.and(TestPredicates.byFaculty(criteria.getFacultyId()));
	if (criteria.getCourseId() == null)
	    // return by faculty
	    return searchCriteria;

	// add course parameter to search
	searchCriteria.and(TestPredicates.byCourse(criteria.getCourseId()));
	if (criteria.getYear() == null)
	    // return by course
	    return searchCriteria;

	// add year parameter to search
	searchCriteria.and(TestPredicates.byYear(criteria.getYear()));
	if (criteria.getSemester() == null)
	    // return by year
	    return searchCriteria;

	// add semester parameter to search
	searchCriteria.and(TestPredicates.bySemester(criteria.getSemester()));
	if (criteria.getMoed() == null)
	    // return by semester
	    return searchCriteria;

	// add moed parameter to search
	searchCriteria.and(TestPredicates.byMoed(criteria.getMoed()));

	// return by moed
	return searchCriteria;
    }

    public Long count(TestCriteria criteria) {
	BooleanBuilder searchCriteria = getSearchCriteria(criteria);
	return dao.count(searchCriteria);
    }

    // ------------Aid Functions---------------------------

    private List<Test> searchBy(BooleanBuilder searchCriteria, int page, int size) {
	if (dao.findAll(searchCriteria, new PageRequest(page, size, orderByTime())) == null)
	    return null;
	return dao.findAll(searchCriteria, new PageRequest(page, size, orderByTime())).getContent();
    }

    private Sort orderByTime() {
	return new Sort(Sort.Direction.DESC, "year", "semester", "moed");
    }

}
