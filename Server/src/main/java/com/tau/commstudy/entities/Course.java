package com.tau.commstudy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "courses")
@XmlRootElement
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Faculty faculty;

    @NotNull
    private String nameEnglish;

    @NotNull
    private String nameHebrew;

    private Long universityId;

    public Course() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Faculty getFaculty() {
	return faculty;
    }

    public void setFaculty(Faculty faculty) {
	this.faculty = faculty;
    }

    public String getNameEnglish() {
	return nameEnglish;
    }

    public void setNameEnglish(String nameEnglish) {
	this.nameEnglish = nameEnglish;
    }

    public String getNameHebrew() {
	return nameHebrew;
    }

    public void setNameHebrew(String nameHebrew) {
	this.nameHebrew = nameHebrew;
    }

    public Long getUniversityId() {
	return universityId;
    }

    public void setUniversityId(Long universityId) {
	this.universityId = universityId;
    }

}
