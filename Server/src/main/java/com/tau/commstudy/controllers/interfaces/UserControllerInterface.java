package com.tau.commstudy.controllers.interfaces;

import java.util.Set;

import org.springframework.web.bind.annotation.RequestBody;

import com.tau.commstudy.entities.Course;

public interface UserControllerInterface {

    // Path should be "user/updateCourses?userTokenId={id}" method = POST
    // return true if updated successfully, throw exception if not
    public Boolean updateUserCourses(@RequestBody Set<Course> courses, String userTokenId);

}
