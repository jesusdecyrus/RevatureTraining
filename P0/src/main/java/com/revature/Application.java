package com.revature;

import com.revature.controllers.KantoPokemonController;
import com.revature.controllers.PokemonController;
import com.revature.controllers.TrainerController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import java.util.Arrays;
import java.util.List;

/**
 * Runs the program
 * @author Cyrus De Jesus
 */
public class Application {

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Javalin setup syntax then create an application and configure static folder containing HTML
        Javalin app = Javalin.create(config -> config.staticFiles.add("/static", Location.CLASSPATH));

        // Check if a user is logged in
        app.before(ctx -> {
            // Protects paths that start with a given prefix to allow dynamic paths
            List<String> protectedPaths = Arrays.asList("/pokemon", "/randomPokemon");
            boolean requiresAuthentication = protectedPaths.stream().anyMatch(prefix -> ctx.path().startsWith(prefix));

            if (requiresAuthentication && TrainerController.session == null) {
               System.out.println("Session is null");
               throw new IllegalArgumentException("You must log in");
           }
        });
        // Exception handler for null session
        app.exception(IllegalArgumentException.class, (e, ctx) -> {
           ctx.status(401);
           ctx.result(e.getMessage());
        });

        // POKEMON CONTROLLER
        PokemonController pc = new PokemonController();
        app.get("/pokemon/{pokemonID}", pc.getPokemonByID); // GET a pokemon
        app.get("/pokemon", pc.getAllPokemon); // GET all pokemon
        app.get("/pokemon/trainer/{trainerID}", pc.getPostPokemonByTrainer); // GET all pokemon matching trainer ID
        app.post("/pokemon/add", pc.postPokemon); // POST a pokemon
        app.put("/pokemon/update/{pokemonID}/{trainerID}", pc.putPokemon); // PUT or UPDATE a pokemon
        app.delete("/pokemon/delete/{pokemonID}", pc.deletePokemon); // DELETE a pokemon

        // RANDOM POKEMON CONTROLLER
        KantoPokemonController kpc = new KantoPokemonController();
        app.get("/randomPokemon", kpc.getRandomPokemon); // GET a random pokemon

        // TRAINER CONTROLLER (LOGIN AND SIGN UP)
        TrainerController tc = new TrainerController();
        app.post("/trainer/login", tc.loginHandler); // GET a trainer
        app.post("/trainer/signup", tc.signUpHandler); // POST a trainer

        // Listen on port 150
        app.start(150);
    }
}
