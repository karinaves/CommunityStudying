package com.tau.commstudy.services;

import java.util.Collections;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.University;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.FacultyDao;
import com.tau.commstudy.exceptions.TableArgumentException;
import com.tau.commstudy.exceptions.UnauthorizesException;

@Service
public class FacultyService {

    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UniversityService universityService;

    /**
     * Creates and Saves to DB a Faculty entity.
     * 
     * @param String
     *            name- not null, Long facultyUniversityId
     * @return the saved Faculty entity
     * @throws ArgumentException
     *             if name is null
     */
    public Faculty add(String name, Long facultyUniversityId) throws TableArgumentException {
	try {
	    Faculty faculty = new Faculty();
	    faculty.setName(name);
	    faculty.setFacultyUniversityId(facultyUniversityId);
	    return facultyDao.save(faculty);
	} catch (ValidationException e) {
	    throw new TableArgumentException(Faculty.class, "name", "null");

	}
    }

    /**
     * Adds to Faculty Entity A University entity that he belongs to.
     * 
     * @param Long
     *            id, University university
     * @return the saved Faculty entity
     * @throws TableArgumentException
     *             if id or universityId is null
     */
    public Faculty addUniversity(Long id, Long universityId) throws TableArgumentException {
	Faculty faculty = get(id);
	University university = universityService.get(universityId);
	faculty.getUniversities().add(university);
	return faculty;
    }

    /**
     * Retrieves an entity by its id.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws TableArgumentException
     *             if {@code id} is {@literal null}
     */
    public Faculty get(Long id) throws TableArgumentException {
	try {
	    return facultyDao.findOne(id);
	} catch (IllegalArgumentException e) {
	    throw new TableArgumentException(Faculty.class, "id", "null");
	}
    }

    /**
     * Deletes an entity by its id.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return void if deleted or not in DB
     * @throws TableArgumentException
     *             if {@code id} is {@literal null}
     */
    public void delete(Long id) throws TableArgumentException {
	try {
	    facultyDao.delete(id);
	} catch (IllegalArgumentException e) {
	    throw new TableArgumentException(Faculty.class, "id", "null");

	}
    }

    public List<Faculty> getAllFaculties(String idTokenString) throws UnauthorizesException {
	List<Faculty> faculties = null;
	User user = userService.getOrCreate(idTokenString); // can throw
							    // UnauthorizesException
	faculties = Lists.newArrayList(facultyDao.findAll());
	// no faculties Error to handle
	Collections.sort(faculties); // sort by faculty name
	return faculties;

    }

}
