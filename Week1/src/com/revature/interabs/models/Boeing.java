package com.revature.interabs.models;

/**
 * A specific type of plane
 */
public class Boeing extends Plane {

    /**
     * Constructor
     * @param numWings number of wings
     * @param numTurbines number of turbines
     */
    public Boeing(int numWings, int numTurbines) {
        super(numWings, numTurbines);

    }
    @Override
    public void takeOff() {
        System.out.println("------------------------------");
        System.out.println("Increasing throttle...");
        System.out.println("Liftoff...");
        System.out.println("------------------------------");
    }

    @Override
    public void goForward() {
        System.out.println("------------------------------");
        System.out.println("Starting engines...");
        System.out.println("Flag has been initiated");
        System.out.println("Rolling liftoff...");
        System.out.println("------------------------------");
    }
}
