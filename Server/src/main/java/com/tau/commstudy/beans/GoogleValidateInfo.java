package com.tau.commstudy.beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleValidateInfo {
    private String email;
    private boolean email_verified;
    private String name;
    private String picture;
    private String family_name;
    private String given_name;
    private String sub;

    public GoogleValidateInfo() {

    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public boolean isEmail_verified() {
	return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
	this.email_verified = email_verified;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPicture() {
	return picture;
    }

    public void setPicture(String picture) {
	this.picture = picture;
    }

    public String getFamily_name() {
	return family_name;
    }

    public void setFamily_name(String family_name) {
	this.family_name = family_name;
    }

    public String getGiven_name() {
	return given_name;
    }

    public void setGiven_name(String given_name) {
	this.given_name = given_name;
    }

    public String getSub() {
	return sub;
    }

    public void setSub(String sub) {
	this.sub = sub;
    }

    @Override
    public String toString() {
	return "GoogleValidateInfo [email=" + email + ", email_verified="
		+ email_verified + ", name=" + name + ", picture=" + picture
		+ ", family_name=" + family_name + ", given_name=" + given_name
		+ ", sub=" + sub + "]";
    }

}
