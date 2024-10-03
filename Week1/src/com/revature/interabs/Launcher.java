package com.revature.interabs;

import com.revature.interabs.models.Boeing;

/**
 * Launches the program
 */
public class Launcher {

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {

        Boeing planeOne = new Boeing(2, 4);

        // Plane abstract class
        planeOne.communicateOverRadio();

        // Vehicle interface, then implemented by Plane, and then Boeing
        planeOne.goForward();

        // Plane abstract class
        planeOne.takeOff();

        // Use Plane variables
        System.out.println("------------------------------");
        System.out.println("Boeing has " + planeOne.numWings + " wings.");
        System.out.println("Boeing has " + planeOne.numTurbines + " turbines.");
        System.out.println("------------------------------");
    }
}
