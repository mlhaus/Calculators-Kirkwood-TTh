package edu.kirkwood.controller;

import edu.kirkwood.model.TemperatureEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaelsTemperatureCalculatorTest {

    private CaelsTemperatureCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new CaelsTemperatureCalculator();
    }

    @Test
    void testCalculateAddition() {
        TemperatureEntry entry1 = new TemperatureEntry(30.0f, "C");
        TemperatureEntry entry2 = new TemperatureEntry(20.0f, "C");

        //calculator.sign = "+"; // Simulate user input

       // float result = calculate(entry1, entry2);
       // assertEquals(50.0f, result);
    }

    @Test
    void testCalculateSubtraction() {
        TemperatureEntry entry1 = new TemperatureEntry(100.0f, "F");
        TemperatureEntry entry2 = new TemperatureEntry(32.0f, "F");

        // calculator.S2 = "-";

        //float result = calculate(entry1, entry2);
       // assertEquals(68.0f, result);
    }

//    @Test
//    void testSetScaleConversionToFahrenheit() {
//        TemperatureEntry entry1 = new TemperatureEntry(0.0f, "C");
//        TemperatureEntry entry2 = new TemperatureEntry(32.0f, "F");
//
//        // calculator.S2 = "+";
//
//
//
//        calculator.setScale(entry1, entry2);
//
//        assertEquals("F", entry1.getScale());
//        assertEquals(32.0f, entry1.getValue(), 0.01);
//    }

    @Test
    void testAbsoluteZeroViolationInCelsius() {
        TemperatureEntry entry1 = new TemperatureEntry(-274.0f, "C");
        TemperatureEntry entry2 = new TemperatureEntry(0.0f, "C");

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                calculator.absoluteZero(entry1, entry2)
        );
        assertEquals("You cannot put the temp below absolute zero", exception.getMessage());
    }

    @Test
    void testAbsoluteZeroValidInFahrenheit() {
        TemperatureEntry entry1 = new TemperatureEntry(-459.67f, "F");
        TemperatureEntry entry2 = new TemperatureEntry(0.0f, "F");

        assertDoesNotThrow(() -> calculator.absoluteZero(entry1, entry2));
    }
}