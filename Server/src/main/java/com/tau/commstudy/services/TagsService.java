package com.tau.commstudy.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Tag;

@Service
public class TagsService {

    @Autowired
    private CourseService courceService;

    public Set<Tag> getAllByCourseId(long courseId) throws Exception {
	Course course = courceService.get(courseId);
	Set<Tag> tags = course.getTags();
	return tags;
    }

    // public User addNewTag(String idTokenString) throws Exception {
    //
    // }

}
