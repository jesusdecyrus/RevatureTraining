package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * A global exception handler mainly for AuthenticationAspect
 * @author Cyrus De Jesus
 */
@ControllerAdvice
public class AdviceController {

    /**
     * Handles illegal access error
     * @param e the illegal access error
     * @return response entity
     */
    @ExceptionHandler(IllegalAccessError.class)
    public ResponseEntity<String> handleNotLoggedInException(IllegalAccessError e) {
        // Return a 401 Unauthorized response with the exception message
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles illegal argument exception
     * @param e the illegal argument exception
     * @return response entity
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    /**
     * Handles the sql exception
     * @param e the sql exception
     * @return response entity
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleIllegalArgument(SQLException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
