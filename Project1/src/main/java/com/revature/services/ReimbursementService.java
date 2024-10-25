package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Reimbursement Service
 * @author Cyrus De Jesus
 */
@Service
public class ReimbursementService {

    /** Reimbursement DAO */
    private final ReimbursementDAO reimbursementDAO;

    /**
     * ReimbursementService Constructor
     * @param reimbursementDAO the reimbursement DAO
     */
    @Autowired
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    /**
     * Inserts the reimbursement to the database
     * @param reimbursement the reimbursement to add
     * @return the reimbursement added
     */
    public Reimbursement insertReimbursement(Reimbursement reimbursement) {
        if (reimbursement == null || !reimbursement.validate()) {
            throw new IllegalArgumentException("Invalid reimbursement");
        }



        return reimbursementDAO.save(reimbursement);
    }

}
