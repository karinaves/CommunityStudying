package com.tau.commstudy.exceptions;

public class TableArgumentException extends RuntimeException{
    
    private static final long serialVersionUID = 1L;
    static String msg = "Invalid Argument Data"; 
 
    public  TableArgumentException(){
	super(msg);
    }
 
    public  TableArgumentException(Class<?> c, String field, String val){
	super( String.format("%s - Entinty: %s, Field: %s, Value: %s", msg, c.getName(), field, val));
    }

}
