package com.tau.commstudy.services;


import javax.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.daos.CourseDao;
import com.tau.commstudy.exceptions.TableArgumentException;



@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    /**
     * Creates and Saves to DB a Course entity. 
     * 
     * @param Faculty faculty, String name - not Null, Long courseUniversityId
     * @return the saved Course entity
     * @throws TableArgumentException if name is null
     */
    public Course add(Faculty faculty, String name, Long courseUniversityId) throws TableArgumentException {
	try{
	    Course course = new Course();
	    course.setFaculty(faculty);
	    course.setName(name);
	    course.setCourseUniversityId(courseUniversityId);
	    return courseDao.save(course);
	}
	catch( ValidationException e){
	    throw new TableArgumentException(Course.class,"name","null");
	    
	}
    }
    
    /**
    * Retrieves an entity by its id.
    * 
    * @param id must not be {@literal null}.
    * @return the entity with the given id or {@literal null} if none found
    * @throws TableArgumentException if {@code id} is {@literal null}
    */
    public Course getById(Long id) throws TableArgumentException{
	try{
	    return courseDao.findOne(id);
	}
	catch (IllegalArgumentException e){
		throw new TableArgumentException(Course.class,"id", "null");
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
	    courseDao.delete(id);
	}
	catch (IllegalArgumentException e) {
	    throw new TableArgumentException(Course.class,"id", "null");
		    
	}
    }
}
