package com.tau.commstudy.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FilesController {
    public static final String uploadingdir = System.getProperty("C:\\files");

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
	for (MultipartFile uploadedFile : uploadingFiles) {
	    File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
	    uploadedFile.transferTo(file);
	}

	return "redirect:/";
    }
}
