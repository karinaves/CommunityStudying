package com.tau.commstudy.entities.daos;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.User;

public interface PostDao extends CrudRepository<Post, Long> {

    public Iterable<Post> findByUser(User user);

    Iterable<Post> findByTestQuestion_Test_CourseOrderByTimeDesc(Course course);

    Iterable<Post> findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedOrderByTimeDesc(
	    Integer year, Character semester, Character moed);
}
