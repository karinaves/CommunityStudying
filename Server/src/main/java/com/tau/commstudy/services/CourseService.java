package com.tau.commstudy.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.beans.UserAllData;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.CourseDao;

@Service
public class CourseService {

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseDao courseDao;

    /**
     * Creates and Saves to DB a Faculty entity.
     * 
     * @param Faculty
     *            object
     * @return the saved Course entity
     * @throws ValidationException
     *             if not saved
     */
    public Course add(Course course) throws ValidationException {
	return courseDao.save(course);
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
    public Course get(Long id) throws IllegalArgumentException {
	return courseDao.findOne(id);
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
	courseDao.delete(id);
	return true;
    }

    /**
     * Adds to Faculty Entity A Course entity .
     * 
     * @param Long
     *            id, University university
     * @return true if saved Faculty entity
     * @throws ValidationException
     *             if id or universityId is null
     */
    public boolean addFaculty(Long id, Long facultyId) throws ValidationException {
	Course course = get(id);
	Faculty faculty = facultyService.get(facultyId);
	course.setFaculty(faculty);
	courseDao.save(course);
	return true;

    }

    public UserAllData<Course> getUserAndAllData(String idTokenString, Long facultyId) {
	UserAllData<Course> data = new UserAllData<>();
	data.setAllData(courseDao.findByFaculty_IdOrderByNameHebrew(facultyId));
	try {
	    User user = userService.get(idTokenString); // can throw
	    List<Course> userCourses = new ArrayList<>(user.getCourses());
	    // sorts user information
	    Collections.sort(userCourses, new Comparator<Course>() {
		@Override
		public int compare(Course o1, Course o2) {
		    if (o1.getNameHebrew() != null && o2.getNameHebrew() != null)
			return o1.getNameHebrew().compareTo(o2.getNameHebrew());
		    if (o1.getNameEnglish() != null && o2.getNameEnglish() != null)
			return o1.getNameEnglish().compareTo(o2.getNameEnglish());
		    return 0;
		}

	    });

	    data.setUserData(userCourses);
	}
	// returns data without user profile information
	catch (Exception e) {
	    return data;
	}
	return data;

    }

    public Set<Course> getAllById(Set<Long> coursesIds) {
	return courseDao.findByIdIn(coursesIds);
    }

}
