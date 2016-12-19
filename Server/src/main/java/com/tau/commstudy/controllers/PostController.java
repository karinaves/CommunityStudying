package com.tau.commstudy.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.PostBean;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.PostDao;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostDao dao;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Post createPost(@RequestBody Post newPost) {
	try {
	    newPost.setTime(Calendar.getInstance());
	    return dao.save(newPost);
	} catch (Exception ex) {
	    return null; // "Error creating the post:" + ex.toString();
	}

	// return "Post succesfully saved!";
    }

    @RequestMapping(value = "/delete", params = { "id" })
    public String delete(long id) {
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
    @RequestMapping(method = RequestMethod.POST, value = "/like")
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

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<Post> getAll() {
	return dao.findAll();
    }

    /**
     * This updates only the content, title of the post. All the other fields
     * (including id and time) stay the same
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String updatePost(@RequestBody Post givenPost) {
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

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public Post getById(long id) {
	return dao.findOne(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByUser")
    public Iterable<Post> getByUser(User user) {
	return dao.findByUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCourse")
    public Iterable<Post> getByCourse(Course course) {
	return dao.findByTestQuestion_Test_CourseOrderByTimeDesc(course);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMoed")
    public Iterable<Post> getByMoed(Integer year, Character semester, Character moed, Course course) {
	return dao
		.findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
			year, semester, moed, course);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoedK") // get,
									 // all
									 // fields
									 // separately
    public boolean checkByMoedK(Integer year, Character semester, Character moed, Course course) {
	List<Post> posts = dao
		.findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
			year, semester, moed, course);
	if (posts.size() == 0)
	    return false;
	return true;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoed") // get,
									// all
									// fields
									// in a
									// bean
    public boolean checkByMoed(PostBean bean) {
	// List<Post> posts = dao
	// .findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
	// b);
	if (bean == null)
	    return false;
	return true;
    }

}
