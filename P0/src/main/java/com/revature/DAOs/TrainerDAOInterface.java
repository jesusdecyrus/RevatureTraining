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

}
