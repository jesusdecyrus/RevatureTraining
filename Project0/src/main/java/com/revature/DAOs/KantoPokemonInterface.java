package com.revature.DAOs;

import com.revature.models.KantoPokemon;

/**
 * KantoPokemon Data Access Object Interface
 * @author Cyrus De Jesus
 */
public interface KantoPokemonInterface {

    /**
     * GET a random Kanto Pokemon
     * @return the random Kanto Pokemon
     */
    KantoPokemon getRandomKP();

}
