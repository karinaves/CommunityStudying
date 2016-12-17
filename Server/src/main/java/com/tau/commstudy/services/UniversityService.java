package com.tau.commstudy.services;

import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.University;
import com.tau.commstudy.entities.daos.UniversityDao;
import com.tau.commstudy.exceptions.TableArgumentException;

public class UniversityService {

    @Autowired
    private UniversityDao universityDao;
    
    /**
     * Creates and Saves to DB a University entity. 
     * 
     * @param  String name- not null, Set<Faculty> faculties - not null, Long universityNum, String address
     * @return the saved Course entity
     * @throws ArgumentException if name or universities is null
     */
    public University add( String name, Set<Faculty> faculties, Long universityNum, String address) throws TableArgumentException {
	try{
	    University university = new University();
	    university.setName(name);
	    university.setFaculties(faculties);
	    university.setUniversityNum(universityNum);
	    university.setAddress(address);
	    return universityDao.save(university);
	}
	catch( ValidationException e){
	    if (name == null)
		throw new TableArgumentException(University.class,"name","null");
	    else
		throw new TableArgumentException(University.class,"universities","null");
	    
	}
    }

    
    
    /**
     * Adds to University Entity A Faculty entity . 
     * 
     * @param  Long id, Faculty faculty
     * @return the saved University entity
     * @throws TableArgumentException if id or university is null   
     */
    public University addFaculty(Long id, Faculty faculty) throws TableArgumentException{
	if (faculty == null){
	    throw new TableArgumentException(University.class,"Faculty", "null");
	}
	University university = get(id);
	university.getFaculties().add(faculty);
	return university;
    }
    
    /**
     * Retrieves an entity by its id.
     * 
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws TableArgumentException if {@code id} is {@literal null}
     */
     public University get(Long id) throws TableArgumentException{
 	try{
 	    return universityDao.findOne(id);
 	}
 	catch (IllegalArgumentException e){
 		throw new TableArgumentException(University.class,"id", "null");
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
 	    universityDao.delete(id);
 	}
 	catch (IllegalArgumentException e) {
 	    throw new TableArgumentException(University.class,"id", "null");
 		    
 	}
     }
     
}
