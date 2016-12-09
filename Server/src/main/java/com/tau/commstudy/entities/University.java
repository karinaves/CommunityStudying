package com.tau.commstudy.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


    
@Entity
@Table(name = "university")
@XmlRootElement
public class University {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private long universityNum;
        private String address;
       

        @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
        @JoinTable(name = "universities_to_faculties", joinColumns = @JoinColumn(name = "university_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "faculty_id", referencedColumnName = "id"))
        private Set<Faculty> faculties;
        


        public University() {

        }



	public long getId() {
	    return id;
	}



	public void setId(long id) {
	    this.id = id;
	}



	public long getUniversityNum() {
	    return universityNum;
	}



	public void setUniversityNum(long universityNum) {
	    this.universityNum = universityNum;
	}



	public String getAddress() {
	    return address;
	}



	public void setAddress(String address) {
	    this.address = address;
	}



	public Set<Faculty> getFaculties() {
	    return faculties;
	}



	public void setFaculties(Set<Faculty> faculties) {
	    this.faculties = faculties;
	}
        
        
    
    

 }
  
