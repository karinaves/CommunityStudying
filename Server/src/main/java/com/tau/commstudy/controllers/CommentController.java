package com.tau.commstudy.controllers;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Comment;
import com.tau.commstudy.entities.daos.CommentDao;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentDao dao;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String createComment(@RequestBody Comment newComment) {
	try {
	    newComment.setTimeStamp(Calendar.getInstance());
	    dao.save(newComment);
	} catch (Exception ex) {
	    return "Error creating the comment:" + ex.toString();
	}

	return "Comment succesfully saved!";
    }

    @RequestMapping(value = "/delete", params = { "id" })
    public String delete(long id) {
	try {
	    dao.delete(id);
	} catch (Exception ex) {
	    return "Error deleting the comment:" + ex.toString();
	}
	return "Comment succesfully deleted!";
    }

    @RequestMapping(value = "/delete", params = {})
    public String delete() {
	return "long parameter 'id' needs to be provided";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public Comment getById(long id) {
	return dao.findOne(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<Comment> getAll() {
	return dao.findAll();
    }

    /**
     * This updates only the content of the comment. All the other fields
     * (including id and time) stay the same
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String updatePost(@RequestBody Comment givenComment) {
	try {
	    Comment comment = dao.findOne(givenComment.getId());
	    comment.setContent(givenComment.getContent());
	    dao.save(comment);
	} catch (Exception ex) {
	    return "Error updating the comment: " + ex.toString();
	}
	return "Comment succesfully updated!";
    }

    /**
     * increases this answer's rating by 1
     */
    @RequestMapping(method = RequestMethod.POST, value = "/like")
    public String like(long id) {
	try {
	    Comment comment = dao.findOne(id);
	    comment.setAnswerRate(comment.getAnswerRate() + 1);
	    dao.save(comment);
	} catch (Exception ex) {
	    return "Error updating rating for comment: " + ex.toString();
	}
	return "Comment succesfully updated!";
    }

}
