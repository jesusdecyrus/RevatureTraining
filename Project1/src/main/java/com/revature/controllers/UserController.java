package com.revature.controllers;

import com.revature.aspects.ManagerOnly;
import com.revature.models.User;
import com.revature.models.dtos.UserDTO;
import com.revature.models.dtos.UserLoginDTO;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
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
@CrossOrigin // Allows cross-origin-resource-sharing
public class UserController {

    /** UserService Instance */
    private final UserService userService;

    /** The HTTP Session */
    public static HttpSession session = null;

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
    public ResponseEntity<UserDTO> signUpUser(@RequestBody User user) {
        // Send user to service to insert and save
        User u = userService.signUp(user);

        // Status code
        return ResponseEntity.ok(new UserDTO(u.getFirstName(), u.getLastName(), u.getUsername(), u.getRole()));
    }

    /**
     * POST request to login a user
     * @param userLoginDTO the UserLoginDTO
     * @param session the HTTP session
     * @return response entity
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLoginDTO, HttpSession session) {
        // Retrieve user from the service
        UserDTO userDTO = userService.login(userLoginDTO, session);

        // Status code
        return ResponseEntity.ok(userDTO);
    }

    /**
     * GET request to a get a user with matching username
     * @param username the user's username
     * @return response entity
     */
    @GetMapping("/one/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        // Retrieve user from the service
        UserDTO userDTO = userService.getUserByUsername(username);

        // Status code
        return ResponseEntity.ok(userDTO);
    }

    /**
     * GET request to get all users
     * @return response entity
     */
    @ManagerOnly
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // Retrieve list from the service
        List<UserDTO> userDTOList = userService.getAllUsers();

        // Status code
        return ResponseEntity.ok(userDTOList);
    }

}
