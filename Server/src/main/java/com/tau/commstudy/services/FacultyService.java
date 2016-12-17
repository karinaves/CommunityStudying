package com.tau.commstudy.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.User;
import com.google.common.collect.Lists;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.University;
import com.tau.commstudy.entities.daos.FacultyDao;
import com.tau.commstudy.exceptions.TableArgumentException;
import com.tau.commstudy.exceptions.UnauthorizesException;

@Service
public class FacultyService {

    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private UserService userService;
   

    /**
     * Creates and Saves to DB a Faculty entity. 
     * 
     * @param  String name- not null, Set<University> universities - not null, Integer facultyUniversityId
     * @return the saved Course entity
     * @throws ArgumentException if name or universities is null
     */
    public Faculty add( String name, Set<University> universities, Integer facultyUniversityId) throws TableArgumentException {
	try{
	    Faculty faculty = new Faculty();
	    faculty.setName(name);
	    faculty.setUniversities(universities);
	    faculty.setFacultyUniversityId(facultyUniversityId);
	    return facultyDao.save(faculty);
	}
	catch( ValidationException e){
	    if (name == null)
		throw new TableArgumentException(Course.class,"name","null");
	    else
		throw new TableArgumentException(Course.class,"universities","null");
	    
	}
    }
    
    /**
     * Adds to Faculty Entity A University entity that he belongs to. 
     * 
     * @param  Long id, University universities , Integer facultyUniversityId
     * @return the saved Faculty entity
     * @throws TableArgumentException if id or university is null   
     */
    public Faculty addUniversity(Long id, University university) throws TableArgumentException{
	if (university == null){
	    throw new TableArgumentException(Faculty.class,"University", "null");
	}
	Faculty faculty = get(id);
	faculty.getUniversities().add(university);
	return faculty;
    }
    
  
    /**
    * Retrieves an entity by its id.
    * 
    * @param id must not be {@literal null}.
    * @return the entity with the given id or {@literal null} if none found
    * @throws TableArgumentException if {@code id} is {@literal null}
    */
    public Faculty get(Long id) throws TableArgumentException{
	try{
	    return facultyDao.findOne(id);
	}
	catch (IllegalArgumentException e){
		throw new TableArgumentException(Faculty.class,"id", "null");
	}
    }
    
    /**
     * Deletes an entity by its id.
     * 
     * @param id must not be {@literal null}.
     * @return void if deleted or not in DB
     * @throws TableArgumentException if {@code id} is {@literal null}
     */  
    public void delete(Long id) throws TableArgumentException {
	try {
	    facultyDao.delete(id);
	}
	catch (IllegalArgumentException e) {
	    throw new TableArgumentException(Faculty.class,"id", "null");
		    
	}
    }

    
       
    
    public List<Faculty> getAllFaculties(String idTokenString) throws UnauthorizesException{
	List<Faculty> faculties = null;
	User user = userService.getOrCreateUser(idTokenString); //can throw UnauthorizesException
	faculties = Lists.newArrayList(facultyDao.findAll());
	//no faculties Error to handle
	Collections.sort(faculties); //sort by faculty name
	return faculties;
	
	
	
    }
    

    
}
