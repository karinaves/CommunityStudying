package com.tau.commstudy.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/test")
public class TestController {


	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getBla() {
		return "bla";
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getHello(@QueryParam("name") @DefaultValue("NO NAME") String name) {
	    return "hello " + name+"!";
	}

}
