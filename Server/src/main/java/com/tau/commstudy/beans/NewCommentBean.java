package com.tau.commstudy.beans;

import java.util.List;

public class NewCommentBean {

    private String content;
    private Long postId;
    private List<String> files;

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

    public List<String> getFiles() {
	return files;
    }

    public void setFiles(List<String> files) {
	this.files = files;
    }

}
