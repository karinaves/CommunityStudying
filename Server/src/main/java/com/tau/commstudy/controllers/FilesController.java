package com.tau.commstudy.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tau.commstudy.services.FileService;

@RestController
@RequestMapping("/files")
@CrossOrigin
public class FilesController {

    @Autowired
    private FileService fileService;

    public static final String uploadingdir = System.getProperty("C:\\files");

    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles) throws IOException {
	for (MultipartFile uploadedFile : uploadingFiles) {
	    File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
	    uploadedFile.transferTo(file);
	}

	return "redirect:/";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public boolean uploadContentFile(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles)
	    throws IOException {

	return fileService.uploadFiles(uploadingFiles);
    }

    public File convert(MultipartFile file) throws IOException {
	File convFile = new File(file.getOriginalFilename());
	convFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(convFile);
	fos.write(file.getBytes());
	fos.close();
	return convFile;
    }
}
