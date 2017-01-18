package com.tau.commstudy.services;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.beans.NewFileBean;
import com.tau.commstudy.entities.Comment;
import com.tau.commstudy.entities.File;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.FileDao;
import com.tau.commstudy.exceptions.UnauthorizesException;

@Service

public class FileService {

    @Autowired
    private FileDao dao;

    @Autowired
    private TestService testService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    public File add(NewFileBean fileBean, String userTokenId) throws UnauthorizesException, IllegalArgumentException {
	File file = new File();
	file.setUploadTimestamp(Calendar.getInstance());
	file.setUrl(fileBean.getUrl());
	Test test = fileBean.getTestId() != null ? testService.getById(fileBean.getTestId()) : null;
	Post post = fileBean.getPostId() != null ? postService.getById(fileBean.getPostId()) : null;
	Comment comment = fileBean.getCommentId() != null ? commentService.getById(fileBean.getCommentId()) : null;
	User owner, editor = userService.get(userTokenId);

	// update only test post or comment - not combined
	if (test != null) {
	    if (test.getFiles().size() == 0)
		file.setPrimaryFile(true);
	    file.setTest(test);
	    return dao.save(file);
	} else if (post != null || comment != null) {
	    owner = post != null ? post.getUser() : comment.getUser();

	    // check user is authorized to edit
	    if (!userService.isAuthorizedEditUser(owner, editor))
		throw new UnauthorizesException();

	    // add File to post
	    if (post != null) {
		if (post.getFiles().size() == 0)
		    file.setPrimaryFile(true);
		file.setPost(post);
	    }
	    // add File to comment
	    else {
		if (comment.getFiles().size() == 0)
		    file.setPrimaryFile(true);
		file.setComment(comment);
	    }
	    return dao.save(file);
	}
	throw new IllegalArgumentException("NewFileBean doesn't have Test/Post/Comment");

    }

    public File getById(Long id) throws IllegalArgumentException {
	return dao.findOne(id);
    }

    public boolean delete(Long id) throws IllegalArgumentException {
	dao.delete(id);
	return true;
    }

}
