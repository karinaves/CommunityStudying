package com.tau.commstudy.entities.daos;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    public User findByGoogleId(String googleId);

    public Set<User> findByCoursesIn(Set<Course> courses);

}