package com.revature.models.dtos;

/**
 * User Data Transfer Object
 * @author Cyrus De Jesus
 */
public class UserDTO {

    /** User's first name */
    private String firstName;

    /** User's last name */
    private String lastName;

    /** User's username */
    private String username;

    /** User's role */
    private String role;

    /**
     * Default Constructor
     */
    public UserDTO() {}

    /**
     * UserDTO Constructor
     * @param firstName the User's first name
     * @param lastName the User's last name
     * @param username the User's username
     * @param role the User's role
     */
    public UserDTO(String firstName, String lastName, String username, String role) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setRole(role);
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
     * Returns the value of the username
     *
     * @return username the current value of username
     */
    public String getUsername() {
        return username;
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
     * Returns the value of the firstName
     *
     * @return firstName the current value of firstName
     */
    public String getFirstName() {
        return firstName;
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

}
