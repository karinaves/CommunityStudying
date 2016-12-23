package com.tau.commstudy.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.tau.commstudy.beans.NewPostBean;
import com.tau.commstudy.beans.PostCriteria;
import com.tau.commstudy.entities.Post;

public interface PostControllerInterface {

    // path should be - "post/" method = POST
    public Post addNewPost(@RequestBody NewPostBean post, String userTokenId);

    // path should be - "post/search" method = POST
    public List<Post> search(PostCriteria criteria);

}