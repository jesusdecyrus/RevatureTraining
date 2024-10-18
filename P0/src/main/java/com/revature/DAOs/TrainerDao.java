package com.revature.DAOs;

import com.revature.models.Trainer;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Trainer Data Access Object
 * @author Cyrus De Jesus
 */
public class TrainerDao implements TrainerDAOInterface {

    /**
     * GET a Trainer using the trainer's username and password (LOGIN)
     *
     * @param username the trainer's username
     * @param password the trainer's password
     * @return the matching trainer
     */
    @Override
    public Trainer login(String username, String password) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // Prepare the query
            String query = "SELECT * FROM trainers WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(query);

            // Set the parameters for the query
            ps.setString(1, username);
            ps.setString(2, password);

            // Execute get
            ResultSet rs = ps.executeQuery();

            // Extract the data from the result set
            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");

                // Return the trainer
                return new Trainer(firstName, lastName, username, password);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getting trainer");
        }

        return null;
    }

}
