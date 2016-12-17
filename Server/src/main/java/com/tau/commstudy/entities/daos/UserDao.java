package com.tau.commstudy.entities.daos;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.tau.commstudy.entities.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    public User findByGoogleId(String googleId);

}