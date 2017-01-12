package com.tau.commstudy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.entities.daos.TestQuestionDao;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionDao dao;

    // @RequestMapping(method = RequestMethod.GET, value = "/save")
    // public String saveQuestion(String title, String content, String userId) {
    // TestQuestion q = new TestQuestion();
    // dao.save(q);
    //
    // return "";
    // }

    public TestQuestion add(TestQuestion q) {
	try {
	    return dao.save(q);
	} catch (Exception ex) {
	    System.out.println("Error creating the question:" + ex.toString());
	    return null; // "Error creating the post:" + ex.toString();
	}
    }

    public TestQuestion get(long id) {
	return dao.findOne(id);
    }

    public Iterable<TestQuestion> getAll() {
	return dao.findAll();
    }

    public TestQuestion getByTestAndNumber(Test test, Integer questionNumber) {
	return dao.findByTestAndQuestionNumber(test, questionNumber);
    }

}

// package com.tau.commstudy.controllers;
//
// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
//
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
//
// import com.amazonaws.AmazonClientException;
// import com.amazonaws.AmazonServiceException;
// import com.amazonaws.auth.AWSCredentials;
// import com.amazonaws.auth.BasicAWSCredentials;
// import com.amazonaws.services.s3.AmazonS3;
// import com.amazonaws.services.s3.AmazonS3Client;
// import com.amazonaws.services.s3.model.PutObjectRequest;
//
// @RestController
// @RequestMapping("/files")
// @CrossOrigin
// public class FilesController {
// public static final String uploadingdir = System.getProperty("C:\\files");
//
// @RequestMapping(value = "/upload2", method = RequestMethod.POST)
// public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile[]
// uploadingFiles) throws IOException {
// for (MultipartFile uploadedFile : uploadingFiles) {
// File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
// uploadedFile.transferTo(file);
// }
//
// return "redirect:/";
// }
//
// // @ResponseBody
// @RequestMapping(value = "/upload", method = RequestMethod.POST)
// public boolean uploadContentFile(@RequestParam("file") MultipartFile
// uploadedFile) throws IOException {
// System.out.println(uploadedFile.getOriginalFilename());
// String accessKeyID = "AKIAJBJANY4WXG3C3MTA";
// String secretAccessKey = "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";
//
// AWSCredentials credentials = new BasicAWSCredentials(accessKeyID,
// secretAccessKey);
// String keyName = "***";
// String bucketName = "study-buddy-files";
// try {
// AmazonS3 s3client = new AmazonS3Client(credentials);
// // for (MultipartFile uploadedFile : uploadingFiles) {
// File file = convert(uploadedFile);
// keyName = uploadedFile.getOriginalFilename();
// System.out.println(keyName);
// s3client.putObject(new PutObjectRequest(bucketName, keyName, file));
// System.out.println(keyName);
// // }
//
// } catch (AmazonServiceException ase) {
// return false;
// } catch (AmazonClientException ace) {
//
// return false;
// }
// System.out.println(keyName);
// return true;
// }
//
// public File convert(MultipartFile file) throws IOException {
// File convFile = new File(file.getOriginalFilename());
// convFile.createNewFile();
// FileOutputStream fos = new FileOutputStream(convFile);
// fos.write(file.getBytes());
// fos.close();
// return convFile;
// }