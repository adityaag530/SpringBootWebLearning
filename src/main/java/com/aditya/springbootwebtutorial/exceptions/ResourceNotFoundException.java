package com.aditya.springbootwebtutorial.exceptions;


/*
 * @author adityagupta
 * @date 13/04/25
 */

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
