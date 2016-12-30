package com.tau.commstudy.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    private String nameEnglish;

    private String nameHebrew;

    private String universityId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "courses_to_tags", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id") )
    private Set<Tag> tags;

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

    public String getUniversityId() {
	return universityId;
    }

    public void setUniversityId(String universityId) {
	this.universityId = universityId;
    }

    public Set<Tag> getTags() {
	return tags;
    }

    public void setTags(Set<Tag> tags) {
	this.tags = tags;
    }

    @Override
    public String toString() {
	return "Course [id=" + id + ", faculty=" + faculty + ", nameEnglish=" + nameEnglish + ", nameHebrew="
		+ nameHebrew + ", universityId=" + universityId + ", tags=" + tags + "]";
    }

}
