package com.tau.commstudy.beans;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.tau.commstudy.entities.Tag;

@XmlRootElement
public class NewPostBean {

    private Long facultyId;
    private Long courseId;
    private Integer year;
    private Character semester;
    private Character moed;
    private Integer questionNumber;
    private String title;
    private String content;
    private Set<Tag> tags;
    private List<String> files;

    public List<String> getFiles() {
	return files;
    }

    public void setFiles(List<String> files) {
	this.files = files;
    }

    public NewPostBean() {
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

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getContent() {
	return content;
    }

    public void setContent(String content) {
	this.content = content;
    }

    public Set<Tag> getTags() {
	return tags;
    }

    public void setTags(Set<Tag> tags) {
	this.tags = tags;
    }

}
