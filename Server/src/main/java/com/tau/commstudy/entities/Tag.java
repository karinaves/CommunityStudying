package com.tau.commstudy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="tags")
@XmlRootElement
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private long courseId;
   
    private String name;
    
    
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

    
    
    
}
