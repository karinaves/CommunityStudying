package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.daos.CourseDao;


@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseDao dao;

}
