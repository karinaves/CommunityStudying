package com.tau.commstudy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.NewCommentBean;
import com.tau.commstudy.controllers.interfaces.CommentControllerInterface;
import com.tau.commstudy.entities.Comment;
import com.tau.commstudy.services.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController implements CommentControllerInterface {

    @Autowired
    private CommentService service;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    @Override
    public Comment add(NewCommentBean commentBean, String userTokenId) {
	return service.add(commentBean, userTokenId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Comment getById(@PathVariable Long id) throws Exception {
	return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id) throws Exception {
	return service.delete(id);
    }

    @Override
    public List<Comment> getAllByPostId(Long postId) throws Exception {
	return service.getAllByPostId(postId);
    }

    @Override
    public Boolean acceptComment(Long id, String userTokenId) throws Exception {
	return service.acceptComment(id, userTokenId);
    }

    @Override
    public Comment updateCommentContent(String content, Long id, String userTokenId) {
	// TODO Auto-generated method stub
	return null;
    }

}
