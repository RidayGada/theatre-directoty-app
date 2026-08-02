package com.theatre_directory.exception;

public class TheatreNotFoundException extends RuntimeException {

    public TheatreNotFoundException(String message) {
        super(message);
    }
}