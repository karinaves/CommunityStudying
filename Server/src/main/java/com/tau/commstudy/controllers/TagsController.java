package com.tau.commstudy.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.entities.Tag;
import com.tau.commstudy.services.TagsService;

@RestController
@RequestMapping("/tag")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, value = "/getAllByCourseId")
    public Set<Tag> getAllByCourseId(long courseId) throws Exception {
	Set<Tag> tags = tagsService.getAllByCourseId(courseId);
	return tags;

    }

    // public Tag addNewTag() throws Exception {
    // Tag tag = tagsService.addNewTag()
    // }

}
