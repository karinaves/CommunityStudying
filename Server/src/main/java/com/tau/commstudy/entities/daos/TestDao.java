package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Test;

@Transactional
public interface TestDao extends CrudRepository<Test, Long> {
    Test findByCourseAndYearAndSemesterAndMoed(Course course, Integer year, Character semester, Character moed);
}
