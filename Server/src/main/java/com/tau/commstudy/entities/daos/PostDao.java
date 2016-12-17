package com.tau.commstudy.entities.daos;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.Post;

public interface PostDao extends CrudRepository<Post, Long> {

}
