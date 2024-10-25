package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

/**
 * Reimbursement Class
 * @author Cyrus De Jesus
 */
@Entity
@Table(name = "Reimbursements")
@Component
public class Reimbursement {

    /** Reimbursement's id */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbursementId;

    /** Reimbursement description */
    @Column(nullable = false)
    private String description;

    /** Reimbursement amount */
    @Column(nullable = false)
    private int amount;

    /** Reimbursement status */
    @Column(nullable = false)
    private String status;

    /** Reimbursement user id */
    @ManyToOne(fetch = FetchType.EAGER) // Eagerly Load Dependency (Many-to-One Relationship)
    @JoinColumn(name = "userId") // Column to Link PK of User
    private User user;

    /**
     * Default Constructor
     */
    public Reimbursement() {}

    /**
     * Reimbursement Constructor
     * @param reimbursementId Reimbursement's id
     * @param description Reimbursement's description
     * @param amount Reimbursement's amount
     * @param status Reimbursement's status
     * @param userId Reimbursement's user id
     */
    public Reimbursement(int reimbursementId, String description, int amount, String status, int userId) {
        setReimbursementId(reimbursementId);
        setDescription(description);
        setAmount(amount);
        setStatus(status);
        setUserId(userId);
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
     * Returns the value of the description field
     *
     * @return description the current value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the value of the amount field
     *
     * @return amount the current value of amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Returns the value of the status field
     *
     * @return status the current value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns the value of the userId field
     *
     * @return userId the current value of userId
     */
    public int getUserId() {
        return user.getUserId();
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
     * Sets the userId
     *
     * @param userId the new value to set for userId
     */
    public void setUserId(int userId) {
        this.user.setUserId(userId);
    }

}
