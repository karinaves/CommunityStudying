package com.tau.commstudy.services;

import java.net.URL;
import java.util.Calendar;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.UserDao;
import com.tau.commstudy.exceptions.UnauthorizesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tau.commstudy.beans.GoogleValidateInfo;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User getOrCreateUser(String idTokenString) throws UnauthorizesException{
	GoogleValidateInfo googleValidateInfo = verifyUserIdToken(idTokenString);
	User user = userDao.findByGoogleId(googleValidateInfo.getSub());
	//creating new User
	if (user ==null){
	    user = new User();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(Calendar.getInstance().getTime());
	    
	    user.setEmail(googleValidateInfo.getEmail());
	    user.setFirstName(googleValidateInfo.getGiven_name());
	    user.setLastName(googleValidateInfo.getFamily_name());
	    user.setGoogleId(googleValidateInfo.getSub());
	    user.setCreated(cal);
	    userDao.save(user);
	    System.out.println(user);
	    
	}
	return user;
    }

    // -----------------Auxiliary functions-----------------------
    
    private GoogleValidateInfo verifyUserIdToken(String idTokenString) throws UnauthorizesException{
	try{
	    URL url = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + idTokenString);
	    ObjectMapper mapper = new ObjectMapper();

	    // Json format from the URL to GoogleValidateInfo instance
	    GoogleValidateInfo validateInfo = mapper.readValue(url, GoogleValidateInfo.class);
	    return validateInfo;
	}
	catch (Exception ex){
	    throw new UnauthorizesException();
	}
    }


}
