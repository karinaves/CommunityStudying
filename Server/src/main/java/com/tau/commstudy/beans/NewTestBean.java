package com.tau.commstudy.beans;

import java.util.List;

public class NewTestBean {

    private Integer year;
    private Character semester;
    private Character moed;
    private String teacher;
    private char numOfquestions;
    private char difficulty;
    private List<String> files;
    private Long CourseId;

    public NewTestBean() {
    }

    public Integer getYear() {
	return year;
    }

    public void setYear(Integer year) {
	this.year = year;
    }

    public Character getSemester() {
	return semester;
    }

    public void setSemester(Character semester) {
	this.semester = semester;
    }

    public Character getMoed() {
	return moed;
    }

    public void setMoed(Character moed) {
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

    public List<String> getFiles() {
	return files;
    }

    public void setFiles(List<String> files) {
	this.files = files;
    }

    public Long getCourseId() {
	return CourseId;
    }

    public void setCourseId(Long courseId) {
	CourseId = courseId;
    }

}
