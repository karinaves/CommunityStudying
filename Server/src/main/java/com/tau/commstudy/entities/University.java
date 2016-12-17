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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "university")
@XmlRootElement
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "universities_to_faculties", joinColumns = @JoinColumn(name = "university_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"))
    private Set<Faculty> faculties;

    @NotNull
    private String name;

    private Long universityNum;

    private String address;

    public University() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getUniversityNum() {
	return universityNum;
    }

    public void setUniversityNum(Long universityNum) {
	this.universityNum = universityNum;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAddress() {
	return address;
    }

    public void setAddress(String address) {
	this.address = address;
    }

    public Set<Faculty> getFaculties() {
	return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
	this.faculties = faculties;
    }

}
