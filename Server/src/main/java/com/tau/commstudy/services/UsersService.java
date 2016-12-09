package com.tau.commstudy.services;

import java.net.URL;
import java.util.Calendar;

import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tau.commstudy.beans.GoogleValidateInfo;

@Service
public class UsersService {

    @Autowired
    private UserDao userDao;

    private GoogleValidateInfo verifyUserIdToken(String idTokenString) {
	try {
	    URL url = new URL(
		    "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token="
			    + idTokenString);
	    ObjectMapper mapper = new ObjectMapper();

	    // json from url to Object
	    GoogleValidateInfo obj = mapper.readValue(url,
		    GoogleValidateInfo.class);
	    return obj;
	} catch (Exception ex) {
	    System.out.println("Invalid ID token.");
	    ex.printStackTrace();
	    return null;
	}
    }

    public User getOrCreateUser(String idTokenString){
	GoogleValidateInfo googleValidateInfo = verifyUserIdToken(idTokenString);
	if (googleValidateInfo == null){
	    return null;
	}
	User user = userDao.findByGoogleId(googleValidateInfo.getSub());

	if (user ==null){
	    User newUser= new User();
	    newUser.setEmail(googleValidateInfo.getEmail());
	    newUser.setFirstName(googleValidateInfo.getGiven_name());
	    newUser.setLastName(googleValidateInfo.getFamily_name());
	    newUser.setGoogleId(googleValidateInfo.getSub());
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(Calendar.getInstance().getTime());
	    newUser.setCreated(cal);
	    userDao.save(newUser);
	    System.out.println(newUser);
	    return newUser;
	}

	return user;	
    }
}
