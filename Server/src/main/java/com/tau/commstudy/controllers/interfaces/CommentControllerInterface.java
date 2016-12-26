package com.tau.commstudy.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.tau.commstudy.beans.NewCommentBean;
import com.tau.commstudy.entities.Comment;

public interface CommentControllerInterface {

    // path should be "commment/getByPost/{postId}" - method GET
    public List<Comment> getAllByPostId(Long postId);

    // path should be "commment/?userTokenId" - method POST
    public Comment addNewComment(@RequestBody NewCommentBean commentBean, Long userTokenId);
}
