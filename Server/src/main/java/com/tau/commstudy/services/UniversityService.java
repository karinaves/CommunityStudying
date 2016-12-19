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
     * @param University
     *            object
     * @return the saved University entity
     * @throws ValidationException
     *             if not saved
     */
    public University add(University university) throws ValidationException {
	return universityDao.save(university);
    }

    /**
     * Retrieves an entity by its id.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @throws IllegalArgumentException
     *             if {@code id} is {@literal null}
     */
    public University get(Long id) throws IllegalArgumentException {
	return universityDao.findOne(id);
    }

    /**
     * Deletes an entity by its id.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return true if deleted or not in DB
     * @throws IllegalArgumentException
     *             if {@code id} is {@literal null}
     */
    public boolean delete(Long id) throws IllegalArgumentException {
	universityDao.delete(id);
	return true;
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
    public University addFaculty(Long id, Long facultyId) throws ValidationException {
	University university = get(id);
	Faculty faculty = facultyService.get(facultyId);
	university.getFaculties().add(faculty);
	universityDao.save(university);
	return university;
    }

}
