package com.tau.commstudy.controllers;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tau.commstudy.exceptions.NotHandeledException;
import com.tau.commstudy.exceptions.UnauthorizesException;

public class ErrorHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(IllegalArgumentException e) {
	return e.getMessage();
    }

    @ExceptionHandler(UnauthorizesException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String handleException(UnauthorizesException e) {
	return e.getMessage();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public String handleException(ValidationException e) {
	return e.getMessage();
    }

    @ExceptionHandler(NotHandeledException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
	return e.getMessage();
    }

}
