package com.tau.commstudy.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.GoogleValidateInfo;
import com.tau.commstudy.entities.Comment;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.daos.CommentDao;
import com.tau.commstudy.entities.daos.PostDao;
import com.tau.commstudy.entities.daos.TagDao;
import com.tau.commstudy.services.CourseService;
import com.tau.commstudy.services.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

    GoogleValidateInfo googleValidateInfo;

    @Autowired
    private TestService testService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commDao;

    // @RequestMapping(method = RequestMethod.POST, value = "/create")
    // public Test createTest(@RequestBody Test newTest) {
    // return dao.save(newTest);
    // }

    @RequestMapping(method = RequestMethod.GET, value = "/show")
    public Post show() {
	Post findOne = postDao.findOne(1L);

	Comment comm = new Comment();
	// comm.setPost(findOne);
	comm.setContent("dfsdfs");

	commDao.save(comm);

	return findOne;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/showComm")
    public Comment showCom() {
	return commDao.findOne(1L);
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public String getHello(String name, String age) {

	Tag tag = tagDao.findOne(1L);
	Tag tag2 = tagDao.findOne(2L);
	Set<Tag> tags = new HashSet<Tag>();
	tags.add(tag);
	tags.add(tag2);

	Post post = new Post();
	post.setTitle("sdfsd");
	post.setContent("content");
	post.setTags(tags);

	tag.getPosts().add(post);
	tag2.getPosts().add(post);

	tagDao.save(tag);
	tagDao.save(tag2);
	postDao.save(post);

	return "hello " + name + "!" + age;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMoed")
    public Test getByMoed(Long courseId, Integer year, Character semester, Character moed) {
	Course course = courseService.get(courseId);
	return testService.getByMoed(course, year, semester, moed);
    }

}
