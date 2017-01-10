package com.tau.commstudy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.beans.UserAllData;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.University;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.FacultyDao;
import com.tau.commstudy.exceptions.UnauthorizesException;

@Service
public class FacultyService {

    @Autowired
    private FacultyDao dao;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UserService userService;

    /**
     * Creates and Saves to DB a Faculty entity.
     * 
     * @param Faculty
     *            object
     * @return the saved Faculty entity
     * @throws ValidationException
     *             if not saved
     */
    public Faculty add(Faculty faculty) throws ValidationException {
	return dao.save(faculty);
    }

    /**
     * Retrieves an entity by its id.
     * 
     * @param id
     *            must not be {@literal null}.
     * @return the entity with the given id or {@literal null} if none found
     * @return the saved Faculty entity
     * @throws IllegalArgumentException
     *             if {@code id} is {@literal null}
     */
    public Faculty get(Long id) throws IllegalArgumentException {
	return dao.findOne(id);
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
	dao.delete(id);
	return true;
    }

    /**
     * Adds to Faculty Entity A University entity that he belongs to.
     * 
     * @param Long
     *            id, University university
     * @return the saved Faculty entity
     * @throws ValidationException
     *             if id or universityId is null
     */
    public boolean addUniversity(Long id, Long universityId) throws ValidationException {
	Faculty faculty = get(id);
	University university = universityService.get(universityId);
	faculty.setUniversity(university);
	dao.save(faculty);
	return true;
    }

    public UserAllData<Faculty> getUserAndAllData(String idTokenString) throws UnauthorizesException {
	UserAllData<Faculty> data = new UserAllData<>();
	data.setAllData(dao.findAllByOrderByName());
	try {
	    User user = userService.get(idTokenString); // can throw
	    Set<Faculty> faculties = new HashSet<>();

	    // finds all faculties of user
	    for (Course course : user.getCourses()) {
		faculties.add(course.getFaculty());
	    }
	    List<Faculty> userFaculties = new ArrayList<>(faculties);
	    // sorts user information
	    Collections.sort(userFaculties, new Comparator<Faculty>() {
		@Override
		public int compare(Faculty o1, Faculty o2) {
		    return o1.getName().compareTo(o2.getName());
		}

	    });
	    data.setUserData(userFaculties);
	}
	// returns data without user profile information
	catch (Exception e) {
	    return data;
	}
	return data;

    }

    // If user will have Faculties mapping table
    public UserAllData<Faculty> getUserAndAllData2(String idTokenString) throws UnauthorizesException {
	UserAllData<Faculty> data = new UserAllData<>();
	data.setAllData(dao.findAllByOrderByName());
	User user = userService.get(idTokenString); // can throw
	data.setUserData(new ArrayList<>(user.getFaculties()));
	return data;

    }

}
