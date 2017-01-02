package com.tau.commstudy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tau.commstudy.entities.daos.CommentDao;

@Service
public class CommentService {

    @Autowired
    private CommentDao dao;

}
