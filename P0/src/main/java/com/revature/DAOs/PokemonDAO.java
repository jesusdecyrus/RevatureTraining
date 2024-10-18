package com.revature.DAOs;

import com.revature.models.Pokemon;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Pokemon Data Access Object
 * @author Cyrus De Jesus
 */
public class PokemonDAO implements PokemonDAOInterface {

    /**
     * GET a Pokemon using the Pokemon's ID
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

            // Execute get
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
            System.out.println("Error getting Pokemon");
        }

        return null;
    }


    /**
     * GET all Pokemon in the table
     *
     * @return an array list of all Pokemon in the table
     */
    @Override
    public ArrayList<Pokemon> getAllPokemon() {
        try (Connection connection = ConnectionUtil.getConnection()) {
            // SQL query
            String query = "SELECT * FROM pokemon";
            // Statement since there is no parameters
            Statement s = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement(query);

            // Loop through ResultSet
            ArrayList<Pokemon> allPokemon = new ArrayList<>();
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("pokemonID");
                String name = rs.getString("name");
                int trainerID = rs.getInt("trainerID");
                String type = rs.getString("type");
                int level = rs.getInt("level");
                char gender = rs.getString("gender").charAt(0);
                boolean isShiny = rs.getBoolean("isShiny");

                // Create the Pokemon and add to the list
                Pokemon p =  new Pokemon(id, name, trainerID, type, level, gender, isShiny);
                allPokemon.add(p);
            }

            return allPokemon;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getting all Pokemon");
        }

        return null;
    }

    /**
     * INSERT a Pokemon to the table
     * @param pokemon the Pokemon to insert
     * @return the inserted Pokemon
     */
    @Override
    public Pokemon insertPokemon(Pokemon pokemon) {
        // Connect with the database
        try (Connection connection = ConnectionUtil.getConnection()) {
            // SQL query
            String query = "INSERT INTO pokemon (name, trainerID, type, level, gender, isShiny) " +
                           "VALUES (?, ?, ?, ?, ?, ?)";
            // Prepared statement
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pokemon.getName());
            if (pokemon.getTrainerID() <= 0) {
                ps.setNull(2, java.sql.Types.INTEGER);
            } else {
                ps.setInt(2, pokemon.getTrainerID());
            }
            ps.setString(3, pokemon.getType());
            ps.setInt(4, pokemon.getLevel());
            ps.setString(5, String.valueOf(pokemon.getGender()));
            ps.setBoolean(6, pokemon.isShiny());
            // Execute insert and retrieve keys
            int rows = ps.executeUpdate();

            // Return pokemon
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (rows > 0 && generatedKeys.next()) {
                // Retrieve the generated pokemonID
                int serialID = generatedKeys.getInt(1);
                pokemon.setPokemonID(serialID);
            }
            return pokemon;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error inserting Pokemon");
        }

        return null;
    }

    /**
     * UPDATE a Pokemon's trainer using the Pokemon's and trainer's ID
     * @param pokemonID the Pokemon's ID
     * @param trainerID the trainer's ID
     * @return the updated Pokemon
     */
    @Override
    public Pokemon updatePokemonTrainer(int pokemonID, int trainerID) {
        // Communicate with the database
        try (Connection connection = ConnectionUtil.getConnection()) {
            // SQL query
            String query = "UPDATE pokemon SET trainerID = ? WHERE pokemonID = ?";

            // Prepared statement
            PreparedStatement ps = connection.prepareStatement(query);

            // Set the parameters for the query with question marks
            ps.setInt(1, trainerID);
            ps.setInt(2, pokemonID);

            // Execute update
            ps.executeUpdate();

            // Return the updated Pokemon
            return getPokemonByID(pokemonID);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating Pokemon");
        }

        return null;
    }

}
