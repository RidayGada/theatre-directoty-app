package com.theatre_directory.exception;

public class ReviewNotFoundException extends RuntimeException {

    public ReviewNotFoundException(String message) {
        super(message);
    }
}