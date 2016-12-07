package com.tau.commstudy.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.tau.commstudy.entities.Comment;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.entities.daos.CommentDao;
import com.tau.commstudy.entities.daos.PostDao;
import com.tau.commstudy.entities.daos.TagDao;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private PostDao postDao;
    
    @Autowired
    private CommentDao commDao;
    
    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public Post show() {
	Post findOne = postDao.findOne(1L);
	
	Comment comm = new Comment();
	comm.setPost(findOne);
	comm.setContent("dfsdfs");
	
	commDao.save(comm);

	return findOne;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/showComm")
    public Comment showCom() {
	return commDao.findOne(1L);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String getHello(String name, String age) {

	Tag tag = tagDao.findOne(1L);
	Tag tag2 = tagDao.findOne(2L);
	Set<Tag> tags = new HashSet<Tag>();
	tags.add(tag);
	tags.add(tag2);

	Post post = new Post();
	post.setTitle("sdfsd");
	post.setContent("content");
	post.setTagsSet(tags);
	
	tag.getPostsSet().add(post);
	tag2.getPostsSet().add(post);

	tagDao.save(tag);
	tagDao.save(tag2);
	postDao.save(post);

	return "hello " + name + "!" + age;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/verifyUserIdToken")
    public static String verifyUserIdToken(String idTokenString, String clientId)
	    throws GeneralSecurityException, IOException {
	System.out.println("the token is: " + idTokenString);
	String email = "";
	String email_verified = "";
	String name = "";
	String picture = "";
	String locale = "";
	String family_name = "";
	String given_name = "";
	try {
	    URL url = new URL(
		    "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token="
			    + idTokenString);
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    url.openStream()));
	    String strTemp = "";
	    String json = "";

	    while (null != (strTemp = br.readLine())) {
		System.out.println(strTemp);
		json += (strTemp + "\n");
	    }
	    JsonFactory factory = new JsonFactory();
	    JsonParser parser = factory.createParser(json);

	    while (!parser.isClosed()) {
		JsonToken jsonToken = parser.nextToken();

		if (JsonToken.FIELD_NAME.equals(jsonToken)) {
		    String fieldName = parser.getCurrentName();
		    jsonToken = parser.nextToken();

		    if ("email".equals(fieldName)) {
			email = parser.getValueAsString();
		    } else if ("email_verified".equals(fieldName)) {
			email_verified = parser.getValueAsString();
		    } else if ("name".equals(fieldName)) {
			name = parser.getValueAsString();
		    } else if ("picture".equals(fieldName)) {
			picture = parser.getValueAsString();
		    } else if ("locale".equals(fieldName)) {
			locale = parser.getValueAsString();
		    } else if ("family_name".equals(fieldName)) {
			family_name = parser.getValueAsString();
		    } else if ("given_name".equals(fieldName)) {
			given_name = parser.getValueAsString();
		    }
		}
	    }

	} catch (Exception ex) {
	    System.out.println("Invalid ID token.");
	    ex.printStackTrace();
	    return "Invalid ID token.";
	}

	return "email: " + email + " emailVerified:" + email_verified
		+ " name: " + name + " pictureUrl: " + picture + " locale: "
		+ locale + " familyName: " + family_name + " givenName: "
		+ given_name;

    }
}
