package com.theatre_directory.exception;

public class MovieAlreadyAssignedException extends RuntimeException {

    public MovieAlreadyAssignedException(String message) {
        super(message);
    }

}