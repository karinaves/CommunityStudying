package com.tau.commstudy.entities.daos;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Course;

@Transactional
public interface CourseDao extends CrudRepository<Course, Long> {
    public List<Course> findByFaculty_IdOrderByName(Long facultyId);
}
