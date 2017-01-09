package com.tau.commstudy.beans;

import java.util.Set;

import com.tau.commstudy.entities.Tag;

public class PostCriteria {

    private Long facultyId;
    private Long courseId;
    private Long userId;
    private Integer year;
    private Character semester;
    private Character moed;
    private Integer questionNumber;
    private String inContentText;
    private Set<Tag> tags;

    public PostCriteria() {
    }

    public Long getFacultyId() {
	return facultyId;
    }

    public void setFacultyId(Long facultyId) {
	this.facultyId = facultyId;
    }

    public Long getCourseId() {
	return courseId;
    }

    public void setCourseId(Long courseId) {
	this.courseId = courseId;
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

    public Integer getQuestionNumber() {
	return questionNumber;
    }

    public void setQuestionNumber(Integer questionNumber) {
	this.questionNumber = questionNumber;
    }

    public String getInContentText() {
	return inContentText;
    }

    public void setInContentText(String inContentText) {
	this.inContentText = inContentText;
    }

    public Long getUserId() {
	return userId;
    }

    public void setUserId(Long userId) {
	this.userId = userId;
    }

    public Set<Tag> getTags() {
	return tags;
    }

    public void setTags(Set<Tag> tags) {
	this.tags = tags;
    }

}
