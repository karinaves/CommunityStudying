package com.tau.commstudy.entities;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String email;
    private String firstName;
    private String lastName;
    private String googleId;
    private String pictureUrl;
    private Integer userRating;
    private boolean isAdmin;
    private boolean emailSubscribed;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastLogin;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_courses", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id") )
    private Set<Course> courses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_to_faculties", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id") )
    private Set<Faculty> faculties;

    public String getPictureUrl() {
	return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
	this.pictureUrl = pictureUrl;
    }

    public User() {
    }

    public User(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public String getGoogleId() {
	return googleId;
    }

    public void setGoogleId(String googleId) {
	this.googleId = googleId;
    }

    public Calendar getCreated() {
	return created;
    }

    public void setCreated(Calendar created) {
	this.created = created;
    }

    public Set<Course> getCourses() {
	return courses;
    }

    public void setCourses(Set<Course> courses) {
	this.courses = courses;
    }

    public Integer getUserRating() {
	return userRating;
    }

    public void setUserRating(Integer userRating) {
	this.userRating = userRating;
    }

    public boolean isAdmin() {
	return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
	this.isAdmin = isAdmin;
    }

    public Set<Faculty> getFaculties() {
	return faculties;
    }

    public void setFaculties(Set<Faculty> faculties) {
	this.faculties = faculties;
    }

    public Calendar getLastLogin() {
	return lastLogin;
    }

    public void setLastLogin(Calendar lastLogin) {
	this.lastLogin = lastLogin;
    }

    public boolean isEmailSubscribed() {
	return emailSubscribed;
    }

    public void setEmailSubscribed(boolean emailSubscribed) {
	this.emailSubscribed = emailSubscribed;
    }

}