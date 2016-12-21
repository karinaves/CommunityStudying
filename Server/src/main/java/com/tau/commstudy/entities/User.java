package com.tau.commstudy.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}