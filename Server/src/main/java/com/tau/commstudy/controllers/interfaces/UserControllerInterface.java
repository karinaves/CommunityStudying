package com.tau.commstudy.controllers.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerInterface {

    // Path should be "user/updateCourses?userTokenId={id}" method = POST
    // return true if updated successfully, throw exception if not
    public Boolean updateUserCourses(@RequestBody List<Long> coursesIds, String userTokenId);

}
