package com.tau.commstudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import com.tau.commstudy.services.FacultyService;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    @Autowired
    static FacultyService facultyService;

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);

    }

}