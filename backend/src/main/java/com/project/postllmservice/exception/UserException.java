package com.project.postllmservice.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter 
public class UserException extends RuntimeException {
    private HttpStatus statusCode;

    public UserException (String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
