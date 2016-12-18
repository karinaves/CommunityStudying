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
@Table(name = "test")
@XmlRootElement
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int courseId;

    private Integer year;
    private Character semester;
    private Character moed;
    private String teacher;
    private char numOfquestions;
    private char difficulty;

    @NotNull
    @ManyToOne
    private Course course;

    public Test() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public int getCourseId() {
	return courseId;
    }

    public void setCourseId(int courseId) {
	this.courseId = courseId;
    }

    public Integer getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public Character getSemester() {
	return semester;
    }

    public void setSemester(char semester) {
	this.semester = semester;
    }

    public Character getMoed() {
	return moed;
    }

    public void setMoed(char moed) {
	this.moed = moed;
    }

    public String getTeacher() {
	return teacher;
    }

    public void setTeacher(String teacher) {
	this.teacher = teacher;
    }

    public char getNumOfquestions() {
	return numOfquestions;
    }

    public void setNumOfquestions(char numOfquestions) {
	this.numOfquestions = numOfquestions;
    }

    public char getDifficulty() {
	return difficulty;
    }

    public void setDifficulty(char difficulty) {
	this.difficulty = difficulty;
    }

    public Course getCourse() {
	return course;
    }

    public void setCourse(Course course) {
	this.course = course;
    }

}