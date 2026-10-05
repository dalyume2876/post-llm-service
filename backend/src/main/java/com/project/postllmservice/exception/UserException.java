package com.project.postllmservice.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter 
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final HttpStatus statusCode;

    public UserException (String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
