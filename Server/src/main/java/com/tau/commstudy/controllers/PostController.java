package com.tau.commstudy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tau.commstudy.beans.NewPostBean;
import com.tau.commstudy.beans.PostCriteria;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Post;
import com.tau.commstudy.entities.User;
import com.tau.commstudy.services.PostService;
import com.tau.commstudy.services.TestQuestionService;
import com.tau.commstudy.services.TestService;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController {

    // @Autowired
    // private PostDao dao;
    //
    // TestQuestionDao questionDao;
    //
    // @Autowired
    // private TestDao testDao;

    @Autowired
    private PostService service;

    @Autowired
    private TestService testService;

    @Autowired
    private TestQuestionService questionService;

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Post createPost(@RequestBody Post newPost) {
	return service.createPost(newPost);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String delete(Long id) {
	return service.delete(id);
    }

    /**
     * increases the number of votes by 1
     */
    @RequestMapping(method = RequestMethod.POST, value = "/like")
    public String like(long id) {
	return service.like(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public Iterable<Post> getAll() {
	return service.getAll();
    }

    /**
     * This updates only the content, title of the post. All the other fields
     * (including id and time) stay the same
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String updatePost(@RequestBody Post givenPost) {
	return service.updatePost(givenPost);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getById")
    public Post getById(long id) {
	return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByUser")
    public Iterable<Post> getByUser(User user) {
	return service.getByUser(user);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByCourse")
    public Iterable<Post> getByCourse(Course course) {
	return service.getByCourse(course);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getByMoed")
    public Iterable<Post> getByMoed(Integer year, Character semester, Character moed, Course course) {
	return service.getByMoed(course, year, semester, moed);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoedK") // get,
									 // all
									 // fields
									 // separately
    public boolean checkByMoedK(Integer year, Character semester, Character moed, Course course) {
	return service.checkByMoedK(year, semester, moed, course);
    }

    /**
     * check if posts for this test moed already exist
     *
     * @param bean
     * @return TRUE or FALSE
     */
    @RequestMapping(method = RequestMethod.GET, value = "/checkByMoed") // get,
									// all
									// fields
									// in a
									// bean
    public boolean checkByMoed(PostCriteria criteria) {
	return service.checkByMoed(criteria);
    }

    // /**
    // * finds all posts connected to a specific testQuestion.
    // *
    // * @param bean
    // * - the field testQuestion should be given
    // * @return List of posts
    // */
    // @RequestMapping(method = RequestMethod.GET, value = "/getByTestQuestion")
    // public List<Post> getByTestQuestion(@RequestBody PostBean bean) {
    // // 1. use findByfields if this test exists
    // // 2. in the specific test, find by question number
    // return service.getByTestQuestion(question);
    // }
    //
    // /**
    // * finds all posts connected to a question, search by question info
    // *
    // * @param bean
    // * the used fields are: -course -year -semester -moed
    // * @return
    // */
    // @RequestMapping(method = RequestMethod.GET, value = "/getByQuestion")
    // public List<Post> getByQuestion(@RequestBody PostBean bean) {
    // // 1. use findByfields if this test exists
    // // 2. in the specific test, find by question number
    // Test test =
    // testDao.findByCourseAndYearAndSemesterAndMoed(bean.getCourse(),
    // bean.getYear(), bean.getSemester(),
    // bean.getMoed());
    // if (test == null)
    // return null;
    // bean.setTest(test);
    //
    // TestQuestion question = questionDao.findByTestAndQuestionNumber(test,
    // bean.getQuestionNumber());
    // if (question == null)
    // return null;
    // bean.setTestQuestion(question);
    //
    // return getByTestQuestion(bean);
    // }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public List<Post> search(PostCriteria criteria) {
	return service.search(criteria);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public Post addNewPost(@RequestBody NewPostBean post, String userTokenId) {
	return service.addNewPost(post, userTokenId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/checkByQuestion")
    public boolean checkByQuestion(@RequestBody PostCriteria criteria) {
	return service.checkByQuestion(criteria);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/check")
    public List<Post> check() {
	PostCriteria bean = new PostCriteria();
	bean.setCourseId((long) 1);
	bean.setMoed('a');
	bean.setSemester('a');
	bean.setYear(2011);
	bean.setQuestionNumber(0);
	return search(bean);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/check2")
    public Post check2() {
	String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImUyNTI0ZmJlMDEwNjE2YTZlODcxYWY5NjljMzQwYjgwYzY3MjA4OTQifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE3MTQ3LCJleHAiOjE0ODI2MjA3NDcsImF0X2hhc2giOiJ2QlhSR05QY19rUEtpQ3lQUGVwM3N3IiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.BYxBPXLzyz5DVdnZasADj2rfZM-9m07C86cbRL6eopw0KVJfjFgU_kxGcF4txMv6U3hUgfh5smFmTzLWZsPp91yZXaTT1LyS0yBJFr0L9K7_8r4aO3MiR5etyKi7MTQzUV8PKzzRlRvpHgKPjICCnt_32pwM_s2uMWWprt5NNHQXXMb_whIVT8WyQwyqoIjRvm-2gznOd-s-2TmDbFxr5JnZbYPFdAacYI3XV_zca164WXjcBYEniGo5NiTw40fGfPcCJssOaMlipjB3PiC4FqB5QxmIMI6mrOQLIjlh1gUIgNo7qhXCV96eYDkIlCz_8tnvxkZcASqirdf6W-gBLw.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2ODEwLCJleHAiOjE0ODI2MjA0MTAsImF0X2hhc2giOiJSbi04RURrNGRabmNzbWx2dUVYUjdRIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.btoU5bSUvDokrIq6Mofdl97nNosCm32Z1-sIe_x9NKNojvL7KZB839KbDO6hFTxNS-4Wu5tO4PTOHNcKzKWJ4V3jnSkoNo4lWAXG2Ks6XiuF2_XV1hklCtPwb-pAGTt40HOhFDgSJBgCNWM7KyPOPVZCBu6g53oQlgFnwe7a3qTeCHAyGTzMCwiX2bf5zr57aGl7rsTdXcxD6kfFUnnGpUky6WY3OTjN9lsAIfagGorGLAwa6L3PNS6t8_zPtEuRKCeDU4snjbtiBDW6FeLiNAp70J7i8KXNemxh_WqEFRltjsPMnLfem9NKBRqMOObXgcuqt5m42KJTjojn8iyK3g.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2Njg0LCJleHAiOjE0ODI2MjAyODQsImF0X2hhc2giOiJGdGxGZnNraDUxSHloRGsxYzYzb3dnIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.lbDjoO7o7V_TucTLcreb_K2xBCztiHu6twfOE-aH4KaRKse0kIpnGiKG6KW0_qxnnize-cx646GuHYgPE1VZ9ABa-WUA8W0QZUVvOf0-BaZLs14BoEQFFIJmIkFLeI8qIilC7LgTw2In65LYBVXQH3U2j6dltVQxW0bx0ggbanYC5ySGJllgeTI2a3iJh2jmK6lbQlx0qwRqZuSwEL9jIK9DPZWOjputRv9b5Fs96MqcaMNjoS8XwnSiSbR-SnYaN8Pcb_ViG2Rm7Ltxuwe1OTKVbndK9OrabECv9ecCQK3QiHMB6EFcjVLAzBgwUxm2pxeMH11j4DuX-MmBVPgB8A.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2NDgzLCJleHAiOjE0ODI2MjAwODMsImF0X2hhc2giOiI3UWFPVXJQVktLSVhOY2lyTFVCOWhnIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.LJmofPw7_vSy8_F7LV5_eCaK2mrG72YYC-20q3EMrti1lTyO4jWw6aBG-sg01ub24O-Fvm_RAx723xZcn1VZzf1Gi6-Q_T1R_klqgaZiDgz3aL9YDZOXKxUPmfomZkP00_r_vjUnO5Ylg-cbixFZ1z-ER3dv5_pfoqU3xAxo8iElDqOV2tj56wu0lkdmwuNGl6R0_jO6JXd7_ESjOvPgmPsuXikz42DOcR7RA_k-e39l1AwbdA8dSNA3gRgyFFILsvujMtXMKeCeSYd_wYFybFH9tcGnD2Ogsx5cb9BkQJ1KQ2hbYsrNJT4H8OfohtsMnKDujoxyjCjM9ldEkajJEA.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2NDA0LCJleHAiOjE0ODI2MjAwMDQsImF0X2hhc2giOiJjN1piWm1UaERQVDUxeWxuSG1KZVhBIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.sxOI1eYx0fTbJTj1WXzyHT_DXsUdEwRWJoIKQ8XFUdi3APda1Yj9FzOwGPtDBWZAzFk6uLrHzB4WeeBOvXK32EnAEapKtEcw6OaEF5ciEhJgJKojKS4aiyBEY_mSBEUGR4iYKqffOu7LSew57qe5KvfDmG45iY4YZtg0DW0H8eowM4hHffRaIVEThAaoRrXSByZbFaMN7ZxLmyGIt9yWhKYGjcfFXauFBAOQFPiWFQ2sJCUXbtV2wV7OL9hdBTiYHm6fDPDxdgrXjBIgMc_VURzPSD30GJxTmJHDU3ak1E3FrmbRXBpgL7HkcKhFE-g76E-8pZgQkNaR0S9qTJH7kg.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2MjcxLCJleHAiOjE0ODI2MTk4NzEsImF0X2hhc2giOiJqRk92bS1SNUhFWkxLZURTNnp2bTBRIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.C8bVd0wTNfSOnUFNWU0n3_ESdos-LoOIIAiLdIpfUQJsCJ0UMhKuo3gQsHz1x2YCgXHO0xwfLQZGYRGAEmy6Qd2dVW-XflXBcx8ZXZKA3w1DI8zn9K9CljOyGxk5raDSXbUxMOwctec9HfKBa2d_MXdSMRTaVxsi4fSrQ57mDY1n_own-K5pVV6Z8n-saoQZe5NUCGdvaHNF_lQZhxuAluNr3d9OYGn8eMd89sFKCM0SAtUBaTy3MHa8lHEHD1RQCctq0T8gycyeM-Z6TUCBAYOWZTdupWmBDq1tTpyP0NOyPHaIaT4hNT2CeyJ2ellSiOPvK0j8vEbe8mA2eYD3KA.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjA5NDEwLCJleHAiOjE0ODI2MTMwMTAsImF0X2hhc2giOiJrQkpITXVsdUJpOW1uQ1cxOFRCd3BBIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.X8fGku9k-QoioRfW_NSvaLj40YwmmZtUOeXqQP8dAJRyTqyB2PnI9aYGT-bcpUL9dq5roicSdQ8dL_Ld2ur-v4KrsTaQVWM9Cc3HBtYzfKQ8e7RKW5o5JQwhjcty-Y4cD0ubLfOux5HjlnovLSt9DWjUHMgDmxZv_dz6FOvgkvUIG0bx_LOqLN9TmzcvU5vuOMpOd7BrOarOqNJCjS5jJawo6kHf6jeu6nQtWfU1TRlGX7XWti35TpJ1Rh_fhgYJYZmjlLLu8dwwnyR6Qi2t86n5I6SLv_N5z4jVMhDTKsyMcmnlGTJhwpxYAd2F6XZWst06uM56Qhu1X9OqXu-MOw";

	NewPostBean bean = new NewPostBean();
	bean.setCourseId((long) 1);
	bean.setMoed('a');
	bean.setSemester('a');
	bean.setYear(2011);
	bean.setQuestionNumber(4);
	bean.setTitle("automatic check2");
	bean.setContent("nonexistent question");
	return addNewPost(bean, token);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/check3")
    public Post check3() {
	String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImUyNTI0ZmJlMDEwNjE2YTZlODcxYWY5NjljMzQwYjgwYzY3MjA4OTQifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE3MTQ3LCJleHAiOjE0ODI2MjA3NDcsImF0X2hhc2giOiJ2QlhSR05QY19rUEtpQ3lQUGVwM3N3IiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.BYxBPXLzyz5DVdnZasADj2rfZM-9m07C86cbRL6eopw0KVJfjFgU_kxGcF4txMv6U3hUgfh5smFmTzLWZsPp91yZXaTT1LyS0yBJFr0L9K7_8r4aO3MiR5etyKi7MTQzUV8PKzzRlRvpHgKPjICCnt_32pwM_s2uMWWprt5NNHQXXMb_whIVT8WyQwyqoIjRvm-2gznOd-s-2TmDbFxr5JnZbYPFdAacYI3XV_zca164WXjcBYEniGo5NiTw40fGfPcCJssOaMlipjB3PiC4FqB5QxmIMI6mrOQLIjlh1gUIgNo7qhXCV96eYDkIlCz_8tnvxkZcASqirdf6W-gBLw.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2ODEwLCJleHAiOjE0ODI2MjA0MTAsImF0X2hhc2giOiJSbi04RURrNGRabmNzbWx2dUVYUjdRIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.btoU5bSUvDokrIq6Mofdl97nNosCm32Z1-sIe_x9NKNojvL7KZB839KbDO6hFTxNS-4Wu5tO4PTOHNcKzKWJ4V3jnSkoNo4lWAXG2Ks6XiuF2_XV1hklCtPwb-pAGTt40HOhFDgSJBgCNWM7KyPOPVZCBu6g53oQlgFnwe7a3qTeCHAyGTzMCwiX2bf5zr57aGl7rsTdXcxD6kfFUnnGpUky6WY3OTjN9lsAIfagGorGLAwa6L3PNS6t8_zPtEuRKCeDU4snjbtiBDW6FeLiNAp70J7i8KXNemxh_WqEFRltjsPMnLfem9NKBRqMOObXgcuqt5m42KJTjojn8iyK3g.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2Njg0LCJleHAiOjE0ODI2MjAyODQsImF0X2hhc2giOiJGdGxGZnNraDUxSHloRGsxYzYzb3dnIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.lbDjoO7o7V_TucTLcreb_K2xBCztiHu6twfOE-aH4KaRKse0kIpnGiKG6KW0_qxnnize-cx646GuHYgPE1VZ9ABa-WUA8W0QZUVvOf0-BaZLs14BoEQFFIJmIkFLeI8qIilC7LgTw2In65LYBVXQH3U2j6dltVQxW0bx0ggbanYC5ySGJllgeTI2a3iJh2jmK6lbQlx0qwRqZuSwEL9jIK9DPZWOjputRv9b5Fs96MqcaMNjoS8XwnSiSbR-SnYaN8Pcb_ViG2Rm7Ltxuwe1OTKVbndK9OrabECv9ecCQK3QiHMB6EFcjVLAzBgwUxm2pxeMH11j4DuX-MmBVPgB8A.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2NDgzLCJleHAiOjE0ODI2MjAwODMsImF0X2hhc2giOiI3UWFPVXJQVktLSVhOY2lyTFVCOWhnIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.LJmofPw7_vSy8_F7LV5_eCaK2mrG72YYC-20q3EMrti1lTyO4jWw6aBG-sg01ub24O-Fvm_RAx723xZcn1VZzf1Gi6-Q_T1R_klqgaZiDgz3aL9YDZOXKxUPmfomZkP00_r_vjUnO5Ylg-cbixFZ1z-ER3dv5_pfoqU3xAxo8iElDqOV2tj56wu0lkdmwuNGl6R0_jO6JXd7_ESjOvPgmPsuXikz42DOcR7RA_k-e39l1AwbdA8dSNA3gRgyFFILsvujMtXMKeCeSYd_wYFybFH9tcGnD2Ogsx5cb9BkQJ1KQ2hbYsrNJT4H8OfohtsMnKDujoxyjCjM9ldEkajJEA.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2NDA0LCJleHAiOjE0ODI2MjAwMDQsImF0X2hhc2giOiJjN1piWm1UaERQVDUxeWxuSG1KZVhBIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.sxOI1eYx0fTbJTj1WXzyHT_DXsUdEwRWJoIKQ8XFUdi3APda1Yj9FzOwGPtDBWZAzFk6uLrHzB4WeeBOvXK32EnAEapKtEcw6OaEF5ciEhJgJKojKS4aiyBEY_mSBEUGR4iYKqffOu7LSew57qe5KvfDmG45iY4YZtg0DW0H8eowM4hHffRaIVEThAaoRrXSByZbFaMN7ZxLmyGIt9yWhKYGjcfFXauFBAOQFPiWFQ2sJCUXbtV2wV7OL9hdBTiYHm6fDPDxdgrXjBIgMc_VURzPSD30GJxTmJHDU3ak1E3FrmbRXBpgL7HkcKhFE-g76E-8pZgQkNaR0S9qTJH7kg.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjE2MjcxLCJleHAiOjE0ODI2MTk4NzEsImF0X2hhc2giOiJqRk92bS1SNUhFWkxLZURTNnp2bTBRIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.C8bVd0wTNfSOnUFNWU0n3_ESdos-LoOIIAiLdIpfUQJsCJ0UMhKuo3gQsHz1x2YCgXHO0xwfLQZGYRGAEmy6Qd2dVW-XflXBcx8ZXZKA3w1DI8zn9K9CljOyGxk5raDSXbUxMOwctec9HfKBa2d_MXdSMRTaVxsi4fSrQ57mDY1n_own-K5pVV6Z8n-saoQZe5NUCGdvaHNF_lQZhxuAluNr3d9OYGn8eMd89sFKCM0SAtUBaTy3MHa8lHEHD1RQCctq0T8gycyeM-Z6TUCBAYOWZTdupWmBDq1tTpyP0NOyPHaIaT4hNT2CeyJ2ellSiOPvK0j8vEbe8mA2eYD3KA.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjA5NDEwLCJleHAiOjE0ODI2MTMwMTAsImF0X2hhc2giOiJrQkpITXVsdUJpOW1uQ1cxOFRCd3BBIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.X8fGku9k-QoioRfW_NSvaLj40YwmmZtUOeXqQP8dAJRyTqyB2PnI9aYGT-bcpUL9dq5roicSdQ8dL_Ld2ur-v4KrsTaQVWM9Cc3HBtYzfKQ8e7RKW5o5JQwhjcty-Y4cD0ubLfOux5HjlnovLSt9DWjUHMgDmxZv_dz6FOvgkvUIG0bx_LOqLN9TmzcvU5vuOMpOd7BrOarOqNJCjS5jJawo6kHf6jeu6nQtWfU1TRlGX7XWti35TpJ1Rh_fhgYJYZmjlLLu8dwwnyR6Qi2t86n5I6SLv_N5z4jVMhDTKsyMcmnlGTJhwpxYAd2F6XZWst06uM56Qhu1X9OqXu-MOw";

	NewPostBean bean = new NewPostBean();
	bean.setCourseId((long) 1);
	bean.setMoed('b');
	bean.setSemester('a');
	bean.setYear(2010);
	bean.setQuestionNumber(1);
	bean.setTitle("automatic check3");
	bean.setContent("nonexistent test");
	return addNewPost(bean, token);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/check4")
    public Post check4() {
	String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImUyNTI0ZmJlMDEwNjE2YTZlODcxYWY5NjljMzQwYjgwYzY3MjA4OTQifQ.eyJpc3MiOiJhY2NvdW50cy5nb29nbGUuY29tIiwiaWF0IjoxNDgyNjU2NzYwLCJleHAiOjE0ODI2NjAzNjAsImF0X2hhc2giOiJESFVxejNxMEdzeVRQY19lR01yeUJBIiwiYXVkIjoiMTA4NzU1OTY0NDM5My01NW90NW10M3NhZnViamwya25tMzAycmoydWFzcjBnbC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjEwNTA3NDYyNzAzOTIxMjU5OTA4MiIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJhenAiOiIxMDg3NTU5NjQ0MzkzLTU1b3Q1bXQzc2FmdWJqbDJrbm0zMDJyajJ1YXNyMGdsLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwiaGQiOiJtYWlsLnRhdS5hYy5pbCIsImVtYWlsIjoia2FyaW5hdmVza2VyQG1haWwudGF1LmFjLmlsIiwibmFtZSI6IkthcmluYSBWZXNrZXIiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1GSnJ2dFZ6MTktNC9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BS0JfVTh0S04yQXg5YXVQV2xVR3hjZVNGVWRiNE9NUmtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJLYXJpbmEiLCJmYW1pbHlfbmFtZSI6IlZlc2tlciJ9.lupyId2OKB-IVpKQYPVBM30zPBsUYLVS-81nSTVK2qwODFa_7rzXsHwwJJEFZo4b471W1dhZRZ5qll-eRqbsK7qPAD29ekf3RK8WwO6hyDSaeFVrS9-KiOFcjesRry-sIxOA7icf2zQUQCPDf-xpT7jeer6kJB1oaftrLhUbDauS0IFr60WMRGVTbpWf7ySc9TE25WXaqDv62hMOXbfl82-Zw31Tw0h5SXTXiUd-vc-m0g3Nao41xmCutjbfaDKRLkOuKFliq2WLt46pS8S9_nBEr1JzLFS-LY779ZD_r64Pzx2anzI-6SyKr8ajTJOVvIEycwgvsYRRGKc8nOQIyw";

	NewPostBean bean = new NewPostBean();
	bean.setCourseId((long) 1);
	bean.setMoed('b');
	bean.setSemester('a');
	bean.setYear(2010);
	bean.setQuestionNumber(1);
	bean.setTitle("automatic check4");
	bean.setContent("check tokenId");
	return addNewPost(bean, token);

    }

}
