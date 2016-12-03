package com.tau.commstudy.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String getHello(String name,String age) {
	return "hello "+name+"!"+age;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/verifyUserIdToken")
    public static String verifyUserIdToken(String idTokenString, String clientId) throws GeneralSecurityException, IOException {
	System.out.println("the token is: "+idTokenString);
	 String email="";
	    String email_verified="";
	    String name="";
	    String picture="";
	    String locale="";
	    String family_name="";
	    String given_name="";
	try {
	    URL url = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token="+idTokenString);
	    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
	    String strTemp = "";
	    String json="";
	   
	    while (null != (strTemp = br.readLine())) {
		System.out.println(strTemp);
		json+= (strTemp+"\n");
	    }
	    JsonFactory factory = new JsonFactory();
	    JsonParser  parser  = factory.createParser(json);
	    
	    while(!parser.isClosed()){
		JsonToken jsonToken = parser.nextToken();

		if(JsonToken.FIELD_NAME.equals(jsonToken)){
		    String fieldName = parser.getCurrentName();		
		    jsonToken = parser.nextToken();

		    if("email".equals(fieldName)){
			email = parser.getValueAsString();
		    }
		    else if("email_verified".equals(fieldName)){
			email_verified = parser.getValueAsString();
		    }
		    else if("name".equals(fieldName)){
			name = parser.getValueAsString();
		    }
		    else if("picture".equals(fieldName)){
			picture = parser.getValueAsString();
		    }
		    else if("locale".equals(fieldName)){
			locale = parser.getValueAsString();
		    }
		    else if("family_name".equals(fieldName)){
			family_name = parser.getValueAsString();
		    }
		    else if("given_name".equals(fieldName)){
			given_name = parser.getValueAsString();
		    }
		}	
	    }

	} catch (Exception ex) {
	    System.out.println("Invalid ID token.");
	    ex.printStackTrace();
	    return "Invalid ID token.";
	}

	   
	    return "email: " + email +" emailVerified:" + email_verified + " name: " + name + " pictureUrl: " + picture + " locale: " + locale +
		    " familyName: " + family_name + " givenName: " + given_name;

	} 
    }
