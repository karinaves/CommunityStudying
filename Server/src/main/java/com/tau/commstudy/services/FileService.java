package com.tau.commstudy.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class FileService {

    public boolean uploadFiles(MultipartFile[] uploadingFiles) throws IOException {

	String accessKeyID = "AKIAJBJANY4WXG3C3MTA";
	String secretAccessKey = "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";

	AWSCredentials credentials = new BasicAWSCredentials(accessKeyID, secretAccessKey);
	String keyName = "***";
	String bucketName = "study-buddy-files";
	try {
	    AmazonS3 s3client = new AmazonS3Client(credentials);
	    for (MultipartFile uploadedFile : uploadingFiles) {
		File file = convert(uploadedFile);
		keyName = uploadedFile.getOriginalFilename();
		s3client.putObject(new PutObjectRequest(bucketName, keyName, file)
			.withCannedAcl(CannedAccessControlList.PublicRead));
		System.out.println(file.exists());
	    }

	} catch (AmazonServiceException ase) {
	    return false;
	} catch (AmazonClientException ace) {

	    return false;
	}
	System.out.println(keyName);
	return true;
    }

    private File convert(MultipartFile file) throws IOException {
	File convFile = new File(file.getOriginalFilename());
	convFile.createNewFile();
	FileOutputStream fos = new FileOutputStream(convFile);
	fos.write(file.getBytes());
	fos.close();
	return convFile;
    }
}
