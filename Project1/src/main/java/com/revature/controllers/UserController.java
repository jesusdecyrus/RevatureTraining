package com.revature.controllers;

import com.revature.models.User;
import com.revature.models.dtos.UserDTO;
import com.revature.models.dtos.UserLoginDTO;
import com.revature.services.UserService;
import com.revature.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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

    /** JWT Token Util */
    private final JwtTokenUtil jwtTokenUtil;

    /** Authentication Manager */
    private final AuthenticationManager authenticationManager;

    /**
     * UserController Constructor
     * @param userService the UserService
     */
    @Autowired
    public UserController(UserService userService, JwtTokenUtil jwtTokenUtil, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
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
        try {
            // Check username and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
            );

            // Build the user after validation
            User validatedUser = (User) authentication.getPrincipal();

            // Generate a JWT
            String token = jwtTokenUtil.generateAccessToken(validatedUser);

            // Return JWT
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            // Status code
            e.printStackTrace();
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
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
     * GET request to a get a user's id
     * @param username the user's username
     * @return response entity
     */
    @GetMapping("/id/{username}")
    public ResponseEntity<?> getIdByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getIdByUsername(username));
    }

    /**
     * GET request to get all users
     * @return response entity
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        // Retrieve list from the service
        List<UserDTO> userDTOList = userService.getAllUsers();

        // Status code
        return ResponseEntity.ok(userDTOList);
    }

    /**
     * PUT request to update a user
     * @param userDTO the user DTO
     * @return response entity
     */
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateUser(userDTO));
    }

    /**
     * PUT request to update a user's username
     * @param oldUsername the old username
     * @param newUsername the new username
     * @return response entity
     */
    @PutMapping("/update/username/{oldUsername}/{newUsername}")
    public ResponseEntity<UserDTO> updateUsername(@PathVariable String oldUsername, @PathVariable String newUsername) {
        return ResponseEntity.ok(userService.updateUsername(oldUsername, newUsername));
    }

    /**
     * PUT request to update a user's password
     * @param username the user's username
     * @param newPassword the new password
     * @return response entity
     */
    @PutMapping("/update/password/{username}/{newPassword}")
    public ResponseEntity<UserDTO> updatePassword(@PathVariable String username, @PathVariable String newPassword) {
        return ResponseEntity.ok(userService.updatePassword(username, newPassword));
    }

    /**
     * DELETE request to delete a user
     * @param userDTO the user DTO
     * @return response entity
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.deleteUser(userDTO));
    }

}