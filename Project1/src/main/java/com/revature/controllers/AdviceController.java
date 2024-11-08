package com.revature.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
