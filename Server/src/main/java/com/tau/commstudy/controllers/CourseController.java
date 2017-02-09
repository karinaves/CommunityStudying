package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.UserAllData;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.daos.UserDao;
import com.tau.commstudy.services.CourseService;
import com.tau.commstudy.services.FacultyService;
import com.tau.commstudy.services.PostService;
import com.tau.commstudy.services.TestQuestionService;
import com.tau.commstudy.services.TestService;
import com.tau.commstudy.services.UserService;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private TestService testService;

    @Autowired
    private TestQuestionService questionService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserDao userDao;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public Course add(@RequestBody Course course, String userTokenId) throws Exception {
	userService.assertAdminUser(userTokenId);
	return courseService.add(course);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/add2")
    public Post add2() {
	// Course newCourse = new Course();
	// newCourse.setNameEnglish("פונקציות מרוכבות");
	// newCourse.setNameHebrew("פונקציות מרוכבות");
	// newCourse.setFaculty(facultyService.get((long) 1));
	//
	// newCourse = courseService.add(newCourse);
	// // return courseService.get((long) 7);
	//
	// Test newTest = new Test();
	// newTest.setCourse(newCourse);
	// newTest.setMoed('A');
	// newTest.setSemester('A');
	// newTest.setYear(2012);
	// newTest = testService.add(newTest);
	//
	// TestQuestion newQuestion = new TestQuestion();
	// newQuestion.setQuestionNumber(1);
	// newQuestion.setTest(newTest);
	// newQuestion = questionService.add(newQuestion);

	Post newPost = new Post();
	newPost.setTitle("שאלה במודלים");
	newPost.setContent("מכילה את המספר 2012");
	newPost.setTestQuestion(questionService.get(11));
	newPost.setUser(userDao.findOne((long) 2));

	return postService.createPost(newPost);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Course get(@PathVariable Long id) throws Exception {
	return courseService.get(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id, String userTokenId) throws Exception {
	userService.assertAdminUser(userTokenId);
	return courseService.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addFaculty")
    public boolean addFaculty(Long id, Long facultyId, String userTokenId) throws Exception {
	userService.assertAdminUser(userTokenId);
	return courseService.addFaculty(id, facultyId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserAllData")
    public UserAllData<Course> getUserAndAllData(String idTokenString, Long facultyId) throws Exception {
	UserAllData<Course> courses = courseService.getUserAndAllData(idTokenString, facultyId);
	return courses;

    }

}
