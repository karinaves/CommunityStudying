package com.tau.commstudy.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.daos.PostDao;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostDao dao;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String createPost(@RequestBody Post newPost) {
	try {
	    newPost.setTime(Calendar.getInstance());
	    dao.save(newPost);
	} catch (Exception ex) {
	    return "Error creating the post:" + ex.toString();
	}

	return "Post succesfully saved!";
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

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public Post getById(long id) {
	return dao.findOne(id);
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

}
