package com.revature.scopes;

/**
 * Scope tester class
 */
public class ScopeTester {

    /** CLASS SCOPE (STATIC) - static variables are shared between objects */
    private static int sharedBetweenObjects = 10;
    public static int sharedBetweenObjects2;

    /** INSTANCE SCOPE (OBJECT) - variables that can only be shared to this object */
    public int specificToThisObject = 1;
}
