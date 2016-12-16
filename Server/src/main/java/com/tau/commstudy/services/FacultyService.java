package com.tau.commstudy.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.daos.FacultyDao;

@Service
public class FacultyService {

    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private UserService userService;
   
    
    public List<Faculty> getAllFaculties(String idTokenString) throws Exception{
	List<Faculty> faculties = new ArrayList<>();
	User user = userService.getOrCreateUser(idTokenString);
	return faculties;
	
	
    }
    
}
