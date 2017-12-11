package com.tau.commstudy.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NewFileBean {

    private Long commentId;
    private Long testId;
    private Long postId;
    private String url;
    private Integer fileType;
    private String teacher;
    private Integer grade;

    public NewFileBean() {
    }

    public Long getCommentId() {
	return commentId;
    }

    public void setCommentId(Long commentId) {
	this.commentId = commentId;
    }

    public Long getTestId() {
	return testId;
    }

    public void setTestId(Long testId) {
	this.testId = testId;
    }

    public Long getPostId() {
	return postId;
    }

    public void setPostId(Long postId) {
	this.postId = postId;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public Integer getFileType() {
	return fileType;
    }

    public void setFileType(Integer fileType) {
	this.fileType = fileType;
    }

    public String getTeacher() {
	return teacher;
    }

    public void setTeacher(String teacher) {
	this.teacher = teacher;
    }

    public Integer getGrade() {
	return grade;
    }

    public void setGrade(Integer grade) {
	this.grade = grade;
    }

}
