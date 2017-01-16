package com.tau.commstudy.services;

import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.BooleanBuilder;
import com.tau.commstudy.beans.NewFileBean;
import com.tau.commstudy.beans.NewTestBean;
import com.tau.commstudy.beans.TestCriteria;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.daos.TestDao;
import com.tau.commstudy.predicates.TestPredicates;

@Service
public class TestService {

    @Autowired
    private TestDao dao;

    @Autowired
    private FileService fileService;

    @Autowired
    private CourseService courseService;

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

    public Test addNewTest(NewTestBean testBean, String userTokenId)
	    throws ValidationException, IllegalArgumentException {
	Long courseId = testBean.getCourseId();
	Integer year = testBean.getYear();
	Course course = courseService.get(courseId);
	Character semester = testBean.getSemester();
	String teacher = testBean.getTeacher();
	char difficulty = testBean.getDifficulty();
	char numOfquestions = testBean.getNumOfquestions();
	Long facultyId = course.getFaculty().getId();
	Character moed = testBean.getMoed();

	TestCriteria criteria = new TestCriteria();
	criteria.setCourseId(courseId);
	criteria.setFacultyId(facultyId);
	criteria.setMoed(moed);
	criteria.setSemester(semester);
	criteria.setYear(year);

	System.out.println(criteria);

	List<Test> isTestExists = search(criteria, 0, 1);
	System.out.println(isTestExists.size());
	if (isTestExists == null || isTestExists.size() == 0) {
	    Test test = new Test();
	    test.setYear(testBean.getYear());
	    test.setSemester(semester);
	    test.setMoed(moed);
	    test.setTeacher(teacher);
	    test.setNumOfquestions(numOfquestions);
	    test.setDifficulty(difficulty);
	    test.setCourse(courseService.get(courseId));

	    Test testNew = add(test);
	    for (String fileUrl : testBean.getFiles()) {
		NewFileBean fileBean = new NewFileBean();
		fileBean.setTestId(testNew.getId());
		fileBean.setUrl(fileUrl);
		fileService.add(fileBean, userTokenId);
	    }
	    return testNew;
	} else {
	    return isTestExists.get(0);
	}

    }

}
