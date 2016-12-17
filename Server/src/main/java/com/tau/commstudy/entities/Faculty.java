package com.tau.commstudy.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="faculties")
@XmlRootElement
public class Faculty implements Comparable<Faculty> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    @ManyToMany(mappedBy="faculties")
    @JsonIgnore
    @NotNull
    private Set<University> universities = new HashSet<>();
    
    @NotNull
    private String name;
    
    private Integer facultyUniversityId; //number of faculty in University
    
    
    

 
   
    
    public Faculty() {
	
    }




    public Long getId() {
        return id;
    }




    public void setId(Long id) {
        this.id = id;
    }




    public Integer getFacultyUniversityId() {
        return facultyUniversityId;
    }




    public void setFacultyUniversityId(Integer facultyUniversityId) {
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


    @Override
    public int compareTo(Faculty o) {
	return this.name.compareTo(o.getName());
    }





    
    
    
    
}
