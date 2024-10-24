package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service
 * @author Cyrus De Jesus
 */
@Service // Register Class As Bean
public class UserService {

    /** UserDAO Instance */
    private UserDAO userDao;

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
        if (user == null || !user.validateUser()) {
            throw new IllegalArgumentException("Invalid user");
        }

        // .save() for JPA POST and PUT
        return userDao.save(user);
    }
}
