package com.tau.commstudy.entities.daos;


import com.tau.commstudy.entities.Faculty;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface FacultyDao extends CrudRepository<Faculty, Long> {

    

}
