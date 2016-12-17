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
@Table(name = "comments")
@XmlRootElement
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar timeStamp;

    private String content;
    private int answerRate;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    public Comment() {

    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
	this.id = id;
    }

    public Calendar getTimeStamp() {
	return timeStamp;
    }

    public void setTimeStamp(Calendar timeStamp) {
	this.timeStamp = timeStamp;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public int getAnswerRate() {
	return answerRate;
    }

    public void setAnswerRate(int answerRate) {
	this.answerRate = answerRate;
    }

    public Post getPost() {
	return post;
    }

    public void setPost(Post post) {
	this.post = post;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

}
