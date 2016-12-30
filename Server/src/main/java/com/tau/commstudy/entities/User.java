package com.tau.commstudy.entities;

import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String email;
    private String firstName;
    private String lastName;
    private String googleId;
    private String pictureUrl;
    private Integer userRating;
    private boolean isAdmin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_to_courses", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public String getPictureUrl() {
	return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
	this.pictureUrl = pictureUrl;
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created;

    public User() {
    }

    public User(long id) {
	this.id = id;
    }

    public long getId() {
	return id;
    }

    public void setId(long id) {
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

}