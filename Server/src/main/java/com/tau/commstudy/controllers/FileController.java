package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.NewFileBean;
import com.tau.commstudy.entities.File;
import com.tau.commstudy.services.FileService;

@RestController
@RequestMapping("/file")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService service;

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public File add(@RequestBody NewFileBean fileBean, String userTokenId) throws Exception {
	return service.add(fileBean, userTokenId, false);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public File getById(@PathVariable Long id) throws Exception {
	return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public boolean delete(@PathVariable Long id) throws Exception {
	return service.delete(id);
    }

}
