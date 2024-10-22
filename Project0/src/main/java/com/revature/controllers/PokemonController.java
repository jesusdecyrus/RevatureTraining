package com.revature.controllers;

import com.revature.DAOs.PokemonDAO;
import com.revature.models.Pokemon;
import io.javalin.http.Handler;

import java.util.ArrayList;

/**
 * Controller for Pokemon DAO where HTTP Requests are sent
 * @author Cyrus De Jesus
 */
public class PokemonController {

    /** Pokemon DAO */
    PokemonDAO pDAO = new PokemonDAO();

    /**
     * Handle GET requests for a Pokemon
     */
    public Handler getPokemonByID = ctx -> {
        // Extract path parameter from HTTP Request URL
        int id = Integer.parseInt(ctx.pathParam("pokemonID"));

        // Find it in the database
        Pokemon p = pDAO.getPokemonByID(id);

        // Response OK
        ctx.json(p);
        ctx.status(200);
    };

    /**
     * Handle GET requests for all pokemon matching trainer ID
     */
    public Handler getPostPokemonByTrainer = ctx -> {
        int trainerID = Integer.parseInt(ctx.pathParam("trainerID"));

        // Store all pokemon into an array list
        ArrayList<Pokemon> pokemonList = pDAO.getAllPokemon();

        // Traverse and store all trainer pokemon
        ArrayList<Pokemon> trainerPokemon = new ArrayList<>();
        for (Pokemon p : pokemonList) {
            if (p.getTrainerID() == trainerID) {
                trainerPokemon.add(p);
            }
        }

        // Convert array list to JSON
        ctx.json(trainerPokemon);

        // Response OK
        ctx.status(200);
    };

    /**
     * Handle GET requests for all Pokemon
     */
    public Handler getAllPokemon = ctx -> {
        // Store all pokemon into an array list
        ArrayList<Pokemon> pokemonList = pDAO.getAllPokemon();

        // Convert array list to JSON
        ctx.json(pokemonList);

        // Response OK
        ctx.status(200);
    };

    /**
     * Handle POST requests for a Pokemon
     */
    public Handler postPokemon = ctx -> {
        // Convert JSON to a Java class
        Pokemon p = ctx.bodyAsClass(Pokemon.class);

        // Error check before inserting
        if (p.checkValidity()) {
            Pokemon pokemon = pDAO.insertPokemon(p);
            ctx.status(201);
            ctx.json(pokemon);
        }
        else {
            ctx.result("Invalid Pokemon");
            ctx.status(400);
        }
    };

    /**
     * Handle PUT requests for a Pokemon
     */
    public Handler putPokemon = ctx -> {
        // Extract path parameter from HTTP Request URL
        int pokemonID = Integer.parseInt(ctx.pathParam("pokemonID"));
        int trainerID = Integer.parseInt(ctx.pathParam("trainerID"));

        if (pokemonID > 0 && trainerID > 0) {
            Pokemon pokemon = pDAO.updatePokemonTrainer(pokemonID, trainerID);
            ctx.status(200);
            ctx.json(pokemon);
        }
        else {
            ctx.result("Invalid Pokemon ID or Trainer ID");
            ctx.status(400);
        }
    };

    public Handler deletePokemon = ctx -> {
        // Extract the path parameter from HTTP Request URL
        int pokemonID = Integer.parseInt(ctx.pathParam("pokemonID"));

        if (pokemonID > 0) {
            if (pDAO.deletePokemon(pokemonID)) {
                ctx.status(200);
            }
            else {
                ctx.result("Invalid Pokemon ID");
                ctx.status(404);
            }
        }
        else {
            ctx.result("Invalid Pokemon ID");
            ctx.status(404);
        }
    };

}
