package com.theatre_directory.exception;

public class MovieAlreadyExistsException extends RuntimeException {

    public MovieAlreadyExistsException(String message) {
        super(message);
    }

}