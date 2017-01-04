package com.tau.commstudy.services;

import java.util.Calendar;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.beans.NewCommentBean;
import com.tau.commstudy.entities.Comment;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.CommentDao;
import com.tau.commstudy.entities.daos.PostDao;
import com.tau.commstudy.exceptions.UnauthorizesException;

@Service
public class CommentService {

    @Autowired
    private CommentDao dao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    /**
     * Creates and Saves to DB a Comment entity.
     * 
     * @param Faculty
     *            object
     * @return the saved Course entity
     * @throws ValidationException
     *             if not saved
     */
    public Comment add(NewCommentBean commentBean, String userTokenId) throws ValidationException,
	    IllegalArgumentException {
	Comment comment = new Comment();
	User user = userService.get(userTokenId);
	Post post = postService.getById(commentBean.getPostId());
	comment.setUser(user);
	comment.setPost(post);
	comment.setContent(commentBean.getContent());
	comment.setTimeStamp(Calendar.getInstance());
	comment.setIsAccepted(false);
	return dao.save(comment);
    }

    /**
     * Retrieves an entity by its id.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @return the saved Comment entity
     * @throws IllegalArgumentException
     *             if {@code id} is {@literal null}
     */
    public Comment getById(Long id) throws IllegalArgumentException {
	return dao.findOne(id);
    }

    /**
     * Deletes an entity by its id.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return true if deleted or not in DB
     * @throws IllegalArgumentException
     *             if {@code id} is {@literal null}
     */
    public boolean delete(Long id) throws IllegalArgumentException {
	dao.delete(id);
	return true;
    }

    public List<Comment> getAllByPostId(Long postId) throws IllegalArgumentException {
	return dao.findByPost_IdOrderByAnswerRateDesc(postId);
    }

    public Boolean acceptComment(Long id, String userTokenId) throws IllegalArgumentException, UnauthorizesException {
	return changedCommentStatus(id, userTokenId, true);
    }

    public Boolean unacceptComment(Long id, String userTokenId) throws IllegalArgumentException, UnauthorizesException {
	return changedCommentStatus(id, userTokenId, false);
    }

    private Boolean changedCommentStatus(Long id, String userTokenId, Boolean newStat) {
	Comment comment = getById(id);
	Post post = comment.getPost();
	User userPost = post.getUser();
	User user = userService.get(userTokenId);
	// User who accepted the comment is the user who asked the question
	if (user.getId().equals(userPost.getId())) {
	    comment.setIsAccepted(newStat);
	    post.setAcceptedComment(newStat);
	    dao.save(comment);
	    postDao.save(post);
	    return true;
	}
	return false;
    }

}
