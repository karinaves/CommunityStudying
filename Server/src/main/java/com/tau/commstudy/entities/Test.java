package com.tau.commstudy.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test", uniqueConstraints = @UniqueConstraint(columnNames = { "course", "year", "semester", "moed" }) )
@XmlRootElement
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer year;
    private Character semester;
    private Character moed;
    private String teacher;
    private char numOfquestions;
    private char difficulty;

    @NotNull
    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "test")
    @JsonIgnore
    private Set<File> files;

    public Test() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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

    public Set<File> getFiles() {
	return files;
    }

    public void setFiles(Set<File> files) {
	this.files = files;
    }

    public void setYear(Integer year) {
	this.year = year;
    }

    public void setSemester(Character semester) {
	this.semester = semester;
    }

    public void setMoed(Character moed) {
	this.moed = moed;
    }

}