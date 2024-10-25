package com.revature.controllers;

import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Reimbursement Controller
 * @author Cyrus De Jesus
 */
@RestController
@RequestMapping("/reimbursements")
public class ReimbursementController {

    /** ReimbursementService Instance */
    private final ReimbursementService reimbursementService;

    /**
     * ReimbursementController Constructor
     * @param reimbursementService the reimbursement service
     */
    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    /**
     * POST request to insert reimbursement to the database
     * @param reimbursement the reimbursement to insert
     * @return response entity
     */
    @PostMapping
    public ResponseEntity<Reimbursement> insertReimbursement(@RequestBody Reimbursement reimbursement) {
        Reimbursement r = reimbursementService.insertReimbursement(reimbursement);
        return ResponseEntity.ok(r);
    }
}
