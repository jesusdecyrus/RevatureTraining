package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.dtos.ReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public ReimbursementDTO insertReimbursement(ReimbursementDTO reimbursementDTO) {
        if (reimbursementDTO == null || !reimbursementDTO.isValid()) {
            throw new IllegalArgumentException("Invalid reimbursement");
        }

        // Check for valid user
        User user = userDAO.findByUsername(reimbursementDTO.getUsername());
        if (user == null || !user.isValid()) {
            throw new IllegalArgumentException("No user found with username: " + reimbursementDTO.getUsername());
        }
        else {
            // User found so add reimbursement
            Reimbursement reimbursement = reimbursementDAO.save(new Reimbursement(reimbursementDTO.getReimbursementId(), reimbursementDTO.getDescription(), reimbursementDTO.getAmount(), reimbursementDTO.getStatus(), user));
            reimbursementDTO.setReimbursementId(reimbursement.getReimbursementId());
            return reimbursementDTO;
        }
    }

    /**
     * Returns all reimbursements from the database
     * @return all reimbursements from the database
     */
    public List<ReimbursementDTO> getAllReimbursements() {
        List<Reimbursement> reimbursementList = reimbursementDAO.findAll();
        return reimbursementList.stream().map(Reimbursement::toDTO).collect(Collectors.toList());
    }

    /**
     * Returns owned reimbursements from the database
     * @param id the user's id
     * @return owned reimbursements from the database
     */
    public List<ReimbursementDTO> getOwnedReimbursements(int id) {
        List<Reimbursement> reimbursementList = reimbursementDAO.findAll();
        return reimbursementList.stream().filter(reimbursement -> reimbursement.getUser().getUserId() == id).map(Reimbursement::toDTO).collect(Collectors.toList());
    }

    /**
     * Update a reimbursement's status
     * @param reimbursementId the reimbursement id
     * @param status the reimbursement status
     * @return a reimbursement DTO
     */
    public ReimbursementDTO updateReimbursementStatus(int reimbursementId, String status) {
        if (reimbursementId <= 0) {
            throw new IllegalArgumentException("Invalid reimbursement id");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Invalid status");
        }

        // Retrieve reimbursement
        Reimbursement reimbursement = reimbursementDAO.findByReimbursementId(reimbursementId);
        if (reimbursement == null) {
            throw new IllegalArgumentException("No reimbursement found");
        }

        // Modify reimbursement
        reimbursement.setStatus(status);
        return reimbursementDAO.save(reimbursement).toDTO();
    }

}