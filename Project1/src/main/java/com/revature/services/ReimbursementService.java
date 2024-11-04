package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.dtos.ReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Reimbursement Service
 * @author Cyrus De Jesus
 */
@Service
public class ReimbursementService {

    /** Reimbursement DAO */
    private final ReimbursementDAO reimbursementDAO;

    /** User DAO */
    private final UserDAO userDAO;

    /**
     * ReimbursementService Constructor
     * @param reimbursementDAO the reimbursement DAO
     * @param userDAO the user DAO
     */
    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO, UserDAO userDAO) {
        this.reimbursementDAO = reimbursementDAO;
        this.userDAO = userDAO;
    }

    /**
     * Inserts the reimbursement to the database
     * @param reimbursementDTO the reimbursement to add
     * @return the reimbursement added
     */
    public Reimbursement insertReimbursement(ReimbursementDTO reimbursementDTO) {
        if (reimbursementDTO == null || !reimbursementDTO.validate()) {
            throw new IllegalArgumentException("Invalid reimbursement");
        }

        // Check for valid user
        Optional<User> user = userDAO.findById(reimbursementDTO.getUserId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("No user found with id: " + reimbursementDTO.getUserId());
        }
        else {
            // User found so add reimbursement
            return reimbursementDAO.save(new Reimbursement(reimbursementDTO.getReimbursementId(), reimbursementDTO.getDescription(), reimbursementDTO.getAmount(), reimbursementDTO.getStatus(), user.get()));
        }
    }

}
