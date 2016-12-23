package com.tau.commstudy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.PostCriteria;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.services.PostService;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    // @Autowired
    // private PostDao dao;
    //
    // TestQuestionDao questionDao;
    //
    // @Autowired
    // private TestDao testDao;

    @Autowired
    private PostService service;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Post createPost(@RequestBody Post newPost) {
	return service.createPost(newPost);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(Long id) {
	return service.delete(id);
    }

    /**
     * increases the number of votes by 1
     */
    @RequestMapping(method = RequestMethod.POST, value = "/like")
    public String like(long id) {
	return service.like(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<Post> getAll() {
	return service.getAll();
    }

    /**
     * This updates only the content, title of the post. All the other fields
     * (including id and time) stay the same
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String updatePost(@RequestBody Post givenPost) {
	return service.updatePost(givenPost);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public Post getById(long id) {
	return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByUser")
    public Iterable<Post> getByUser(User user) {
	return service.getByUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCourse")
    public Iterable<Post> getByCourse(Course course) {
	return service.getByCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMoed")
    public Iterable<Post> getByMoed(Integer year, Character semester, Character moed, Course course) {
	return service.getByMoed(course, year, semester, moed);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoedK") // get,
									 // all
									 // fields
									 // separately
    public boolean checkByMoedK(Integer year, Character semester, Character moed, Course course) {
	return service.checkByMoedK(year, semester, moed, course);
    }

    /**
     * check if posts for this test moed already exist
     *
     * @param bean
     * @return TRUE or FALSE
     */
    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoed") // get,
									// all
									// fields
									// in a
									// bean
    public boolean checkByMoed(PostCriteria criteria) {
	return service.checkByMoed(criteria);
    }

    // /**
    // * finds all posts connected to a specific testQuestion.
    // *
    // * @param bean
    // * - the field testQuestion should be given
    // * @return List of posts
    // */
    // @RequestMapping(method = RequestMethod.GET, value = "/getByTestQuestion")
    // public List<Post> getByTestQuestion(@RequestBody PostBean bean) {
    // // 1. use findByfields if this test exists
    // // 2. in the specific test, find by question number
    // return service.getByTestQuestion(question);
    // }
    //
    // /**
    // * finds all posts connected to a question, search by question info
    // *
    // * @param bean
    // * the used fields are: -course -year -semester -moed
    // * @return
    // */
    // @RequestMapping(method = RequestMethod.GET, value = "/getByQuestion")
    // public List<Post> getByQuestion(@RequestBody PostBean bean) {
    // // 1. use findByfields if this test exists
    // // 2. in the specific test, find by question number
    // Test test =
    // testDao.findByCourseAndYearAndSemesterAndMoed(bean.getCourse(),
    // bean.getYear(), bean.getSemester(),
    // bean.getMoed());
    // if (test == null)
    // return null;
    // bean.setTest(test);
    //
    // TestQuestion question = questionDao.findByTestAndQuestionNumber(test,
    // bean.getQuestionNumber());
    // if (question == null)
    // return null;
    // bean.setTestQuestion(question);
    //
    // return getByTestQuestion(bean);
    // }

    public List<Post> search(PostCriteria criteria) {
	return service.search(criteria);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkByQuestion")
    public boolean checkByQuestion(@RequestBody PostCriteria criteria) {
	return service.checkByQuestion(criteria);
    }

}
