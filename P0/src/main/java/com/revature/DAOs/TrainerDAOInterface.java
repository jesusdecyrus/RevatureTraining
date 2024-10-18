package com.revature.DAOs;

import com.revature.models.Trainer;

/**
 * Trainer Data Access Object Interface
 * @author Cyrus De Jesus
 */
public interface TrainerDAOInterface {

    /**
     * GET a Trainer using the trainer's username and password (LOGIN)
     * @param username the trainer's username
     * @param password the trainer's password
     * @return the matching trainer
     */
    Trainer login(String username, String password);

    /**
     * POST a Trainer using the trainer's credentials (SIGN UP)
     * @param firstName the trainer's first name
     * @param lastName the trainer's last name
     * @param username the trainer's username
     * @param password the trainer's password
     * @return the created trainer
     */
    Trainer signup(String firstName, String lastName, String username, String password);

}
