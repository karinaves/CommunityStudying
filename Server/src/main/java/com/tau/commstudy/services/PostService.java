package com.tau.commstudy.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysema.query.BooleanBuilder;
import com.tau.commstudy.beans.NewFileBean;
import com.tau.commstudy.beans.NewPostBean;
import com.tau.commstudy.beans.PostCriteria;
import com.tau.commstudy.beans.UpdatePostBean;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.Test;
import com.tau.commstudy.entities.TestQuestion;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.entities.daos.PostDao;
import com.tau.commstudy.exceptions.UnauthorizesException;
import com.tau.commstudy.predicates.PostPredicates;

@Service
public class PostService {

    @Autowired
    private PostDao dao;

    @Autowired
    private FileService fileService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TestService testService;

    @Autowired
    private TestQuestionService questionService;

    @Autowired
    private UserService userService;

    public Post createPost(@RequestBody Post newPost) {
	try {
	    newPost.setTime(Calendar.getInstance());
	    return dao.save(newPost);
	} catch (Exception ex) {
	    System.out.println("Error creating the post:" + ex.toString());
	    return null; // "Error creating the post:" + ex.toString();
	}

	// return "Post succesfully saved!";
    }

    public boolean delete(Long id, String userTokenId) throws Exception {
	Post post = dao.findOne(id);
	User owner = post.getUser();
	User editor = userService.get(userTokenId);
	if (userService.isAuthorizedEditUser(owner, editor) || editor.isAdmin()) {
	    dao.delete(id);
	    return true;
	}
	return false;
    }

    /**
     * if the parameter was not provided, returns corresponding message
     */
    @RequestMapping(value = "/delete", params = {})
    public String delete() {
	return "long parameter 'id' needs to be provided";
    }

    /**
     * increases the number of votes of post and number of votes of user by 1
     */
    public Boolean like(Long id) {
	try {
	    Post post = dao.findOne(id);
	    post.setVotes(post.getVotes() + 1);
	    dao.save(post);
	    userService.incOrDecRank(post.getUser(), 1);
	    return true;

	} catch (Exception ex) {
	    return false;
	}
    }

    public Boolean dislike(Long id) {
	try {
	    Post post = dao.findOne(id);
	    post.setVotes(post.getVotes() - 1);
	    dao.save(post);
	    userService.incOrDecRank(post.getUser(), -1);
	    return true;

	} catch (Exception ex) {
	    return false;
	}
    }

    public Iterable<Post> getAll() {
	return dao.findAll();
    }

    public Post getById(long id) {
	return dao.findOne(id);
    }

    public Iterable<Post> getByUser(User user) {
	return dao.findByUser(user);
    }

    public List<Post> getByCourse(Course course) {
	return dao.findByTestQuestion_Test_CourseOrderByTimeDesc(course);
    }

    public List<Post> getByTest(Test test) {
	return dao.findByTestQuestion_TestOrderByTimeDesc(test);
    }

    public List<Post> getByYear(Course course, Integer year) {
	return dao.findByTestQuestion_Test_CourseAndTestQuestion_Test_YearOrderByTimeDesc(course, year);
    }

    private List<Post> getBySemester(Course course, Integer year, Character semester) {
	return dao.findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_CourseOrderByTimeDesc(
		year, semester, course);
    }

    public List<Post> getByMoed(Course course, Integer year, Character semester, Character moed) {
	Test test = testService.getByMoed(course, year, semester, moed);
	return getByTest(test);
    }

    public List<Post> getByTestAndNumber(Test test, Integer number) {
	TestQuestion question = questionService.getByTestAndNumber(test, number);
	return getByTestQuestion(question);
    }

    public boolean checkByMoedK(Integer year, Character semester, Character moed, Course course) {
	List<Post> posts = dao
		.findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
			year, semester, moed, course);
	if (posts.size() == 0)
	    return false;
	return true;
    }

    /**
     * check if posts for this test moed already exist
     *
     * @param bean
     * @return TRUE or FALSE
     */

    public boolean checkByMoed(PostCriteria criteria) {
	Course course = courseService.get(criteria.getCourseId());
	List<Post> posts = dao
		.findByTestQuestion_Test_YearAndTestQuestion_Test_SemesterAndTestQuestion_Test_MoedAndTestQuestion_Test_CourseOrderByTimeDesc(
			criteria.getYear(), criteria.getSemester(), criteria.getMoed(), course);
	if (posts == null || posts.isEmpty())
	    return false;
	return true;
    }

    /**
     * finds all posts connected to a specific testQuestion.
     *
     * @param TestQuestion
     * @return List of posts
     */
    public List<Post> getByTestQuestion(TestQuestion question) {
	return dao.findByTestQuestionOrderByTimeDesc(question);
    }

    /**
     * finds all posts connected to a question. If no question number given,
     * return by test
     *
     * @param criteria
     *            the used fields are: -course -year -semester -moed
     *            -questionNumber
     * @return List of posts
     */
    public List<Post> search3(PostCriteria criteria) {
	// if only year given - find posts for this year's tests
	Course course = courseService.get(criteria.getCourseId());

	if (criteria.getMoed() == null) {
	    if (criteria.getSemester() == null) {
		if (criteria.getYear() == null) {
		    // return by course
		    return getByCourse(course);
		}
		// return by year
		return getByYear(course, criteria.getYear());
	    }
	    // return by semester
	    return getBySemester(course, criteria.getYear(), criteria.getSemester());
	}

	// return by test
	Test test = testService.getByMoed(course, criteria.getYear(), criteria.getSemester(), criteria.getMoed());
	if (test == null) // no such test in the table
	    return null;

	if (criteria.getQuestionNumber() == null) // if no number was given
	    return getByTest(test);

	return getByTestAndNumber(test, criteria.getQuestionNumber());
    }

    public Long count(PostCriteria criteria) {
	BooleanBuilder searchCriteria = getSearchCriteria(criteria);
	return dao.count(searchCriteria);
    }

    /**
     * finds all posts by the given parameters the function uses Querydsl
     * queries
     *
     * @param criteria
     *            the fields are: -faculty -course -year -semester -moed
     *            -questionNumber
     * @param page
     * @param size
     * @return List of corresponding posts
     */
    public List<Post> search(PostCriteria criteria, int page, int size) {

	BooleanBuilder searchCriteria = getSearchCriteria(criteria);
	return searchBy(searchCriteria, page, size);
    }

    public List<Post> searchFreeText(String text, int page, int size) {

	BooleanBuilder searchCriteria = getFreeTextSearchCriteria(text);
	return searchBy(searchCriteria, page, size);
    }

    private BooleanBuilder getSearchCriteria2(PostCriteria criteria) {
	BooleanBuilder searchCriteria = new BooleanBuilder();

	// add tags parameter to search
	if (criteria.getTags() != null)
	    searchCriteria.and(PostPredicates.byTags(criteria.getTags()));

	// add userId parameter to search
	if (criteria.getUserId() != null)
	    searchCriteria.and(PostPredicates.byUserId(criteria.getUserId()));

	// free text can be the only search param
	if (criteria.getInContentText() != null && !criteria.getInContentText().equals(""))
	    searchCriteria.and(PostPredicates.byContentOrTitleLike(criteria.getInContentText()));

	if (criteria.getFacultyId() == null)
	    // return all
	    return searchCriteria;

	// add faculty parameter to search
	searchCriteria.and(PostPredicates.byFaculty(criteria.getFacultyId()));
	if (criteria.getCourseId() == null)
	    // return by faculty
	    return searchCriteria;

	// add course parameter to search
	searchCriteria.and(PostPredicates.byCourse(criteria.getCourseId()));
	if (criteria.getYear() == null)
	    // return by course
	    return searchCriteria;

	// add year parameter to search
	searchCriteria.and(PostPredicates.byYear(criteria.getYear()));
	if (criteria.getSemester() == null)
	    // return by year
	    return searchCriteria;

	// add semester parameter to search
	searchCriteria.and(PostPredicates.bySemester(criteria.getSemester()));
	if (criteria.getMoed() == null)
	    // return by semester
	    return searchCriteria;

	// add moed parameter to search
	searchCriteria.and(PostPredicates.byMoed(criteria.getMoed()));
	if (criteria.getQuestionNumber() == null)
	    // return by moed
	    return searchCriteria;

	// add question number parameter to search
	searchCriteria.and(PostPredicates.byQuestionNumber(criteria.getQuestionNumber()));

	// return by question number
	return searchCriteria;

    }

    private BooleanBuilder getFreeTextSearchCriteria(String text) {
	BooleanBuilder searchCriteria = new BooleanBuilder();
	int i = 0;

	String[] words = text.split(" +");

	while (i < words.length) {

	    // if it's a year, search both in year field and in content
	    if (words[i].matches("\\d+") && Integer.parseInt(words[i]) > 1950 && Integer.parseInt(words[i]) < 2100) {
		int year = Integer.parseInt(words[i]);
		searchCriteria.andAnyOf(PostPredicates.byYear(year), PostPredicates.byContentOrTitleLike(words[i]));
	    }

	    // check if it's moed
	    else if (words[i].equalsIgnoreCase("מועד") && i + 1 < words.length) {
		if (words[i + 1].equalsIgnoreCase("א") || words[i + 1].equalsIgnoreCase("א'")) {
		    searchCriteria.and(PostPredicates.byMoed('A'));
		    i++;
		} else if (words[i + 1].equalsIgnoreCase("ב") || words[i + 1].equalsIgnoreCase("ב'")) {
		    searchCriteria.and(PostPredicates.byMoed('B'));
		    i++;
		} else if (words[i + 1].equalsIgnoreCase("ג") || words[i + 1].equalsIgnoreCase("ג'")) {
		    searchCriteria.and(PostPredicates.byMoed('C'));
		    i++;
		} else
		    searchCriteria.andAnyOf(PostPredicates.byCourseName(words[i]),
			    PostPredicates.byContentOrTitleLike(words[i]));
	    }

	    // check if it's semester
	    else if (words[i].equalsIgnoreCase("סמסטר") && i + 1 < words.length) {
		if (words[i + 1].equalsIgnoreCase("א") || words[i + 1].equalsIgnoreCase("א'")) {
		    searchCriteria.and(PostPredicates.bySemester('A'));
		    i++;
		} else if (words[i + 1].equalsIgnoreCase("ב") || words[i + 1].equalsIgnoreCase("ב'")) {
		    searchCriteria.and(PostPredicates.bySemester('B'));
		    i++;
		} else if (words[i + 1].equalsIgnoreCase("קיץ")) {
		    searchCriteria.and(PostPredicates.bySemester('C'));
		    i++;
		} else
		    searchCriteria.andAnyOf(PostPredicates.byCourseName(words[i]),
			    PostPredicates.byContentOrTitleLike(words[i]));
	    }

	    else if (words[i].length() > 1) {
		searchCriteria.andAnyOf(PostPredicates.byCourseName(words[i]),
			PostPredicates.byContentOrTitleLike(words[i]));
	    }

	    i++;
	}
	return searchCriteria;
    }

    private BooleanBuilder getSearchCriteria(PostCriteria criteria) {
	BooleanBuilder searchCriteria = new BooleanBuilder();

	// add tags parameter to search
	if (criteria.getTags() != null)
	    searchCriteria.and(PostPredicates.byTags(criteria.getTags()));

	// add userId parameter to search
	if (criteria.getUserId() != null)
	    searchCriteria.and(PostPredicates.byUserId(criteria.getUserId()));

	// free text can be the only search param
	if (criteria.getInContentText() != null && !criteria.getInContentText().equals(""))
	    searchCriteria.and(PostPredicates.byContentOrTitleLike(criteria.getInContentText()));
	// searchCriteria.and(getFreeTextSearchCriteria(criteria.getInContentText()));

	// add faculty parameter to search
	searchCriteria.and(PostPredicates.byFaculty(criteria.getFacultyId()));

	// add course parameter to search
	searchCriteria.and(PostPredicates.byCourse(criteria.getCourseId()));

	// add year parameter to search
	searchCriteria.and(PostPredicates.byYear(criteria.getYear()));

	// add semester parameter to search
	searchCriteria.and(PostPredicates.bySemester(criteria.getSemester()));

	// add moed parameter to search
	searchCriteria.and(PostPredicates.byMoed(criteria.getMoed()));

	// add question number parameter to search
	searchCriteria.and(PostPredicates.byQuestionNumber(criteria.getQuestionNumber()));

	// return by question number
	return searchCriteria;

    }

    List<Post> searchBy(BooleanBuilder searchCriteria, int page, int size) {
	if (dao.findAll(searchCriteria, new PageRequest(page, size, orderByTime())) == null)
	    return null;
	return (List<Post>) dao.findAll(searchCriteria, new PageRequest(page, size, orderByTime())).getContent();
    }

    private Sort orderByTime() {
	return new Sort(Sort.Direction.DESC, "time");
    }

    public boolean checkByQuestion(PostCriteria criteria) {
	List<Post> results = search(criteria, 0, 1);
	if (results == null || results.isEmpty())
	    return false;

	return true;
    }

    public Post addNewPost(NewPostBean bean, String userTokenId) throws UnauthorizesException {
	// 1. check if such test exists
	// 2. if not - create
	// 3. check if such testQuestion exists
	// 4. if not - create
	// 5. create post related to testQuestion

	User user = userService.get(userTokenId);
	if (user == null)
	    return null;

	Course course = courseService.get(bean.getCourseId());
	Test test = testService.getByMoed(course, bean.getYear(), bean.getSemester(), bean.getMoed());
	if (test == null) // no such test in the table
	{
	    test = new Test();
	    test.setCourse(course);
	    test.setYear(bean.getYear());
	    test.setSemester(bean.getSemester());
	    test.setMoed(bean.getMoed());
	    test = testService.add(test);
	}

	if (bean.getQuestionNumber() == null) // if no number was given
	    bean.setQuestionNumber(0);

	TestQuestion question = questionService.getByTestAndNumber(test, bean.getQuestionNumber());
	if (question == null) {
	    question = new TestQuestion();
	    question.setQuestionNumber(bean.getQuestionNumber());
	    question.setTest(test);
	    question = questionService.add(question);
	}

	Post post = new Post();
	post.setTitle(bean.getTitle());
	post.setContent(bean.getContent());
	// post.setTags(bean.getTags());
	post.setTestQuestion(question);
	post.setTime(Calendar.getInstance());
	post.setUser(user);
	post.setAcceptedComment(false);
	post.setTags(bean.getTags());

	Post postNew = createPost(post);
	for (String fileUrl : bean.getFiles()) {
	    NewFileBean fileBean = new NewFileBean();
	    fileBean.setPostId(postNew.getId());
	    fileBean.setUrl(fileUrl);
	    fileService.add(fileBean, userTokenId);
	}
	return postNew;
    }

    public Post updatePost(UpdatePostBean updateBean, Long id, String userTokenId)
	    throws UnauthorizesException, IllegalArgumentException {
	Post post = this.getById(id);
	User owner = post.getUser();
	User editor = userService.get(userTokenId);
	// unAuthorized
	if (!userService.isAuthorizedEditUser(owner, editor) && !editor.isAdmin()) {
	    return null;
	}
	post.setTitle(updateBean.getTitle());
	post.setContent(updateBean.getContent());
	post.setLastUpdated(Calendar.getInstance());
	post = dao.save(post);
	return post;

    }

    /**
     * returns posts by courses of the user
     *
     * @param userTokenId
     * @return posts of the user's courses
     */
    public List<Post> getByUserCourses(String userTokenId) {
	User user = userService.get(userTokenId);

	return dao.findByTestQuestion_Test_CourseInOrderByTimeDesc(user.getCourses());
    }

}
