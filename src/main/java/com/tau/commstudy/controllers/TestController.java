package com.tau.commstudy.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    	@RequestMapping(method = RequestMethod.GET, value = "")
	public String getHello(String name) {
	    return "hello "+name+"!";
	}

}
