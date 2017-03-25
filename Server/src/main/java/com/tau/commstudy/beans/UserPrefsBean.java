package com.tau.commstudy.beans;

public class UserPrefsBean {
    private boolean isEmailSubscribed;
    private boolean isEmailSubscribedForNewPost;

    public UserPrefsBean() {
	super();
    }

    public Boolean getIsEmailSubscribed() {
	return isEmailSubscribed;
    }

    public void setIsEmailSubscribed(Boolean isEmailSubscribed) {
	this.isEmailSubscribed = isEmailSubscribed;
    }

    public Boolean getIsEmailSubscribedForNewPost() {
	return isEmailSubscribedForNewPost;
    }

    public void setIsEmailSubscribedForNewPost(Boolean isEmailSubscribedForNewPost) {
	this.isEmailSubscribedForNewPost = isEmailSubscribedForNewPost;
    }

}
