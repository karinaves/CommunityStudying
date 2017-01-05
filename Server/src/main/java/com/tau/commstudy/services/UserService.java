package com.tau.commstudy.services;

import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tau.commstudy.beans.GoogleValidateInfo;
import com.tau.commstudy.entities.Comment;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.Post;
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

    public User get(String idTokenString) throws UnauthorizesException {
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
	}
	logger.debug("******************************************");
	return user;
    }

    public List<Faculty> getFaculties(String idTokenString) throws UnauthorizesException {
	List<Faculty> faculties = null;
	return faculties;
    }

    // -----------------Auxiliary functions-----------------------

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
	userDao.save(user);

	return user;
    }

    public Boolean updateUserCourses(Set<Long> coursesIds, String userTokenId) {
	User user = getOrCreate(userTokenId);
	user.setCourses(courseService.getAllById(coursesIds));
	userDao.save(user);
	return true;
    }

    public Boolean likePost(Post post) {
	User user = post.getUser();
	if (user.getUserRating() == null) {
	    user.setUserRating(1);
	} else {
	    user.setUserRating(user.getUserRating() + 1);
	}
	userDao.save(user);
	return true;

    }

    public Boolean likeComment(Comment comment) {
	User user = comment.getUser();
	if (user.getUserRating() == null) {
	    user.setUserRating(1);
	} else {
	    user.setUserRating(user.getUserRating() + 1);
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

}
