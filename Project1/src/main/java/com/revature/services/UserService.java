package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (user == null || !user.validateUser()) {
            throw new IllegalArgumentException("Invalid user");
        }

        // Check for unique username
        User getUser = getUserByUsername(user.getUsername());
        if (getUser != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        // .save() for JPA POST and PUT
        return userDao.save(user);
    }

    /**
     * Returns a user given the user's username
     * @param username the user's username
     * @return the user with matching username
     */
    public User getUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    /**
     * Returns a user given the user's username and password
     * @param username the user's username
     * @param password the user's password
     * @return the matching user
     */
    public User getUserByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    /**
     * Returns all users from the database
     * @return all users from the database
     */
    public List<User> getAllUsers() {
        // .findAll() returns all records in a table
        return userDao.findAll();
    }
}
