package com.tau.commstudy.controllers.interfaces;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface FileControllerInterface {

    // path should be "file/addToPost?postId=&userTokenId="
    public Boolean addFilesToPost(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, Long postId,
	    String userTokenId);

    // path should be "file/addToComment?commentId=&userTokenId="
    public Boolean addFilesToComment(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, Long commentId,
	    String userTokenId);

    // path should be "file/addToTest?testId=&userTokenId="
    public Boolean addFilesToTest(@RequestParam("uploadingFiles") MultipartFile[] uploadingFiles, Long testId,
	    String userTokenId);

}
