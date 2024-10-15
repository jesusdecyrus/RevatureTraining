package com.revature;

import com.revature.controllers.PokemonController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

/**
 * Runs the program
 */
public class Application {

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Javalin setup syntax then create an application and configure static folder containing HTML
        Javalin app = Javalin.create(config -> config.staticFiles.add("/static", Location.CLASSPATH));

        // POKEMON CONTROLLER
        PokemonController pc = new PokemonController();
        app.get("/pokemon/{pokemonID}", pc.getPokemonByID); // GET a pokemon
        app.get("/pokemon", pc.getAllPokemon); // GET all pokemon
        app.post("/addPokemon", pc.postPokemon); // POST a pokemon
        app.put("/updatePokemonTrainer/{pokemonID}/{trainerID}", pc.putPokemon); // PUT or UPDATE a pokemon

        // TRAINER CONTROLLER


        // Listen on port 150
        app.start(150);
    }
}
