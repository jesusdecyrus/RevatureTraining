package com.revature.interabs.models;

/**
 * Plane abstract class that implements Vehicle interface
 */
public abstract class Plane implements Vehicle {

    /** Number of wings */
    public int numWings;

    /** Number of turbines */
    public int numTurbines;

    /**
     * Constructor
     */
    public Plane(int numWings, int numTurbines) {
        this.numWings = numWings;
        this.numTurbines = numTurbines;
    }

    /**
     * Plane take off differently (abstract method)
     */
    public abstract void takeOff();

    /**
     * All planes communicate the same (concrete method)
     */
    public void communicateOverRadio() {
        System.out.println("------------------------------");
        System.out.println("Welcome aboard everyone : )");
        System.out.println("------------------------------");
    }

}
