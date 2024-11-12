package com.revature.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Reimbursement Data Transfer Object
 * @author Cyrus De Jesus
 */
public class ReimbursementDTO {

    /** Reimbursement's id */
    private int reimbursementId;

    /** Reimbursement description */
    private String description;

    /** Reimbursement amount */
    private int amount;

    /** Reimbursement status */
    private String status;

    /** Reimbursement username to link to user */
    private String username;

    /**
     * Default Constructor
     */
    public ReimbursementDTO() {}

    /**
     * ReimbursementDTO Constructor
     * @param reimbursementId Reimbursement's id
     * @param description Reimbursement's description
     * @param amount Reimbursement's amount
     * @param status Reimbursement's status
     */
    public ReimbursementDTO(int reimbursementId, String description, int amount, String status, String username) {
        setReimbursementId(reimbursementId);
        setDescription(description);
        setAmount(amount);
        setStatus(status);
        setUsername(username);
    }

    /**
     * Returns the value of the reimbursementId
     *
     * @return reimbursementId the current value of reimbursementId
     */
    public int getReimbursementId() {
        return reimbursementId;
    }

    /**
     * Returns the value of the status
     *
     * @return status the current value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the value of the amount
     *
     * @return amount the current value of amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the value of the description
     *
     * @return description the current value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the value of the username
     *
     * @return username the current value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the reimbursementId
     *
     * @param reimbursementId the new value to set for reimbursementId
     */
    public void setReimbursementId(int reimbursementId) {
        this.reimbursementId = reimbursementId;
    }

    /**
     * Sets the description
     *
     * @param description the new value to set for description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the amount
     *
     * @param amount the new value to set for amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Sets the status
     *
     * @param status the new value to set for status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Sets the username
     *
     * @param username the new value to set for username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Checks if a reimbursement dto is valid
     * @return true if valid; otherwise, false
     */
    @JsonIgnore
    public boolean isValid() {
        return description != null && !description.isEmpty() &&
                amount > 0 &&
                status != null && !status.isEmpty() &&
                username != null && !username.isEmpty();
    }

}
