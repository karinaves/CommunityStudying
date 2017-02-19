package com.tau.commstudy.services;

import java.util.Calendar;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.beans.NewCommentBean;
import com.tau.commstudy.beans.NewFileBean;
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

    @Autowired
    private EmailService emailService;

    @Autowired
    private FileService fileService;

    /**
     * Creates and Saves to DB a Comment entity.
     * 
     * @param Faculty
     *            object
     * @return the saved Course entity
     * @throws ValidationException
     *             if not saved
     */
    public Comment add(NewCommentBean commentBean, String userTokenId)
	    throws ValidationException, IllegalArgumentException {
	Comment comment = new Comment();
	User user = userService.get(userTokenId);
	Post post = postService.getById(commentBean.getPostId());
	comment.setUser(user);
	comment.setPost(post);
	comment.setContent(commentBean.getContent());
	comment.setTimeStamp(Calendar.getInstance());
	User author = post.getUser();
	comment.setIsAccepted(false);
	try {
	    emailService.emailComment(author.getEmail(), post.getTitle(), post.getId(), post.getUser().getFirstName());
	} catch (Exception ex) {
	    System.out.println("Error sending email: " + ex.toString());
	}

	Comment commentNew = dao.save(comment);
	for (String fileUrl : commentBean.getFiles()) {
	    NewFileBean fileBean = new NewFileBean();
	    fileBean.setCommentId(commentNew.getId());
	    fileBean.setUrl(fileUrl);
	    fileService.add(fileBean, userTokenId);
	}
	return commentNew;
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
    public boolean delete(Long id, String userTokenId) throws Exception {
	Comment comment = dao.findOne(id);
	User owner = comment.getUser();
	User editor = userService.get(userTokenId);
	System.out.println(owner.getId());
	System.out.println(editor.getId());

	if (userService.isAuthorizedEditUser(owner, editor) || editor.isAdmin()) {
	    // edge case - deleting an accepted comment
	    if (comment.getIsAccepted()) {
		Post post = comment.getPost();
		post.setAcceptedComment(false);
		postDao.save(post);
	    }
	    dao.delete(id);
	    return true;
	}
	return false;
    }

    public List<Comment> getAllByPostId(Long postId) throws IllegalArgumentException {
	return dao.findByPost_IdOrderByIsAcceptedDescAnswerRateDesc(postId);
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
	User owner = post.getUser();
	User editor = userService.get(userTokenId);
	// unAuthorized
	if (!userService.isAuthorizedEditUser(owner, editor) && !editor.isAdmin()) {
	    return false;
	}
	comment.setIsAccepted(newStat);
	post.setAcceptedComment(newStat);
	dao.save(comment);
	postDao.save(post);
	return true;
    }

    public Boolean like(Long id) {
	try {
	    Comment comment = dao.findOne(id);
	    comment.setAnswerRate(comment.getAnswerRate() + 1);
	    dao.save(comment);
	    userService.incOrDecRank(comment.getUser(), 1);
	    return true;
	} catch (Exception ex) {
	    return false;
	}
    }

    public Boolean dislike(Long id) {
	try {
	    Comment comment = dao.findOne(id);
	    comment.setAnswerRate(comment.getAnswerRate() - 1);
	    dao.save(comment);
	    userService.incOrDecRank(comment.getUser(), -1);
	    return false;
	} catch (Exception ex) {
	    return true;
	}
    }

    public Comment updateCommentContent(String content, Long id, String userTokenId)
	    throws UnauthorizesException, IllegalArgumentException {
	Comment comment = this.getById(id);
	User owner = comment.getUser();
	User editor = userService.get(userTokenId);
	if (!userService.isAuthorizedEditUser(owner, editor) && !editor.isAdmin()) {
	    return null;
	}
	comment.setContent(content);
	comment.setLastUpdated(Calendar.getInstance());
	comment = dao.save(comment);

	return comment;
    }

}
