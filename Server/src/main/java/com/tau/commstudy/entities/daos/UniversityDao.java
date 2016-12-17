package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import com.tau.commstudy.entities.University;



@Transactional
public interface UniversityDao extends CrudRepository<University, Long> {

}
