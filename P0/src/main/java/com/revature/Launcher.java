package com.revature;

import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Runs the program
 */
public class Launcher {

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Code clean up and prevent memory leak
        try (Connection connection = ConnectionUtil.getConnection()) {
            System.out.println("CONNECTION SUCCESSFUL :)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CONNECTION FAILED :(");
        }
    }
}
