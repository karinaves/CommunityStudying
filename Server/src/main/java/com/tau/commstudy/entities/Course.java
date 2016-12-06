package com.tau.commstudy.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="courses")
@XmlRootElement
public class Course {
    
    @Id
//    @OneToMany(mappedBy = "courseId", cascade = CascadeType.ALL)
    private int id;
    
//    @ManyToOne
//    @JoinColumn(name = "id")
    private int facultyId;
    
   
    
    
    public Course(){
	
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    
}
