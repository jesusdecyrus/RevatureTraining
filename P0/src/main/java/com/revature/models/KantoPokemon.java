package com.revature.models;

/**
 * KantoPokemon Class
 * @author Cyrus De Jesus
 */
public class KantoPokemon extends Pokemon {

    /**
     * Default Constructor
     */
    public KantoPokemon(){}

    /**
     * KantoPokemon Constructor
     * @param pokedexNumber the pokedex number of the Kanto Pokemon
     * @param name the name of the Kanto Pokemon
     * @param type the type of the Kanto Pokemon
     */
    public KantoPokemon(int pokedexNumber, String name, String type) {
        setPokemonID(pokedexNumber);
        setName(name);
        setType(type);
    }
}
