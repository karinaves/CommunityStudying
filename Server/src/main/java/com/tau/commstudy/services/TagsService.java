package com.tau.commstudy.services;

import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.entities.daos.TagDao;

@Service
public class TagsService {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TagDao tagDao;

    public Set<Tag> getAllByCourseId(long courseId) throws Exception {
	Course course = courseService.get(courseId);
	Set<Tag> tags = course.getTags();
	return tags;
    }

    public Tag addNewTagToCourse(Long courseId, String tagName) throws ValidationException {
	Course course = courseService.get(courseId);
	Set<Tag> courseTags = course.getTags();
	for (Tag tag : courseTags) {
	    if (tag.getName().equals(tagName)) {
		return tag;
	    }
	}
	Tag tag = new Tag();
	tag.setName(tagName);
	tagDao.save(tag);
	courseTags.add(tag);
	courseService.add(course);
	return tag;
    }
}
