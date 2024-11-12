package com.revature.models.dtos;

/**
 * UserLogin Data Transfer Object
 * @author Cyrus De Jesus
 */
public class UserLoginDTO {

    /** User's username */
    private String username;

    /** User's password */
    private String password;

    /**
     * Default Constructor
     */
    public UserLoginDTO() {}

    /**
     * UserLoginDTO Constructor
     * @param username the User's username
     * @param password the User's password
     */
    public UserLoginDTO(String username, String password) {
        setUsername(username);
        setPassword(password);
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
     * Returns the value of the password
     *
     * @return password the current value of password
     */
    public String getPassword() {
        return password;
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
     * Sets the password
     *
     * @param password the new value to set for password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
