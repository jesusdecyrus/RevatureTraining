package com.revature.daos;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User DAO Interface
 * @author Cyrus De Jesus
 */
@Repository // Register Class As Bean
public interface UserDAO extends JpaRepository<User, Integer> {

    /**
     * Returns a user given the user's username
     * @param username the user's username
     * @return the user with matching username
     */
    User findByUsername(String username);

    /**
     * Return a user given the user's username and password
     * @param username the user's username
     * @param password the user's password
     * @return the matching user
     */
    User findByUsernameAndPassword(String username, String password);

}
