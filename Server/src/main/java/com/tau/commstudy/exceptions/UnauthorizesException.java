package com.tau.commstudy.exceptions;

public class UnauthorizesException extends RuntimeException {
    private static final long serialVersionUID = 7023907624084830848L;

    public UnauthorizesException() {
	super("Invalid user token");
    }
}