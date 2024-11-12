package com.revature.controllers;

import com.revature.aspects.ManagerOnly;
import com.revature.models.dtos.ReimbursementDTO;
import com.revature.services.ReimbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Reimbursement Controller
 * @author Cyrus De Jesus
 */
@RestController
@RequestMapping("/reimbursements")
@CrossOrigin 
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
     * @param reimbursementDTO the reimbursement to insert
     * @return response entity
     */
    @PostMapping
    public ResponseEntity<ReimbursementDTO> insertReimbursement(@RequestBody ReimbursementDTO reimbursementDTO) {
        ReimbursementDTO r = reimbursementService.insertReimbursement(reimbursementDTO);
        return ResponseEntity.status(201).body(r);
    }

    /**
     * GET request to get all reimbursements
     * @return response entity
     */
    @ManagerOnly
    @GetMapping("/all")
    public ResponseEntity<List<ReimbursementDTO>> getAllReimbursements() {
        return ResponseEntity.ok(reimbursementService.getAllReimbursements());
    }

    /**
     * GET request to get owned reimbursements
     * @return response entity
     */
    @GetMapping("/owned/{userId}")
    public ResponseEntity<List<ReimbursementDTO>> getOwnedReimbursements(@PathVariable int userId) {
        return ResponseEntity.ok(reimbursementService.getOwnedReimbursements(userId));
    }

    /**
     * PUT request to update a reimbursement status
     * @param reimbursementId the reimbursement id
     * @param status the reimbursement status
     * @return response entity
     */
    @PutMapping("/update/{reimbursementId}/{status}")
    public ResponseEntity<ReimbursementDTO> updateReimbursementStatus(@PathVariable int reimbursementId, @PathVariable String status) {
        return ResponseEntity.ok(reimbursementService.updateReimbursementStatus(reimbursementId, status));
    }

}