package com.revature.controllers;

import com.revature.DAOs.PokemonDAO;
import com.revature.models.Pokemon;
import io.javalin.http.Handler;

import java.util.ArrayList;

/**
 * Controller for Pokemon DAO where HTTP Requests are sent
 */
public class PokemonController {

    /** Pokemon DAO */
    PokemonDAO controller = new PokemonDAO();

    /**
     * Handle GET requests for all Pokemon
     */
    public Handler getAllPokemon = ctx -> {
        // Store all pokemon into an array list
        ArrayList<Pokemon> pokemonList = controller.getAllPokemon();

        // Convert array list to json
        ctx.json(pokemonList);

        // Response OK
        ctx.status(200);
    };

}
