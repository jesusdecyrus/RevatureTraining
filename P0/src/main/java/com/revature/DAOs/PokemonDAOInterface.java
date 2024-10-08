package com.revature.DAOs;

import com.revature.models.Pokemon;

/**
 * Pokemon Data Access Object Interface
 * @author Cyrus De Jesus
 */
public interface PokemonDAOInterface {

    /**
     * GETS a Pokemon using the Pokemon's ID
     * @param pokemonID the Pokemon's ID
     * @return the Pokemon with the matching ID
     */
    Pokemon getPokemonByID(int pokemonID);

    /**
     * UPDATES a Pokemon's trainer using the Pokemon's and trainer's ID
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
}
