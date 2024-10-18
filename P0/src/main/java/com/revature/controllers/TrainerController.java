package com.revature.controllers;

import com.revature.DAOs.TrainerDao;
import com.revature.models.Trainer;
import jakarta.servlet.http.HttpSession;

import io.javalin.http.Handler;

/**
 * Controller for Trainer DAO where HTTP Requests are sent and handles authentication functionalities
 * @author Cyrus De Jesus
 */
public class TrainerController {

    /** Trainer DAO */
    TrainerDao tDAO = new TrainerDao();

    // HTTP Session
    public static HttpSession session;

    /**
     * Handle GET requests for a Trainer (LOGIN)
     */
    public Handler loginHandler = ctx -> {
        // Extract request body
        Trainer trainer = ctx.bodyAsClass(Trainer.class);

        // Create session
        HttpSession currentSession = ctx.req().getSession();

        // Retrieve the trainer
        Trainer loggedInTrainer = tDAO.login(trainer.getUsername(), trainer.getPassword());

        if (loggedInTrainer != null) {
            // Create a session object
            session = ctx.req().getSession();

            // Store user info into the session
            session.setAttribute("firstName", loggedInTrainer.getFirstName());
            session.setAttribute("lastName", loggedInTrainer.getLastName());
            session.setAttribute("username", loggedInTrainer.getUsername());

            // Success Message
            ctx.status(200);
            ctx.result("Login Successful! Welcome, " + session.getAttribute("firstName") + " " + session.getAttribute("lastName") + "!");
        }
        else {
            // Login fails
            ctx.status(401);
            ctx.result("Login failed! Try again.");
        }
    };

    /**
     * Handle POST requests for a Trainer (SIGN UP)
     */
    public Handler signUpHandler = ctx -> {
        // Convert JSON to Java
        Trainer t = ctx.bodyAsClass(Trainer.class);

        // Error check before inserting
        if (t.checkValidity()) {
            Trainer trainer = tDAO.signup(t.getFirstName(), t.getLastName(), t.getUsername(), t.getPassword());

            // Ensure that the trainer be inserted into the table
            if (trainer != null) {
                ctx.status(201);
                ctx.json(trainer);
            }
            else {
                ctx.result("Trainer Username Already Taken");
                ctx.status(400);
            }
        }
        else {
            ctx.result("Invalid Trainer");
            ctx.status(400);
        }
    };

}
