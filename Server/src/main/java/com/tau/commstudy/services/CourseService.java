package com.tau.commstudy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tau.commstudy.entities.daos.CourseDao;



@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;


}
