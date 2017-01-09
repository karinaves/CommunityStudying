package com.tau.commstudy.entities;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysema.query.annotations.QueryInit;

@Entity
@Table(name = "posts")
@XmlRootElement
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar time;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastUpdated;

    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    private int answers;

    private int votes;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<Comment> comments;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private Set<File> file;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "posts_to_tags", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id") )
    private Set<Tag> tags;

    @NotNull
    @ManyToOne
    private User user;

    @ManyToOne
    @QueryInit("test.course.faculty.id")
    private TestQuestion testQuestion;

    Boolean acceptedComment;

    public Post() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Calendar getTime() {
	return time;
    }

    public void setTime(Calendar time) {
	this.time = time;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public int getAnswers() {
	return answers;
    }

    public void setAnswers(int answers) {
	this.answers = answers;
    }

    public int getVotes() {
	return votes;
    }

    public void setVotes(int votes) {
	this.votes = votes;
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

    public Calendar getLastUpdated() {
	return lastUpdated;
    }

    public void setLastUpdated(Calendar lastUpdated) {
	this.lastUpdated = lastUpdated;
    }

    public Boolean getAcceptedComment() {
	return acceptedComment;
    }

    public void setAcceptedComment(Boolean acceptedComment) {
	this.acceptedComment = acceptedComment;
    }

    public Set<Comment> getComments() {
	return comments;
    }

    public void setComments(Set<Comment> comments) {
	this.comments = comments;
    }

    public Set<File> getFile() {
	return file;
    }

    public void setFile(Set<File> file) {
	this.file = file;
    }

}
