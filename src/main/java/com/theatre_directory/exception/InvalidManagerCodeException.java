package com.theatre_directory.exception;

public class InvalidManagerCodeException extends RuntimeException {

    public InvalidManagerCodeException(String message) {
        super(message);
    }
}