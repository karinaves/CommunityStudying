package com.tau.commstudy.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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

	String[] keys = getKeysFromFile();
	String accessKeyID = keys[0];
	String secretAccessKey = keys[1];

	AWSCredentials credentials = new BasicAWSCredentials(accessKeyID, secretAccessKey);
	String bucketName = "study-buddy-files";
	try {
	    AmazonS3 s3client = new AmazonS3Client(credentials);
	    for (MultipartFile uploadedFile : uploadingFiles) {
		File file = convert(uploadedFile);
		String keyName = uploadedFile.getOriginalFilename();
		s3client.putObject(new PutObjectRequest(bucketName, keyName, file)
			.withCannedAcl(CannedAccessControlList.PublicRead));
		System.out.println(file.exists());
	    }

	} catch (AmazonServiceException ase) {
	    return false;
	} catch (AmazonClientException ace) {

	    return false;
	}
	return true;
    }

    private String[] getKeysFromFile() {
	String csvFilePath = "C:\\wamp\\www\\accessKeys\\accessKeys.csv";
	String[] keys = new String[2];
	BufferedReader fileReader = null;

	final String DELIMITER = ",";
	try {
	    String line = "";
	    fileReader = new BufferedReader(new FileReader(csvFilePath));
	    int i = 0;
	    while ((line = fileReader.readLine()) != null) {
		String[] row = line.split(DELIMITER);
		keys[i] = row[1];
		i++;
	    }
	    try {
		fileReader.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return keys;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
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
