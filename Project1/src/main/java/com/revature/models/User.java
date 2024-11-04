package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User Class
 * @author Cyrus De Jesus
 */
@Entity // Class Mapped to the DB (creates table)
@Table(name = "users") // Set Properties of Table
@Component // Register Class As Bean
public class User {

    /** User's id */
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK Auto-Increment
    private int userId;

    /** User's first name */
    private String firstName;

    /** User's last name */
    private String lastName;

    /** User's username */
    @Column(unique = true) // Set Column Constraints
    private String username;

    /** User's password */
    @Column(nullable = false) // Set Column Constraints
    private String password;

    /** User's role */
    @Column(nullable = false) // Set Column Constraints
    private String role = "Employee";

    /** List of Reimbursements */
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL) // (One-to-Many Relationship)
    @JsonIgnore // Prevents Circular Reference
    private List<Reimbursement> reimbursementList;

    /**
     * Default User Constructor
     */
    public User() {}

    /**
     * User Constructor
     * @param userId the User's id
     * @param firstName the User's first name
     * @param lastName the User's last name
     * @param username the User's username
     * @param password the User's password
     * @param role the User's role
     */
    public User(int userId, String firstName, String lastName, String username, String password, String role) {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    /**
     * Returns the value of the userId
     *
     * @return userId the current value of userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the value of the firstName 
     *
     * @return firstName the current value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the value of the lastName 
     *
     * @return lastName the current value of lastName
     */
    public String getLastName() {
        return lastName;
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
     * Returns the value of the role 
     *
     * @return role the current value of role
     */
    public String getRole() {
        return role;
    }

    /**
     * Returns the value of the password 
     *
     * @return password the current value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the userId
     *
     * @param userId the new value to set for userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Sets the firstName
     *
     * @param firstName the new value to set for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the lastName
     *
     * @param lastName the new value to set for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the password
     *
     * @param password the new value to set for password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * Sets the role
     *
     * @param role the new value to set for role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Returns whether the user is valid or not
     * @return true when valid; otherwise, false
     */
    public boolean validateUser() {
        return firstName != null && !firstName.isEmpty() &&
               lastName != null && !lastName.isEmpty() &&
               username != null && !username.isEmpty() &&
               password != null && !password.isEmpty();
    }

}
