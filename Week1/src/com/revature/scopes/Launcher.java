package com.revature.scopes;

/**
 * Launches the program
 */
public class Launcher {

    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // CLASS SCOPE
        System.out.println();
        System.out.println("CLASS SCOPE:");
        System.out.println(ScopeTester.sharedBetweenObjects1);
        System.out.println(ScopeTester.sharedBetweenObjects2);
        staticMethod();

        // INSTANCE SCOPE
        System.out.println();
        System.out.println("INSTANCE SCOPE:");
        ScopeTester st = new ScopeTester();
        System.out.println(st.specificToThisObject);

        // BLOCK SCOPE
        System.out.println();
        System.out.println("BLOCK SCOPE:");
        boolean canRun = true;
        if (canRun) {
            System.out.println("I can run in the block scope.");
        }
        else {
            System.out.println("I cannot run in the block scope.");
        }

        // METHOD SCOPE
        System.out.println();
        System.out.println("METHOD SCOPE:");
        methodScope();
    }

    public static void staticMethod() {
        System.out.println("Static makes me run in the main method.");
    }

    public static void methodScope() {
        int garbageInt = 20;
        System.out.println(garbageInt);
    }
}
