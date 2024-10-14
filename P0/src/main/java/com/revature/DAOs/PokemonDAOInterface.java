package com.revature.DAOs;

import com.revature.models.Pokemon;

import java.util.ArrayList;

/**
 * Pokemon Data Access Object Interface
 * @author Cyrus De Jesus
 */
public interface PokemonDAOInterface {

    /**
     * GET a Pokemon using the Pokemon's ID
     * @param pokemonID the Pokemon's ID
     * @return the Pokemon with the matching ID
     */
    Pokemon getPokemonByID(int pokemonID);

    /**
     * UPDATE a Pokemon's trainer using the Pokemon's and trainer's ID
     * @param pokemonID the Pokemon's ID
     * @param trainerID the trainer's ID
     * @return the updated Pokemon
     */
    Pokemon updatePokemonTrainer(int pokemonID, int trainerID);

    /**
     * INSERT a Pokemon to the table
     * @param pokemon the Pokemon to insert
     * @return the inserted Pokemon
     */
    Pokemon insertPokemon(Pokemon pokemon);

    /**
     * GET all Pokemon in the table
     * @return an array list of all Pokemon in the table
     */
    ArrayList<Pokemon> getAllPokemon();
}
