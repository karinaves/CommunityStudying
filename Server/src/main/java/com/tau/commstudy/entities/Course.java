package com.tau.commstudy.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="courses")
@XmlRootElement
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long courseUniversityId;
    
    
   
    public Course(){
	
    }



    public long getId() {
        return id;
    }



    public void setId(long id) {
        this.id = id;
    }



    public long getCourseUniversityId() {
        return courseUniversityId;
    }



    public void setCourseUniversityId(long courseUniversityId) {
        this.courseUniversityId = courseUniversityId;
    }
    
    

}
