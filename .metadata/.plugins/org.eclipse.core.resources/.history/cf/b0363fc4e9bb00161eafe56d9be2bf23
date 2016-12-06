package com.tau.commstudy.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="faculties")
@XmlRootElement
public class Faculty {
    @Id
    @OneToMany(mappedBy = "facultyId", cascade = CascadeType.ALL)
    private int id;
    
    private String facultyName;
   
    
    
    public Faculty() {
	
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getFacultyName() {
        return facultyName;
    }



    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    
    
    
}
