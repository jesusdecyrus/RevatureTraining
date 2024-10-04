package com.revature.collection.models;

public class Pokemon {

    /** Pokémon name */
    private String name;
    /** Pokémon level */
    private int level;
    /** Pokémon health */
    private int health;
    /** Pokémon attack */
    private int attack;

    /**
     * Empty Constructor
     */
    public Pokemon() {}

    /**
     * Constructor
     * @param name the name of the Pokémon
     * @param level the level of the Pokémon
     */
    public Pokemon(String name, int level) {
        this.name = name;
        this.level = level;
        this.health = 5 * level;
        this.attack = 2 * level;
    }

    /**
     * Returns the Pokémon  name
     * @return the Pokémon name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the Pokémon name
     * @param name the Pokémon name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the Pokémon level
     * @return the Pokémon level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets the Pokémon level
     * @param level the Pokémon level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Pokémon fights another Pokémon
     * @param opponent the Pokémon to fight
     */
    public void fight(Pokemon opponent) {
        System.out.println("------------------------------------------------");
        System.out.println("BATTLE BEGINS");
        System.out.println(this.name + ": " + this.health + " HP");
        System.out.println(opponent.name + ": " + opponent.health + " HP");
        System.out.println("------------------------------------------------");

        while (this.health > 0 && opponent.health > 0) {
            int damage1 = (int)(this.attack * Math.random());
            System.out.println(this.name + " uses a move and does " + damage1 + " damage.");
            opponent.health -= damage1;
            if (opponent.health > 0) {
                System.out.println(opponent.name + ": " + opponent.health + " HP");
            }
            else if (opponent.health <= 0) {
                opponent.health = 0;
                System.out.println(opponent.name + ": " + opponent.health + " HP");
            }

            if (opponent.health > 0) {
                int damage2 = (int)(opponent.attack * Math.random());
                System.out.println(opponent.name + " uses a move and does " + damage2 + " damage.");
                this.health -= damage2;
                if (this.health > 0) {
                    System.out.println(this.name + ": " + this.health + " HP");
                }
                else if (this.health <= 0) {
                    this.health = 0;
                    System.out.println(this.name + ": " + this.health + " HP");
                }
            }
        }

        System.out.println("------------------------------------------------");
        if (this.health <= 0) {
            this.health = 0;
            System.out.println(this.name + " is unable to battle.");
        }
        if (opponent.health <= 0) {
            opponent.health = 0;
            System.out.println(opponent.name + " is unable to battle.");
        }
        System.out.println(this.name + ": " + this.health + " HP");
        System.out.println(opponent.name + ": " + opponent.health + " HP");
        System.out.println("------------------------------------------------");
    }
}
