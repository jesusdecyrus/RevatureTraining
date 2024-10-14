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

//        // Simple GET
//        app.get("/", ctx -> ctx.result("Hello Javalin and Postman!"));

        // Get all pokemon
        PokemonController pc = new PokemonController();
        app.get("/pokemon", pc.getAllPokemon);

        // Listen on port 150
        app.start(150);
    }
}
