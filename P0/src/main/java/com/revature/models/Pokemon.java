package com.revature.models;

/**
 * Pokemon Class
 * @author Cyrus De Jesus
 */
public class Pokemon {

    /** Pokemon ID */
    private int pokemonID;
    /** Pokemon name */
    private String name;
    /** Pokemon's trainer ID */
    private int trainerID;
    /** Pokemon's type */
    private String type;
    /** Pokemon's level */
    private int level;
    /** Pokemon's gender */
    private int gender;
    /** Pokemon's shiny check */
    private boolean isShiny;

    /**
     * Pokemon Constructor
     * @param pokemonID the Pokemon's ID
     * @param name the Pokemon's name
     * @param trainerID the Pokemon's trainer ID
     * @param type the Pokemon's type
     * @param level the Pokemon's level
     * @param gender the Pokemon's gender
     * @param isShiny the Pokemon's shiny check
     */
    public Pokemon(int pokemonID, String name, int trainerID, String type, int level, int gender, boolean isShiny) {
        setPokemonID(pokemonID);
        setName(name);
        setTrainerID(trainerID);
        setType(type);
        setLevel(level);
        setGender(gender);
        setShiny(isShiny);
    }

    /**
     * Returns the Pokemon's ID
     * @return the Pokemon's ID
     */
    public int getPokemonID() {
        return pokemonID;
    }

    /**
     * Returns the Pokemon's name
     * @return the Pokemon's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Pokemon's trainer ID
     * @return the Pokemon's trainer ID
     */
    public int getTrainerID() {
        return trainerID;
    }

    /**
     * Returns the Pokemon's type
     * @return the Pokemon's type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the Pokemon's level
     * @return the Pokemon's level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the Pokemon's gender
     * @return the Pokemon's gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * Returns the Pokemon's shiny check
     * @return the Pokemon's shiny check
     */
    public boolean isShiny() {
        return isShiny;
    }

    /**
     * Sets the Pokemon's ID
     * @param pokemonID the Pokemon's ID
     */
    public void setPokemonID(int pokemonID) {
        this.pokemonID = pokemonID;
    }

    /**
     * Sets the Pokemon's name
     * @param name the Pokemon's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Pokemon's trainer ID
     * @param trainerID the Pokemon's trainer ID
     */
    public void setTrainerID(int trainerID) {
        this.trainerID = trainerID;
    }

    /**
     * Sets the Pokemon's type
     * @param type the Pokemon's type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the Pokemon's level
     * @param level the Pokemon's level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the Pokemon's gender
     * @param gender the Pokemon's gender
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * Sets the Pokemon's shiny check
     * @param shiny the Pokemon's shiny check
     */
    public void setShiny(boolean shiny) {
        isShiny = shiny;
    }
}