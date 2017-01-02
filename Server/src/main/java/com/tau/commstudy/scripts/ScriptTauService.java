package com.tau.commstudy.scripts;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tau.commstudy.beans.ScriptTauBean;
import com.tau.commstudy.beans.ScriptTauBean.FacultyBean;
import com.tau.commstudy.beans.ScriptTauBean.SchoolBean;
import com.tau.commstudy.entities.Course;
import com.tau.commstudy.entities.Faculty;
import com.tau.commstudy.entities.University;
import com.tau.commstudy.services.CourseService;
import com.tau.commstudy.services.FacultyService;
import com.tau.commstudy.services.UniversityService;

@Service
public class ScriptTauService {
    @Autowired
    private UniversityService universityService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private CourseService courseService;

    private String jsonPath;
    private ScriptTauBean bean = null;
    private University university = null;

    public ScriptTauService() {
    }

    public void init(String path, Long tauId) {
	this.jsonPath = path;
	this.university = universityService.get(tauId);
    }

    public void run() {
	try {
	    ObjectMapper mapper = new ObjectMapper();
	    File f = new File(this.jsonPath);

	    this.bean = mapper.readValue(f, ScriptTauBean.class);

	    for (FacultyBean facultyBean : this.bean.getFaculties()) {
		String facName = facultyBean.getName();
		System.out.println(facName);
		for (SchoolBean schoolBean : facultyBean.getSchools()) {
		    String name = String.format("%s - %s", facName, schoolBean.getName());
		    Faculty faculty = new Faculty();
		    faculty.setName(name);
		    faculty.setUniversityId(schoolBean.getUniversityId());
		    faculty.setUniversity(this.university);
		    faculty = facultyService.add(faculty);

		    for (Course course : schoolBean.getCourses()) {
			course.setFaculty(faculty);
			System.out.println(course);
			courseService.add(course);
		    }
		}

	    }
	} catch (IOException e) {

	    e.printStackTrace();
	}
    }

}
