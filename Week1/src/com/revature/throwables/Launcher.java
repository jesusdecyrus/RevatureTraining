package com.revature.throwables;

/**
 * Launch the program
 */
public class Launcher {

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Division by zero is not allowed.");
        }
    }
}
