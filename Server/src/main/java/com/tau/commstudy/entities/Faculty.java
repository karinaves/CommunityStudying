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
@Table(name = "faculties")
@XmlRootElement
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private University university;

    @NotNull
    private String name;

    private Long universityId; // number of faculty in University

    public Faculty() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public University getUniversity() {
	return university;
    }

    public void setUniversity(University university) {
	this.university = university;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Long getUniversityId() {
	return universityId;
    }

    public void setUniversityId(Long universityId) {
	this.universityId = universityId;
    }

}
