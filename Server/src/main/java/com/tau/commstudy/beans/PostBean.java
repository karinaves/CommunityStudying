package com.tau.commstudy.beans;

import java.util.Set;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.entities.User;

public class PostBean {
    private Long id;

    private String postTitle;

    private String postContent;

    private Set<Tag> tags;

    private User user;

    private TestQuestion testQuestion;

    private Test test;

    private Course course;

    private Integer year;
    private Character semester;
    private Character moed;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getPostTitle() {
	return postTitle;
    }

    public void setPostTitle(String postTitle) {
	this.postTitle = postTitle;
    }

    public String getPostContent() {
	return postContent;
    }

    public void setPostContent(String postContent) {
	this.postContent = postContent;
    }

    public Set<Tag> getTags() {
	return tags;
    }

    public void setTags(Set<Tag> tags) {
	this.tags = tags;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public TestQuestion getTestQuestion() {
	return testQuestion;
    }

    public void setTestQuestion(TestQuestion testQuestion) {
	this.testQuestion = testQuestion;
    }

    public Test getTest() {
	return test;
    }

    public void setTest(Test test) {
	this.test = test;
    }

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }

    public Integer getYear() {
	return year;
    }

    public void setYear(Integer year) {
	this.year = year;
    }

    public Character getSemester() {
	return semester;
    }

    public void setSemester(Character semester) {
	this.semester = semester;
    }

    public Character getMoed() {
	return moed;
    }

    public void setMoed(Character moed) {
	this.moed = moed;
    }

}
