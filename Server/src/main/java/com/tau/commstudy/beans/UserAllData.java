package com.tau.commstudy.beans;

import java.util.List;

public class UserAllData<T> {
    List<T> userData;
    List<T> allData;

    public UserAllData() {

    }

    public List<T> getUserData() {
	return userData;
    }

    public void setUserData(List<T> userData) {
	this.userData = userData;
    }

    public List<T> getAllData() {
	return allData;
    }

    public void setAllData(List<T> allData) {
	this.allData = allData;
    }

}
