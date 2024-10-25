package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 * @author Cyrus De Jesus
 */
@RestController // Combines Controller and Response Body
@RequestMapping("/users") // All HTTP Requests made to /users
public class UserController {

    /** UserService Instance */
    private final UserService userService;

    /**
     * UserController Constructor
     * @param userService the UserService
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * POST request to insert a User
     * @param user the user to insert
     * @return the status code
     */
    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@RequestBody User user) {
        // Send user to service to insert and save
        User u = userService.signUp(user);

        // Status code
        return ResponseEntity.ok(u);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User user) {
//        if (user != null) {
//            return ResponseEntity.ok(user);
//        }
//        else {
//            return ResponseEntity.status(401).body("Invalid username or password");
//        }
//    }

    /**
     * Return error status code
     * @param e the exception
     * @return error status code
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
