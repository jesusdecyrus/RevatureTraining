package com.revature.DAOs;

import com.revature.models.Pokemon;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Pokemon Data Access Object
 * @author Cyrus De Jesus
 */
public class PokemonDAO implements PokemonDAOInterface {

    /**
     * GETS a Pokemon using the Pokemon's ID
     * @param pokemonID the Pokemon's ID
     * @return the Pokemon with the matching ID
     */
    @Override
    public Pokemon getPokemonByID(int pokemonID) {

        // Communicate with the database
        try (Connection connection = ConnectionUtil.getConnection()) {
            // SQL query
            String query = "SELECT * FROM pokemon WHERE pokemonID = ?";

            // Prepared statement
            PreparedStatement ps = connection.prepareStatement(query);

            // Set the parameters for the query with question marks
            ps.setInt(1, pokemonID);

            // Execute query
            ResultSet rs = ps.executeQuery();

            // Extract the data from result set
            if (rs.next()) {
                int id = rs.getInt("pokemonID");
                String name = rs.getString("name");
                int trainerID = rs.getInt("trainerID");
                String type = rs.getString("type");
                int level = rs.getInt("level");
                char gender = rs.getString("gender").charAt(0);
                boolean isShiny = rs.getBoolean("isShiny");

                // Return the Pokemon
                return new Pokemon(id, name, trainerID, type, level, gender, isShiny);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CONNECTION FAILED :(");
        }

        return null;
    }

    /**
     * UPDATES a Pokemon's trainer using the Pokemon's and trainer's ID
     * @param pokemonID the Pokemon's ID
     * @param trainerID the trainer's ID
     * @return the updated Pokemon
     */
    @Override
    public Pokemon updatePokemonTrainer(int pokemonID, int trainerID) {
        return null;
    }

    /**
     * INSERT a Pokemon to the table
     * INSERT a Pokemon to the table
     * @param pokemon the Pokemon to insert
     * @return the inserted Pokemon
     */
    @Override
    public Pokemon insertPokemon(Pokemon pokemon) {
        return null;
    }
}
