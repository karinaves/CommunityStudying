package com.tau.commstudy.services;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.University;
import com.tau.commstudy.entities.daos.UniversityDao;
import com.tau.commstudy.exceptions.TableArgumentException;

@Service
public class UniversityService {

    @Autowired
    private UniversityDao universityDao;
    @Autowired
    private FacultyService facultyService;

    /**
     * Creates and Saves to DB a University entity.
     * 
     * @param String
     *            name- not null, Long universityNum, String address
     * @return the saved Course entity
     * @throws ArgumentException
     *             if name is null
     */
    public University add(String name, Long universityNum, String address) throws TableArgumentException {
	try {
	    University university = new University();
	    university.setName(name);
	    university.setUniversityNum(universityNum);
	    university.setAddress(address);
	    return universityDao.save(university);
	} catch (ValidationException e) {
	    e.printStackTrace();
	    throw new TableArgumentException(University.class, "name", "null");

	}
    }

    /**
     * Adds to University Entity A Faculty entity .
     * 
     * @param Long
     *            id, Long facultyId
     * @return the saved University entity
     * @throws TableArgumentException
     *             if id or universityId is null
     */
    public University addFaculty(Long id, Long facultyId) throws TableArgumentException {
	University university = get(id);
	Faculty faculty = facultyService.get(facultyId);
	university.getFaculties().add(faculty);
	universityDao.save(university);
	return university;
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
    public University get(Long id) throws TableArgumentException {
	try {
	    return universityDao.findOne(id);
	} catch (IllegalArgumentException e) {
	    throw new TableArgumentException(University.class, "id", "null");
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
	    universityDao.delete(id);
	} catch (IllegalArgumentException e) {
	    throw new TableArgumentException(University.class, "id", "null");

	}
    }

}
