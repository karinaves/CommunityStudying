package com.tau.commstudy.services;

import java.net.URL;
import java.util.Calendar;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tau.commstudy.beans.GoogleValidateInfo;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.UserDao;
import com.tau.commstudy.exceptions.UnauthorizesException;

@Service
public class UserService {
    public static Logger logger = Logger.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EmailService emailService;

    public User get(String idTokenString) throws UnauthorizesException, IllegalArgumentException {
	GoogleValidateInfo google = verifyUserIdToken(idTokenString);
	User user = userDao.findByGoogleId(google.getSub());
	return user;
    }

    /**
     * Verify the user's id token. In case the validation successes, checks if
     * the user exists. Create a new user in case there is no user with its
     * google id.
     * 
     * @param String
     *            idTokenString - the google id token of the user.
     * @return user or a new user with the relevant google id. if In case
     *         validation failed, return null.
     *
     */

    public User getOrCreate(String idTokenString) throws UnauthorizesException {
	User user = get(idTokenString);
	// creating new User
	if (user == null) {
	    user = createUser(idTokenString);
	    try {
		emailService.emailNewUser(user.getEmail(), user.getFirstName());
	    } catch (Exception ex) {
		System.out.println("Error sending email: " + ex.toString());
	    }
	}
	return user;
    }

    public User login(String idTokenString) throws UnauthorizesException {
	User user = getOrCreate(idTokenString);
	user.setLastLogin(Calendar.getInstance());
	return user;
    }

    /**
     * Authenticate user login. verify the user's id token.
     * 
     * @param String
     *            idTokenString - the google id token of the user.
     * @return A new GoogleValidateInfo of the user in case validation succeed,
     *         otherwise returns null.
     *
     */

    private GoogleValidateInfo verifyUserIdToken(String idTokenString) throws UnauthorizesException {
	try {
	    URL url = new URL("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + idTokenString);
	    ObjectMapper mapper = new ObjectMapper();

	    // Json format from the URL to GoogleValidateInfo instance
	    GoogleValidateInfo validateInfo = mapper.readValue(url, GoogleValidateInfo.class);
	    return validateInfo;
	} catch (Exception ex) {
	    throw new UnauthorizesException();
	}
    }

    private User createUser(String idTokenString) {
	GoogleValidateInfo google = verifyUserIdToken(idTokenString);
	User user = new User();
	Calendar cal = Calendar.getInstance();
	cal.setTime(Calendar.getInstance().getTime());

	user.setEmail(google.getEmail());
	user.setFirstName(google.getGiven_name().substring(0, 1).toUpperCase() + google.getGiven_name().substring(1));
	user.setLastName(google.getFamily_name().substring(0, 1).toUpperCase() + google.getFamily_name().substring(1));
	user.setGoogleId(google.getSub());
	user.setPictureUrl(google.getPicture());
	user.setCreated(cal);
	user.setEmailSubscribed(true);
	userDao.save(user);

	return user;
    }

    public Boolean updateUserFaculties(Set<Faculty> faculties, String userTokenId) {
	User user = getOrCreate(userTokenId);
	user.setFaculties(faculties);
	userDao.save(user);
	return true;
    }

    public Boolean addUserFaculties(Faculty faculty, String userTokenId) {
	User user = get(userTokenId);
	user.getFaculties().add(faculty);
	userDao.save(user);
	return true;
    }

    public Boolean updateUserCourses(Set<Course> courses, String userTokenId) {
	User user = getOrCreate(userTokenId);
	user.setCourses(courses);
	userDao.save(user);
	return true;
    }

    public Boolean addUserCourse(Course course, String userTokenId) {
	User user = get(userTokenId);
	user.getCourses().add(course);
	userDao.save(user);
	return true;
    }

    public Boolean incOrDecRank(User user, int num) {
	if (user == null)
	    return false;
	if (user.getUserRating() == null) {
	    user.setUserRating(num);
	} else {
	    user.setUserRating(user.getUserRating() + num);
	}
	userDao.save(user);
	return true;

    }

    // --------------- Aid Functions ----------------

    public boolean isAuthorizedEditUser(User owner, User editor) {
	if (owner == null || editor == null)
	    return false;
	return owner.getId().equals(editor.getId());
    }

    public boolean isAdminUser(String userTokenId) {
	try {
	    if (userTokenId == null)
		return false;
	    return get(userTokenId).isAdmin();
	} catch (Exception e) {
	    return false;
	}
    }

    public boolean assertAdminUser(String userTokenId) {
	User user = this.get(userTokenId);
	if (!user.isAdmin())
	    throw new UnauthorizesException();
	return true;
    }

}
