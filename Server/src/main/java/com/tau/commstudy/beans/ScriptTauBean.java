package com.tau.commstudy.beans;

import java.util.List;

import com.tau.commstudy.entities.Course;

public class ScriptTauBean {
    List<FacultyBean> faculties;

    public ScriptTauBean() {

    }

    public List<FacultyBean> getFaculties() {
	return faculties;
    }

    public void setFaculties(List<FacultyBean> faculties) {
	this.faculties = faculties;
    }

    public static class SchoolBean {
	private String name;
	private String universityId;
	List<Course> courses;

	public SchoolBean() {

	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getUniversityId() {
	    return universityId;
	}

	public void setUniversityId(String universityId) {
	    this.universityId = universityId;
	}

	public List<Course> getCourses() {
	    return courses;
	}

	public void setCourses(List<Course> courses) {
	    this.courses = courses;
	}

    }

    public static class FacultyBean {
	private String name;
	private String universityId;
	List<SchoolBean> schools;

	public FacultyBean() {

	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getUniversityId() {
	    return universityId;
	}

	public void setUniversityId(String universityId) {
	    this.universityId = universityId;
	}

	public List<SchoolBean> getSchools() {
	    return schools;
	}

	public void setSchools(List<SchoolBean> schools) {
	    this.schools = schools;
	}

    }

}
