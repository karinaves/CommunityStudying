package com.tau.commstudy.exceptions;

public class NotHandeledException extends Exception {

    private static final long serialVersionUID = 1L;
    static String msg = "--------ERROR WE DID NOT HANDLE----------";

    public NotHandeledException(Exception e) {
	super(e);
	System.out.println(msg);

    }

}
