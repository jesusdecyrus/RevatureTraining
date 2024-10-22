package com.revature.controllers;

import com.revature.DAOs.KantoPokemonDAO;
import com.revature.models.KantoPokemon;
import com.revature.models.Pokemon;
import io.javalin.http.Handler;

/**
 * Controller for KantoPokemon DAO where HTTP Requests are sent
 * @author Cyrus De Jesus
 */
public class KantoPokemonController {

    /** KantoPokemon DAO */
    KantoPokemonDAO pDAO = new KantoPokemonDAO();

    /**
     * Handle GET requests for a KantoPokemon randomly chosen
     * Additionally, it will convert it to a Pokemon with random stats
     */
    public Handler getRandomPokemon = ctx -> {
        KantoPokemon kp = pDAO.getRandomKP();

        // Generate the random Pokemon
        int level = (int)(Math.random() * 100) + 1;
        char gender = ((int)(Math.random() * 2) == 0) ? 'M' : 'F';
        boolean shiny = ((int)(Math.random() * 8192) + 1) == 15;
        Pokemon p = new Pokemon(kp.getPokemonID(), kp.getName(), 0, kp.getType(), level, gender, shiny);

        // Status
        ctx.json(p);
        ctx.status(200);
    };
}
