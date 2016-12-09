package com.tau.commstudy.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="faculties")
@XmlRootElement
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int facultyUniversityId; //number of faculty in University
    private String name;
    
    @ManyToMany(mappedBy="faculties")
    @JsonIgnore
    private Set<University> universities = new HashSet<>();

 
   
    
    public Faculty() {
	
    }




    public int getId() {
        return id;
    }




    public void setId(int id) {
        this.id = id;
    }




    public int getFacultyUniversityId() {
        return facultyUniversityId;
    }




    public void setFacultyUniversityId(int facultyUniversityId) {
        this.facultyUniversityId = facultyUniversityId;
    }




    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
    }




    public Set<University> getUniversities() {
        return universities;
    }




    public void setUniversities(Set<University> universities) {
        this.universities = universities;
    }





    
    
    
    
}
