package com.tau.commstudy.entities.daos;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Course;

@Transactional
public interface CourseDao extends CrudRepository<Course, Long> {

    public List<Course> findByFaculty_IdOrderByNameHebrew(Long facultyId);

    public Set<Course> findByIdIn(Set<Long> coursesIds);
}
