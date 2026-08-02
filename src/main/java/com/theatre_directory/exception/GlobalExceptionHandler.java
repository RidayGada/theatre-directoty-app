package com.theatre_directory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(
            EmailAlreadyExistsException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(
            InvalidCredentialsException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidManagerCodeException.class)
    public ResponseEntity<String> handleInvalidManagerCode(
            InvalidManagerCodeException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TheatreNotFoundException.class)
    public ResponseEntity<String> handleTheatreNotFound(
            TheatreNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<String> handleReviewNotFound(
            ReviewNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorized(
            UnauthorizedException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ForbiddenOperationException.class)
    public ResponseEntity<String> handleForbidden(
            ForbiddenOperationException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
    
    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFound(MovieNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<String> handleMovieAlreadyExists(MovieAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(MovieAlreadyAssignedException.class)
    public ResponseEntity<String> handleMovieAlreadyAssigned(MovieAlreadyAssignedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(MovieAssignmentNotFoundException.class)
    public ResponseEntity<String> handleMovieAssignmentNotFound(MovieAssignmentNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}