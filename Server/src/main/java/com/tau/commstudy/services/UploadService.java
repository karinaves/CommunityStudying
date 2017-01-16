package com.tau.commstudy.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.tau.commstudy.exceptions.UnauthorizesException;

@Service
public class UploadService {

    @Value("${awsKeys.file}")
    private String accessKeyFile;

    @Autowired
    UserService userService;

    public List<String> uploadFiles(MultipartFile[] uploadingFiles, String userTokenId)
	    throws IOException, UnauthorizesException, IllegalArgumentException {
	// assert that user is logged in - or throws Exception
	userService.get(userTokenId);

	long maxUploadSizeInMb = 10 * 1024 * 1024;
	String[] fileTypes = { "png", "docx", "doc", "txt", "pdf", "jpg", "bmp" };
	String[] keys = getKeysFromFile();
	String pathPrefix = "https://s3-eu-west-1.amazonaws.com/study-buddy-files/";
	String accessKeyID = keys[0];
	String secretAccessKey = keys[1];
	String fileName = "";
	List<String> result = new ArrayList<>();
	AWSCredentials credentials = new BasicAWSCredentials(accessKeyID, secretAccessKey);
	String bucketName = "study-buddy-files";
	try {
	    AmazonS3 s3client = new AmazonS3Client(credentials);
	    for (MultipartFile uploadedFile : uploadingFiles) {
		Boolean fileType = false;
		if (uploadedFile.getSize() > maxUploadSizeInMb) {
		    return null;
		}
		for (String suffix : fileTypes) {
		    if (uploadedFile.getOriginalFilename().endsWith(suffix)) {
			fileType = true;
			break;
		    }
		}
		if (!fileType) {
		    return null;
		}
		File file = convert(uploadedFile);
		fileName = uploadedFile.getOriginalFilename();
		s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
			.withCannedAcl(CannedAccessControlList.PublicRead));
		result.add(pathPrefix + fileName);
		file.delete();
	    }

	} catch (AmazonServiceException ase) {
	    result.clear();
	    return null;
	} catch (AmazonClientException ace) {
	    result.clear();
	    return null;
	}
	return result;
    }

    private String[] getKeysFromFile() {
	String[] keys = new String[2];
	BufferedReader fileReader = null;

	final String DELIMITER = ",";
	try {
	    String line = "";
	    fileReader = new BufferedReader(new FileReader(accessKeyFile));
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
