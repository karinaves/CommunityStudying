package com.tau.commstudy.entities.daos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.entities.User;

public interface PostDao extends CrudRepository<Post, Long> {

    public Iterable<Post> findByUser(User user);

    public Iterable<Post> findByTestQuestion_Test_CourseOrderByTimeDesc(Course course);

    public List<Post> findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
	    Integer year, Character semester, Character moed, Course course);

    public List<Post> findByTestQuestionOrderByTimeDesc(TestQuestion testQuestion);

    public List<Post> findByTestQuestion_TestOrderByTimeDesc(Test test);
}
