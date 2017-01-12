package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.File;

@Transactional
public interface FileDao extends CrudRepository<File, Long> {

}
