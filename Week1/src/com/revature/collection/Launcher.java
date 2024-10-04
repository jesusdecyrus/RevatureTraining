package com.revature.collection;

import com.revature.collection.models.Pokemon;

import java.util.ArrayList;

/**
 * Launches the program
 */
public class Launcher {

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Pokemon one = new Pokemon("Bulbasaur", 100);
        Pokemon two = new Pokemon("Gardevoir", 50);
        Pokemon three = new Pokemon("Goodra", 25);

        // ARRAY LIST
        ArrayList<Pokemon> myPokemons = new ArrayList<>();
        myPokemons.add(one);
        myPokemons.add(two);
        myPokemons.add(three);


        Pokemon enemy = new Pokemon("MewTwo", 100);
        one.fight(enemy);
    }

}
