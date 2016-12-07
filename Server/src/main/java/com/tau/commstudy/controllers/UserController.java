package com.tau.commstudy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tau.commstudy.entities.daos.UserDao;

@Controller
public class UserController {
    
    @Autowired
    private UserDao userDao;

}