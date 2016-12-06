package com.tau.commstudy.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="commentsToPosts")
@XmlRootElement
public class Comment {

    private long id;
    private long userId;
    private long timeStamp;
    private long postId;
    private String content;
    private int answerRate ;
    
   
    public Comment() {
	
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getUserId() {
        return userId;
    }


    public void setUserId(long userId) {
        this.userId = userId;
    }


    public long getTimeStamp() {
        return timeStamp;
    }


    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }


    public long getPostId() {
        return postId;
    }


    public void setPostId(long postId) {
        this.postId = postId;
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

}
