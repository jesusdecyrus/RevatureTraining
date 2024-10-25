package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
     * @return response entity
     */
    @PostMapping("/signup")
    public ResponseEntity<User> signUpUser(@RequestBody User user) {
        // Send user to service to insert and save
        User u = userService.signUp(user);

        // Status code
        return ResponseEntity.ok(u);
    }


    /**
     * POST request to login a user
     * @param user the user to login
     * @return response entity
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Retrieve user from the service
        User u = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        // No user found
        if (u == null) {
            return ResponseEntity.status(404).body("Invalid username or password");
        }

        // Status code
        return ResponseEntity.ok("Welcome " + u.getFirstName() + " " + u.getLastName() + "!");
    }

    /**
     * GET request to a get a user with matching username
     * @param username the user's username
     * @return response entity
     */
    @GetMapping("/one/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        // Retrieve user from the service
        User user = userService.getUserByUsername(username);

        // No user found
        if (user == null) {
            return ResponseEntity.status(404).body("No user found with username: " + username);
        }

        // Status code
        return ResponseEntity.ok(user);
    }

    /**
     * GET request to get all users
     * @return response entity
     */
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        // Retrieve list from the service
        List<User> list = userService.getAllUsers();

        // Status code
        return ResponseEntity.ok(list);
    }

    /**
     * Return error status code for Illegal Argument
     * @param e the exception
     * @return error status code
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    /**
     * Return error status code for SQL
     * @param e the exception
     * @return error status code
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleIllegalArgument(SQLException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

}
