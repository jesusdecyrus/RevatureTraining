package com.revature.models;

/**
 * Trainer Class
 * @author Cyrus De Jesus
 */
public class Trainer {

    /** The trainer's first name */
    private String firstName;
    /** The trainer's last name */
    private String lastName;
    /** The trainer's unique username */
    private String username;
    /** The trainer's password */
    private String password;

    /**
     * Default Constructor
     */
    public Trainer() {}

    /**
     * Trainer Constructor
     * @param firstName the trainer's first name
     * @param lastName the trainer's last name
     * @param username the trainer's username
     * @param password the trainer's password
     */
    public Trainer(String firstName, String lastName, String username, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
    }

    /**
     * Returns the trainer's first name
     * @return the trainer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Returns the trainer's last name
     * @return the trainer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Returns the trainer's username
     * @return the trainer's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the trainer's password
     * @return the trainer's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the trainer's first name
     * @param firstName the trainer's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets the trainer's last name
     * @param lastName the trainer's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets the trainer's username
     * @param username the trainer's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns whether the trainer is a valid trainer
     * @return true when the trainer is valid; otherwise, false
     */
    public boolean checkValidity() {
        return firstName != null && !firstName.isEmpty() && lastName != null && !lastName.isEmpty() &&
               username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }

    /**
     * Sets the trainer's password
     * @param password the trainer's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
