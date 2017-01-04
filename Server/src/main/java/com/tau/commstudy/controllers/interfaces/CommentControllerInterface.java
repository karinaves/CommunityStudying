package com.tau.commstudy.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.tau.commstudy.beans.NewCommentBean;
import com.tau.commstudy.entities.Comment;

public interface CommentControllerInterface {

    // path should be "commment/getByPost/{postId}" - method GET
    public List<Comment> getAllByPostId(Long postId) throws Exception;

    // path should be "commment/?userTokenId=" - method POST
    public Comment add(@RequestBody NewCommentBean commentBean, String userTokenId);

    // path should be "comment/accept/{id}" - method GET
    // return true only if the right user accepted it, otherwise - throw an
    // exception
    public Boolean acceptComment(@PathVariable Long id, String userTokenId) throws Exception;

    // path should be "comment/updateContent/{id}?userTokenId="
    // should only work if comment.user == user
    // should set the content + set lastUpadted timestamp
    public Comment updateCommentContent(@RequestBody String content, Long id, String userTokenId);
}
