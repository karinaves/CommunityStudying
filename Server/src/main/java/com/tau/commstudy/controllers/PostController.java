package com.tau.commstudy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.NewPostBean;
import com.tau.commstudy.beans.PostCriteria;
import com.tau.commstudy.beans.UpdatePostBean;
import com.tau.commstudy.controllers.interfaces.PostControllerInterface;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.services.PostService;
import com.tau.commstudy.services.TestQuestionService;
import com.tau.commstudy.services.TestService;
import com.tau.commstudy.services.UserService;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController implements PostControllerInterface {

    @Autowired
    private PostService service;

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private TestQuestionService questionService;

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id) throws Exception {
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

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Post getById(@PathVariable Long id) {
	return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByUser")
    public Iterable<Post> getByUser(String userTokenId) throws UnauthorizesException {
	User user = userService.get(userTokenId);
	return service.getByUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCourse")
    public List<Post> getByCourse(Course course) {
	return service.getByCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMoed")
    public Iterable<Post> getByMoed(Integer year, Character semester, Character moed, Course course) {
	return service.getByMoed(course, year, semester, moed);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoedK")
    // get,
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
    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoed")
    // get,
    // all
    // fields
    // in a
    // bean
    public boolean checkByMoed(PostCriteria criteria) {
	return service.checkByMoed(criteria);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public List<Post> search(@RequestBody PostCriteria criteria) {
	return service.search(criteria);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Post addNewPost(@RequestBody NewPostBean post, String userTokenId) {
	return service.addNewPost(post, userTokenId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkByQuestion")
    public boolean checkByQuestion(@RequestBody PostCriteria criteria) {
	return service.checkByQuestion(criteria);
    }

    @Override
    public Post updatePost(UpdatePostBean updateBean, Long id, String userTokenId) {
	// TODO Auto-generated method stub
	return null;
    }

}
