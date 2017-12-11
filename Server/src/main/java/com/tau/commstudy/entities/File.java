package com.tau.commstudy.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "files")
@XmlRootElement
public class File {

    public static enum FileType {
	TEST(1), SOLUTION(2), TEST_AND_SOL(3), POST_ATTACH(4);

	private Integer id;

	FileType(int id) {
	    this.id = id;
	}

	public Integer getId() {
	    return this.id;
	}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar uploadTimestamp;

    @ManyToOne
    @JsonIgnore
    private Comment comment;

    @ManyToOne
    @JsonIgnore
    private Test test;

    @ManyToOne
    @JsonIgnore
    private Post post;

    private String url;

    private boolean primaryFile;

    private Integer fileType;

    private String teacher;

    private Integer grade;

    @NotNull
    @ManyToOne
    private User user;

    private boolean approved;

    public File() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Calendar getUploadTimestamp() {
	return uploadTimestamp;
    }

    public void setUploadTimestamp(Calendar uploadTimestamp) {
	this.uploadTimestamp = uploadTimestamp;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public Comment getComment() {
	return comment;
    }

    public void setComment(Comment comment) {
	this.comment = comment;
    }

    public Test getTest() {
	return test;
    }

    public void setTest(Test test) {
	this.test = test;
    }

    public Post getPost() {
	return post;
    }

    public void setPost(Post post) {
	this.post = post;
    }

    public boolean isPrimaryFile() {
	return primaryFile;
    }

    public void setPrimaryFile(boolean primaryFile) {
	this.primaryFile = primaryFile;
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

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public boolean isApproved() {
	return approved;
    }

    public void setApproved(boolean approved) {
	this.approved = approved;
    }

}
