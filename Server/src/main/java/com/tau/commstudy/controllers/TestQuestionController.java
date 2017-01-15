package com.tau.commstudy.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.services.PostService;
import com.tau.commstudy.services.TestQuestionService;

@RestController
@RequestMapping("/testQuestion")
@CrossOrigin
public class TestQuestionController {

    @Autowired
    private TestQuestionService service;

    @Autowired
    private PostService postService;

    // @RequestMapping(method = RequestMethod.GET, value = "/save")
    // public String saveQuestion(String title, String content, String userId) {
    // TestQuestion q = new TestQuestion();
    // dao.save(q);
    //
    // return "";
    // }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public String saveByPost(@RequestBody TestQuestion q) {
	service.add(q);

	return "";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public TestQuestion get(long id) {
	return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<TestQuestion> getAll() {
	return service.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByTags")
    public Iterable<TestQuestion> getByTags(Set<Tag> tags, int page, int size) {
	return service.getByTags(tags, page, size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/check4")
    public Iterable<TestQuestion> check4() {

	Post post = postService.getById((long) 12);
	// Set<Tag> tags = new HashSet<Tag>();
	// tags.add(tagsController.addNewTagToCourse((long) 1, "four"));
	// post.setTags(tags);
	// System.out.println(post.getTags() == null);
	return getByTags(post.getTags(), 0, 5);

    }
}
