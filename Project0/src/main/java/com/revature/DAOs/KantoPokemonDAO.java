package com.revature.DAOs;

import com.revature.models.KantoPokemon;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * KantoPokemon Data Access Object
 * @author Cyrus De Jesus
 */
public class KantoPokemonDAO implements KantoPokemonInterface {

    /**
     * GET a random Kanto Pokemon
     *
     * @return the random Kanto Pokemon
     */
    @Override
    public KantoPokemon getRandomKP() {
        // Chances of finding a legendary
        int legendary = (int)(Math.random() * 125000);

        // Determines which pokemon it is
        int pokedexNumber;
        if (legendary == 15) {
            pokedexNumber = (int)(Math.random() * 8) + 144;
        }
        else {
            pokedexNumber = (int)(Math.random() * 143) + 1;
        }

        try (Connection connection = ConnectionUtil.getConnection()) {
            // SQL Query
            String query = "SELECT * FROM kantoPokedex WHERE pokedexNumber = ?";

            // Prepared Statement
            PreparedStatement ps = connection.prepareStatement(query);

            // Set the parameters for the query with the question marks
            ps.setInt(1, pokedexNumber);

            // Execute get
            ResultSet rs = ps.executeQuery();

            // Extract the data from the result set
            if (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");

                // Return the Kanto Pokemon
                return new KantoPokemon(pokedexNumber, name, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error getting Pokemon");
        }

        return null;
    }
}
