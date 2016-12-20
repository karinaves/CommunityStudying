package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Faculty;

@Transactional
public interface FacultyDao extends CrudRepository<Faculty, Long> {

}
