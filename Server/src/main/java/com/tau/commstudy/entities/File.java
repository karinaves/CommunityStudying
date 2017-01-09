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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "files")
@XmlRootElement
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar uploadTimestamp;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Test test;

    @ManyToOne
    private Post post;

    private String url;

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

}
