package com.tau.commstudy.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tau.commstudy.beans.PostCriteria;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.PostDao;

@Service
public class PostService {

    @Autowired
    private PostDao dao;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TestService testService;

    @Autowired
    private TestQuestionService questionService;

    public Post createPost(@RequestBody Post newPost) {
	try {
	    newPost.setTime(Calendar.getInstance());
	    return dao.save(newPost);
	} catch (Exception ex) {
	    return null; // "Error creating the post:" + ex.toString();
	}

	// return "Post succesfully saved!";
    }

    public String delete(Long id) {
	if (id == null)
	    return "long parameter 'id' needs to be provided";

	try {
	    dao.delete(id);
	} catch (Exception ex) {
	    return "Error deleting the post:" + ex.toString();
	}
	return "Post succesfully deleted!";
    }

    /**
     * if the parameter was not provided, returns corresponding message
     */
    @RequestMapping(value = "/delete", params = {})
    public String delete() {
	return "long parameter 'id' needs to be provided";
    }

    /**
     * increases the number of votes by 1
     */
    public String like(long id) {
	try {
	    Post post = dao.findOne(id);
	    post.setVotes(post.getVotes() + 1);
	    dao.save(post);
	} catch (Exception ex) {
	    return "Error updating votes for post: " + ex.toString();
	}
	return "Post succesfully updated!";
    }

    public Iterable<Post> getAll() {
	return dao.findAll();
    }

    /**
     * This updates only the content, title of the post. All the other fields
     * (including id and time) stay the same
     */
    public String updatePost(Post givenPost) {
	try {
	    Post post = dao.findOne(givenPost.getId());
	    post.setContent(givenPost.getContent());
	    post.setTitle(givenPost.getTitle());
	    dao.save(post);
	} catch (Exception ex) {
	    return "Error updating the post: " + ex.toString();
	}
	return "Post succesfully updated!";
    }

    public Post getById(long id) {
	return dao.findOne(id);
    }

    public Iterable<Post> getByUser(User user) {
	return dao.findByUser(user);
    }

    public Iterable<Post> getByCourse(Course course) {
	return dao.findByTestQuestion_Test_CourseOrderByTimeDesc(course);
    }

    public List<Post> getByTest(Test test) {
	return dao.findByTestQuestion_TestOrderByTimeDesc(test);
    }

    public List<Post> getByMoed(Course course, Integer year, Character semester, Character moed) {
	Test test = testService.getByMoed(course, year, semester, moed);
	return getByTest(test);
    }

    public List<Post> getByTestAndNumber(Test test, Integer number) {
	TestQuestion question = questionService.getByTestAndNumber(test, number);
	return getByTestQuestion(question);
    }

    public boolean checkByMoedK(Integer year, Character semester, Character moed, Course course) {
	List<Post> posts = dao
		.findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
			year, semester, moed, course);
	if (posts.size() == 0)
	    return false;
	return true;
    }

    /**
     * check if posts for this test moed already exist
     *
     * @param bean
     * @return TRUE or FALSE
     */

    public boolean checkByMoed(PostCriteria criteria) {
	Course course = courseService.get(criteria.getCourseId());
	List<Post> posts = dao
		.findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
			criteria.getYear(), criteria.getSemester(), criteria.getMoed(), course);
	if (posts == null || posts.isEmpty())
	    return false;
	return true;
    }

    /**
     * finds all posts connected to a specific testQuestion.
     *
     * @param TestQuestion
     * @return List of posts
     */
    public List<Post> getByTestQuestion(TestQuestion question) {
	return dao.findByTestQuestionOrderByTimeDesc(question);
    }

    /**
     * finds all posts connected to a question. If no question number given,
     * return by test
     *
     * @param criteria
     *            the used fields are: -course -year -semester -moed
     *            -questionNumber
     * @return List of posts
     */
    public List<Post> search(PostCriteria criteria) {
	Course course = courseService.get(criteria.getCourseId());
	Test test = testService.getByMoed(course, criteria.getYear(), criteria.getSemester(), criteria.getMoed());
	if (test == null) // no such test in the table
	    return null;

	if (criteria.getQuestionNumber() == null) // if no number was given
	    return getByTest(test);

	return getByTestAndNumber(test, criteria.getQuestionNumber());
    }

    public boolean checkByQuestion(PostCriteria criteria) {
	if (search(criteria) == null)
	    return false;

	return true;
    }

}