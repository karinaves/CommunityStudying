package com.tau.commstudy.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long courseId;

    private String name;
    
    @ManyToMany(mappedBy="tags")
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    @ManyToMany(mappedBy="tags")
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    
    public Tag() {

    }



    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }



    public long getCourseId() {
        return courseId;
    }



    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Set<Post> getPosts() {
        return posts;
    }



    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }



    public Set<Question> getQuestions() {
        return questions;
    }



    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    

    

    
}
