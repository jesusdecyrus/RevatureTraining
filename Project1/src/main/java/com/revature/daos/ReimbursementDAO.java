package com.revature.daos;

import com.revature.models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Reimbursement DAO Interface
 * @author Cyrus De Jesus
 */
@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer> {

    /**
     * Finds reimbursement given its reimbursement id
     * @param reimbursementId the reimbursement id
     * @return the reimbursement
     */
    Reimbursement findByReimbursementId(int reimbursementId);
}