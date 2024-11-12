package com.revature.services;

import com.revature.controllers.UserController;
import com.revature.daos.UserDAO;
import com.revature.models.User;
import com.revature.models.dtos.UserDTO;
import com.revature.models.dtos.UserLoginDTO;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User Service
 * @author Cyrus De Jesus
 */
@Service // Register Class As Bean
public class UserService {

    /** UserDAO Instance */
    private final UserDAO userDao;

    /**
     * UserService Constructor
     * @param userDAO the UserDAO
     */
    @Autowired // Constructor Injection
    public UserService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    /**
     * Signs up a user by adding to the database
     * @param user the new user to add
     * @return the new user added
     */
    public User signUp(User user) {
        // Check if user is valid
        if (user == null || !user.isValid()) {
            throw new IllegalArgumentException("Invalid user sign up");
        }

        // Check for unique username
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // .save() for JPA POST and PUT
        return userDao.save(user);
    }

    /**
     * Returns a user DTO given the user's username and password as well as session
     * @param userLoginDTO the UserLoginDTO
     * @param session the HTTP session
     * @return a UserDTO
     */
    public UserDTO login(UserLoginDTO userLoginDTO, HttpSession session) {
        // Error check
        if (userLoginDTO.getUsername() == null || userLoginDTO.getUsername().isEmpty() || userLoginDTO.getPassword() == null || userLoginDTO.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Error check
        User u = userDao.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (u == null || !u.isValid()) {
            throw new IllegalArgumentException("No user found");
        }

        // User found then set session
        UserController.session = session;
        UserController.session.setAttribute("userId", u.getUserId());
        UserController.session.setAttribute("username", u.getUsername());
        UserController.session.setAttribute("role", u.getRole());

        return new UserDTO(u.getFirstName(), u.getLastName(), u.getUsername(), u.getRole());
    }

    /**
     * Returns a user DTO given the user's username
     * @param username the user's username
     * @return a UserDTO
     */
    public UserDTO getUserByUsername(String username) {
        User u = userDao.findByUsername(username);

        // Error check
        if (u == null || !u.isValid()) {
            throw new IllegalArgumentException("No user found");
        }

        return new UserDTO(u.getFirstName(), u.getLastName(), u.getUsername(), u.getRole());
    }

    /**
     * Returns a user id
     * @param username the user's username
     * @return a user id
     */
    public int getIdByUsername(String username) {
        User u = userDao.findByUsername(username);

        // Error check
        if (u == null || !u.isValid()) {
            throw new IllegalArgumentException("No user found");
        }

        return u.getUserId();
    }

    /**
     * Returns all users from the database
     * @return all users from the database
     */
    public List<UserDTO> getAllUsers() {
        // .findAll() returns all records in a table
        List<User> userList = userDao.findAll();

        return userList.stream().map(User::toDTO).collect(Collectors.toList());
    }

    /**
     * Updates a user
     * @param userDTO the user DTO
     * @return a userDTO
     */
    public UserDTO updateUser(UserDTO userDTO) {
        // Error check
        if (!userDTO.isValid()) {
            throw new IllegalArgumentException("Invalid user to update");
        }
        User u = userDao.findByUsername(userDTO.getUsername());
        if (u == null || !u.isValid()) {
            throw new IllegalArgumentException("No user found");
        }

        // Update user
        u.setFirstName(userDTO.getFirstName());
        u.setLastName(userDTO.getLastName());
        u.setRole(userDTO.getRole());

        userDao.save(u);
        return new UserDTO(u.getFirstName(), u.getLastName(), u.getUsername(), u.getRole());
    }

    /**
     * Update a user's username
     * @param oldUsername the old username
     * @param newUsername the new username
     * @return a userDTO
     */
    public UserDTO updateUsername(String oldUsername, String newUsername) {
        if (oldUsername == null || oldUsername.isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }
        if (newUsername == null || newUsername.isEmpty()) {
            throw new IllegalArgumentException("Invalid new username");
        }

        // Retrieve user
        User user = userDao.findByUsername(oldUsername);
        if (user == null || !user.isValid()) {
            throw new IllegalArgumentException("No user found");
        }

        // Modify username
        user.setUsername(newUsername);
        return userDao.save(user).toDTO();
    }

    /**
     * Update a user's password
     * @param username the username
     * @param newPassword the new password
     * @return a userDTO
     */
    public UserDTO updatePassword(String username, String newPassword) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Invalid username");
        }

        // Retrieve user
        User user = userDao.findByUsername(username);
        if (user == null || !user.isValid()) {
            throw new IllegalArgumentException("No user found");
        }

        // Modify password
        user.setPassword(newPassword);
        return userDao.save(user).toDTO();
    }

    /**
     * Deletes a user and returns true if successful
     * @param userDTO the userDTO
     * @return true when user is deleted
     */
    @Transactional
    public boolean deleteUser(UserDTO userDTO) {
        if (userDTO == null || !userDTO.isValid()) {
            throw new IllegalArgumentException("Invalid user");
        }

        // Ensures the user exist then deletes
        getUserByUsername(userDTO.getUsername());
        userDao.deleteByUsername(userDTO.getUsername());
        return true;
    }

}