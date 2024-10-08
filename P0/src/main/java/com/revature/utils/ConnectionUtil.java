package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages and establishes our database connection
 */
public class ConnectionUtil {

    /**
     * Returns a Connection object which is used to interact with our database
     * @return a Connection object
     * @throws SQLException when problem occurs during connection to database
     */
    public static Connection getConnection() throws SQLException {

        // Register our PostgreSQL driver
        try {
            // Search for the postgres driver
            Class.forName("org.postgresql.Driver"); //
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("problem occurred locating driver");
        }

        // Hardcoded credentials
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=project0";
        String username = "postgres";
        String password = "password";

        // Returns the Connection object using credentials
        return DriverManager.getConnection(url, username, password);

        //These variables holding my DB credentials are hidden in my Environment Variables
        //Run -> Edit Configurations -> Application -> Then create key/value pairs for these credentials
        //Right click Launcher and run it if you don't see these options (creates an application config)

        //  String url = System.getenv("URL");
        //  String username = System.getenv("USERNAME");
        //  String password = System.getenv("PASSWORD");
        //
        //  return DriverManager.getConnection(url, username, password);
    }

}