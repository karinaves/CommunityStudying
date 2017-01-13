package com.tau.commstudy.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tau.commstudy.services.UploadService;

@ControllerAdvice
@RestController
@RequestMapping("/files")
@CrossOrigin
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public List<String> uploadContentFile(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles)
	    throws IOException {
	return uploadService.uploadFiles(uploadingFiles);
    }

}
