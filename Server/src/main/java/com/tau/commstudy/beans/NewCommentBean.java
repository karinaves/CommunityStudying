package com.tau.commstudy.beans;

public class NewCommentBean {

    private String content;
    private Long postId;

    public NewCommentBean() {
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Long getPostId() {
	return postId;
    }

    public void setPostId(Long postId) {
	this.postId = postId;
    }

}
