package com.tau.commstudy.spring;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.tau.commstudy.controllers.TestController;


@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(TestController.class);
	}

}