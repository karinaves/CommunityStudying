package com.tau.commstudy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.daos.TestDao;

@Service
public class TestService {

    @Autowired
    private TestDao dao;

    public Test getByMoed(Course course, Integer year, Character semester, Character moed) {
	return dao.findByCourseAndYearAndSemesterAndMoed(course, year, semester, moed);
    }
}
