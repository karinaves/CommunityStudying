package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Tag;

@Transactional
public interface TagDao extends CrudRepository<Tag, Long>{

}
