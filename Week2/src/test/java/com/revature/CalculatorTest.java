package com.revature;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Tests the Calculator class
 */
public class CalculatorTest {

    Calculator calculator;

    @Test
    public void testAdd(){
        calculator = new Calculator();
        assertEquals(15, calculator.add(10,5));
        assertEquals(17, calculator.add(10, 7));
        assertEquals(0, calculator.add(-5, 5));
    }

    @Test
    public void testSubtract() {
        calculator = new Calculator();
        assertEquals(15, calculator.subtract(25, 10));
        assertEquals(17, calculator.subtract(27, 10));
        assertEquals(0, calculator.subtract(-10, -10));
    }

    @Test
    public void testMultiply() {
        calculator = new Calculator();
        assertEquals(15, calculator.multiply(3, 5));
        assertEquals(17, calculator.multiply(17, 1));
        assertEquals(0, calculator.multiply(10, 0));
    }

    @Test
    public void testDivide() {
        calculator = new Calculator();
        assertEquals(15, calculator.divide(150, 10), 0);
        assertEquals(17, calculator.divide(17, 1), 0);

        Exception e1 = assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
        assertEquals("Cannot divide by zero.", e1.getMessage());
    }
}
